import React from 'react';
import { StyleSheet, ScrollView, FlatList, View, Text, TouchableOpacity } from 'react-native';
import { StackNavigator } from 'react-navigation';

import TagEntry from "../list/tagEntry";
import Header from '../header';
import * as firebase from "firebase";

var moment = require('moment');
var s = require("../colors");

export default class Day extends React.Component {
    constructor(props){
        super(props);
        const { params } = this.props.navigation.state;

        this.dateFormat = "Y-M-D";
        this.moment = moment();
        this.date = moment().subtract(params.offset, 'days').format(this.dateFormat);

        this.state = {
            offset:params.offset,
            tag:params.tag,
            tagContent:[],
        };
    }
    componentDidMount(){
        firebase.database().ref('/debriefings/'+this.date+"/"+this.state.tag).once('value').then( (snapshot) => {
            var obj = snapshot.val();
            var output = [];
            for(k in obj){
                output.push( {key:obj[k].title, fbind:k, shortsum:obj[k].shortsum, tag:this.state.tag} );
            }
            this.setState({
                tagContent:output
            })
        });
    }
    back(){
        this.props.navigation.navigate('Home', {current:this.state.offset});
    }
    openTag(keyValue){
        this.props.navigation.navigate('Article', {offset:this.state.offset, tag:this.state.tag, article:keyValue});
    }
    render() {
        return (
            <View style={styles.wrapper}>
                <Header offset={this.state.offset} tag={this.state.tag}></Header>
                <FlatList
                    data = {this.state.tagContent}
                    renderItem={({item}) => <TagEntry info={item} openTag={this.openTag.bind(this)}></TagEntry>}
                />
            </View>
        );
    }
}

const styles = StyleSheet.create({
    wrapper: {
        flex: 1,
        paddingTop: 50,
        paddingBottom:20,
        backgroundColor: "#FFFFFF"
    },
    button:{
        marginVertical: 10,
        marginHorizontal:20,
        marginTop:20,
        padding:10,
      },
      buttonText:{
        fontSize: 20,
        textAlign:'center'
      }
});

/*
<TouchableOpacity onPress={this.back.bind(this)} style={ [styles.button,s[this.state.tag+"Button"]] } >
                    <Text style={styles.buttonText}>Back</Text>
                </TouchableOpacity>
                */