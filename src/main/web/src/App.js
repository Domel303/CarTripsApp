import React from 'react';
import {Route, Switch, HashRouter} from "react-router-dom";
import {Container} from "react-bootstrap";
import './App.css';
import Home from './app/components/Home';
import Profile from './app/components/Profile';
import UserPage from './app/components/UserPage';
import ProjectManagerPage from './app/components/ProjectManagerPage';
import SignUp from './app/components/SignUp';
import AdminPage from './app/components/AdminPage';
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
              <Route path='/pm' exact={true} component={ProjectManagerPage}/>
              <Route path='/admin' exact={true} component={AdminPage}/>
              <Route path='/signin' exact={true} component={Login}/>
              <Route path='/user' exact={true} component={UserPage}/>
              <Route path='/signup' exact={true} component={SignUp}/>
            </Switch>
          </HashRouter>
        </Container>
      </div>
  );
}

export default App;
