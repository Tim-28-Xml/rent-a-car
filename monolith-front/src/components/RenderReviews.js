import React from 'react';
import { Form, Button, FormGroup, FormControl, ControlLabel,Col, Modal, Card } from "react-bootstrap";
import more from '../icons/download (1).svg';
import clock from '../icons/clock.svg';
import user from '../icons/location.svg';
import chat from '../icons/speech-bubble.svg';
import ReactStars from "react-rating-stars-component";
import response from '../icons/response.svg';
import {serviceConfig} from '../appSettings.js'
import axios from 'axios'



class RenderReviews extends React.Component{

    constructor(props){
        super(props);
        this.handleChange = this.handleChange.bind(this);


        this.state = {
            response: '',
        }
    }

    handleChange(e) {
      this.setState({...this.state, [e.target.name]: e.target.value});
  }

  submitResponse(review){

      let token = localStorage.getItem('token');
      const options = {
          headers: {
              'Authorization': 'Bearer ' + token,
              'Content-Type': 'application/json',
          },
      };

      var d = Date(Date.now());
      var time = d.substring(15,25);

      var obj = { ad_id: this.props.id , id :review.id,
                  title: this.state.title,response: this.state.response,rating: this.state.submited_rating
      }

      console.log('obj');
      console.log(obj);

      axios.post(`${serviceConfig.baseURL}/api/review/submit-response`,obj, options).then(
          (response) => {
            
            window.location.reload();
          },
          (response) => { console.log('error') }
  );

  }


    renderAdCards() {

      console.log(this.props);

        return this.props.reviews.map((review, index) => {

            return (
                <Card key={review.id} style={{marginBottom:'15px'}} >

                    <Card.Body>

                        <Card.Title style={{textAlign:"left"}}> " {review.title}  " </Card.Title>

                        <Card.Text style={{padding:'3px'}} name={review.id} >

                        <ReactStars
                                count={5}
                                size={24}
                                half={true}
                                emptyIcon={<i className="far fa-star"></i>}
                                halfIcon={<i className="fa fa-star-half-alt"></i>}
                                fullIcon={<i className="fa fa-star"></i>}
                                color2={"#ffd700"}
                                edit={false}
                                value={review.rating}


                            />

                            <br/>

                                <img src={clock} style={{height:'30px',width:'30px',marginTop:'-1%',padding:'7px'}}></img>
                                &nbsp; {review.time.substring(11,16)}
                               <br/>
                               <img src={user} style={{height:'30px',width:'30px',marginTop:'-1%',padding:'7px'}}></img>
                               &nbsp;
                               {review.creator}
                               <br/>
                               <div style={{marginTop:'5px'}}>

                                   <img src={chat} style={{height:'30px',width:'30px',marginTop:'-1%',padding:'7px'}}></img>
                                   &nbsp;
                                {review.content}
                                </div>
                                {
                                this.props.permissons.includes('ROLE_AGENT') && this.props.isMy === true && review.response == null &&
                                <div style={{padding:'10px'}}>

                              <Card>
                                <Card.Title style={{marginTop:'10px',marginLeft:'20px',padding:'7px'}}>

                                <img src={response} style={{height:'50px',width:'50px',marginTop:'-1%',padding:'7px'}}></img>
                                &nbsp;

                                Leave a response</Card.Title>
                                <Card.Body>

                                    <input onChange={this.handleChange} name="response" placeholder="Enter your review here" type="text" style={{width:'80%',height:'150px'}}></input>
                                    <br/>
                                    <Button onClick={this.submitResponse.bind(this,review)} variant="outline-dark" style={{marginTop:'10px'}}>Submit</Button>
                            </Card.Body>
                        </Card>
                                </div>
                              }

                              {
                               review.response !== "" && review.response !== null &&
                              <div style={{padding:'10px'}}>
                                <Card>
                                <Card.Body>
                                  <img src={response} style={{height:'50px',width:'50px',marginTop:'-1%',padding:'7px'}}></img>
                                "{review.response}" by {this.props.creator}
                                </Card.Body>
                                </Card>
                              </div>
                            }
                        </Card.Text>
                    </Card.Body>
                </Card>
            )
        })
    }

    render() {

        return (
            <div>
                {this.renderAdCards()}
            </div>
        )

    }



}

export default RenderReviews
