import {Link} from 'react-router-dom';
import {Button, Container, Alert} from 'react-bootstrap';
import AuthenticationService from "../services/AuthenticationService";
import EventList from "./events/EventList";
import React from "react";

const WEB_NAME = "Car trips"

function Home() {
    return (
        <div>
            <Container fluid>
                {
                    !AuthenticationService.isSignedIn() && (<div>
                        <Alert variant="primary">
                            <h2> {WEB_NAME} </h2>
                            <p>Welcome to car trip website !</p>
                            <p>To see upcoming events please log into application</p>
                            <Button color="success"><Link to="/signin"><span
                                style={{color: "black"}}>Sign in</span></Link></Button>
                            <Button color="warning"><Link to={"/signup"}><span
                                style={{color: "black"}}>Sign up</span></Link></Button>
                        </Alert>
                    </div>)
                }
                {
                    AuthenticationService.isSignedIn() && (
                        <div>
                            <h1 style={{marginTop: "0.5em"}}>Upcoming events</h1>
                            <EventList/>
                        </div>
                    )
                }
            </Container>
        </div>
    );
}

export default Home;
