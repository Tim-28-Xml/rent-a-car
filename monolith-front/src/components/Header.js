import React from 'react';
import RegisterPageAgent from './RegisterPageAgent'
import {Button} from "react-bootstrap"
import '../css/Header.css'
import caricon from '../icons/vehicle.svg'
import RegisterPageUser from './RegisterPageUser'
import LoginPage from './LoginPage'

class Header extends React.Component{
    constructor(props){
        super(props);

        this.state = {

        }
    }

    render(){
        return(
            <div style={{background: "rgb(112,128,144)", display: "flex", height: "45px"}} >
                <h2 className="headerTitle">Rent a Car</h2>
                <img src={caricon} style={{height:'40px', width: 'auto', margin: "0 0 0 40%"}} alt='Unavailable icon' />

                <button className="btnHeaderHome">Home</button>
                <label className="borderLabel">|</label>
                <RegisterPageAgent />
                <label className="borderLabel">|</label>
                <RegisterPageUser />

                <LoginPage />

            </div>
        )
    }
}

export default Header;