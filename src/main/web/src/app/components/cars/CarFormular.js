import React, {useEffect, useState} from "react";
import {Button, Form, Input} from "reactstrap";
import BackendService from "../../services/BackendService";
import {useHistory} from "react-router-dom";

const AddCar = () => {

    const [newItem, setNewItem] = useState({
        carBrand: undefined,
        carModel: undefined,
        countryOfOrigin: undefined,
        enginePowerKW: undefined,
    });

    const history = useHistory()
    useEffect(() => {
        BackendService.getCarList()
            .then((resp) => {
                console.log(resp)
                setNewItem(resp.data)
            }, (error) => {
                console.log(error.toString())
            })
    }, []);

    const onNewItem = (event) => {
        event.preventDefault()
        BackendService.postCreateCar(newItem).then((resp) => {
            history.push("/profile")
        })
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

export default AddCar