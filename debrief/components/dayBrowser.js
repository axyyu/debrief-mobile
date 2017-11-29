import React from 'react';
import { View, StyleSheet, Text, Dimensions, TouchableOpacity } from 'react-native';
import Swiper from 'react-native-swiper';
import { StackNavigator } from 'react-navigation';

import Day from './view/day';
import Header from './header';

var moment = require('moment');
import * as firebase from "firebase";

let dayLimit = 6;
export default class DayBrowser extends React.Component {
    constructor(props){
        super(props);
        const { params } = this.props.navigation.state;

        this.offset = 65;
        this.current = 65;
        if( params && params.current ){
            this.current = params.current;
        }

        this.start = dayLimit - (this.current - this.offset);
        console.log(this.start);

        this.state={
            offset: this.current,
            days:[]
        }
    }
    componentDidMount(){
        this.setupDays();
    }
    setupDays(){
        let offsetValues = [];
        for(a = 0; a<=dayLimit; a++){
            if(this.offset+a > 0){
                offsetValues.push(this.offset+a);
            }
        }
        offsetValues.reverse();
        this.days = offsetValues.map((offset) =>
            <Day key={offset} offset={offset} openDay={this.openDay.bind(this)}></Day>
        );
        this.setState({
            days:this.days
        });
        this.forceUpdate();
    }
    openDay(keyValue){
        this.props.navigation.navigate('Tag', { offset: this.current, tag:keyValue });
    }
    openSettings(){
        this.props.navigation.navigate('Settings');
    }
    update(index){
        this.current = (dayLimit-index) + this.offset;

        this.setState({
            offset:this.current
        })
    }
    render() {
        return (
            <View style={styles.wrapper}>
                <Header offset={this.state.offset}></Header>
                <Swiper onIndexChanged={this.update.bind(this)} index={this.start} loop={false} showsPagination={false} loadMinimal={true}>
                    {this.state.days}
                </Swiper>
            </View>
        );
    }
}

const styles = StyleSheet.create({
    wrapper:{
        width: Dimensions.get('window').width,
        flex: 1,
        paddingTop: 50,
        paddingBottom:0,
        backgroundColor: "#FFFFFF"
    },
    button:{
        marginBottom: 10,
        padding:10,
        borderColor: "black",
        borderWidth:2
    },
    buttonText:{
        fontSize: 20,
        textAlign:'center'
    }
});

/*
<TouchableOpacity onPress={this.openSettings.bind(this)} style={ [styles.button] } >
                    <Text style={styles.buttonText}>Settings</Text>
                </TouchableOpacity>
                */