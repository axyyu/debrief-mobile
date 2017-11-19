import React from 'react';
import { View, StyleSheet, Text, ScrollView, Dimensions } from 'react-native';
import { StackNavigator } from 'react-navigation';
// import Swiper from 'react-native-swiper';

import DayBrowser from './components/dayBrowser';
import Tag from './components/view/tag';
import Article from './components/view/article';
import Settings from './components/view/settings';

import * as firebase from "firebase";
var config = {
    apiKey: "AIzaSyClEcUda49RQvTGD4BtIkh-3G3_TXYO4_w",
    authDomain: "debrief-d5edb.firebaseapp.com",
    databaseURL: "https://debrief-d5edb.firebaseio.com",
  };
firebase.initializeApp(config);

console.disableYellowBox = true;
console.ignoredYellowBox = ['Warning: Failed prop type'];

const DebriefApp = StackNavigator({
    Home: { screen: DayBrowser },
    Tag: { screen:Tag },
    Article: { screen:Article }
},
{ 
    headerMode: 'none' 
});
  
export default class App extends React.Component {
    render() {
      return <DebriefApp />;
    }
}