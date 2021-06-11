import React, {useEffect, useState} from "react"
import BackendService from "../../services/BackendService"
import {Alert, Col, Container, Row} from "react-bootstrap";
import MyList from "../MyList";

function EventsUser() {
    const [user, setUsers] = useState([])
    const [error, setError] = useState("")

    const properties = [{
        dataField: 'id',
        text: 'Id'
    }, {
        dataField: 'firstname',
        text: 'Jméno'
    }, {
        dataField: 'lastname',
        text: 'Přijmení'
    }, {
        dataField: 'username',
        text: 'Přezdívka'
    }, {
        dataField: 'email',
        text: 'email'
    }];

    useEffect(()=>{
        getUsers(2,(response)=>{
            console.log(response)
            setUsers(response)
        })
    },[])
    const getUsers = (id, onResponseReceived) => {
        BackendService.getEventsUsers(id)
            .then(
                response => {
                    onResponseReceived(response.data)
                },
                error => {
                    setError(error.toString())
                }
            )
    }

    return (
        <Container fluid>
            <Row>
                <Col><h1>User Events</h1></Col>
            </Row>
            <Row>
                <Col>
                    {error && <Alert variant={'danger'}>{error}</Alert>}
                </Col>
            </Row>
            <Row>
                <Col>
                    <MyList
                        items={user}
                        properties={properties}
                        detailUrl={undefined}
                        maxPage={5}
                        sizePerPage={5}
                        onNewItemsRequest={(page) => {
                        }}
                    />
                </Col>
            </Row>
        </Container>
    )
}

export default EventsUser;
