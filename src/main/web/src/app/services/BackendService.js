import axios from 'axios';

axios.interceptors.request.use(config => {
    const user = JSON.parse(localStorage.getItem('user'));

    if (user && user.accessToken) {
        config.headers.Authorization = 'Bearer ' + user.accessToken;
    }

    return config;
});

const SERVER_PREFIX = process.env.REACT_APP_BASE_URI

const BackendService = {
    getUserBoard: async function () {
        return await axios.get(`${SERVER_PREFIX}/api/test/user`)
    },

    getPmBoard: async function () {
        return await axios.get(`${SERVER_PREFIX}/api/test/pm`)
    },

    getAdminBoard: async function () {
        return await axios.get(`${SERVER_PREFIX}/api/test/admin`)
    },

    //car function
    postCreateCar: async function (car) {
        return await axios.post(`${SERVER_PREFIX}/api/cars/`, car)
    },

    deleteCar: async function (id) {
        return await axios.delete(`${SERVER_PREFIX}/api/cars/${id}`)
    },
    getCar: async function (id) {
        return await axios.get(`${SERVER_PREFIX}/api/cars/car/${id}`)
    },
    putCar: async function (car) {
        return await axios.put(`${SERVER_PREFIX}/api/cars/`, car)
    },

    getAllCars: async function (page, size) {
        let url = `${SERVER_PREFIX}/api/cars/`

        if (page !== undefined) url += `?page=${page}`
        if (size !== undefined) url += `&size=${size}`

        return await axios.get(url)
    },
    postUpdateCar: async function (car) {
        return await axios.put(`${SERVER_PREFIX}/api/cars/`, car)
    },

    //event function
    postCreateEvent: async function (event) {
        return await axios.post(`${SERVER_PREFIX}/api/events`, event)
    },
    getAllEvents: async function (page, size) {
        let url = `${SERVER_PREFIX}/api/events`

        if (page !== undefined) url += `?page=${page}`
        if (size !== undefined) url += `&size=${size}`

        return await axios.get(url)
    },
    getEvent: async function (id) {
        return await axios.get(`${SERVER_PREFIX}/api/events/updateEvent/${id}`)
    },
    deleteEvent: async function (id) {
        return await axios.delete(`${SERVER_PREFIX}/api/events/${id}`)
    },
    getUsersEvents: async function () {
        return await axios.get(`${SERVER_PREFIX}/api/events/event`)
    },
    getEventsUsers: async function (id) {
        return await axios.get(`${SERVER_PREFIX}/api/events/user/${id}`)
    },
    registerUser: async function (id) {
        let url = `${SERVER_PREFIX}/api/events/register/`
        if (id !== undefined) url += `?id=${id}`
        return await axios.post(url)
    },
    unregisterUser: async function (id) {
        let url = `${SERVER_PREFIX}/api/events/unregister/`
        if (id !== undefined) url += `?id=${id}`
        return await axios.post(url)
    },
    putEvent: async function (event) {
        return await axios.put(`${SERVER_PREFIX}/api/events`, event)
    },
    //user function
    getAllUsers: async function (page, size) {
        let url = `${SERVER_PREFIX}/api/users/`

        if (page !== undefined) url += `?page=${page}`
        if (size !== undefined) url += `&size=${size}`

        return await axios.get(url)
    },
    getMyCar: async function () {
        return await axios.get(`${SERVER_PREFIX}/api/users/car`)
    },
    getUserCar: async function (username) {
        return await axios.get(`${SERVER_PREFIX}/api/users/userCar/${username}`)
    }


}

export default BackendService;