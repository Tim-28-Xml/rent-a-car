import React from 'react';
import RegisterPageAgent from './RegisterPageAgent'
import { Button, Card } from "react-bootstrap"
import { serviceConfig } from '../appSettings.js'
import axios from 'axios'
import CreateNewAd from './CreateNewAd'
import megaphoneicon from '../icons/megaphone.svg'
import '../css/HomePage.css'


class HomePage extends React.Component {
    constructor(props) {
        super(props);


        this.state = {
            isLoggedIn: false,
            roles: [],
        }
    }

    componentDidMount() {

        this.getRole();
    }

    getRole() {

        let token = localStorage.getItem('token');
        let self = this;

        if (token !== null) {

            const options = {
                headers: { 'Authorization': 'Bearer ' + token }
            };

            axios.get(`${serviceConfig.baseURL}/auth/role`, options).then(
                (response) => { self.changeState(response) },
                (response) => { }
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

        console.log(this.state);
    }



    render() {
        if (this.state.roles[0] === 'ROLE_USER') {
            return (
                <div>
                    <h1 style={{ color: 'rgb(110,120,130)', textAlign: 'center', margin: "2% 0 0 0" }}>Welcome to Rent a Car</h1>
                    <CreateNewAd role={this.state.roles[0]}></CreateNewAd>

                </div>
            )
        } else {
            return (
                <div>
                    <h1 style={{ color: 'rgb(110,120,130)', textAlign: 'center', margin: "2% 0 0 0" }}>Welcome to Rent a Car</h1>



                </div>
            )
        }
    }
}

export default HomePage;