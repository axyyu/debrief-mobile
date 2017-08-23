/**
 * Created by andrew on 8/22/17.
 */

import React, { Component } from 'react';
import { StyleSheet, Text, View, ScrollView } from 'react-native';

export default class Heading extends React.Component {
    constructor(props) {
        super(props);
        let moment = require('moment');
        this.state = {date:moment().format('dddd, MMM Do') };
    }
    render() {
        return (
            <View style={styles.headerContainer}>
                <Text style={styles.date}>{this.state.date}</Text>
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
    date:{
        color:"#56BAFC",
        fontSize:20
    }
});
