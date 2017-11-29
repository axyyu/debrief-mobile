import React from 'react';
import { StyleSheet, Text, FlatList, Dimensions, TouchableOpacity } from 'react-native';

var s = require("../colors");

var width = Dimensions.get('window').width - 40;
export default class TagEntry extends React.Component {
    constructor(props){
        super(props);
    }
    openTag(){
        this.props.openTag(this.props.info.fbind);
    }
    render() {
        return (
            <TouchableOpacity onPress={this.openTag.bind(this)} title={this.props.info.key} style={ [styles.button,s[this.props.info.tag+"Button"]] } >
                <Text style={[styles.tag,s[this.props.info.tag+"Text"]]}>{(this.props.info.tag).toUpperCase()}</Text>
                <Text style={[styles.title] }>{this.props.info.key}</Text>
                <Text style={styles.summary}>{this.props.info.shortsum}</Text>
            </TouchableOpacity>
        );
    }
}

const styles = StyleSheet.create({
    button:{
        marginVertical: 10,
        marginHorizontal: 20,
        // backgroundColor:"#b5c7e5AA",
        alignSelf: 'stretch',
        paddingHorizontal: 20,
        paddingVertical:20,
        width: width,
        borderRadius:20,
    },
    title:{
        fontSize:16,
        fontWeight:"bold",
        // borderBottomWidth:1,
        marginVertical: 10,
    },
    summary:{
        // marginTop: 10,
        fontSize:15
    },
    tag:{
        fontSize:12,
        fontWeight:"bold"
    }
});
