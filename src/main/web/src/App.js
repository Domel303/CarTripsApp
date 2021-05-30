import React from 'react';
import {Route, Switch, HashRouter} from "react-router-dom";
import {Container} from "react-bootstrap";
import './App.css';
import Home from './app/components/Home';
import Profile from './app/components/Profile';
import SignUp from './app/components/SignUp';
import AppNavbar from "./app/components/AppNavbar";
import Login from "./app/components/Login";

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
              <Route path='/signin' exact={true} component={Login}/>
              <Route path='/signup' exact={true} component={SignUp}/>
            </Switch>
          </HashRouter>
        </Container>
      </div>
  );
}

export default App;
