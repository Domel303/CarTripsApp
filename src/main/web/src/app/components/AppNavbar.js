import React, {useEffect, useState} from 'react'
import {Button, Nav, Navbar,} from 'react-bootstrap'
import {useHistory, withRouter} from 'react-router-dom'
import AuthenticationService from '../services/AuthenticationService'

function AppNavbar() {
    const [user, setUser] = useState(false)
    const history = useHistory()

    useEffect(() => {
        const user = AuthenticationService.getCurrentUser();
        if (user) {
            setUser(true)
        }
    }, [])

    const signOut = () => {
        AuthenticationService.signOut()
        history.push("/#/signin")
        window.location.reload()
    }
    return (
        <Navbar bg="primary" variant="dark">
            <Navbar.Brand href="#home">Car trips</Navbar.Brand>
            <Navbar.Toggle aria-controls="basic-navbar-nav"/>
            <Nav className="mr-auto">
                {user && <Nav.Link href="/#/profile">Profile</Nav.Link>}
                {user && <Nav.Link href="/#/eventFormular">Create event</Nav.Link>}
                {user && <Nav.Link href="/#/carList">Cars</Nav.Link>}
                {user && <Nav.Link href="/#/userList">Users</Nav.Link>}
                {user && <Nav.Link href="/#/userEvents">My events</Nav.Link>}
                {user && <Nav.Link href="/#/eventsUsers">Users on event</Nav.Link>}
                {user && <Nav.Link><Button variant={'danger'} onClick={() => {
                    signOut()
                }}>Sign out</Button></Nav.Link>}

            </Nav>
        </Navbar>
    )
}

export default withRouter(AppNavbar);
