import React from 'react';
import { StyleSheet, Switch, Picker, View, Text, FlatList, CheckBox, TouchableOpacity } from 'react-native';

import Header from "../header";
import TagToggle from "../list/tagToggle";

var moment = require('moment');
var s = require("../colors");

var tagSelection = [{key:'sports'},{key: 'politics'},{key: 'money'},{key:'technology'},{key:'entertainment'},{key:'science'},{key:'music'},{key:'movies'}];

export default class Settings extends React.Component {
    constructor(props){
        super(props);

        this.hourPicker = [];
        for(x=0;x<=24;x++){
            this.hourPicker.push(<Picker.Item label={""+x} value={""+x}/>);
        }

        this.state={
            notif:true,
            notifHour:"10",
            sports:true,
        }
    }
    componentWillMount(){
        
    }
    back(){
        this.props.navigation.navigate('Home');
    }
    toggleNotif(value){
        this.setState({notif: value});
    }
    toggleTag(value){
        console.log("Hello");
    }
    render() {
        return (
            <View style={styles.wrapper}>
                <Header></Header>
                <View style={styles.page}>
                    <View style={styles.option}>
                        <Text style={styles.optionHeader}>Notifications</Text>
                        <Switch
                            onValueChange = {this.toggleNotif.bind(this)}
                            value = {this.state.notif}/>
                    </View>
                    <View style={styles.option}>
                        <Text style={styles.optionHeader}>Notification Time</Text>
                        <Picker
                            style={styles.picker}
                            selectedValue={this.state.notifHour}
                            onValueChange={(itemValue, itemIndex) => this.setState({notifHour: itemValue})}>
                            {this.hourPicker}
                        </Picker>
                    </View>
                    <Text style={styles.optionHeader}>Tags</Text>
                    <FlatList
                        data={tagSelection}
                        renderItem={({item}) => <TagToggle key={item.key}></TagToggle> }
                    />
                </View>
                <TouchableOpacity onPress={this.back.bind(this)} style={ [styles.button,s[this.state.tag+"Button"]] } >
                    <Text style={styles.buttonText}>Back</Text>
                </TouchableOpacity>
            </View>
        );
    }
}

const styles = StyleSheet.create({
    wrapper:{
        flex: 1,
        paddingHorizontal: 20,
        paddingTop: 50,
        paddingBottom:20,
        backgroundColor: "#FFFFFF"
    },
    page:{
        flex:1
    },
    button:{
        marginBottom: 10,
        padding:10,
        borderColor: "black",
        borderWidth:2
    },
    buttonText:{
        fontSize: 20,
        textAlign:'center'
    },
    option:{
        flexDirection:"row",
        justifyContent:"space-between",
        alignItems: 'center',
        marginVertical:10
    },
    optionHeader:{
        fontSize:15
    },
    picker:{
        width:100
    }
});
