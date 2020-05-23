import React from 'react';
import RegisterPageAgent from './RegisterPageAgent'
import {Button} from "react-bootstrap"
import AddAd from './AddAd'


class HomePage extends React.Component{
    constructor(props){
        super(props);

        this.state = {

        }
    }

    render(){
        return(
            <div>
                <h1 style={{color: 'rgb(110,120,130)', textAlign: 'center', margin: "2% 0 0 0"}}>Welcome to Rent a Car</h1>
                <AddAd/>

            </div>
        )
    }
}

export default HomePage;