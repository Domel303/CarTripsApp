import React, {useState} from "react";
import {useHistory} from "react-router-dom";
import BootstrapTable from 'react-bootstrap-table-next';
import paginationFactory, {
    PaginationProvider,
    PaginationListStandalone,
    PaginationTotalStandalone,
    SizePerPageDropdownStandalone
} from 'react-bootstrap-table2-paginator';

function MyList({properties, items, detailUrl,sizePerPage, maxPage, onNewItemsRequest}) {
    const [actualPage, setActualPage] = useState(1)
    const history = useHistory()

    const handleTableChange = (type, { page, sizePerPage }) => {
        console.log(page)
        onNewItemsRequest(page)
        setActualPage(page)
    }

    return (
        <div style={{"margin": "1em 0em 1em 0em"}}>
            <PaginationProvider
                pagination={
                    paginationFactory({
                        custom: true,
                        page: actualPage,
                        sizePerPage:1,
                        totalSize: maxPage
                    })
                }
            >
                {
                    ({
                         paginationProps,
                         paginationTableProps
                     }) => (
                        <div>
                            <div>
                                <p>Current Page: { paginationProps.page }</p>
                                <p>Current SizePerPage: { paginationProps.sizePerPage }</p>
                            </div>
                            <div>
                                <PaginationListStandalone
                                    { ...paginationProps }
                                />
                            </div>
                            <BootstrapTable remote
                                            keyField='id'
                                            data={ items }
                                            columns={ properties }

                                            onTableChange={ handleTableChange }
                                            { ...paginationTableProps }
                            />
                        </div>
                    )
                }
            </PaginationProvider>

        </div>
    )
}

export default MyList
