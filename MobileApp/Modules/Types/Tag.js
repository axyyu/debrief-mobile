/**
 * Created by andrew on 8/22/17.
 */

import React, { Component } from 'react';
import { StyleSheet, Text, View, FlatList } from 'react-native';
var color = require("../../Library/color.js");

export default class Tag extends Component {
    constructor(props) {
        super(props);
        let c = color.colorDict[props.payload.tag];
        this.state = {color:c,
            background: color.hexToRGBA(c)};
    }
    render() {
        return (
            <View style={[styles.tag,{backgroundColor:this.state.background}]}>
                <Text style={[styles.title,{color:this.state.color}]}>{this.props.payload.key}</Text>
                <Text style={[styles.summary,{color:this.state.color}]}>{this.props.payload.summary}</Text>
            </View>
        );
    }
}

const styles = StyleSheet.create({
    tag:{
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
    summary:{
        fontSize:16,
        paddingVertical: 5,
    }
});