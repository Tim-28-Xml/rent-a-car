import React from 'react'
import {Route, withRouter, Switch } from "react-router-dom";
import LoginPage from './components/LoginPage';
import RegisterPageAgent from './components/RegisterPageAgent';
import Header from './components/Header'
import HomePage from './components/HomePage'

class Routes extends React.Component {

    constructor(props){
        super(props);
    }

    render(){
        return (
            <Switch>
                <Route path='/' render={props =>
                    <div>
                        <Header />
                        <HomePage />
                    </div>
                    } />
            </Switch>
        );
    }
}

export default withRouter(Routes);