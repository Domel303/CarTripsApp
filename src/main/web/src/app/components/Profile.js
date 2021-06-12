import React, {useEffect, useState} from 'react'
import {Link, useHistory} from 'react-router-dom'
import {Alert, Button, Container} from 'reactstrap'

import AuthenticationService from '../services/AuthenticationService'
import BackendService from "../services/BackendService";

function Profile() {
    const [user, setUser] = useState(undefined)
    const [car, setCar] = useState({
        carBrand: undefined,
        carModel: undefined,
        countryOfOrigin: undefined,
        enginePowerKW: undefined
    })
    const history = useHistory()

    useEffect(() => {
        const user = AuthenticationService.getCurrentUser();
        setUser(user);
        console.log(user)
        myCar(user);
    }, [])

    const myCar = (user) => {
        BackendService.getMyCar().then((response) => {
            console.log(response)
            setCar(response.data)
        })
    }
    let userInfo;

    if (user && user.accessToken) {
        let roles = "";
        user.authorities.forEach(authority => {
            roles = roles + " " + authority.authority
        });

        userInfo = (
            <div style={{marginTop: "20px"}}>
                <Alert variant="info">
                    <h2>Welcome {user.username} !</h2>

                    <ul>
                        <li>Car brand: {car.carBrand}</li>
                        <li>Car model: {car.carModel}</li>
                        <li>Country of origin: {car.countryOfOrigin}</li>
                        <li>Engine power: {car.enginePowerKW}</li>
                    </ul>
                </Alert>
                <Button onClick={(event) => {
                    history.push("/carFormular")
                }} variant="outline-primary">Create car</Button>
            </div>
        );
    } else { // not login
        userInfo = <div style={{marginTop: "20px"}}>
            <Alert variant="primary">
                <h2>Profile Component</h2>
                <Button color="success"><Link to="/signin"><span style={{color: "white"}}>Login</span></Link></Button>
            </Alert>
        </div>
    }

    return (
        <div>
            <Container fluid>
                {userInfo}
            </Container>
        </div>
    )
}

export default Profile;
