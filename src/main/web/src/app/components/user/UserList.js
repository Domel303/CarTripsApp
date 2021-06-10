import React, {useEffect, useState} from "react"
import BackendService from "../../services/BackendService"
import {Alert, Col, Container, Row} from "react-bootstrap";
import MyList from "../MyList";

function UserList() {
    const [users, setUsers] = useState([])
    const [maxPage, setMaxPage] = useState(0)
    const [error, setError] = useState("")

    const SIZE = 1

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

    useEffect(() => {
        getUsers(maxPage, (response) => {
            parseUser(response)
        })
    }, [maxPage])

    const getUsers = (page, onResponseReceived) => {
        BackendService.getAllUsers(page, SIZE)
            .then(
                response => {
                    onResponseReceived(response.data)
                },
                error => {
                    setError(error.toString())
                }
            )
    }

    const parseUser = (response) => {
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
                        sizePerPage={SIZE}
                        onNewItemsRequest={(page) => {
                            getUsers(page-1, (response) => {
                                parseUser(response)
                            })
                        }}
                    />
                </Col>
            </Row>
        </Container>
    )
}

export default UserList;
