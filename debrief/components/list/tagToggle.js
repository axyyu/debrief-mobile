import React from 'react';
import { View, Text, StyleSheet, Switch, CheckBox } from 'react-native';

var s = require("../colors");

export default class TagToggle extends React.Component {
    constructor(props){
        super(props);

        this.state={
            val:true,

        };
    }
    toggle(value){
        this.setState({val:value});
    }
    render() {
        return (
            <View style={styles.option}>
                <Text style={styles.optionHeader}>Hello</Text>
                <Switch
                    onValueChange = {this.toggle.bind(this)}
                    value = {this.state.val}
                />
            </View>
        );
    }
}

const styles = StyleSheet.create({
    option:{
        flexDirection:"row",
        justifyContent:"space-between",
        alignItems: 'center',
        marginVertical:2
    },
    optionHeader:{
        fontSize:15
    }
});
