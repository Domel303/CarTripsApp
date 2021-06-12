import React, {useEffect, useState} from "react";
import {Button, Form, Input} from "reactstrap";
import BackendService from "../../services/BackendService";
import {useHistory, useParams} from "react-router-dom";
import DatePicker from "react-datepicker";

import "react-datepicker/dist/react-datepicker.css";

const EventForm = () => {

    const [newItem, setNewItem] = useState({
        start: undefined,
        destination:undefined,
        carCulture:undefined,
        distance:undefined,
        duration:undefined,
        description:undefined,
        dateOfEvent:undefined
    });
    const {id} = useParams()

    const [startDate, setStartDate] = useState(new Date());

    const history = useHistory()

    useEffect(() => {
        if (id)
            BackendService.getEvent(id).then(response => {
                console.log(id)
                console.log(response.data)
                setNewItem(response.data)
            })
    }, [id]);

    const onNewItem = (event) => {
        event.preventDefault()
        if (id) {
            BackendService.putEvent(newItem).then(() => {
                history.push("/home")
            })
        } else {
            BackendService.postCreateEvent(newItem).then(() => {
                history.push("/home")
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
                    <Input placeholder="start" value={newItem?.start || ""} name='start' onChange={(event) => {
                        changeValue(event)
                    }}/>
                    <Input placeholder="destination" value={newItem?.destination||""} name='destination' onChange={(event) => {
                        changeValue(event)
                    }}/>
                    <Input placeholder="carCulture" value={newItem?.carCulture||""} name='carCulture' onChange={(event) => {
                        changeValue(event)
                    }}/>
                    <Input placeholder="distance" value={newItem?.distance||""} name='distance' onChange={(event) => {
                        changeValue(event)
                    }}/>
                    <Input placeholder="duration" value={newItem?.duration||""} name='duration' onChange={(event) => {
                        changeValue(event)
                    }}/>
                    <Input placeholder="description" value={newItem?.description||""} name='description' onChange={(event) => {
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

export default EventForm