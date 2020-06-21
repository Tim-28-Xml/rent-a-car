import React from 'react';
import { Form, Button, FormGroup, FormControl, ControlLabel,Col, Modal, Card,Badge } from "react-bootstrap";
import {serviceConfig} from '../appSettings.js'
import axios from 'axios'
import miles from '../icons/tire.svg'
import star from '../icons/star.svg'
import comment from '../icons/comment.svg'
import stats from '../icons/graph.svg'
import ReactStars from "react-rating-stars-component";

class MyStatistics extends React.Component{

    constructor(props){
        super(props);


        this.state = {

            ads_mileage:[],
            ads_rating:[],
            ads_reviews:[],

        }
    }

    componentWillMount(){

        let token = localStorage.getItem('token');
        let self = this;

        if(token !== null){

            const options = {
                headers: { 'Authorization': 'Bearer ' + token}
            };

            axios.get(`${serviceConfig.baseURL}/ads/my-ads-mileage`, options).then(
                (response) => { console.log(response.data); this.setState({ ads_mileage: response.data }); },
                (response) => { alert('error') }
            );

            axios.get(`${serviceConfig.baseURL}/ads/my-ads-rating`, options).then(
                (response) => { console.log(response.data); this.setState({ ads_rating: response.data }); },
                (response) => { alert('error') }
            );

            axios.get(`${serviceConfig.baseURL}/ads/my-ads-reviews`, options).then(
                (response) => { console.log(response.data); this.setState({ ads_reviews: response.data }); },
                (response) => { alert('error') }
            );
        }

    }

    renderHighestMileage(){

        var top5 = this.state.ads_mileage.slice(0,5);
        console.log(top5);

        return top5.map((ad, index) => {
            var actualIndex = index + 1;

            return (
                <Card key={ad.id} style={{marginTop:'5%'}} >

                    <Card.Body >

            <Card.Title className="cardTitle" style={{textAlign:"left"}}><Badge variant="primary">{actualIndex}</Badge>
            &nbsp;
            {ad.carDTO.brand} {ad.carDTO.model} </Card.Title>

                        <Card.Text>

                               km: &nbsp; {ad.carDTO.km}
                               <br/>
                                class: &nbsp; {ad.carDTO.carClass}
                                <br/>
                                transmission: &nbsp; {ad.carDTO.transmission}
                        </Card.Text>
                    </Card.Body>
                </Card>
            )
        })
    }


    renderHighestRating(){

        var top5 = this.state.ads_rating.slice(0,5);
        console.log(top5);

        return top5.map((ad, index) => {
            var actualIndex = index + 1;

            return (
                <Card key={ad.id} style={{marginTop:'5%'}} >

                    <Card.Body >

            <Card.Title className="cardTitle" style={{textAlign:"left"}}><Badge variant="warning">{actualIndex}</Badge>
            &nbsp;
            {ad.carDTO.brand} {ad.carDTO.model} </Card.Title>

                        <Card.Text>

                        <ReactStars
                                count={5}
                                size={24}
                                half={true}
                                emptyIcon={<i className="far fa-star"></i>}
                                halfIcon={<i className="fa fa-star-half-alt"></i>}
                                fullIcon={<i className="fa fa-star"></i>}
                                color2={"#ffd700"}
                                edit={false}
                                value={ad.rating}
                            />

                               <br/>
                                class: &nbsp; {ad.carDTO.carClass}
                                <br/>
                                transmission: &nbsp; {ad.carDTO.transmission}
                        </Card.Text>
                    </Card.Body>
                </Card>
            )
        })
    }


    renderHighestReview(){

        var top5 = this.state.ads_reviews.slice(0,5);
        console.log(top5);

        return top5.map((ad, index) => {
            var actualIndex = index + 1;

            return (
                <Card key={ad.id} style={{marginTop:'5%'}} >

                    <Card.Body >

            <Card.Title className="cardTitle" style={{textAlign:"left"}}><Badge variant="success">{actualIndex}</Badge>
            &nbsp;
            {ad.carDTO.brand} {ad.carDTO.model} </Card.Title>

                        <Card.Text style={{textAlign:'left'}}>
                                number of reviews : {ad.reviewNum }
                               <br/>
                                class: &nbsp; {ad.carDTO.carClass}
                                <br/>
                                transmission: &nbsp; {ad.carDTO.transmission}
                        </Card.Text>
                    </Card.Body>
                </Card>
            )
        })
    }




    render() {

        return (
            <div style={{display:'flex'}}>

                <h1 style={{color:'black',marginTop:'4%',marginLeft:'2%'}}><img src={stats} style={{height:'40px',height:'40px',marginTop:'-2%'}}></img>Statistics</h1>

                <Card style={{marginTop:'10%',width:'30%',marginLeft:'-12%'}}>
                <Card.Body>

                        <Card.Title style={{ textAlign: "left" }}>
                            <img src={miles} style={{height:'40px',width:'40px'}}></img>
                            &nbsp;
                            Top 5 cars with the highest mileage</Card.Title>

                        {this.renderHighestMileage()}

                    </Card.Body>
                </Card>


                <Card style={{marginTop:'10%',width:'30%',marginLeft:'2%'}}>
                <Card.Body>

                        <Card.Title style={{ textAlign: "left" }}>
                            <img src={star} style={{height:'35px',width:'35px'}}></img>
                            &nbsp;
                            Top 5 cars with the highest rating</Card.Title>

                        {this.renderHighestRating()}

                    </Card.Body>
                </Card>

                <Card style={{marginTop:'10%',width:'30%',marginLeft:'2%'}}>
                <Card.Body>

                        <Card.Title style={{ textAlign: "left" }}>
                            <img src={comment} style={{height:'35px',width:'35px'}}></img>
                            &nbsp;
                            Top 5 cars with the most reviews</Card.Title>

                        {this.renderHighestReview()}

                    </Card.Body>
                </Card>



            </div>
        )

    }

}

export default MyStatistics
