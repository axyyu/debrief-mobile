/**
 * Created by andrew on 8/22/17.
 */

import React, { Component } from 'react';
import { StyleSheet, Text, View, FlatList } from 'react-native';
var color = require("../../Library/color.js");

export default class Day extends Component {
    constructor(props) {
        super(props);
        let c = color.colorDict[props.payload.key];
        this.state = {color:c,
            background: color.hexToRGBA(c)};
    }
    render() {
        return (
            <View style={[styles.day,{backgroundColor:this.state.background}]}>
                <Text style={[styles.tag,{color:this.state.color}]}>{this.props.payload.key}</Text>
                <FlatList
                    data={this.props.payload.article}
                    renderItem={({item}) => <Text style={[styles.article,{color:this.state.color}]}>{item.key}</Text> }
                />
            </View>
        );
    }
}

const styles = StyleSheet.create({
    day:{
        padding:10,
        paddingLeft:20,
        marginVertical: 10,
        marginHorizontal:10,
    },
    tag:{
        fontSize:22,
        fontWeight:"bold",
        paddingTop: 5,
        paddingBottom:10,
    },
    article:{
        fontSize:18,
        paddingVertical: 5,
        paddingLeft:10,
    }
});