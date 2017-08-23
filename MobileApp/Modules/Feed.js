/**
 * Created by andrew on 8/22/17.
 */

import React, { Component } from 'react';
import { StyleSheet, Text, View, ScrollView, FlatList } from 'react-native';
import Day from './Types/Day';
import Tag from './Types/Tag';
import Article from './Types/Article';

export default class Heading extends React.Component {
    constructor(props) {
        super(props);
    }
    render() {
        if(this.props.article){
            return (
                <View style={styles.feedContainer}>
                    <FlatList
                        data={[
                            {key: "Most prescribed opioid", tag:'science', summary: "Most prescribed opioid is bomb"},
                            {key: "Mot prescribed opioid", tag:'science',summary: "Most prescribed opioid is bomb"},
                        ]}
                        renderItem={({item}) => <Tag payload={item} /> }
                    />
                </View>
            );
        }
        if(this.props.tag){
            return (
                <View style={styles.feedContainer}>
                    <FlatList
                        data={[
                            {key: "Most prescribed opioid", tag:'science', summary: "Most prescribed opioid is bomb"},
                            {key: "Mot prescribed opioid", tag:'science',summary: "Most prescribed opioid is bomb"},
                        ]}
                        renderItem={({item}) => <Tag payload={item} /> }
                    />
                </View>
            );
        }
        return (
            <View style={styles.feedContainer}>
                <Article payload={{key: "Most prescribed opioid", tag:'science', source:'Guardian', content: "Most prescribed opioid is bomb"}} />
            </View>
        );
    }
}

const styles = StyleSheet.create({
    feedContainer: {
        flex: 1,
        flexDirection: 'row',
        justifyContent: 'flex-start',
    }
});

// <FlatList
//     data={[
//         {key: 'science', article: [{key:"Most prescribed opioid"}, {key:"Mot prescribed opioid"}]},
//         {key: 'sports', article: [{key:"Most prescribed opioid"}, {key:"Mot prescribed opioid"}]},
//         {key: 'politics', article: [{key:"Most prescribed opioid"}, {key:"Mot prescribed opioid"}]},
//         {key: 'entertainment', article: [{key:"Most prescribed opioid"}, {key:"Mot prescribed opioid"}]},
//         {key: 'money', article: [{key:"Most prescribed opioid"}, {key:"Mot prescribed opioid"}]},
//         {key: 'movies', article: [{key:"Most prescribed opioid"}, {key:"Mot prescribed opioid"}]}
//     ]}
//     renderItem={({item}) => <Day payload={item} /> }
// />
