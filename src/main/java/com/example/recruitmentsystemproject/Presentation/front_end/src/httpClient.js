import axios from "axios";

export default axios.create({
    baseURL: 'http://localhost:8080/careers',
    withCredentials: false,
    headers: {
        'Content-Type':'application/json',
    }
})