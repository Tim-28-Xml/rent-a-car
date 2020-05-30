import React from 'react';
import {serviceConfig} from '../appSettings.js'
import axios from 'axios'
import '../css/AdminProfile.css'
import {BootstrapTable, TableHeaderColumn } from 'react-bootstrap-table'
import { Button, Table, Tab, Tabs } from 'react-bootstrap';
import ManagePermissions from './ManagePermissions'

class AdminProfile extends React.Component{
    constructor(props){
        super(props);

        this.renderTable = this.renderTable.bind(this);
        this.renderTableAgents = this.renderTableAgents.bind(this);
        this.renderTableRequests = this.renderTableRequests.bind(this);

        this.state = {
            endUsers: [],
            agents: [],
            requests: [],

            
        }
    }

    componentDidMount(){
        this.getEndUsers();
        this.getAgents();
        this.getRequests();
    }

    getEndUsers(){
        let token = localStorage.getItem('token');
        const options = {
            headers: { 'Authorization': 'Bearer ' + token}
        };
        
        axios.get(`${serviceConfig.baseURL}/users/endusers`,options).then(
            (resp) => { 

                this.setState({
                    endUsers: resp.data
                })

             },
            (resp) => { alert('error') }
        );
    }

    getAgents(){
        let token = localStorage.getItem('token');
        const options = {
            headers: { 'Authorization': 'Bearer ' + token}
        };
        
        axios.get(`${serviceConfig.baseURL}/users/agents`,options).then(
            (resp) => { 

                this.setState({
                    agents: resp.data
                })

             },
            (resp) => { alert('error') }
        );
    }

    getRequests(){
        let token = localStorage.getItem('token');
        const options = {
            headers: { 'Authorization': 'Bearer ' + token}
        };
        
        axios.get(`${serviceConfig.baseURL}/users/requests`,options).then(
            (resp) => { 

                this.setState({
                    requests: resp.data
                })

             },
            (resp) => { alert('error') }
        );
    }


    removeUser(username){
        let token = localStorage.getItem('token');
        const options = {
            headers: { 'Authorization': 'Bearer ' + token}
        };
        
        axios.get(`${serviceConfig.baseURL}/users/remove/${username}`,options).then(
            (resp) => { 
                
                window.location.reload();

             },
            (resp) => { alert('error') }
        );
    }

    acceptRequest(username){
        let token = localStorage.getItem('token');
        const options = {
            headers: { 'Authorization': 'Bearer ' + token}
        };
        console.log(username);
        axios.get(`${serviceConfig.baseURL}/users/requests/accept/${username}`,options).then(
            (resp) => { 

                window.location.reload();

             },
            (resp) => { alert('error') }
        );
    }

    declineRequest(username){
        let token = localStorage.getItem('token');
        const options = {
            headers: { 'Authorization': 'Bearer ' + token}
        };
        
        axios.get(`${serviceConfig.baseURL}/users/requests/decline/${username}`,options).then(
            (resp) => { 

                window.location.reload();

             },
            (resp) => { alert('error') }
        );
    }

    renderTable(){
        return this.state.endUsers.map((endUser, index) => {
            const { username, email, firstname, lastname} = endUser
    
            return (
                <tr key={username}>
                    <td>{username}</td>
                    <td>{email}</td>
                    <td>{firstname}</td>
                    <td>{lastname}</td>
                    <td><ManagePermissions content={username}/></td>
                    <td><Button variant="outline-danger" onClick={this.removeUser.bind(this, username)}>Remove</Button></td>
                </tr>
            )
        })
    }

    renderTableAgents(){
        return this.state.agents.map((agent, index) => {
            const { username, email, name, mbr, address} = agent
    
            return (
                <tr key={username}>
                    <td>{username}</td>
                    <td>{email}</td>
                    <td>{name}</td>
                    <td>{mbr}</td>
                    <td>{address}</td>
                    <td><ManagePermissions content={username}/></td>
                    <td><Button variant="outline-danger" onClick={this.removeUser.bind(this, username)}>Remove</Button></td>
                </tr>
            )
        })
    }

    renderTableRequests(){
        return this.state.requests.map((req, index) => {
            const { username, email, firstname, lastname} = req
    
            return (
                <tr key={username}>
                    <td>{username}</td>
                    <td>{email}</td>
                    <td>{firstname}</td>
                    <td>{lastname}</td>
                    <td><Button variant="outline-success" onClick={this.acceptRequest.bind(this, username)}>Accept</Button></td>
                    <td><Button variant="outline-danger" onClick={this.declineRequest.bind(this, username)}>Decline</Button></td>
                </tr>
            )
        })
    }

    render(){
        return(
            <div className="userTablesAdmin">
                
                
                <Tabs className="tabsAdmin" defaultActiveKey="requests" id="uncontrolled-tab-example">
                    <Tab eventKey="requests" title="Registration requests">
                    <div className="endUsersTable">
                
                        <div className="col-xs-9">              
                                <h2 className="tableTitle1users" >Registration requests</h2>
                                <table className="table table-hover table-mc-light-blue">

                                    <thead>
                                        <tr>
                                            <th>Username</th>
                                            <th>Email</th>
                                            <th>First name</th>
                                            <th>Last name</th>
                                            <th></th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        {this.renderTableRequests()}
                                    </tbody>
                                </table>
                        </div>
                </div>
                </Tab>

                    <Tab eventKey="users" title="End users">
                    <div className="endUsersTable">
                
                <div className="col-xs-9">              
                        <h2 className="tableTitle1users" >End users</h2>
                        <table className="table table-hover table-mc-light-blue">

                            <thead>
                                <tr>
                                    <th>Username</th>
                                    <th>Email</th>
                                    <th>First name</th>
                                    <th>Last name</th>
                                    <th></th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                {this.renderTable()}
                            </tbody>
                        </table>
                </div>
            </div>
                    </Tab>
                    <Tab eventKey="agents" title="Agents">
                    <div className="endUsersTable">
                <div className="col-xs-9">              
                            <h2 className="tableTitle1users" >Agents</h2>
                            <table className="table table-hover table-mc-light-blue">

                                <thead>
                                    <tr>
                                        <th>Username</th>
                                        <th>Email</th>
                                        <th>Name</th>
                                        <th>Business ID</th>
                                        <th>Adress</th>
                                        <th></th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {this.renderTableAgents()}
                                </tbody>
                            </table>
                    </div>
                    </div>
                    </Tab>

                </Tabs>

            </div>
        )
    }
}
export default AdminProfile;