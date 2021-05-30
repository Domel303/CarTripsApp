import React, {useState} from 'react'
import {Button} from 'react-bootstrap'
import {Form, Alert, FormGroup, Row, Col} from "react-bootstrap"
import AuthenticationService from '../services/AuthenticationService'
import '../../App.css';
import {Input, Label} from "reactstrap";
import {useHistory} from "react-router-dom"

function Login() {
    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")
    const [error, setError] = useState("")

    const history = useHistory();

    const changeHandler = function (event) {
        let nam = event.target.name;
        let val = event.target.value;

        if (nam === "username") setUsername(val)
        if (nam === "password") setPassword(val)
    }

    const doLogin = async (event) => {
        event.preventDefault();

        AuthenticationService.signIn(username, password)
            .then(
                () => {
                    history.push('/profile')
                    window.location.reload()
                },
                error => {
                    console.log("Login fail: error = { " + error.toString() + " }");
                    setError("Can not signin successfully ! Please check username/password again");
                });
    }

    return (
        <Row style={{marginTop: "20px"}}>
            <Col sm="12" md={{size: 3}}>
                <div style={{marginBottom: "10px"}}>
                </div>
                <Form onSubmit={doLogin}>
                    <FormGroup>
                        <Label for="username"><strong>Username</strong></Label>
                        <Input autoFocus type="text" name="username" id="username" value={username}
                               placeholder="Enter Username" autoComplete="username" onChange={changeHandler}/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="password"><strong>Password</strong></Label>
                        <Input type="password" name="password" id="password" value={password}
                               placeholder="Enter Password" autoComplete="password" onChange={changeHandler}/>
                    </FormGroup>

                    <Button type="submit" name="submitButton" id="submitButton" variant="primary" size="lg" block>Sign In</Button>
                    {error && (<Alert color="danger">{error}</Alert>)}
                </Form>
            </Col>
        </Row>
    )
}

export default Login;
