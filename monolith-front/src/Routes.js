import React from 'react'
import {Route, withRouter, Switch } from "react-router-dom";
import LoginPage from './components/LoginPage';
import RegisterPageAgent from './components/RegisterPageAgent';
import Header from './components/Header'
import HomePage from './components/HomePage'
import AdminProfile from './components/AdminProfile'
import Codebook from './components/Codebook'
import ActivatedAccount from './components/ActivatedAccount'

class Routes extends React.Component {

    constructor(props){
        super(props);
    }

    render(){
        return (
            <Switch>
                <Route exact path='/' render={props =>
                    <div>
                        <Header />
                        <HomePage />
                    </div>
                    } />

                <Route path='/profile/admin' render={props =>
                    <div>
                        <Header />
                        <AdminProfile />
                    </div>
                    } />
                <Route path='/codebook' render={props =>
                    <div>
                        <Header />
                        <Codebook />
                    </div>
                    } />
                <Route exact path='/activated-account' render={props =>
                    <div>
                        <Header />
                        <ActivatedAccount />
                    </div>
                    } />
            </Switch>
        );
    }
}

export default withRouter(Routes);