import React from 'react';
import { Button, Card, Modal, Badge } from "react-bootstrap"
import axios from 'axios'
import '../css/MyRentedCars.css'

class MyRentedCars extends React.Component {

    constructor(props) {
        super(props);


        this.handleShow = this.handleShow.bind(this);
        this.handleClose = this.handleClose.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.showHide = this.showHide.bind(this);
        this.writeReport = this.writeReport.bind(this);

        this.state = {
            ads: [],
            km: '',
            text: '',
            show: false,
            adId: 0,
            username: '',
            requestId: 0,

        }
    }

    handleClose() {
        this.setState({ show: false });
    }

    handleShow() {
        this.setState({ show: true });
    }

    handleChange(e) {
        this.setState({ ...this.state, [e.target.name]: e.target.value });
    }

    showHide() {
        var x = document.getElementById("writtenLabel");
        if (x.style.display === "none") {
            x.style.display = "block";
        } else {
            x.style.display = "none";
        }
    }

    writeReport(event) {

        event.preventDefault();

        let token = localStorage.getItem('token');

        const options = {
            headers: { 'Authorization': 'Bearer ' + token }
        };

        var obj = {km : this.state.km, text: this.state.text, adId: this.state.adId, username: this.state.username, requestId: this.state.requestId}

        this.showHide();
        //console.log(obj)

        axios.post(`http://localhost:8082/reports/save`, obj, options).then(
            (resp) => this.onSuccessHandler(resp),
            (resp) => { alert("error")
             }
        );
    }

    onSuccessHandler(resp) {
        alert("success")

       // console.log(resp.data);

        this.setState({ redirect: this.state.redirect === false });
        window.location.reload();
        this.handleClose();
    }


    componentDidMount() {
        let token = localStorage.getItem('token');
        const options = {
            headers: { 'Authorization': 'Bearer ' + token }
        };

        axios.get(`http://localhost:8082/reports/my-rented-ads`, options).then(
            (resp) => {
                console.log(resp.data)
                this.setState({
                    ads: resp.data
                })

            },
            (resp) => {
                alert("error")
            }
        );

    }

    renderAdCards() {
        return this.state.ads.map((ad, index) => {
            this.state.adId = ad.id;
            this.state.username = ad.username;
            this.state.requestId = ad.requestId;
            return (
                <Card key={ad.id} className="cardContainer">
                    <Card.Body className="cardBody">
                        <Card.Title className="cardTitle" >{ad.brand} {ad.model} <Badge id="writtenLabel">Written</Badge> </Card.Title>
                        <Card.Text className='cardText'>

                            fuel: {ad.fuel}
                            <br />
                                class: {ad.carClass}
                            <br />
                                transmission: {ad.transmission}
                        </Card.Text>
                        <Button onClick={this.handleShow}>Add a report</Button>
                        <Modal
                            show={this.state.show}
                            onHide={this.handleClose}
                            size="lg"
                            aria-labelledby="contained-modal-title-vcenter"
                            centered="true"
                        >
                            <Modal.Header closeButton>
                                <Modal.Title id="contained-modal-title-vcenter">
                                    Write a report
                                </Modal.Title>
                            </Modal.Header>
                            <Modal.Body>
                                <form onSubmit={this.writeReport} id="creatorForm">
                                    <div className="form-group">
                                        <label htmlFor="km">Passed km</label>
                                        <input type="number"
                                            className="form-control form-control-sm"
                                            id="km"
                                            name="km"
                                            onChange={this.handleChange}
                                            placeholder="Enter passed km"
                                            required
                                        />
                                        <br />
                                        <label htmlFor="text">Additional Text</label>
                                        <input type="text"
                                            className="form-control form-control-sm"
                                            id="text"
                                            name="text"
                                            onChange={this.handleChange}
                                            placeholder="Write additional text here"
                                            required
                                        />
                                    </div>
                                    <hr />
                                    <Button type="submit" variant="info" className="dugme1dr">Create</Button>
                                    <Button className="dugmad" variant="secondary" onClick={this.handleClose}>Close</Button>

                                </form>
                            </Modal.Body>
                        </Modal>
                    </Card.Body>
                </Card>
            )

        })
    }

    render() {
        //console.log(this.state)
        return (
            <div>
                <div className="renderMyAds">
                    {this.renderAdCards()}
                </div>
            </div>
        )
    }

} export default MyRentedCars;