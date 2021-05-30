import React, {useEffect, useState} from 'react';
import {Container, Alert} from 'react-bootstrap';
import BackendService from '../services/BackendService';

function AdminPage() {
  const [content, setContent] = useState("")
  const [error, setError] = useState("")

  useEffect(() => {
    BackendService.getUserBoard()
        .then(
            response => {
              setContent(response.data)
            },
            error => {
              setError(error.toString())
            }
        );
  })

  return (
      <div>
        <Container fluid>
          {
            content ? (
                <div style={{marginTop: "20px"}}>
                  <Alert variant="info"><h2>{content}</h2></Alert>
                </div>
            ) : (
                <div style={{marginTop: "20px"}}>
                  <Alert variant="danger">{error}</Alert>
                </div>
            )
          }
        </Container>
      </div>
  )
}

export default AdminPage;
