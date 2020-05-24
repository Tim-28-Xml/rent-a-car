import React from 'react';
import RegisterPageAgent from './RegisterPageAgent'
import {Button} from "react-bootstrap"
import {serviceConfig} from '../appSettings.js'
import axios from 'axios'
import '../css/HomePage.css'


class HomePage extends React.Component{
    constructor(props){
        super(props);

        this.proveAdmin = this.proveAdmin.bind(this);
        this.proveComment = this.proveComment.bind(this);
        this.proveUser = this.proveUser.bind(this);
        this.proveOrder = this.proveOrder.bind(this);
        this.proveAgent = this.proveAgent.bind(this);
        this.proveCreate = this.proveCreate.bind(this);

        
        this.state = {
            isLoggedIn: false,
            roles: [],
        }
    }

    componentDidMount(){
        
        this.getRole();
    }

    getRole(){

        let token = localStorage.getItem('token');
        let self = this;

        if(token !== null){
  
            const options = {
                headers: { 'Authorization': 'Bearer ' + token}
            };

            axios.get(`${serviceConfig.baseURL}/auth/role`, options).then(
                    (response) => { self.changeState(response) },
                    (response) => {alert('Please log in.')}
            );
        }

    }


    changeState(resp) {
        console.log(resp);

        var permissons = [];

        resp.data.forEach(element => {
            permissons.push(element.authority);
        });
        
        
        this.setState({ 
            isLoggedIn: true,
            roles: permissons,
         })
    }

    proveAdmin(){

        let token = localStorage.getItem('token');
        const options = {
            headers: { 'Authorization': 'Bearer ' + token}
        };

        axios.get(`${serviceConfig.baseURL}/permission/admin`, options).then(
            (response) => { alert('Success!') },
            (response) => {
                alert('Forbidden');
            }
    );
    }

    
    proveComment(){

        let token = localStorage.getItem('token');
        const options = {
            headers: { 'Authorization': 'Bearer ' + token}
        };

        axios.get(`${serviceConfig.baseURL}/permission/comment`, options).then(
            (response) => { alert('You can leave a comment!') },
            (response) => {
                alert('You do not have a permisson to leave a comment!');
            }
    );
    }

    proveUser(){

        let token = localStorage.getItem('token');
        const options = {
            headers: { 'Authorization': 'Bearer ' + token}
        };

        axios.get(`${serviceConfig.baseURL}/permission/user`, options).then(
            (response) => { alert('Success') },
            (response) => {
                alert('Forbidden!');
            }
    );
    }

    proveOrder(){

        let token = localStorage.getItem('token');
        const options = {
            headers: { 'Authorization': 'Bearer ' + token}
        };

        axios.get(`${serviceConfig.baseURL}/permission/order`, options).then(
            (response) => { alert('Order successful!') },
            (response) => {
                alert('You do not have a permisson to create an order!');
            }
    );
    }

    proveAgent(){

        let token = localStorage.getItem('token');
        const options = {
            headers: { 'Authorization': 'Bearer ' + token}
        };

        axios.get(`${serviceConfig.baseURL}/permission/agent`, options).then(
            (response) => { alert('Success!') },
            (response) => {
                alert('Forbidden!');
            }
    );
    }

    proveCreate(){

        let token = localStorage.getItem('token');
        const options = {
            headers: { 'Authorization': 'Bearer ' + token}
        };

        axios.get(`${serviceConfig.baseURL}/permission/create`, options).then(
            (response) => { alert('You can create an ad!') },
            (response) => {
                alert('You do not have a permission to create an ad!');
            }
    );
    }


    render(){
        return(
            <div>
                <h1 style={{color: 'rgb(110,120,130)', textAlign: 'center', margin: "2% 0 0 0"}}>Welcome to Rent a Car</h1>

                {
                    this.state.isLoggedIn &&
                    <div className="homeDivBtn">
                        <label>Roles:</label>
                        <br/>
                        <Button style={{margin: '10px'}} variant="primary" onClick={this.proveAdmin}>Admin</Button>
                        <Button style={{margin: '10px'}} variant="danger" onClick={this.proveAgent}>Agent</Button> 
                        <Button style={{margin: '10px'}} variant="info" onClick={this.proveUser}>User</Button>  
                        <br/>
                        <label>Permissions:</label>
                        <br/>
                        <Button style={{margin: '10px'}} variant="outline-primary" onClick={this.proveComment}>Comment</Button>
                        <Button style={{margin: '10px'}} variant="outline-danger" onClick={this.proveCreate}>Create ad</Button> 
                        <Button style={{margin: '10px'}} variant="outline-info" onClick={this.proveOrder}>Order</Button>  
                    </div>
                }

            </div>
        )
    }
}

export default HomePage;