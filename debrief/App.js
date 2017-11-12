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
        this.offset = 61;
        
        this.content = <DayBrowser offset={this.offset} updateDay={this.updateDay.bind(this)}></DayBrowser>
        this.state={
            time:this.time,
            date:this.date,
            content:this.content,
            offset:this.offset
        }
    }
    componentDidMount(){
        
    }
    updateDay(keyValue){
        this.setState({
            offset:keyValue
        });
    }
    openDay(keyValue){
        this.page = <Tag data={this.date} tag={keyValue} openTag={this.openTag.bind(this)}></Tag>
        this.setState({
            page:this.page,
            tag:keyValue
        })
        
    }
    openTag(keyValue){
        this.page = <Article data={this.date} tag={this.state.tag} openTag={this.openTag.bind(this)} title={keyValue}></Article>
        this.setState({
            page:this.page,
            title:keyValue
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