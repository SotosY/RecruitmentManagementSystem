import axios from "axios";

const getActiveJobs_API = "http://localhost:8080/careers"
const getLogin_API="http://localhost:8080/careers/login"
const getApplicantsDashboard_API="http://localhost:8080/careers/applicant/dashboard"

class JobService {

    getJobs(){
        return axios.get(getActiveJobs_API);
    }

    getUser(email, password){
        return axios.get(getLogin_API,
            {headers: {authorization: this.createBasicAuthToken(email, password)}})
    }

    createBasicAuthToken(email, password) {
        return 'Basic ' + window.btoa(email + ":" + password)
    }

    getApplicantDashboard(){
        return axios.get(getApplicantsDashboard_API);
    }
}

export default new JobService()