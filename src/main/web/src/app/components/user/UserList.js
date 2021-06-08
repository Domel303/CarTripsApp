import React, {useEffect, useState} from "react"
import BackendService from "../../services/BackendService"
import {Alert, Col, Container, Row} from "react-bootstrap";
import MyList from "../MyList";

function UserList() {
    const [users, setUsers] = useState([])
    const [maxPage, setMaxPage] = useState(0)
    const [error, setError] = useState("")

    const SIZE = 15

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
    }, {
        dataField: 'car',
        text: 'Auto'
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
            setUsers(response.content)

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
                <Col><h1>App users</h1></Col>
            </Row>
            <Row>
                <Col>
                    {error && <Alert variant={'danger'}>{error}</Alert>}
                </Col>
            </Row>
            <Row>
                <Col>
                    <MyList
                        items={users}
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

export default UserList;
