import React, {Component, useState} from 'react';
import './css/Breadcrumb.css';
import {Table, DropdownButton, ButtonGroup, Dropdown} from "react-bootstrap";
import {Link} from "react-router-dom";
import {getJobs} from "../Services/UserService";

class JobListComponent extends Component {

    constructor(props) {
        super(props);

        this.state ={
                jobs:[]
        }
    }

    componentDidMount() {
        getJobs()
            .then((res) => {
                this.setState({jobs: res.data})
            });
    }

    render() {

        return (
            <main>
                <section className='section-outside'>
                    <div className='container-breadcrumb'>
                        <ol className="breadcrumb">
                            <li className='active'>CAREERS</li>
                        </ol>
                    </div>
                    <div className='container-button'>
                        <Link to="/careers/login">
                            <button type="submit" className="btn btn-secondary" id="btn_login" style={{marginRight: '10px'}}>SIGN IN</button>
                        </Link>
                        <DropdownButton as={ButtonGroup} title="REGISTER" id="bg-vertical-dropdown-3" variant="secondary">
                                <Dropdown.Item as={Link} to={"/careers/register/a"} eventKey="1">AS AN APPLICANT</Dropdown.Item>
                                <Dropdown.Item as={Link} to={"/careers/register/e"} eventKey="2">AS AN EMPLOYER</Dropdown.Item>
                        </DropdownButton>
                    </div>
                </section>

                <div className='container'>
                    <div className="mainbody">

                        <h1 className="text-center">Jobs List</h1>

                        <Table striped bordered hover size="sm" id="sortTable">
                            <thead>
                                <tr>
                                    <th>Job Title</th>
                                    <th>Company</th>
                                    <th>Location</th>
                                </tr>
                            </thead>

                            <tbody>
                                {
                                    this.state.jobs.map(
                                        job =>
                                            <tr key = {job.jobId} >
                                                <td>{job.title}</td>
                                                <td>{job.company}</td>
                                                <td>{job.location}</td>
                                            </tr>
                                    )
                                }

                            </tbody>
                        </Table>

                    </div>
                </div>
            </main>
        );
    }

}


export default JobListComponent;