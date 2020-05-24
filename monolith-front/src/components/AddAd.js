import React from 'react';
import ReactDOM from 'react-dom';
import { Modal, Button } from "react-bootstrap";
import axios from 'axios'
import '../css/AddAd.css'


class AddAd extends React.Component {

    constructor(props) {
        super(props);

        this.handleShow = this.handleShow.bind(this);
        this.handleClose = this.handleClose.bind(this);
        this.handleChange = this.handleChange.bind(this);

        this.newAd = this.newAd.bind(this);

        this.state = {
            show: false,
            name: '',
            brand: '',
            model: '',
            fuel: '',
            transmission: '',
            carClass: '',
            km: 0,
            kmLimit: 0,
        };
    }

    newAd(event) {
        event.preventDefault();
        console.log(this.state);

        if(isNaN(this.state.km)) {
           return alert("Please enter a number for Km Limit!");
        } else if(isNaN(this.state.kmLimit))
            return alert("Please enter a number for Km Limit!")

        var obj = {name:this.state.name, brand:this.state.brand, model:this.state.model, fuel: this.state.fuel, transmission: this.state.transmission, carClass: this.state.carClass, km: this.state.km, kmLimit: this.state.kmLimit}
        
        var array = [this.state.name, this.state.model, this.state.brand, this.state.carClass, this.state.fuel,this.state.transmission];
        let malicious = 0;
        let whitespaces = 0;

        array.forEach(element => {
            if(element.includes("<") || element.includes(">"))
            {
                malicious++;
            }

            if (!element.replace(/\s/g, '').length)
                whitespaces++;
            
        });

        if(malicious > 0) {
            return alert("Sorry..");
        }

        if(whitespaces > 0) {
            return alert("Sorry but some inputs contain only white spaces!");
        }

        if(this.state.km.valueOf() > this.state.kmLimit.valueOf()) {
            return alert("Km cannot be greater than km limit!");
        }
        

         axios.post("http://localhost:8082/api/ads/save", obj).then(
             (resp) => this.onSuccessHandler(resp),
             (resp) => this.onErrorHandler(resp)
         );
    }

    onErrorHandler(resp) {
        alert("Error!");
    }

    onSuccessHandler(resp) {
        alert("Ok");
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
        this.setState({...this.state, [e.target.name]: e.target.value});
    }

    render() {
        return (
            <div>
                <Button id="newAddBtn" onClick={this.handleShow}>
                    New Add
                </Button>
                <Modal
                    show={this.state.show}
                    onHide={this.handleClose}
                    size="lg"
                    aria-labelledby="contained-modal-title-vcenter"
                    centered = "true"
                >
                    <Modal.Header closeButton>
                        <Modal.Title id="contained-modal-title-vcenter">
                            New Add
                        </Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <form onSubmit={this.newAd} id="newAdForm">
                            <div className="form-group">
                                <label htmlFor="name">Name</label>
                                <input type="text"
                                    className="form-control form-control-sm"
                                    id="name"
                                    name="name"
                                    onChange={this.handleChange}
                                    placeholder="Enter ad name"
                                    required
                                />
                                <br/>
                                <label htmlFor="model">Model of a car</label>
                                <input type="text"
                                    className="form-control form-control-sm"
                                    id="model"
                                    name="model"
                                    onChange={this.handleChange}
                                    placeholder="Enter model of a car"
                                    required
                                />
                                <br/>
                                <label htmlFor="brand">Brand of a car</label>
                                <input type="brand"
                                    className="form-control form-control-sm"
                                    id="brand"
                                    name="brand"
                                    onChange={this.handleChange}
                                    placeholder="Enter brand of a car"
                                    required
                                />
                                <br/>
                                <label htmlFor="fuel">Fuel type</label>
                                <input type="text"
                                    className="form-control form-control-sm"
                                    id="fuel"
                                    name="fuel"
                                    onChange={this.handleChange}
                                    placeholder="Enter fuel type"
                                    required
                                />
                                <br/>
                                <label htmlFor="km">Km</label>
                                <input type="text"
                                    className="form-control form-control-sm"
                                    id="km"
                                    name="km"
                                    onChange={this.handleChange}
                                    placeholder="Enter km"
                                    required
                                />
                                <br/>
                                <label htmlFor="kmLimit">Km Limit</label>
                                <input type="text"
                                    className="form-control form-control-sm"
                                    id="kmLimit"
                                    name="kmLimit"
                                    onChange={this.handleChange}
                                    placeholder="Enter km limit"
                                    required
                                />
                                <br/>
                                <br/>
                                <label htmlFor="carClass">Car class</label>
                                <input type="text"
                                    className="form-control form-control-sm"
                                    id="carClass"
                                    name="carClass"
                                    onChange={this.handleChange}
                                    placeholder="Enter car class"
                                    required
                                />
                                <br/>
                                <label htmlFor="transmission">Transmission</label>
                                <input type="text"
                                    className="form-control form-control-sm"
                                    id="transmission"
                                    name="transmission"
                                    onChange={this.handleChange}
                                    placeholder="Enter transmission"
                                    required
                                />
                                <br/>
                            </div>
                            <hr/>
                            <Button type="submit" className="btnCreateAd">Create</Button>
                            <Button className="btnCloseAd" onClick={this.handleClose}>Close</Button>
                        </form>
                    </Modal.Body>
                </Modal>
            </div>

        );
    }
}

export default AddAd;