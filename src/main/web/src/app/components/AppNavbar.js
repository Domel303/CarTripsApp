import React, {useEffect, useState} from 'react'
import {Nav, Navbar,} from 'react-bootstrap'
import {withRouter} from 'react-router-dom'
import AuthenticationService from '../services/AuthenticationService'

function AppNavbar() {
  const [user, setUser] = useState(false)

  useEffect(() => {
    const user = AuthenticationService.getCurrentUser();
    if (user) {setUser(true)}
  }, [])

  return (
      <Navbar bg="primary" variant="dark">
        <Navbar.Brand href="#home">Car trips</Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Nav className="mr-auto">
          {user && <Nav.Link href="/ProjectManagerPage">Zatim nic</Nav.Link>}
          {user && AuthenticationService.isAdmin() && <Nav.Link href="/#/signup">User registration</Nav.Link>}
        </Nav>
      </Navbar>
  )
}

export default withRouter(AppNavbar);
