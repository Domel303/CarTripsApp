import React, {useEffect, useState} from "react";
import {Button, Form, Input} from "reactstrap";
import BackendService from "../../services/BackendService";
import {useHistory, useParams} from "react-router-dom";

const CarForm = () => {

    const [newItem, setNewItem] = useState({
        carBrand: undefined,
        carModel: undefined,
        countryOfOrigin: undefined,
        enginePowerKW: undefined
    });

    const history = useHistory()

    const {id} = useParams()

    useEffect(() => {
        if (id)
            BackendService.getCar(id).then(response => {
                setNewItem(response.data)
            })
    }, [id])

    const onNewItem = (event) => {
        event.preventDefault()
        if (id) {
            BackendService.putCar(newItem).then(() => {
                history.push("/profile")
            })
        } else {
            BackendService.postCreateCar(newItem).then(() => {
                history.push("/profile")
            })
        }
    }

    const changeValue = (event) => {
        setNewItem({...newItem, [event.target.name]: event.target.value})
    }

    return (
        <div>
            <div style={{
                marginTop: "20px"
            }
            }>
                <Form onSubmit={(event) => {
                    onNewItem(event)
                }}>
                    <Input placeholder="Znacka" name='carBrand' onChange={(event) => {
                        changeValue(event)
                    }}/>
                    <Input placeholder="Model" name='carModel' onChange={(event) => {
                        changeValue(event)
                    }}/>
                    <Input placeholder="Zeme vyroby" name='countryOfOrigin' onChange={(event) => {
                        changeValue(event)
                    }}/>
                    <Input placeholder="Vykon" name='enginePowerKW' onChange={(event) => {
                        changeValue(event)
                    }}/>
                    <Button type="submit">Add car</Button>
                </Form>
            </div>
        </div>
    );
}

export default CarForm