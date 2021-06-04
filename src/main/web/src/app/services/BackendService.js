import axios from 'axios';

axios.interceptors.request.use(config => {
    const user = JSON.parse(localStorage.getItem('user'));

    if (user && user.accessToken) {
        config.headers.Authorization = 'Bearer' + user.accessToken;
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
    postCreateCar: async function (userName, car) {
        return await axios.post(`${SERVER_PREFIX}/api/cars/`,{userName: userName, car: car})
    },

    postDeleteCar: async function (carId) {
        return await axios.post(`${SERVER_PREFIX}/api/cars/delete?id=${carId}`)
    },

    getAllCars: async function(){
        return await axios.get(`${SERVER_PREFIX}/api/cars/allCars`)
    },
    postUpdateCar: async function(car){
        return await axios.put(`${SERVER_PREFIX}/api/cars/update`,car)
    },
    //event function
    postCreateEvent: async function(event){
        return await axios.post(`${SERVER_PREFIX}/api/events`,event)
    },
    getAllEvents: async function(page, size) {
        let url = `${SERVER_PREFIX}/api/events/allEvents`

        if (page !== undefined) url += `?page=${page}`
        if (size !== undefined) url += `&size=${size}`

        return await axios.get(url)
    },
    deleteEvent: async function(event){
        return await axios.delete(`${SERVER_PREFIX}/api/events/${event.id}`)
    }



}

export default BackendService;