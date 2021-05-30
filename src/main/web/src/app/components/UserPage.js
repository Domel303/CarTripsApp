import React, {useEffect, useState} from 'react';
import {Alert, Container} from 'reactstrap';
import BackendService from '../services/BackendService';

function UserPage() {
  const [error, setError] = useState("")
  const [content, setContent] = useState("")

  useEffect(() => {
    BackendService.getUserBoard()
        .then(
            response => {
              setContent(response.data)
            },
            error => {
              setError(error.toString())
            })
  })

  return (
      <div>
        <Container fluid>
          {content ? (
              <div style={{marginTop: "20px"}}><Alert variant="info"><h2>{content}</h2></Alert></div>
          ) : (
              <div style={{marginTop: "20px"}}><Alert variant="danger">{error}</Alert></div>
          )}
        </Container>
      </div>
  )
}

export default UserPage
