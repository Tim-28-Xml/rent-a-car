import React from 'react'
import {Route, withRouter, Switch } from "react-router-dom";
import LoginPage from './components/LoginPage';
import RegisterPageAgent from './components/RegisterPageAgent';
import Header from './components/Header'
import HomePage from './components/HomePage'
import AdminProfile from './components/AdminProfile'
import Codebook from './components/Codebook'
import ActivatedAccount from './components/ActivatedAccount'
import SingleAd from './components/SingleAd'
import CreateNewAd from './components/CreateNewAd';

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
                <Route exact path='/create-ad' render={props =>
                    <div>
                        <Header />
                        <CreateNewAd />
                    </div>
                    } />
                <Route path='/ad/:id' component = {SingleAd}/>

            </Switch>
        );
    }
}

export default withRouter(Routes);