import React, { Component } from 'react';
import '../css/SingleAd.css'
import {serviceConfig} from '../appSettings.js'
import axios from 'axios'
import Header from './Header';
import RenderReviews from './RenderReviews.js';
import ReactStars from "react-rating-stars-component";
import {Card} from "react-bootstrap";

class SingleAd extends React.Component{
    constructor(props){
        super(props);
        console.log(this.props.match.params)

        this.state = {
            ad: [],
            creator: '',
            rating:'',
            reviews:[],
            permissons:[],
            is_my_ad:false,
        }
    }

    componentDidMount(){
        this.getAd();
        this.getReviews();
          this.getRole();

    }

    componentWillMount(){


      let token = localStorage.getItem('token');

      const options = {
          headers: { 'Authorization': 'Bearer ' + token}
      };

      axios.get(`${serviceConfig.baseURL}/ads/is-my-ads/${this.props.match.params.id}`,options).then(
          (resp) => {

            console.log(resp);

                  this.setState({
                    is_my_ad: resp.data,
                  });


           },
          (resp) => { alert('error myads') }
      );
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
            permissons: permissons,
         })
    }



    getReviews(){

    let token = localStorage.getItem('token');

    if(token !== null){

    const options = {
        headers: { 'Authorization': 'Bearer ' + token }
    };

    axios.get(`${serviceConfig.baseURL}/api/review/by-ad-approved/${this.props.match.params.id}`, options).then(
        (resp) => {

            console.log("REviews: ");
            console.log(resp.data);
            var sum= 0;
            var size=0;

            resp.data.forEach(review => {

                sum = sum + review.rating;
                size = size+1;

            });

            var adRating = sum/size;

            this.setState({
                reviews : resp.data,
                rating : adRating,
            })

        },
        (resp) => { alert('error reviews ') }
    );

    }



}

    render(){

      console.log(this.state);
        return(
            <div>
                <Header />

                <div className = "singleCarTitle">
                    <p style={{margin: "10px 19px"}}> {this.state.ad.brand}</p>
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
                            <p>Rating:

                            <ReactStars
                                        count={5}
                                        size={24}
                                        half={true}
                                        emptyIcon={<i className="far fa-star"></i>}
                                        halfIcon={<i className="fa fa-star-half-alt"></i>}
                                        fullIcon={<i className="fa fa-star"></i>}
                                        color2={"#ffd700"}
                                        edit={false}
                                        value={this.state.rating}



                                    />
                            </p>
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


                    <h2 style={{marginLeft:'15%',marginTop:'2%'}}>REVIEWS &amp; RATINGS</h2>
                    <br/>
                    <Card className="reviewsDiv">
                    <RenderReviews reviews={this.state.reviews} permissons={this.state.permissons} id={this.props.match.params.id}
                     isMy={this.state.is_my_ad} creator={this.state.creator}/>
                </Card>

            </div>
        )
    }



}

export default SingleAd
