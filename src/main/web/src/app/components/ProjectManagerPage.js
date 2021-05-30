import React, {useEffect, useState} from 'react';
import { Alert } from 'react-bootstrap';
import BackendService from '../services/BackendService';

function ProjectManagerPage() {
  const [error, setError] = useState("")
  const [content, setContent] = useState("")

  useEffect(() => {
    BackendService.getPmBoard()
        .then(
            response => { setContent(response.data)},
            error => { setError(error) });
  })

  return (
      <div>
        {content ? (
            <div style={{marginTop: "20px"}}>
              <Alert color="info"><h2>{content}</h2></Alert>
            </div>
        ) : (
            <div style={{marginTop: "20px"}}>
              <Alert color="danger">{error}</Alert>
            </div>
        )
        }
      </div>
  );

}

export default ProjectManagerPage;
