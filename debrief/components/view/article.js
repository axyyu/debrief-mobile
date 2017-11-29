import React from 'react';
import { StyleSheet, ScrollView, FlatList, View, Text, Linking, TouchableOpacity } from 'react-native';

import * as firebase from "firebase";
var moment = require('moment');
import Header from '../header';

var s = require("../colors");

export default class Article extends React.Component {
    constructor(props){
        super(props);
        const { params } = this.props.navigation.state;

        this.dateFormat = "M-D";
        this.moment = moment();
        this.date = moment().subtract(params.offset, 'days').format(this.dateFormat);

        this.state = {
            article:params.article,
            offset:params.offset,
            tag:params.tag,
            output:[],
        };
    }
    componentDidMount(){
        firebase.database().ref('/debriefings/'+this.date+"/"+this.state.tag+"/"+this.state.article).once('value').then( (snapshot) => {
            var obj = snapshot.val();
            var output = {key:obj.title, longsum:obj.longsum,link:obj.url};
            this.setState({
                output:output
            })
        });
    }
    back(){
        this.props.navigation.navigate('Tag', { offset: this.state.offset, tag:this.state.tag });
    }
    openArticle(){
        Linking.openURL(this.state.output.link).catch(err => console.error('An error occurred', err));
    }
    render() {
        return (
            <View style={styles.wrapper}>
                <Header offset={this.state.offset} tag={this.state.tag}></Header>
                <ScrollView style={styles.page}>
                    <Text style={styles.title}>{this.state.output.key}</Text>
                    <Text style={styles.article}>{this.state.output.longsum}</Text>
                </ScrollView>
            </View>
        );
    }
}

const styles = StyleSheet.create({
    wrapper:{
        flex: 1,
        paddingHorizontal: 20,
        paddingTop: 50,
        paddingBottom:20,
        backgroundColor: "#FFFFFF"
    },
    page: {
        flex: 1,
        // justifyContent: 'center',
    },
    pagescroll: {
        flex: 1,
        // justifyContent: 'center',
    },
    title:{
      flex:1,
      fontSize:20,
      fontWeight:"bold"
    //   marginBottom:10
    },
    article:{
      flex:1,
      fontSize:15,
      marginVertical:10,
    },
    buttons:{
        marginTop: 20,
        flexDirection: "row",
    },
    button:{
        flex:1,
        padding:10,
        marginHorizontal: 10,
        marginVertical: 10,
    },
    buttonText:{
      fontSize: 20,
      textAlign:'center'
    }
});

/*
<View style={styles.buttons}>
                    <TouchableOpacity onPress={this.back.bind(this)} style={ [styles.button,s[this.state.tag+"Button"]] } >
                        <Text style={styles.buttonText}>Back</Text>
                    </TouchableOpacity>
                    <TouchableOpacity onPress={this.openArticle.bind(this)} style={ [styles.button,s[this.state.tag+"Button"]] } >
                        <Text style={styles.buttonText}>Link</Text>
                    </TouchableOpacity>
                </View>
                */