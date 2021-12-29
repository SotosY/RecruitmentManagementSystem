import axios from "axios";

export default axios.create({
    baseURL: 'http://localhost:8080/careers',
    credentials: 'same-origin',
    headers: {
        'Access-Control-Allow-Origin': '*',
        'Content-Type':'application/json'
    }
})