import React from 'react';
import RegisterPageAgent from './RegisterPageAgent'
import {Button} from "react-bootstrap"
import '../css/Header.css'
import caricon from '../icons/vehicle.svg'
import RegisterPageUser from './RegisterPageUser'
import LoginPage from './LoginPage'
import {serviceConfig} from '../appSettings.js'
import axios from 'axios'

class Header extends React.Component{
    constructor(props){
        super(props);

        this.logout = this.logout.bind(this);

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

    logout(){
        console.log('usao')

        //const token = JSON.parse(localStorage.getItem('token'));
      
        this.setState({
            isLoggedIn: false
        });
          
        localStorage.clear();
        window.location.href="http://localhost:3000/";


    }

    render(){
        console.log(this.state.roles)
        return(
            <div style={{background: "rgb(112,128,144)", display: "flex", height: "45px"}} >
                <h2 className="headerTitle">Rent a Car</h2>
                <img src={caricon} style={{height:'40px', width: 'auto', margin: "0 0 0 40%"}} alt='Unavailable icon' />

                { 
                    !this.state.isLoggedIn &&
                    <div className="headerButtons1">
                    <button className="btnHeaderHome" href="http://localhost:3000/">Home</button>
                    <RegisterPageUser />
                    <LoginPage />
                    </div>
                }

                { 
                    this.state.roles[0] === 'ROLE_ADMIN' &&
                    <div className="headerButtons1">
                    <button href="http://localhost:3000/" className="btnHeaderHome">Home</button>
                    <RegisterPageAgent />
                    <button className="logoutBtn" onClick={this.logout}>Log out</button>
                    </div>
                }

                { 
                    this.state.roles[0] === 'ROLE_USER' &&
                    <div className="headerButtons">
                    <button className="btnHeaderHome" href="http://localhost:3000/">Home</button>
                    <button className="logoutBtn" onClick={this.logout}>Log out</button>
                    </div>
                }

                { 
                    this.state.roles[0] === 'ROLE_AGENT' &&
                    <div className="headerButtons">
                    <button className="btnHeaderHome" href="http://localhost:3000/">Home</button>
                    <button className="logoutBtn" onClick={this.logout}>Log out</button>
                    </div>
                }

            </div>
        )
    }
}

export default Header;