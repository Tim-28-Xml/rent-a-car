import React from 'react';
import { Button, Card, Modal, Badge } from "react-bootstrap"
import axios from 'axios'
import '../css/AllMyPricelists.css'

class AllMyPricelists extends React.Component {

    constructor(props) {
        super(props);

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
