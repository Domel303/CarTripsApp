import React, {useEffect, useState} from "react"
import BackendService from "../../services/BackendService"
import {useHistory} from "react-router-dom"
import {Alert, Button, Col, Container, Row} from "react-bootstrap";
import MyList from "../MyList";

function EventList() {
    const [events, setEvents] = useState([])
    const [maxPage, setMaxPage] = useState(0)
    const [error, setError] = useState("")

    const SIZE = 15

    const history = useHistory()

    const onDelete = (id) => {
        BackendService.deleteEvent(id).then()
    }

    const actionsFormatter = (cell, row) =>
        <Button type="submit" onClick={(event) => {
            onDelete(row.id)
        }}>Delete</Button>


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
    }, {
        dataField: 'action',
        text: 'Smazat',
        isDummyField: true,
        csvExport: false,
        formatter: actionsFormatter
    }];

    useEffect(() => {
        getEvents(maxPage, (response) => {
            parseEvent(response)
        })
    }, [maxPage])

    const getEvents = (page, onResponseReceived) => {
        BackendService.getAllEvents(page, SIZE)
            .then(
                response => {
                    onResponseReceived(response.data)
                },
                error => {
                    setError(error.toString())
                }
            )
    }

    const parseEvent = (response) => {
        if (response && response.content && response.content.length > 0) {
            setEvents(response.content)

            if (response.totalPages && response.totalPages > 1) {
                setMaxPage(response.totalPages)
            } else {
                setMaxPage(0)
            }
        }
    }

    return (
        <Container fluid>
            <Row>
                <Col><h1>Events</h1></Col>
            </Row>
            <Row>
                <Col>
                    <Button onClick={(event) => {
                        history.push("/event/add")
                    }} variant="outline-primary">Add Event</Button>
                </Col>
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
                        maxPage={maxPage}
                        onNewItemsRequest={(page) => {
                            getEvents(page, (response) => {
                                parseEvent(response)
                            })
                        }}
                    />
                </Col>
            </Row>
        </Container>
    )
}

export default EventList;