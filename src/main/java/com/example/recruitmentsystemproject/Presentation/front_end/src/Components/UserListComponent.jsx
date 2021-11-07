import React, {Component} from 'react';
import '../css/Breadcrumb.css';
import UserService from "../Services/UserService";
import {Table} from "react-bootstrap";

class UserListComponent extends Component {
    constructor(props) {
        super(props);

        this.state ={
                jobs:[]
        }

    }

    componentDidMount() {
        UserService.getJobs()
            .then((res) => {
                this.setState({jobs: res.data})
            });
    }


    render() {
        return (
            <main >
                <nav>
                    <ol className="breadcrumb">
                        <li><a href="#">APPLICANT DASHBOARD</a></li>
                        <li><a href="#">APPLICATION</a></li>
                        <li className='active'>JOB INFO</li>
                        <div>
                            <button  margin-left='700px' type="submit" className="btn btn-secondary" id="submit">LOG OUT</button>
                        </div>
                    </ol>
                </nav>
                <h2 className="text-center">Jobs List</h2>

                <div className="row">
                    <Table striped bordered hover size="sm">
                        <thead>
                            <tr>
                                <th>Job ID</th>
                                <th>Title</th>
                                <th>Department</th>
                                <th>Location</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.jobs.map(
                                    job =>
                                        <tr key = {job.jobId} >
                                            <td>{job.jobId}</td>
                                            <td>{job.title}</td>
                                            <td>{job.department}</td>
                                            <td>{job.location}</td>
                                        </tr>
                                )
                            }

                        </tbody>
                    </Table>
                </div>
            </main>
        );
    }
}


export default UserListComponent;