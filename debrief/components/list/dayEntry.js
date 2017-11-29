import React from 'react';
import { StyleSheet, Text, FlatList, TouchableOpacity, Dimensions } from 'react-native';

import Headline from '../list/headline';
var s = require("../colors");

var width = Dimensions.get('window').width - 40;

export default class DayEntry extends React.Component {
    constructor(props){
        super(props);
    }
    openDay(){
        this.props.openDay(this.props.info.key);
        // console.log(this.props.info.key);
    }
    render() {
        return (
            <TouchableOpacity onPress={this.openDay.bind(this)} title={this.props.info.key} style={ [styles.button,s[this.props.info.key+"Button"]] } >
                <Text style={[styles.tag,s[this.props.info.key+"Text"]]}>{(this.props.info.key).toUpperCase()}</Text>
                <FlatList
                    data={this.props.info.headlines}
                    renderItem={({item}) => <Headline info={item.key}></Headline>}
                />
            </TouchableOpacity>
        );
    }
}

const styles = StyleSheet.create({
    button:{
        marginVertical: 10,
        marginHorizontal:20,
        // backgroundColor:"#b5c7e5AA",
        alignSelf: 'stretch',
        paddingHorizontal: 20,
        paddingVertical:20,
        width: width,
        borderRadius:20,
    },
    tag:{
        fontSize:12,
        fontWeight:"bold"
    }
});
