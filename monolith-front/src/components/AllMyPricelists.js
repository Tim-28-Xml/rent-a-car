import React from 'react';
import { Button, Card, Modal, Badge } from "react-bootstrap"
import axios from 'axios'
import {serviceConfig} from '../appSettings.js'
import { store } from 'react-notifications-component'
import '../css/AllMyPricelists.css'

class AllMyPricelists extends React.Component {

    constructor(props) {
        super(props);

        this.deletePricelist = this.deletePricelist.bind(this);

        this.state = {
            p: [], 

        }

    }

    componentDidMount() {
        let token = localStorage.getItem('token');
        const options = {
            headers: { 'Authorization': 'Bearer ' + token }
        };

        axios.get(`http://localhost:8082/pricelists/all-mine`, options).then(
            (resp) => {
                console.log(resp.data)
                this.setState({
                    p: resp.data
                })

            },
            (resp) => {
                alert("error")
            }
        );
    }

    deletePricelist(pricelist) {
        let token = localStorage.getItem('token');

        const options = {
            headers: { 'Authorization': 'Bearer ' + token }
        };

        console.log(pricelist);

        console.log(this.state);

        axios.post(`http://localhost:8082/pricelists/delete`, pricelist, options).then(
            (resp) => {
                store.addNotification({
                    title: "Success!",
                    message: "Pricelist is added.",
                    type: "success",
                    insert: "top",
                    container: "top-center",
                    animationIn: ["animated", "fadeIn"],
                    animationOut: ["animated", "fadeOut"],
                    dismiss: {
                        duration: 2000,
                        pauseOnHover: true
                    },
                    onRemoval: (id, removedBy) => {
                        window.
                            location.href = "https://localhost:3000/"
                    }
                })
            },
            (resp) => {
                store.addNotification({
                    title: "Error",
                    message: "Pricelists is connected to some ads!",
                    type: "danger",
                    insert: "top",
                    container: "top-center",
                    animationIn: ["animated", "fadeIn"],
                    animationOut: ["animated", "fadeOut"],
                    dismiss: {
                        duration: 2000,
                        pauseOnHover: true
                      },
                    
                  })
            }
        );
    }

    renderPricelistCards() {
        return this.state.p.map((pricelist, index) => {
            return (
                <Card key={pricelist.id} className="cardContainer">
                    <Card.Body className="cardBody">
                        <Card.Title className="cardTitleP" >{pricelist.name}</Card.Title>
                        <Card.Text className='cardText'>

                            <b>Price Per day:</b> {pricelist.dailyPrice}
                            <br />
                            <b>Price with cdw:</b> {pricelist.cdwPrice}
                            <br />
                            <b>Price if the number of kms are passed:</b> {pricelist.pricePerExtraKm}
                            <Button className="deletePricelist" variant="outline-danger" onClick={this.deletePricelist.bind(this, pricelist)} >Delete</Button>
                        </Card.Text>
                    </Card.Body>
                </Card>
            )

        })
    }

    render() {
        return (
            <div>
                <div className="renderMyPricelists">
                    {this.renderPricelistCards()}
                </div>
            </div>
        )
    }

} export default AllMyPricelists
