import React from 'react';
import { Form, Button, FormGroup, FormControl, ControlLabel,Col, Modal } from "react-bootstrap";
import '../css/LoginPage.css'

class LoginPage extends React.Component{
    constructor(props){
        super(props);

        this.handleChange = this.handleChange.bind(this);
        this.Login = this.Login.bind(this);
        this.handleShow = this.handleShow.bind(this);
        this.handleClose = this.handleClose.bind(this);

        this.state = {
            userType: '',
            email: '',
            password: '',
            username: '',
        }
    }

    handleChange(e) {
        this.setState({...this.state, [e.target.name]: e.target.value});
    }

    Login() {

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
                <button className="modalBtnLogin" onClick={this.handleShow}>
                    Log in
                </button>
                <Modal
                    show={this.state.show}
                    onHide={this.handleClose}
                    aria-labelledby="contained-modal-title-vcenter"
                    centered = "true"
                >
                <Modal.Header style={{background: "rgb(234,241,248)"}}>
                    <h2 className="regAtitle">Log in</h2>
                </Modal.Header>

                <Modal.Body style={{background: "rgb(234,241,248)"}}>

                <Form className="formRLogin" onSubmit={this.Login}>

                    <Form.Group as={Col}>
                        <Form.Label>Email</Form.Label>
                        <Form.Control type="email" style={{background: "rgb(244, 245, 249)"}} placeholder="Enter email" id="email" name="email" onChange={this.handleChange} />
                    </Form.Group>

                    <Form.Group as={Col}>
                        <Form.Label>Password</Form.Label>
                        <Form.Control type="password" style={{background: "rgb(244, 245, 249)"}} placeholder="Password" id="password" name="password" onChange={this.handleChange} />
                    </Form.Group>

                    
                <Button variant="outline-secondary" style={{float: "right", margin: "2% 32% 1% 0", width: "15%"}} onClick={this.handleClose}>
                    Close
                </Button>

                <Button variant="outline-primary" type="submit" style={{ margin: "2% 1% 1% 32%"}}>
                    Log in
                </Button> 


                </Form>
                
                </Modal.Body>
                </Modal>
            </div>
        )
    }
}

export default LoginPage;