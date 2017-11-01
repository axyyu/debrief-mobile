import React from 'react';
import { StyleSheet, Text, View } from 'react-native';

var s = require("./colors");

export default class Header extends React.Component {
    constructor(props){
        super(props);
    }
    render() {
        tag = null;
        if(this.props.tag != null){
            tag = <Text style={[styles.tag, s[this.props.tag]]}>{this.props.tag}</Text>
        }
        return (
        <View style={styles.header}>
            <Text style={styles.text}>{this.props.day}</Text>
            {tag}
        </View>
        );
    }
}

const styles = StyleSheet.create({
    header: {
        paddingVertical:20,
    },
    text:{
        minHeight:0,
        textAlign:"center",
        fontSize: 25
    },
    tag:{
        minHeight:0,
        textAlign:"center",
        fontSize: 20
    },
    title:{
        minHeight:0,
        textAlign:"center",
        fontSize: 15
    }
});
