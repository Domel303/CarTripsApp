import React, {useEffect, useState} from "react"
import BackendService from "../../services/BackendService"
import {Container} from "react-bootstrap";
import {useParams} from "react-router-dom";

function UserCar() {
    const [newItem, setNewItem] = useState({
        carBrand: undefined,
        carModel: undefined,
        countryOfOrigin: undefined,
        enginePowerKW: undefined
    });

    const {username} = useParams()


    useEffect(() => {
        getCar(username, (response) => {
            console.log(response)
            setNewItem(response)
        })
    }, [username])

    const getCar = (username, onResponseReceived) => {
        BackendService.getUserCar(username)
            .then(
                response => {
                    console.log(response)
                    onResponseReceived(response.data)
                }
            )
    }

    return (
        <Container fluid>
            <div>
                <h2>{username}´s car !</h2>

                <ul>
                    <li>Car brand: {newItem.carBrand}</li>
                    <li>Car model: {newItem.carModel}</li>
                    <li>Country of origin: {newItem.countryOfOrigin}</li>
                    <li>Engine power: {newItem.enginePowerKW}</li>
                </ul>
            </div>
        </Container>
    )
}

export default UserCar;
