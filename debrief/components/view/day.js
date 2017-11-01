import React from 'react';
import { StyleSheet, ScrollView, FlatList, View, Text } from 'react-native';

import DayEntry from "../list/dayEntry";
import * as firebase from "firebase";

export default class Day extends React.Component {
    constructor(props){
        super(props);
        this.state = {dayContent:[]};
    }
    componentDidMount(){
        firebase.database().ref('/debriefings/'+"9-23").once('value').then( (snapshot) => {
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
    }
});
