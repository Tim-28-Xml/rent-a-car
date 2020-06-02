import React from 'react';
import { Button, Card, Modal } from "react-bootstrap"
import { serviceConfig } from '../appSettings.js'
import axios from 'axios'
import Swal from 'sweetalert2';
import withReactContent from 'sweetalert2-react-content';
import megaphoneicon from '../icons/megaphone.svg'
import moreicon from '../icons/more.svg'
import Select from 'react-select';
import DatePicker from 'react-datepicker';
import "react-datepicker/dist/react-datepicker.css";
import '../css/CreateNewAd.css'

const moment = require('moment');
const AdCreationAlert = withReactContent(Swal)
class CreateNewAd extends React.Component {

    constructor(props) {
        super(props);
        this.handleShow = this.handleShow.bind(this);
        this.handleClose = this.handleClose.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.handleSelect = this.handleSelect.bind(this);
        this.createNewAd = this.createNewAd.bind(this);
        this.handleSelectBrand = this.handleSelectBrand.bind(this);
        this.handleSelectModel = this.handleSelectModel.bind(this);
        this.handleSelectTransmission = this.handleSelectTransmission.bind(this);
        this.handleSelectFuel = this.handleSelectFuel.bind(this);
        this.handleSelectCarClass = this.handleSelectCarClass.bind(this);
        this.handleChangeStartDate = this.handleChangeStartDate.bind(this);
        this.handleChangeEndDate = this.handleChangeEndDate.bind(this);
        this.handleChangeChecked = this.handleChangeChecked.bind(this);
        this.fileSelectedHandler = this.fileSelectedHandler.bind(this);


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
            brandList: [],
            modelList: [],
            startDate: new Date(),
            endDate: new Date(),
            dateStringStart: '',
            dateStringEnd: '',
            inputValueStart: '',
            inputValueEnd: '',
            minEndDate: new Date(),
            km: 0,
            kmLimit: 0,
            cdw: 'off',
            collision: false,
            childSeats: '',
            files: [],
        }
    }

    componentDidMount() {
        let token = localStorage.getItem('token');
        const options = {
            headers: { 'Authorization': 'Bearer ' + token }
        };

        this.state.minEndDate = this.addDays(this.state.startDate, 1);
        this.state.endDate = this.state.minEndDate;

        axios.get("http://localhost:8082/codebook/fuel-types", options).then(
            (resp) => { this.setState({ fuelList: resp.data }) },
            (resp) => this.onErrorHandler(resp),
        );

        axios.get('http://localhost:8082/codebook/transmission-types', options).then(
            (resp) => { this.setState({ transmissionList: resp.data }) },
            (resp) => this.onErrorHandler(resp),
        );

        axios.get('http://localhost:8082/codebook/car-classes', options).then(
            (resp) => { this.setState({ carClassList: resp.data }) },
            (resp) => this.onErrorHandler(resp),
        );

        axios.get('http://localhost:8082/codebook/brands', options).then(
            (resp) => { this.setState({ brandList: resp.data }) },
            (resp) => this.onErrorHandler(resp),
        );
    }

    getModelsFromBrand(brand) {
        let token = localStorage.getItem('token');
        const options = {
            headers: { 'Authorization': 'Bearer ' + token }
        };

        axios.get(`http://localhost:8082/codebook/models-from-brand/${brand}`, options).then(
            (resp) => { this.setState({ modelList: resp.data }) },
            (resp) => this.onErrorHandler(resp),
        );
    }

    createNewAd(event) {
        event.preventDefault();

        let token = localStorage.getItem('token');
        const options = {
            headers: { 'Authorization': 'Bearer ' + token }
        };

        if (isNaN(this.state.km)) {
            return alert("Please enter a number for Km!");
        } else if (isNaN(this.state.kmLimit))
            return alert("Please enter a number for Km Limit!")
        else if (isNaN(this.state.childSeats))
            return alert("Please enter a number for child seats!");

        this.state.endDate = moment(this.state.dateStringEnd).format('YYYY-MM-DD');

        axios.post("http://localhost:8082/ads/save", this.state, options).then(
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

    handleSelectBrand(e) {
        console.log(e);
        this.setState({ brand: e.value });
        this.getModelsFromBrand(e.value);
        console.log(this.state);
    }

    handleSelectModel(e) {
        this.setState({ model: e.value });
    }

    handleSelectCarClass(e) {
        this.setState({ carClass: e.value });
    }

    handleSelectTransmission(e) {
        this.setState({ transmission: e.value });
    }

    handleSelectFuel(e) {
        this.setState({ fuel: e.value });
    }

    handleChangeStartDate = date => {
        var dateString = date.toISOString().substring(0, 10);
        console.log(dateString);

        this.setState({
            startDate: date,
            dateStringStart: dateString,
            minEndDate: this.addDays(this.state.startDate, 1)
        });
    }

    handleChangeEndDate = date => {
        var dateString = date.toISOString().substring(0, 10);
        console.log(dateString);

        this.setState({
            endDate: date,
            dateStringEnd: dateString,

        });
    }

    handleChangeChecked(e) {

        if (document.getElementsByName(e.target.name)[0].checked === true) {
            this.setState({ ...this.state, [e.target.name]: e.target.value });
            this.setState({ collision: true });
        } else {
            document.getElementsByName(e.target.name)[0].checked = false;
            this.setState({ ...this.state, [e.target.name]: "off" });
            this.setState({ collision: false });
        }

    }

    fileSelectedHandler = (e) => {
        var imageNames = [];
        var array = e.target.files;
        var i;

        for (i = 0; i < array.length; i++) {

            var file = array[i];
            var reader = new FileReader();

            reader.onload = (em) => {
                imageNames.push(em.target.result);
                console.log(em.target.result)
            }

            reader.readAsDataURL(e.target.files[i]);
        }

        this.setState({files: imageNames});
    }

    addDays(date, days) {
        var result = new Date(date);
        result.setDate(result.getDate() + days);
        return result;
    }


    render() {

        console.log(this.state);
        this.state.minEndDate = this.addDays(this.state.startDate, 1);
        this.state.endDate = this.state.minEndDate;

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
                                    <label htmlFor="brand">Brand</label>
                                    <Select
                                        className="selectoptions"
                                        style={{ width: "40px", marginBottom: "10px" }}
                                        onChange={this.handleSelectBrand}
                                        value={this.state.brand.value}
                                        options={
                                            this.state.brandList.map((type, i) => {
                                                return { value: type.brand, label: type.brand };
                                            })
                                        }
                                    />
                                    <br />
                                    <br />
                                    <label htmlFor="model">Model</label>
                                    <Select
                                        className="selectoptions"
                                        style={{ width: "70%", marginBottom: "10px" }}
                                        onChange={this.handleSelectModel}
                                        value={this.state.model.value}
                                        options={
                                            this.state.modelList.map((type, i) => {
                                                return { value: type, label: type };
                                            })
                                        }
                                    />
                                    <br />
                                    <br />
                                    <label htmlFor="fuel">Fuel type</label>
                                    <Select
                                        className="selectoptions"
                                        style={{ width: "70%", marginBottom: "10px" }}
                                        onChange={this.handleSelectFuel}
                                        value={this.state.fuel.value}
                                        options={

                                            this.state.fuelList.map((type, i) => {
                                                return { value: type, label: type };
                                            })
                                        }
                                    />
                                    <br />
                                    <br />
                                    <label htmlFor="transmission">Transmission type</label>
                                    <Select
                                        className="selectoptions"
                                        style={{ width: "70%", marginBottom: "10px" }}
                                        onChange={this.handleSelectTransmission}
                                        value={this.state.transmission.value}
                                        options={

                                            this.state.transmissionList.map((type, i) => {
                                                return { value: type, label: type };
                                            })
                                        }
                                    />
                                    <br />
                                    <br />
                                    <label htmlFor="carClass">Car class</label>
                                    <Select
                                        className="selectoptions"
                                        style={{ width: "70%", marginBottom: "10px" }}
                                        onChange={this.handleSelectCarClass}
                                        value={this.state.carClass.value}
                                        options={

                                            this.state.carClassList.map((type, i) => {
                                                return { value: type, label: type };
                                            })
                                        }
                                    />
                                    <br />
                                    <div className="startDate">
                                        <label htmlFor="startDate">Start date</label>
                                        <br />
                                        <DatePicker
                                            selected={this.state.startDate}
                                            onChange={this.handleChangeStartDate}
                                            value={this.state.dateStringStart.value}
                                            name="startDate"
                                            minDate={moment().toDate()}
                                        />
                                    </div>
                                    <br />
                                    <div className="endDate">
                                        <label htmlFor="endDate">End date</label>
                                        <br />
                                        <DatePicker
                                            selected={this.state.endDate}
                                            onChange={this.handleChangeEndDate}
                                            value={this.state.dateStringEnd.value}
                                            name="endDate"
                                            minDate={this.state.minEndDate}
                                        />
                                    </div>
                                    <br />
                                    <label htmlFor="km">Km</label>
                                    <input type="text"
                                        className="form-control form-control-sm"
                                        id="km"
                                        name="km"
                                        onChange={this.handleChange}
                                        placeholder="Enter km"
                                        required
                                    />
                                    <br />
                                    <label htmlFor="kmLimit">Km Limit</label>
                                    <input type="text"
                                        className="form-control form-control-sm"
                                        id="kmLimit"
                                        name="kmLimit"
                                        onChange={this.handleChange}
                                        placeholder="Enter km limit"
                                        required
                                    />
                                    <br />
                                    <label htmlFor="collision">Collision damage waiver</label>
                                    <input type="checkbox" id="cdw" name="cdw" onChange={this.handleChangeChecked} />
                                    <br />
                                    <label htmlFor="childSeats">Child seats</label>
                                    <input type="text"
                                        className="form-control form-control-sm"
                                        id="childSeats"
                                        name="childSeats"
                                        onChange={this.handleChange}
                                        placeholder="Enter number of childSeats"
                                        required
                                    />
                                    <br />
                                    <label htmlFor="images">Images</label>
                                    <input type="file" multiple onChange={this.fileSelectedHandler} name="images" class="images" />
                                </div>
                                <hr />
                                <button type="submit" className="submitAd">Create</button>
                                <button className="closeModal" onClick={this.handleClose}>Close</button>
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