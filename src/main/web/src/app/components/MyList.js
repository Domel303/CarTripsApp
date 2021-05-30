import {Button, Col, Row, Table} from "react-bootstrap";
import React, {useState} from "react";
import {useHistory} from "react-router-dom";
import Pagination from "./Pages";

function MyList({properties, items, detailUrl, maxPage, onNewItemsRequest}) {
    const [actualPage, setActualPage] = useState(0)
    const history = useHistory()


    const onPageChange = (page) => {
        onNewItemsRequest(page)
        setActualPage(page)
    }

    return (
        <div style={{"margin": "1em 0em 1em 0em"}}>
            {items?.length > 0 && (
                <div>
                    <Row>
                        <Col xs={12}>
                            <Table striped bordered hover responsive style={{"margin": "1em 0em 1em 0em"}}>
                                <thead>
                                <tr>
                                    {Object.entries(properties).map(([key, value]) =>
                                        <th key={key}>{value}</th>
                                    )}
                                </tr>
                                </thead>
                                <tbody>
                                {items.map((item) =>
                                    <tr key={item.id}>
                                        {Object.entries(item).map(([key, value]) =>
                                            <td key={`${key}-${item.id}`}>{value}</td>
                                        )}
                                        {detailUrl && (<td><Button onClick={() => {
                                            history.push(`${detailUrl}/${item.id}`)
                                        }}>Detail</Button></td>)}
                                    </tr>
                                )}
                                </tbody>
                            </Table>
                        </Col>
                    </Row>
                    {maxPage > 1 && (
                        <Pagination actualPage={actualPage}
                               totalPages={maxPage}
                               onPageChangeHandler={(page) => {
                                   onPageChange(page)
                               }}/>
                    )}
                </div>
            )}
        </div>
    )
}

export default MyList
