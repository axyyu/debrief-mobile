import React from 'react';
import { StyleSheet, Text, FlatList, TouchableOpacity } from 'react-native';

var s = require("../colors");

export default class TagEntry extends React.Component {
    constructor(props){
        super(props);
    }
    openTag(){
        this.props.openTag(this.props.info.fbind);
    }
    render() {
        return (
            <TouchableOpacity onPress={this.openTag.bind(this)} title={this.props.info.key} style={ [styles.button,s[this.props.info.tag]] } >
                <Text style={styles.title}>{this.props.info.key}</Text>
                <Text style={styles.summary}>{this.props.info.shortsum}</Text>
            </TouchableOpacity>
        );
    }
}

const styles = StyleSheet.create({
    button:{
        marginVertical: 10,
        // backgroundColor:"#b5c7e5AA",
        alignSelf: 'stretch',
        paddingHorizontal: 20,
        paddingVertical:5,
        width:"100%",
    },
    title:{
        fontSize:20,
    },
    summary:{
        marginTop: 10,
        fontSize:15
    }
});
