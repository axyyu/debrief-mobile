import React from 'react';
import { View, StyleSheet, Text, ScrollView, Dimensions } from 'react-native';
import Swiper from 'react-native-swiper';

import Header from './components/header';
import Day from './components/view/day';
import DayBrowser from './components/dayBrowser';
import ArticleStack from './components/articleStack';
import Tag from './components/view/tag';
import Article from './components/view/article';

import * as firebase from "firebase";
var config = {
    apiKey: "AIzaSyClEcUda49RQvTGD4BtIkh-3G3_TXYO4_w",
    authDomain: "debrief-d5edb.firebaseapp.com",
    databaseURL: "https://debrief-d5edb.firebaseio.com",
  };
firebase.initializeApp(config);

console.disableYellowBox = true;
console.ignoredYellowBox = ['Warning: Failed prop type'];

export default class App extends React.Component {
    constructor(props){
        super(props);
        console.disableYellowBox = true;
        console.ignoredYellowBox = ['Warning: Failed prop type'];

        //Initialize
        this.origOffset = 61;
        this.offset = 61;
        
        this.content = <DayBrowser offset={this.origOffset} current={this.offset} updateDay={this.updateDay.bind(this)} openDay={this.openDay.bind(this)}></DayBrowser>
        this.state={
            time:this.time,
            date:this.date,
            content:this.content,
            offset:this.offset
        }
    }
    dayView(){
        this.content = <DayBrowser offset={this.origOffset} current={this.offset} updateDay={this.updateDay.bind(this)} openDay={this.openDay.bind(this)}></DayBrowser>
        this.setState({
            content:this.content,
            tag:null
        })
    }
    tagView(){
        this.content = <Tag tag={this.state.tag} dayView={this.dayView.bind(this)} offset={this.offset} openTag={this.openTag.bind(this)}></Tag>;
        this.setState({
            content:this.content
        })
    }
    updateDay(keyValue){
        this.setState({
            offset:keyValue
        });
    }
    openDay(keyValue){
        // this.content = <ArticleStack tag={keyValue} offset={this.offset} dayView={this.dayView.bind(this)}></ArticleStack>;
        this.content = <Tag tag={keyValue} dayView={this.dayView.bind(this)} offset={this.offset} openTag={this.openTag.bind(this)}></Tag>;
        this.setState({
            content:this.content,
            tag:keyValue
        })
        // console.log(keyValue);
    }
    openTag(keyValue){
        this.content = <Article offset={this.offset} tag={this.state.tag} article={keyValue} tagView={this.tagView.bind(this)}></Article>;
        this.setState({
            content:this.content
        })
    }
    render() {
        return (
            <View style={styles.container}>
                <Header offset={this.state.offset} tag={this.state.tag}></Header>
                {this.state.content}
            </View>
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

/*
<View style={styles.container}>
            <Header day={this.state.time} tag={this.state.tag}></Header>
            {this.page}
        </View>
*/
/*
<ScrollView style={styles.scroll} horizontal>
                <View style={styles.container}>
                    <Header day={this.state.time} tag={this.state.tag}></Header>
                    {this.page}
                </View>
            </ScrollView>
            */
/*
<Swiper style={styles.wrapper} horizontal showsPagination={false}>
                    {this.page}
                    {this.page}
                </Swiper>
*/