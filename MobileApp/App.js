import React, { Component } from 'react';
import { StyleSheet, Text, View, ScrollView } from 'react-native';
import Heading from './Modules/Heading';
import Feed from './Modules/Feed';

export default class App extends React.Component {
  render() {
    return (
      <View style={styles.container}>
        <Heading />
        <Feed />
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'flex-start',
  }
});
