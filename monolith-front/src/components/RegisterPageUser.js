import React, { Component } from 'react';
import { Form, Button, FormGroup, FormControl, ControlLabel,Col, Modal } from "react-bootstrap";
import '../css/RegisterPageAgent.css'


class RegisterPageUser extends React.Component{
    constructor(props){
        super(props);

        this.handleChange = this.handleChange.bind(this);
        this.handleChangePass = this.handleChangePass.bind(this);
        this.SendRegisterRequest = this.SendRegisterRequest.bind(this);
        this.handleShow = this.handleShow.bind(this);
        this.handleClose = this.handleClose.bind(this);

        this.state = {
            show: false,

            email: '',
            password: '',
            username: '',

            firstname: '',
            lastname: '',

            goodPass:'',


        }
    }

    handleChange(e) {
        this.setState({...this.state, [e.target.name]: e.target.value});
    }

    handleChangePass(event) {
        var pass = event.target.value;
        var reg = /^[A-Z]*$/;
        var test = reg.test(pass);
        if (test) {
           this.setState({password: pass});
           this.setState({goodPass: "Good"});
        }else{
            this.setState({goodPass: "Not good enough"});
        }        
   }


    SendRegisterRequest() {

    }

    handleClose() {
        this.setState({ show: false });
    }

    handleShow() {
        this.setState({ show: true });
    }

    render(){
        return(
            <div>

                <button className="modalBtnRegA" onClick={this.handleShow}>
                    User Registration
                </button>
                <Modal
                    show={this.state.show}
                    onHide={this.handleClose}
                    size="lg"
                    aria-labelledby="contained-modal-title-vcenter"
                    centered = "true"
                >
            <Modal.Header style={{background: "rgb(234,241,248)"}}>
                <h2 className="regAtitle">User registration</h2>
            </Modal.Header>

            <Modal.Body style={{background: "rgb(234,241,248)"}}>

            <Form className="formReg" onSubmit={this.SendRegisterRequest}>

                <Form.Group as={Col}>
                    <Form.Label className="labelRegA">First Name</Form.Label>
                    <Form.Control type="text" style={{background: "rgb(244, 245, 249)"}} placeholder="Enter first name" id="firstname" name="firstname" onChange={this.handleChange} />
                </Form.Group>

                <Form.Group as={Col}>
                    <Form.Label className="labelRegA">Last Name</Form.Label>
                    <Form.Control  type="text" style={{background: "rgb(244, 245, 249)"}} placeholder="Enter last name" id="lastname" name="lastname" onChange={this.handleChange} />
                </Form.Group>



                <Form.Group as={Col}>
                    <Form.Label className="labelRegA">Email</Form.Label>
                    <Form.Control type="email" style={{background: "rgb(244, 245, 249)"}} placeholder="Enter email" id="email" name="email" onChange={this.handleChange} />
                </Form.Group>

                
                <Form.Group as={Col}>
                    <Form.Label className="labelRegA">Username:</Form.Label>
                    <Form.Control type="email" style={{background: "rgb(244, 245, 249)"}} placeholder="Enter username" id="username" name="username" onChange={this.handleChange} />
                </Form.Group>

                <Form.Row >
                    <Form.Group as={Col} className="formRowRegL">
                        <Form.Label className="labelRegA">Password</Form.Label>
                        <Form.Control type="password" style={{background: "rgb(244, 245, 249)"}} placeholder="Password" id="password" name="password" onChange={this.handleChange} />
                        <legend className="legendPass">Password should contain 8 characters minimum, at least one number and a special character.</legend>
                    </Form.Group>

                    <Form.Group as={Col} className="formRowRegR">
                        <Form.Label className="labelRegA">Repeat password</Form.Label>
                        <Form.Control type="password" style={{background: "rgb(244, 245, 249)"}} placeholder="Repeat your password" id="repeatedPassword" name="repeatedPassword" onChange={this.handleChange} />
                    </Form.Group>
                </Form.Row>

                <Button variant="outline-secondary" style={{float: "right", margin: "2% 2% 1% 0", width: "10%"}} onClick={this.handleClose}>
                    Close
                </Button>

                <Button variant="outline-primary" type="submit" style={{float: "right", margin: "2% 1% 1% 0"}}>
                    Register
                </Button> 
                

                
            </Form>
            </Modal.Body>
            </Modal>
        </div>

        )
    }
}

export default RegisterPageUser;