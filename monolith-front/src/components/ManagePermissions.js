import React from 'react';
import { Form, Button, FormGroup, FormControl, ControlLabel,Col, Modal } from "react-bootstrap";
import {serviceConfig} from '../appSettings.js'
import axios from 'axios'

class ManagePermissions extends React.Component{
    constructor(props){
        super(props);

        this.handleShow = this.handleShow.bind(this);
        this.handleClose = this.handleClose.bind(this);
        this.renderTable = this.renderTable.bind(this);
        this.renderTableBlocked = this.renderTableBlocked.bind(this);

        this.state = {
            show: false,
            permissions: []             
        }
    }

    componentDidMount(){
        this.getPermissions();
    }

    getPermissions(){
        let token = localStorage.getItem('token');
        var username = this.props.content;

        const options = {
            headers: { 'Authorization': 'Bearer ' + token}
        };
        
        axios.get(`${serviceConfig.baseURL}/users/enduser/permissions/${username}`,options).then(
            (resp) => { 

                this.setState({
                    permissions: resp.data
                })
                
             },
            (resp) => { alert('error') }
        );
    }

    removePermission(perm){
        let token = localStorage.getItem('token');
        const options = {
            headers: { 'Authorization': 'Bearer ' + token}
        };
        
        var username = this.props.content;
        console.log(perm)
        axios.get(`${serviceConfig.baseURL}/users/remove/permission/${username}/${perm}`,options).then(
            (resp) => { 

                this.setState({
                    permissions: resp.data
                })


             },
            (resp) => { alert('error') }
        );
    }

    addPermission(perm){
        let token = localStorage.getItem('token');
        const options = {
            headers: { 'Authorization': 'Bearer ' + token}
        };
        
        var username = this.props.content;
        console.log(perm)
        axios.get(`${serviceConfig.baseURL}/users/add/permission/${username}/${perm}`,options).then(
            (resp) => { 

                this.setState({
                    permissions: resp.data
                })

             },
            (resp) => { alert('error') }
        );
    }
    



    renderTable(){

        if(this.state.permissions != null) {
        return this.state.permissions.map((perm, index) => {
            
            return (
                <tr key={perm}>
                    <td>{perm}</td>
                    <td><Button onClick={this.removePermission.bind(this, perm)} style={{float: 'right'}} variant="outline-danger">Remove</Button></td>
                </tr>
            )
        })
    } else {
        return(
            <td></td>
        )
    }
    }

    renderTableBlocked(){
        return(
            <div>
                <h4 className="blockedPermTitle">Blocked permissions:</h4>
                {
                    !this.state.permissions.includes("ORDER") &&
                    <tr>
                        <td><Button onClick={this.addPermission.bind(this, "ORDER")} style={{float: 'right'}} variant="outline-success">Allow ordering</Button></td>
                    </tr>
                }
                {
                    !this.state.permissions.includes("CREATE_AD") &&
                    <tr>
                        <td><Button onClick={this.addPermission.bind(this, "CREATE_AD")} style={{float: 'right'}} variant="outline-success">Allow creating ads</Button></td>
                    </tr>
                }
                {
                    !this.state.permissions.includes("CREATE_REVIEW") &&
                    <tr>
                        <td><Button onClick={this.addPermission.bind(this, "CREATE_REVIEW")} style={{float: 'right'}} variant="outline-success">Allow creating reviews</Button></td>
                    </tr>
                }
                {
                    !this.state.permissions.includes("USE_CART") &&
                    <tr>
                        <td><Button onClick={this.addPermission.bind(this, "USE_CART")} style={{float: 'right'}} variant="outline-success">Allow using shopping cart</Button></td>
                    </tr>
                }
            </div>
        )
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
                <Button variant="outline-info" onClick={this.handleShow}>
                    Manage Permissions
                </Button>
                <Modal
                    show={this.state.show}
                    onHide={this.handleClose}
                    aria-labelledby="contained-modal-title-vcenter"
                    centered = "true"
                >
                <Modal.Header style={{background: "rgb(234,241,248)"}}>
                    <h2 className="regAtitle">Manage Permissions</h2>
                </Modal.Header>
                <Modal.Body style={{background: "rgb(234,241,248)", padding: '1% 8%'}}>
                    <h2 style={{color: "rgb(85, 93, 95)", textAlign:'center'}}>{this.props.content}</h2>

                    <table className="table table-hover table-mc-light-blue">

                                <tbody>
                                    {this.renderTable()}
                                    {this.renderTableBlocked()}
                                </tbody>
                            </table>
                </Modal.Body>
                </Modal>
            </div>

        )
    }

}

export default ManagePermissions;