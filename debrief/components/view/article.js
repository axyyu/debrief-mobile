import React from 'react';
import { StyleSheet, ScrollView, FlatList, View, Text, Linking, TouchableOpacity } from 'react-native';

import * as firebase from "firebase";

var s = require("../colors");

export default class Article extends React.Component {
    constructor(props){
        super(props);
        this.state = {output:{}};
    }
    componentDidMount(){
        firebase.database().ref('/debriefings/'+"9-23"+"/"+this.props.tag+"/"+this.props.title).once('value').then( (snapshot) => {
            var obj = snapshot.val();
            var output = {key:obj.title, longsum:obj.longsum,link:obj.url};
            this.setState({
                output
            })
        });
    }
    openArticle(){
      Linking.openURL(this.state.output.link).catch(err => console.error('An error occurred', err));
    }
    render() {
        return (
            <ScrollView style={styles.page}>
              <Text style={styles.title}>{this.state.output.key}</Text>
              <Text style={styles.article}>{this.state.output.longsum}</Text>
              <TouchableOpacity onPress={this.openArticle.bind(this)} style={ [styles.button,s[this.props.tag+"Button"]] } >
                <Text style={styles.buttonText}>Open Article</Text>
              </TouchableOpacity>
            </ScrollView>
        );
    }
}

const styles = StyleSheet.create({
    page: {
        flex: 1,
        // justifyContent: 'center',
    },
    title:{
      flex:1,
      fontSize:20,
      marginBottom:10
    },
    article:{
      flex:1,
      fontSize:15,
    },
    button:{
      marginVertical: 10,
      marginTop:20,
      padding:10,
    },
    buttonText:{
      fontSize: 20,
      textAlign:'center'
    }
});
