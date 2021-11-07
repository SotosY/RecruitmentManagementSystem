import axios from "axios";

const USER_API = "http://localhost:8080/careers/users"
const getActiveJobs_API = "http://localhost:8080/careers"

class UserService {

    getUsers(){
        return axios.get(USER_API);
    }

    getJobs(){
        return axios.get(getActiveJobs_API);
    }
}

export default new UserService()