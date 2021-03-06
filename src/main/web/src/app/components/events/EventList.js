import React, {useEffect, useState} from "react"
import BackendService from "../../services/BackendService"
import {useHistory} from "react-router-dom"
import {Alert, Button, Col, Container, Row} from "react-bootstrap";
import MyList from "../MyList";

function EventList() {
    const [events, setEvents] = useState([])
    const [maxPage, setMaxPage] = useState(0)
    const [error, setError] = useState("")

    const SIZE = 1

    const history = useHistory()

    const onDelete = (id) => {
        BackendService.deleteEvent(id).then(() => {
            setEvents(events.filter((item) => item.id !== id))
        })
    }

    const actionsFormatter = (cell, row) =>
        <Button type="submit" onClick={() => {
            onDelete(row.id)
        }}>Delete</Button>

    const eventsUserFormatter = (cell, row) =>
        <Button type="submit" onClick={() => {
            getEventsUser(row.id)
        }}>Users</Button>

    const getEventsUser = (id) => {
        history.push("/eventsUsers/" + id)
    }
    const registerUserFormatter = (cell, row) =>
        <Button type="submit" onClick={() => {
            registerUser(row.id)
        }}>Register</Button>

    const registerUser = (id) => {
        console.log(id)
        BackendService.registerUser(id).then()
    }

    const updateFormatter = (cell, row) =>
        <Button type="submit" onClick={() => {
            updateEvent(row.id)
        }}>Update</Button>

    const updateEvent = (id) => {
        history.push("/eventFormular/edit/" + id)
    }

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
    }, {
        dataField: 'getUser',
        text: 'Users',
        isDummyField: true,
        csvExport: false,
        formatter: eventsUserFormatter
    }, {
        dataField: 'register',
        text: 'Register',
        isDummyField: true,
        csvExport: false,
        formatter: registerUserFormatter
    }, {
        dataField: 'update',
        text: 'Update',
        isDummyField: true,
        csvExport: false,
        formatter: updateFormatter
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
                    <Button onClick={() => {
                        history.push("/eventFormular/add")
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
                        sizePerPage={SIZE}
                        onNewItemsRequest={(page) => {
                            getEvents(page - 1, (response) => {
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
