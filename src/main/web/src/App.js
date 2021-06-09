import React from 'react';
import {Route, Switch, HashRouter} from "react-router-dom";
import {Container} from "react-bootstrap";
import './App.css';
import Home from './app/components/Home';
import Profile from './app/components/Profile';
import SignUp from './app/components/SignUp';
import AppNavbar from "./app/components/AppNavbar";
import Login from "./app/components/Login";
import EventFormular from "./app/components/events/EventFormular";
import CarList from "./app/components/cars/CarList";
import CarFormular from "./app/components/cars/CarFormular";
import UserList from "./app/components/user/UserList";

function App() {
    return (
        <div>
            <AppNavbar/>
            <Container fluid>
                <HashRouter>
                    <Switch>
                        <Route path='/' exact={true} component={Home}/>
                        <Route path='/home' exact={true} component={Home}/>
                        <Route path='/profile' exact={true} component={Profile}/>
                        <Route path='/eventFormular' exaxt={true} component={EventFormular}/>
                        <Route path='/carFormular' exaxt={true} component={CarFormular}/>
                        <Route path='/userList' exaxt={true} component={UserList}/>
                        <Route path='/carList' exaxt={true} component={CarList}/>
                        <Route path='/signin' exact={true} component={Login}/>
                        <Route path='/signup' exact={true} component={SignUp}/>
                    </Switch>
                </HashRouter>
            </Container>
        </div>
    );
}

export default App;
