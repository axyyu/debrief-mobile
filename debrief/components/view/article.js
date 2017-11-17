import React from 'react';
import { StyleSheet, ScrollView, FlatList, View, Text, Linking, TouchableOpacity } from 'react-native';

import * as firebase from "firebase";
var moment = require('moment');

var s = require("../colors");

export default class Article extends React.Component {
    constructor(props){
        super(props);

        this.dateFormat = "M-D";
        this.moment = moment();
        this.date = moment().subtract(props.offset, 'days').format(this.dateFormat);

        console.log(this.props);

        this.state = {output:{}};
    }
    componentDidMount(){
        firebase.database().ref('/debriefings/'+this.date+"/"+this.props.tag+"/"+this.props.article).once('value').then( (snapshot) => {
            var obj = snapshot.val();
            var output = {key:obj.title, longsum:obj.longsum,link:obj.url};
            this.setState({
                output:output
            })
        });
    }
    openArticle(){
      Linking.openURL(this.state.output.link).catch(err => console.error('An error occurred', err));
    }
    render() {
        return (
            <View style={styles.page}>
                <ScrollView style={styles.page}>
                    <Text style={styles.title}>{this.state.output.key}</Text>
                    <Text style={styles.article}>{this.state.output.longsum}</Text>
                </ScrollView>
                <TouchableOpacity onPress={this.openArticle.bind(this)} style={ [styles.button,s[this.props.tag+"Button"]] } >
                    <Text style={styles.buttonText}>Open Article</Text>
                </TouchableOpacity>
                <TouchableOpacity onPress={this.props.tagView.bind(this)} style={ [styles.button,s[this.props.tag+"Button"]] } >
                    <Text style={styles.buttonText}>Back</Text>
                </TouchableOpacity>
            </View>
        );
    }
}

const styles = StyleSheet.create({
    page: {
        flex: 1,
        // justifyContent: 'center',
    },
    pagescroll: {
        flex: 1,
        marginBottom: 20,
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
      padding:10,
    },
    buttonText:{
      fontSize: 20,
      textAlign:'center'
    }
});