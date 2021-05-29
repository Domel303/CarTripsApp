import React, {useState} from 'react'
import {Button, Col, Container, Form, FormGroup, Row} from 'react-bootstrap'
import {Input, Label} from "reactstrap"
import {Alert} from "react-bootstrap"

import Authentication from '../services/AuthenticationService'

function SignUp() {
  const [user, setUser] = useState({username: undefined, password: undefined, email: undefined})
  const [message, setMessage] = useState(undefined)
  const [error, setError] = useState(undefined)


  const changeValueHandler = (name, value) => {
    setUser({...user, [name]: value})
  }

  const register = function (e) {
    e.preventDefault()

    console.log(user)

    Authentication.register(user)
        .then(
            response => {setMessage(response.data.message)},
            error => {setError(error.toString())}
        )
  }

  const validateEmail = (email) => {
    const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(String(email).toLowerCase());
  }

  return (
      <Container fluid>
        <Row><Col><h1>Register User</h1></Col></Row>
        <Row><Col>{error && <Alert variant={'danger'}>{error}</Alert> }</Col></Row>
        <Row><Col>{message && <Alert variant={'success'}>{message}</Alert> }</Col></Row>
        <Row><Col>
          <Form onSubmit={register}>
            <FormGroup style={{marginTop : "1em"}} controlId="forUsername">
              <Label for="username">Username</Label>
              <Input type="text" placeholder="Enter your username" name="username"
                     id="username" value={user?.username} autoComplete="username"
                     onChange={(e) => {changeValueHandler(e.target.name, e.target.value)}}/>
              {user?.username?.length < 5 && (<Alert variant="danger">Username at least 5 characters.</Alert>)}
            </FormGroup>

            <FormGroup style={{"margin-top" : "1em"}} controlId="formEmail">
              <Label for="email">Email</Label>
              <Input required type="text" placeholder="enter your email" name="email" id="email"
                     value={user?.email} autoComplete="email"
                     onChange={(e) => {changeValueHandler(e.target.name, e.target.value)}}/>
              {user?.email && !validateEmail(user?.email) && (<Alert variant="danger">Email is not valid.</Alert>)}
            </FormGroup>

            <FormGroup style={{"margin-top" : "1em"}} controlId="formPassword">
              <Label for="password">Password</Label>
              <Input required type="password" placeholder="Enter Password" name="password"
                     id="password" value={user?.password} autoComplete="password"
                     onChange={(e) => {changeValueHandler(e.target.name, e.target.value)}}/>
              {user?.password?.length < 5 && (<Alert key="errorspassword" variant="danger">Password at least 5 characters.</Alert>)}
            </FormGroup>

            <Button style={{"margin-top" : "1em"}} variant="primary" type="submit">Create</Button>
          </Form>
        </Col></Row>
      </Container>
  )
}


export default SignUp;