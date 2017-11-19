import React from 'react';
import { StyleSheet, ScrollView, FlatList, View, Text } from 'react-native';

import DayEntry from "../list/dayEntry";

import * as firebase from "firebase";
var moment = require('moment');

export default class Day extends React.Component {
    constructor(props){
        super(props);

        // this.dateFormat = "Y-M-D";
        this.dateFormat = "M-D";
        this.moment = moment();
        this.date = moment().subtract(props.offset, 'days').format(this.dateFormat);

        this.state = {dayContent:[]};
    }
    componentDidMount(){
        firebase.database().ref('/debriefings/'+this.date).once('value').then( (snapshot) => {
            var obj = snapshot.val();
            var output = [];
            for(k in obj){
                var n = {
                    key:k,
                    headlines: []
                };
                if(k.trim() !== "timestamp"){
                    for(k2 in obj[k]){
                        n["headlines"].push( {key:obj[k][k2].title} );
                    }
                    output.push(n);
                }
            }
            this.setState({
                dayContent:output
            })
        });
    }
    openDay(keyValue){
        this.props.openDay(keyValue);
    }
    render() {
        return (
            <View style={styles.page}>
                <FlatList
                    data = {this.state.dayContent}
                    renderItem={({item}) => <DayEntry info={item} openDay={this.openDay.bind(this)}></DayEntry>}
                />
            </View>
        );
    }
}

const styles = StyleSheet.create({
    page: {
        flex: 1,
        justifyContent: 'center',
        paddingBottom:20
    }
});
