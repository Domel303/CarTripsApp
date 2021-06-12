import React, {useEffect, useState} from "react"
import BackendService from "../../services/BackendService"
import {Alert, Col, Container, Row} from "react-bootstrap";
import MyList from "../MyList";
import {useParams} from "react-router-dom";

function EventsUsers() {
    const [user, setUsers] = useState([])
    const [error, setError] = useState("")
    const {id} = useParams()

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
        getUsers(id,(response)=>{
            console.log(response)
            setUsers(response)
        })
    },[id])

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

export default EventsUsers;
