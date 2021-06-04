import {Button, Col, Row, Table} from "react-bootstrap";
import React, {useState} from "react";
import {useHistory} from "react-router-dom";
import Pagination from "./Pages";
import BootstrapTable from 'react-bootstrap-table-next';

function MyList({properties, items, detailUrl, maxPage, onNewItemsRequest}) {
    const [actualPage, setActualPage] = useState(0)
    const history = useHistory()


    const onPageChange = (page) => {
        onNewItemsRequest(page)
        setActualPage(page)
    }

    return (
        <div style={{"margin": "1em 0em 1em 0em"}}>
            <BootstrapTable keyField='id' data={ items } columns={ properties } />
        </div>
    )
}

export default MyList
