import React from 'react';
import { View, StyleSheet, Text, ScrollView, Dimensions } from 'react-native';
import Swiper from 'react-native-swiper';

import Day from './view/day';

var moment = require('moment');
import * as firebase from "firebase";

export default class DayBrowser extends React.Component {
    constructor(props){
        super(props);
        this.offset = props.offset;

        this.state={
            days:[]
        }
    }
    componentDidMount(){
        this.setupDays();
    }
    setupDays(){
        let offsetValues = [];
        for(a = 0; a<=7; a++){
            if(this.offset+a > 60){
                offsetValues.push(this.offset+a);
            }
        }
        offsetValues.reverse();
        console.log(offsetValues);
        this.days = offsetValues.map((offset) =>
            <Day key={offset} offset={offset} openDay={this.openDay.bind(this)}></Day>
        );
        this.setState({
            days:this.days
        });
        this.forceUpdate();
    }
    openDay(keyValue){
        this.openDay(keyValue);
    }
    update(index){
        this.offset -= index-1;
        this.props.updateDay(this.offset);
    }
    render() {
        return (
            <Swiper style={styles.wrapper} onIndexChanged={this.update.bind(this)} index={7} loop={false} showsPagination={false} loadMinimal={true}>
                {this.state.days}
            </Swiper>
        );
    }
}

const styles = StyleSheet.create({
    wrapper:{

    },
    container: {
        width: Dimensions.get('window').width,
        flex: 1,
        paddingHorizontal: 20,
        paddingVertical: 50
    },
    page:{
        flex:1,
        backgroundColor:"blue",
        padding:10
    }
});