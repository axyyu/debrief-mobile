import React from 'react';
import { View, StyleSheet, Text } from 'react-native';

import Header from './components/header';
import Day from './components/view/day';
import Tag from './components/view/tag';
import Article from './components/view/article';

// import * as moment from 'moment';
var moment = require('moment');
import * as firebase from "firebase";
var config = {
    apiKey: "AIzaSyClEcUda49RQvTGD4BtIkh-3G3_TXYO4_w",
    authDomain: "debrief-d5edb.firebaseapp.com",
    databaseURL: "https://debrief-d5edb.firebaseio.com",
  };
firebase.initializeApp(config);

console.disableYellowBox = true;

export default class App extends React.Component {
    constructor(props){
        super(props);
        this.moment = moment();
        this.timeFormat = "dddd, MMMM D";
        this.dateFormat = "M-D";

        this.time = moment().subtract(1, 'days').format(this.timeFormat)
        this.date = moment().subtract(1, 'days').format(this.dateFormat)
        this.page = <Day data={this.date} openDay={this.openDay.bind(this)}></Day>

        this.state={
            time:this.time,
            date:this.date,
            page:this.page
        }
    }
    componentDidMount(){
        
    }
    openDay(keyValue){
        this.page = <Tag data={this.date} tag={keyValue} openTag={this.openTag.bind(this)}></Tag>
        this.setState({
            time:this.time,
            date:this.date,
            page:this.page,
            tag:keyValue
        })
        
    }
    openTag(keyValue){
        this.page = <Article data={this.date} tag={this.state.tag} openTag={this.openTag.bind(this)} title={keyValue}></Article>
        this.setState({
            time:this.time,
            date:this.date,
            page:this.page,
            tag:this.state.tag,
            title:keyValue
        })
    }
    render() {
        return (
        <View style={styles.container}>
            <Header day={this.state.time} tag={this.state.tag}></Header>
            {this.page}
        </View>
        );
    }
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        paddingHorizontal: 20,
        paddingVertical: 50
    },
});
