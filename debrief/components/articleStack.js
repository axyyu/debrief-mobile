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
        this.offset = props.offset;
        this.pageIndex = 1;

        this.state = {};
    }
    componentDidMount(){
        this.setup();
    }
    setup(){
        this.day = <Day offset={this.props.offset} openDay={this.openDay.bind(this)}></Day>;
        this.tag = <Tag tag={this.props.tag} offset={this.props.offset} openTag={this.openTag.bind(this)}></Tag>;
        this.setState({
            day:this.day,
            tag:this.tag
        });
        this.forceUpdate();
    }
    openDay(keyValue){
        console.log("this shouldn't happen");
    }
    openTag(keyValue){
        this.pageIndex = 1;
        this.article = <Article offset={this.offset} tag={this.props.tag} article={keyValue.substring(1,keyValue.length)}></Article>;
        // this.article = <Tag tag={this.props.tag} offset={this.props.offset} openTag={this.openTag.bind(this)}></Tag>;
        this.setState({
            article: this.article
        })
    }
    dayView(index){
        console.log(index);
        if(index == 0){
            this.props.dayView();
        }
    }
    render() {
        return (
            <Swiper style={styles.wrapper} onIndexChanged={this.dayView.bind(this)} index={this.pageIndex} loop={false} showsPagination={false} loadMinimal={true}>
                {this.state.day}
                {this.state.tag}
                {this.state.article}
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