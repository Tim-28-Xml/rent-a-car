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
import PhysicalRent from './components/PhysicalRent';
import CreatePricelist from './components/CreatePricelist';
import MyRentedCars from './components/MyRentedCars';
import MyStatistics from './components/MyStatistics';

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
                <Route path='/physicalrent' render={props =>
                    <div>
                        <Header />
                        <PhysicalRent />
                    </div>
                    } />
                    <Route path='/createpricelist' render={props =>
                    <div>
                        <Header />
                        <CreatePricelist />
                    </div>
                    } />
                    <Route path='/rented-cars' render={props =>
                    <div>
                        <Header />
                        <MyRentedCars/>
                    </div>
                    } />

                <Route path='/statistics' render={props =>
                    <div>
                        <Header />
                        <MyStatistics />
                    </div>
                } />


            </Switch>
        );
    }
}

export default withRouter(Routes);
