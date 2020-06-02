import React, { Component } from 'react';
import '../css/SingleAd.css'
import {serviceConfig} from '../appSettings.js'
import axios from 'axios'
import Header from './Header';

class SingleAd extends React.Component{
    constructor(props){
        super(props);
        console.log(this.props.match.params)
        
        this.state = {
            ad: [],
            creator: '',
        }
    }

    componentDidMount(){
        this.getAd();
    }

    getAd(){
        let token = localStorage.getItem('token');

        const options = {
            headers: { 'Authorization': 'Bearer ' + token}
        };
        
        axios.get(`${serviceConfig.baseURL}/ads/one/${this.props.match.params.id}`,options).then(
            (resp) => { 
                //console.log(resp.data)
                this.setState({
                    ad: resp.data.carDTO,
                    creator: resp.data.username
                })
                console.log(this.state.ad)
             },
            (resp) => { alert('error') }
        );
    }

    render(){
        return(
            <div>
                <Header />

                <div className = "singleCarTitle">
                    <p style={{margin: "10px 19px"}}>{this.state.ad.brand}</p>
                    <p style={{margin: "10px 0"}}>{this.state.ad.model}</p>
                </div>

                <div className="imagesAd">
                    <h2>IMAGES</h2>
                </div>

                <div className="middleAdPart">
                    
                    <div className="leftAdPart">
                        <div className="leftTitle">
                            <p>Class:</p>
                            <p>Fuel type:</p>
                            <p>Transmission</p>
                            <p>Kilometers traveled:</p>
                            <p>Kilometers limit:</p>
                            <p>Number of child seats:</p>
                            {
                                this.state.ad.cdw &&
                                <p>Collision damage waiver </p>
                            }
                            {
                                !this.state.ad.cdw &&
                                <p style={{textDecoration: 'line-through'}}>Collision damage waiver </p>
                            }
                        </div>
                        <div className="leftInfo">

                        
                            <p>{this.state.ad.carClass}</p>
                            <p>{this.state.ad.fuel}</p>
                            <p>{this.state.ad.transmission}</p>
                            <p>{this.state.ad.km}</p>
                            <p>{this.state.ad.kmLimit}</p>
                            <p>{this.state.ad.childSeats}</p>
                        </div>
                    </div>

                    <div className="rightAdPart">
                        <div className="rightTitle">
                            <p>Owner:</p>
                            <p>Price</p>
                            <p>Calendar</p>                           
                        </div>
                        <div>
                            <p>{this.state.creator}</p>
                        </div>
                    </div>
                </div>

                <div className="reviewsDiv">
                    <h2>REVIEWS &amp; RATINGS</h2>
                </div>
            </div>
        )
    }

    

}

export default SingleAd