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
            ads: [],
        }
    }

    componentDidMount() {

        this.getAds();
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

    getAds() {
        let token = localStorage.getItem('token');

        const options = {
            headers: { 'Authorization': 'Bearer ' + token }
        };

        axios.get(`${serviceConfig.baseURL}/ads/all`, options).then(
            (resp) => {
                console.log(resp.data)
                this.setState({
                    ads: resp.data
                })

            },
            (resp) => { alert('error') }
        );
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

    view(id) {
        window.location.href = `http://localhost:3000/ad/${id}`
    }


    renderAdCards() {
        return this.state.ads.map((ad, index) => {
            const { carDTO, username } = ad

            return (
                <Card key={carDTO.id} className="cardContainer" onClick={this.view.bind(this, carDTO.id)}>
                    <Card.Body className="cardBody">
                        <Card.Title className="cardTitle" >{carDTO.brand} {carDTO.model}</Card.Title>
                        <Card.Text className='cardText'>

                            fuel: {carDTO.fuel}
                            <br />
                                class: {carDTO.carClass}
                            <br />
                                transmission: {carDTO.transmission}


                        </Card.Text>
                    </Card.Body>
                </Card>
            )

        })
    }


    render() {
        if (this.state.roles[0] === 'ROLE_USER') {
            return (
                <div>
                    <h1 style={{ color: 'rgb(110,120,130)', textAlign: 'center', margin: "2% 0 0 0" }}>Welcome to Rent a Car</h1>
                    <h1 style={{ color: 'rgb(110,120,130)', textAlign: 'center', margin: "2% 0 0 0" }}>Ads</h1>
                    <div className="renderCardsAds">
                        {this.renderAdCards()}
                    </div>
                </div>
            )
        } else if(this.state.roles[0] === 'ROLE_AGENT') {
            return (
                <div>
                    <h1 style={{ color: 'rgb(110,120,130)', textAlign: 'center', margin: "2% 0 0 0" }}>Welcome to Rent a Car</h1>
                    <Card className="adCard" >
                        <Card.Body>
                            <Card.Title>Create a new ad <img className="carMegaphoneIcon" src={megaphoneicon}></img> </Card.Title>
                            <Card.Text style={{ color: 'rgb(110,120,130)' }}>
                                You can click here and create your new ad so others can rent your car.
                            </Card.Text>
                            <a href="http://localhost:3000/create-ad" className="createAdBtn">New ad</a>
                        </Card.Body>
                    </Card>
                    <h1 style={{ color: 'rgb(110,120,130)', textAlign: 'center', margin: "2% 0 0 0" }}>Ads</h1>
                    <div className="renderCardsAds">
                        {this.renderAdCards()}
                    </div>
                </div>
            )
        }
         else {
            return (
                <div>
                    <h1 style={{ color: 'rgb(110,120,130)', textAlign: 'center', margin: "2% 0 0 0" }}>Welcome to Rent a Car</h1>
                    <div className="renderCardsAds">
                        {this.renderAdCards()}
                    </div>
                </div>
            )
        }
    }
}

export default HomePage;