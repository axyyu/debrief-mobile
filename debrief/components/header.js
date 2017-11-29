import React from 'react';
import { StyleSheet, Text, View } from 'react-native';

var moment = require('moment');
var s = require("./colors");

export default class Header extends React.Component {
    constructor(props){
        super(props);

        if(props.offset != null){
            this.timeFormat = "dddd, MMMM D";
            this.date = this.time = moment().subtract(props.offset, 'days').format(this.timeFormat);
        }
        else{
            this.date = "Settings";
        }
    }
    componentWillReceiveProps(nextProps) {
        if(nextProps.offset != null){
            this.date = this.time = moment().subtract(nextProps.offset, 'days').format(this.timeFormat);
        }
        else{
            this.date = "Settings";
        }
    }
    render() {
        tag = null;
        if(this.props.tag != null){
            tag = <Text style={[styles.tag, s[this.props.tag+"Text"]]}>{this.props.tag.toUpperCase()}</Text>
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
        marginTop:5,
        minHeight:0,
        textAlign:"center",
        fontSize: 14,
        fontWeight:"bold"
    },
    title:{
        minHeight:0,
        textAlign:"center",
        fontSize: 15
    }
});
