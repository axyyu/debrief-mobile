import React from 'react';
import { StyleSheet, Text, View } from 'react-native';

var moment = require('moment');
var s = require("./colors");

export default class Header extends React.Component {
    constructor(props){
        super(props);

        this.timeFormat = "dddd, MMMM D";
        this.date = this.time = moment().subtract(props.offset, 'days').format(this.timeFormat);
    }
    componentWillReceiveProps(nextProps) {
        this.date = this.time = moment().subtract(nextProps.offset, 'days').format(this.timeFormat);
    }
    render() {
        tag = null;
        if(this.props.tag != null){
            tag = <Text style={[styles.tag, s[this.props.tag+"Text"]]}>{this.props.tag}</Text>
        }
        return (
        <View style={styles.header}>
            <Text style={styles.text}>{this.date}</Text>
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
