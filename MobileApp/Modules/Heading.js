/**
 * Created by andrew on 8/22/17.
 */

import React, { Component } from 'react';
import { StyleSheet, Text, View, ScrollView } from 'react-native';
var color = require("../Library/color.js");

export default class Heading extends React.Component {
    constructor(props) {
        super(props);
        let moment = require('moment');
        this.state = {
            date:moment().format('MMM Do'),
            datestyle:{color:"#56BAFC"},
            tag:"science",
            tagstyle:{color:color.colorDict["science"]},
            article:"test",
            articlestyle:{color:color.colorDict["science"]},
        };
    }

    render() {
        if(this.state.article){
            return (
                <View style={styles.headerContainer}>
                    <Text style={[styles.content, this.state.datestyle]}>{this.state.date}</Text>
                    <Text style={styles.content}> > </Text>
                    <Text style={[styles.content, this.state.tagstyle]}>{this.state.tag}</Text>
                    <Text style={styles.content}> > </Text>
                    <Text style={[styles.content, this.state.articlestyle]}>article</Text>
                </View>
            );
        }
        if(this.state.tag){
            return (
                <View style={styles.headerContainer}>
                    <Text style={[styles.content, this.state.datestyle]}>{this.state.date}</Text>
                    <Text style={styles.content}>></Text>
                    <Text style={[styles.content, this.state.tagstyle]}>{this.state.tag}</Text>
                </View>
            );
        }
        return (
            <View style={styles.headerContainer}>
                <Text style={[styles.content, this.state.datestyle]}>{this.state.date}</Text>
            </View>
        );
    }
}

const styles = StyleSheet.create({
    headerContainer: {
        marginTop:30,
        height:50,
        paddingBottom:10,
        width:"100%",
        flexDirection: 'row',
        alignItems: 'center',
        justifyContent: 'center',
        borderBottomColor: 'gray',
        borderBottomWidth: 1,
    },
    content:{
        fontSize:22
    }
});
