import React, {useEffect, useState} from "react"
import BackendService from "../../services/BackendService"
import {Alert, Col, Container, Row} from "react-bootstrap";
import MyList from "../MyList";

function UserEvents() {
    const [events, setEvents] = useState([])
    const [error, setError] = useState("")

    const properties = [{
        dataField: 'id',
        text: 'Id'
    }, {
        dataField: 'start',
        text: 'Začátek'
    }, {
        dataField: 'destination',
        text: 'Cíl'
    }, {
        dataField: 'carCulture',
        text: 'Typ aut'
    }, {
        dataField: 'distance',
        text: 'Vzdalenost'
    }, {
        dataField: 'duration',
        text: 'Trvání'
    }, {
        dataField: 'dateOfEvent',
        text: 'Datum konání'
    }, {
        dataField: 'description',
        text: 'Popis'
    }];

    useEffect(()=>{
        getEvents((response)=>{
            console.log(response)
            setEvents(response)
        })
    },[])
    const getEvents = (onResponseReceived) => {
        BackendService.getUsersEvents()
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
                        items={events}
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

export default UserEvents;
