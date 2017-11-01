import React from 'react';
import { StyleSheet, Text, View } from 'react-native';

export default class Headline extends React.Component {
    constructor(props){
        super(props);
    }
    render() {
        return (
            <View style={styles.headlineCont}>
                <Text style={styles.headline}>{this.props.info}</Text>
            </View>
        );
    }
}

const styles = StyleSheet.create({
    headlineCont:{
        marginTop: 10,
    },
    headline:{
        fontSize:15
    }
});
