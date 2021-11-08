import axios from "axios";

const getActiveJobs_API = "http://localhost:8080/careers"

class JobService {

    getJobs(){
        return axios.get(getActiveJobs_API);
    }
}

export default new JobService()