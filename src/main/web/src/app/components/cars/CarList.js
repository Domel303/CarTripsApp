import React, {useEffect, useState} from "react"
import BackendService from "../../services/BackendService"
import {useHistory} from "react-router-dom"
import {Alert, Button, Col, Container, Row} from "react-bootstrap";
import MyList from "../MyList";

function CarList() {
    const [cars, setCars] = useState([])
    const [maxPage, setMaxPage] = useState(0)
    const [error, setError] = useState("")

    const SIZE = 1

    const history = useHistory()

    const onDelete = (id) => {
        BackendService.deleteCar(id).then(() => {
            setCars(cars.filter((item) => item.id !== id))
        })
    }

    const actionsFormatter = (cell, row) =>
        <Button type="submit" onClick={() => {
            onDelete(row.id)
        }}>Delete</Button>

    const updateFormatter = (cell, row) =>
        <Button type="submit" onClick={() => {
            updateCar(row.id)
        }}>Update</Button>
    const updateCar = (id) => {
        history.push("/carFormular/edit/" + id)
    }
    const properties = [{
        dataField: 'id',
        text: 'Id'
    }, {
        dataField: 'carBrand',
        text: 'Značka auta'
    }, {
        dataField: 'carModel',
        text: 'Model auta'
    }, {
        dataField: 'countryOfOrigin',
        text: 'Země výroby'
    }, {
        dataField: 'enginePowerKW',
        text: 'Výkon'
    }, {
        dataField: 'action',
        text: 'Smazat',
        isDummyField: true,
        csvExport: false,
        formatter: actionsFormatter
    }, {
        dataField: 'update',
        text: 'Update',
        isDummyField: true,
        csvExport: false,
        formatter: updateFormatter
    }];

    useEffect(() => {
        getCars(maxPage, (response) => {
            parseCar(response)
        })
    }, [maxPage])

    const getCars = (page, onResponseReceived) => {
        BackendService.getAllCars(page, SIZE)
            .then(
                response => {
                    onResponseReceived(response.data)
                },
                error => {
                    setError(error.toString())
                }
            )
    }

    const parseCar = (response) => {
        if (response && response.content && response.content.length > 0) {
            setCars(response.content)

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
                <Col><h1>Cars</h1></Col>
            </Row>
            <Row>
                <Col>
                    {error && <Alert variant={'danger'}>{error}</Alert>}
                </Col>
            </Row>
            <Row>
                <Col>
                    <MyList
                        items={cars}
                        properties={properties}
                        detailUrl={undefined}
                        maxPage={maxPage}
                        sizePerPage={SIZE}
                        onNewItemsRequest={(page) => {
                            getCars(page - 1, (response) => {
                                parseCar(response)
                            })
                        }}
                    />
                </Col>
            </Row>
        </Container>
    )
}

export default CarList;
