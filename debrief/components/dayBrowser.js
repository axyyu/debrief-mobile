import React from 'react';
import { View, StyleSheet, Text, ScrollView, Dimensions } from 'react-native';
import Swiper from 'react-native-swiper';

import Day from './view/day';

var moment = require('moment');
import * as firebase from "firebase";

let dayLimit = 7;
// let start = dayLimit;
export default class DayBrowser extends React.Component {
    constructor(props){
        super(props);
        this.offset = props.offset;
        this.start = dayLimit - (props.current - props.offset);
        console.log(this.start);

        this.state={
            days:[]
        }
    }
    componentDidMount(){
        this.setupDays();
    }
    setupDays(){
        let offsetValues = [];
        for(a = 0; a<=dayLimit; a++){
            if(this.offset+a > 60){
                offsetValues.push(this.offset+a);
            }
        }
        offsetValues.reverse();
        // console.log(offsetValues);
        this.days = offsetValues.map((offset) =>
            <Day key={offset} offset={offset} openDay={this.openDay.bind(this)}></Day>
        );
        this.setState({
            days:this.days
        });
        this.forceUpdate();
    }
    openDay(keyValue){
        // console.log(keyValue);
        this.props.openDay(keyValue);
    }
    update(index){
        // this.offset -= (index-7);
        this.props.updateDay((dayLimit-index) + this.offset);
    }
    render() {
        return (
            <Swiper style={styles.wrapper} onIndexChanged={this.update.bind(this)} index={this.start} loop={false} showsPagination={false} loadMinimal={true}>
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