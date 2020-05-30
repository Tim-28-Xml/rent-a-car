import React from 'react';
import { Button, Card, Modal } from "react-bootstrap"
import { serviceConfig } from '../appSettings.js'
import axios from 'axios'
import Swal from 'sweetalert2';
import withReactContent from 'sweetalert2-react-content';
import megaphoneicon from '../icons/megaphone.svg'
import '../css/CreateNewAd.css'
import Calendar from './Calendar.js';

const AdCreationAlert = withReactContent(Swal)
class CreateNewAd extends React.Component {

    constructor(props) {
        super(props);
        this.handleShow = this.handleShow.bind(this);
        this.handleClose = this.handleClose.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.handleSelect = this.handleSelect.bind(this);
        this.createNewAd = this.createNewAd.bind(this);

        this.state = {
            show: false,
            brand: '',
            model: '',
            fuel: '',
            transmission: '',
            carClass: '',
            carClassList: [],
            transmissionList: [],
            fuelList: [],
            startDate: null,
            endDate: null
        }
    }

    componentDidMount() {
        let token = localStorage.getItem('token');
        const options = {
            headers: { 'Authorization': 'Bearer ' + token }
        };


        axios.get("http://localhost:8081/api/codebook/fuel-types", options).then(
            (resp) => { this.setState({ fuelList: resp.data }) },
            (resp) => this.onErrorHandler(resp),
        );

        axios.get('http://localhost:8082/api/codebook/transmission-types', options).then(
            (resp) => { this.setState({ transmissionList: resp.data }) },
            (resp) => this.onErrorHandler(resp),
        );

        axios.get('http://localhost:8082/api/codebook/car-classes', options).then(
            (resp) => { this.setState({ carClassList: resp.data }) },
            (resp) => this.onErrorHandler(resp),
        );
    }

    createNewAd(event) {
        event.preventDefault();

        let token = localStorage.getItem('token');
        const options = {
            headers: { 'Authorization': 'Bearer ' + token }
        };

        axios.post("http://localhost:8082/api/ad/save", this.state, options).then(
            (resp) => this.onSuccessHandler(resp),
            (resp) => this.onErrorHandler(resp)
        );
    }

    onErrorHandler(resp) {
        AdCreationAlert.fire({
            title: "Error occured",
            text: '',
            type: "error",
            button: true
        });

    }

    onSuccessHandler(resp) {
        AdCreationAlert.fire({
            title: "Ad successfully created",
            text: "",
            type: "success",
        });

        this.setState({ redirect: this.state.redirect === false });
        window.location.reload();
        this.handleClose();
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

    handleSelect(e) {
        this.setState({ ...this.state, [e.target.name]: e.target.value });
        console.log(this.state);
    }

    render() {
        if (this.props.role === 'ROLE_USER') {
            return (
                <div>
                    <Card className="adCard" >
                        <Card.Body>
                            <Card.Title>Create a new ad <img className="carMegaphoneIcon" src={megaphoneicon}></img> </Card.Title>
                            <Card.Text style={{ color: 'rgb(110,120,130)' }}>
                                You can click here and create your new ad so others can rent your car.
                            </Card.Text>
                            <button className="createAdBtn" onClick={this.handleShow}>New Ad</button>
                        </Card.Body>
                    </Card>
                    <Modal
                        show={this.state.show}
                        onHide={this.handleClose}
                        size="lg"
                        aria-labelledby="contained-modal-title-vcenter"
                        centered="true"
                        className="modalAd"
                    >
                        <Modal.Header closeButton>
                            <Modal.Title style={{ color: 'rgb(110,120,130)' }} id="contained-modal-title-vcenter">
                                Enter information about your ad
                        </Modal.Title>
                        </Modal.Header>
                        <Modal.Body>
                            <form onSubmit={this.createNewAd} id="createAdForm">
                                <div className="form-group">
                                    <br />  
                                    <label htmlFor="brand">Brand</label>
                                    <select name="brand" className="selectValue" defaultValue="None" onChange={this.handleSelect}>
                                        <option value="None">None</option>
                                        {this.state.transmissionList}
                                    </select>
                                    <br />
                                    <br />
                                    <label htmlFor="model">Model</label>
                                    <select name="model" className="selectValue" defaultValue="None" onChange={this.handleSelect}>
                                        <option value="None">None</option>
                                        {this.state.transmissionList}
                                    </select>
                                    <br />
                                    <br />
                                    <label htmlFor="fuel">Fuel type</label>
                                    <select name="fuel" className="selectValue" defaultValue="None" onChange={this.handleSelect}>
                                        <option value="None">None</option>
                                        {this.state.fuelList}
                                    </select>
                                    <br />
                                    <br />
                                    <label htmlFor="transmission">Transmission type</label>
                                    <select name="transmission" className="selectValue" defaultValue="None" onChange={this.handleSelect}>
                                        <option value="None">None</option>
                                        {this.state.transmissionList}
                                    </select>
                                    <br />
                                    <br />
                                    <label htmlFor="carClass">Car class</label>
                                    <select name="carClass" className="selectValue" defaultValue="None" onChange={this.handleSelect}>
                                        <option value="None">None</option>
                                        {this.state.carClassList}
                                    </select>
                                    <br />
                                    <br />
                                    <Calendar></Calendar>
                                </div>
                                <hr />
                                <button type="submit" className="submitAd">Create</button>
                                <button className="closeModal" onClick={this.handleClose}>Close</button>
                                <br/>
                                <br/>
                            </form>
                        </Modal.Body>
                    </Modal>


                </div>
            )
        } else {
            return (
                <div>
                    <h1>no user</h1>



                </div>
            )
        }

    }

}

export default CreateNewAd;