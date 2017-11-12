import React from 'react';
import { View, StyleSheet, Text, ScrollView, Dimensions } from 'react-native';
import Swiper from 'react-native-swiper';

import Day from './view/day';
import Tag from './view/tag';
import Article from './view/article';

var moment = require('moment');
import * as firebase from "firebase";

export default class ArticleStack extends React.Component {
    constructor(props){
        super(props);
    }
    componentDidMount(){
        
    }
    openDay(keyValue){

    }
    update(index){
        console.log(index);
    }
    render() {
        return (
            <Swiper style={styles.wrapper} onIndexChanged={this.update.bind(this)}horizontal>
                <Text>Hello</Text>
                <Text>Hello</Text>
                <Text>Hello</Text>
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