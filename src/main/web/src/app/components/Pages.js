import {Pagination} from "react-bootstrap";
import React from "react";

function Pages({actualPage, totalPages, onPageChangeHandler}) {
    const pages = () => {
        let pages = []
        for (let i = 0; i < totalPages; ++i) {
            pages.push(i + 1)
        }
        return pages
    }

    const onPageChange = (page) => {
        onPageChangeHandler(page)
    }

    return (
        <div>
            {(totalPages > 1) && (
                <Pagination size="sm">
                    {pages().map((nextPage) => {
                        if (actualPage === nextPage - 1)
                            return <Pagination.Item onClick={() => onPageChange(nextPage - 1)}
                                                    active>{nextPage}</Pagination.Item>
                        else
                            return <Pagination.Item
                                onClick={() => onPageChange(nextPage - 1)}>{nextPage}</Pagination.Item>
                    })}
                </Pagination>
            )}
        </div>
    )
}

export default Pages
