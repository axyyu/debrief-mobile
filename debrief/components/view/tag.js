import React from 'react';
import { StyleSheet, ScrollView, FlatList, View, Text } from 'react-native';

import TagEntry from "../list/tagEntry";
import * as firebase from "firebase";
var moment = require('moment');

export default class Day extends React.Component {
    constructor(props){
        super(props);

        this.dateFormat = "M-D";
        this.moment = moment();
        this.date = moment().subtract(props.offset, 'days').format(this.dateFormat);

        this.state = {tagContent:[]};
    }
    componentDidMount(){
        firebase.database().ref('/debriefings/'+this.date+"/"+this.props.tag).once('value').then( (snapshot) => {
            var obj = snapshot.val();
            var output = [];
            for(k in obj){
                output.push( {key:obj[k].title, fbind:k, shortsum:obj[k].shortsum, tag:this.props.tag} );
            }
            this.setState({
                tagContent:output
            })
        });
    }
    openTag(keyValue){
        this.props.openTag(keyValue);
    }
    render() {
        return (
            <View style={styles.page}>
                <FlatList
                    data = {this.state.tagContent}
                    renderItem={({item}) => <TagEntry info={item} openTag={this.openTag.bind(this)}></TagEntry>}
                />
            </View>
        );
    }
}

const styles = StyleSheet.create({
    page: {
        flex: 1,
        justifyContent: 'center',
    }
});
