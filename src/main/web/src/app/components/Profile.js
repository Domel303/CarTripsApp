import React, {useEffect, useState} from 'react'
import {Link, useHistory} from 'react-router-dom'
import {Alert, Button, Container} from 'reactstrap'

import AuthenticationService from '../services/AuthenticationService'

function Profile() {
    const [user, setUser] = useState(undefined)

    const history = useHistory()

    useEffect(() => {
        const user = AuthenticationService.getCurrentUser();
        setUser(user);
    }, [])

    let userInfo;

    if (user && user.accessToken) {
        let roles = "";
        user.authorities.forEach(authority => {
            roles = roles + " " + authority.authority
        });

        userInfo = (
            <div style={{marginTop: "20px"}}>
                <Alert variant="info">
                    <h2>User Info</h2>
                    <ul>
                        <li>Username: {user.username}</li>
                        <li>Access Token: {user.accessToken}</li>
                        <li>Authorities: {roles}</li>
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
