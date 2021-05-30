import {Link} from 'react-router-dom';
import {Button, Container, Alert} from 'react-bootstrap';
import AuthenticationService from "../services/AuthenticationService";

const WEB_NAME = "Car trips"

function Home() {
  return (
      <div>
        <Container fluid>
          {
            !AuthenticationService.isSignedIn() && (<div>
              <Alert variant="primary">
                <h2> {WEB_NAME} </h2>
                <Button color="success"><Link to="/signin"><span
                    style={{color: "black"}}>Login</span></Link></Button>
              </Alert>
            </div>)
          }
          {
            AuthenticationService.isSignedIn() && (
                <div>
                  <h1 style={{marginTop: "0.5em"}}>Your experience:</h1>
                </div>
            )
          }
        </Container>
      </div>
  );
}

export default Home;
