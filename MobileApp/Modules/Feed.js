/**
 * Created by andrew on 8/22/17.
 */

import React, { Component } from 'react';
import { StyleSheet, Text, View, ScrollView, FlatList } from 'react-native';
import Day from './Types/Day';

export default class Heading extends React.Component {
    constructor(props) {
        super(props);
    }
    render() {
        return (
            <View style={styles.feedContainer}>
                <FlatList
                    data={[
                        {key: 'science', article: [{key:"Most prescribed opioid"}, {key:"Mot prescribed opioid"}]},
                        {key: 'sports', article: [{key:"Most prescribed opioid"}, {key:"Mot prescribed opioid"}]},
                        {key: 'politics', article: [{key:"Most prescribed opioid"}, {key:"Mot prescribed opioid"}]},
                        {key: 'entertainment', article: [{key:"Most prescribed opioid"}, {key:"Mot prescribed opioid"}]},
                        {key: 'money', article: [{key:"Most prescribed opioid"}, {key:"Mot prescribed opioid"}]},
                        {key: 'movies', article: [{key:"Most prescribed opioid"}, {key:"Mot prescribed opioid"}]}
                    ]}
                    renderItem={({item}) => <Day payload={item} /> }
                />
            </View>
        );
    }
}

const styles = StyleSheet.create({
    feedContainer: {
        flex: 1,
        flexDirection: 'row',
        alignItems: 'center',
        justifyContent: 'flex-start',
    }
});
