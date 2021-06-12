import React, {useEffect, useState} from "react";
import {Button, Form, Input, Label} from "reactstrap";
import BackendService from "../../services/BackendService";
import {useHistory} from "react-router-dom";
import DatePicker from "react-datepicker";

import "react-datepicker/dist/react-datepicker.css";

const AddEvent = () => {

    const [newItem, setNewItem] = useState({
        start: undefined,
        destination: undefined,
        carCulture: undefined,
        distance: undefined,
        duration: undefined,
        dateOfEvent: undefined,
        description: undefined
    });

    const [startDate, setStartDate] = useState(new Date());

    const history = useHistory()

    useEffect(() => {
        BackendService.getAllEvents()
            .then((resp) => {
                setNewItem(resp.data)
            }, (error) => {
                console.log(error.toString())
            })
    }, []);

    const onNewItem = (event) => {
        event.preventDefault()
        BackendService.postCreateEvent(newItem).then((resp) => {
            history.push("/home")
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
                    <Input placeholder="start" name='start' onChange={(event) => {
                        changeValue(event)
                    }}/>
                    <Input placeholder="destination" name='destination' onChange={(event) => {
                        changeValue(event)
                    }}/>
                    <Input placeholder="carCulture" name='carCulture' onChange={(event) => {
                        changeValue(event)
                    }}/>
                    <Input placeholder="distance" name='distance' onChange={(event) => {
                        changeValue(event)
                    }}/>
                    <Input placeholder="duration" name='duration' onChange={(event) => {
                        changeValue(event)
                    }}/>
                    <Input placeholder="description" name='description' onChange={(event) => {
                        changeValue(event)
                    }}/>
                    <DatePicker dateFormat="dd/MM/yyyy" name='dateOfEvent' selected={startDate} onChange={(date) => {
                        setStartDate(date)
                        changeValue({
                            target: {
                                name: 'dateOfEvent',
                                value: date
                            }
                        })
                    }}/>


                    <Button type="submit">Add event</Button>
                </Form>
            </div>
        </div>
    );
}

export default AddEvent