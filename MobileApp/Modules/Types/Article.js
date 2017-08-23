/**
 * Created by andrew on 8/22/17.
 */

import React, { Component } from 'react';
import { StyleSheet, Text, View, FlatList } from 'react-native';
var color = require("../../Library/color.js");

export default class Article extends Component {
    constructor(props) {
        super(props);
        let c = color.colorDict[props.payload.tag];
        this.state = {color:c,
            background: color.hexToRGBA(c)};
    }
    render() {
        return (
            <View style={[styles.article,{backgroundColor:this.state.background}]}>
                <Text style={[styles.title,{color:this.state.color}]}>{this.props.payload.key}</Text>
                <Text style={[styles.source,{color:this.state.color}]}>From: {this.props.payload.source}</Text>
                <Text style={[styles.content,{color:this.state.color}]}>{this.props.payload.content}</Text>
            </View>
        );
    }
}

const styles = StyleSheet.create({
    article:{
        flex:1,
        padding:10,
        paddingLeft:20,
        marginVertical: 10,
        marginHorizontal:10,
    },
    title:{
        fontSize:20,
        fontWeight:"bold",
        paddingTop: 5,
        paddingBottom:10,
    },
    source:{
        fontSize:20,
        paddingTop: 5,
        paddingBottom:10,
    },
    content:{
        fontSize:16,
        paddingVertical: 5,
    }
});