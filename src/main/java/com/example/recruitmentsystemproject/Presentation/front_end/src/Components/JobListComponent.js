import React, {Component, useState} from 'react';
import './css/Breadcrumb.css';
import UserService from "../Services/JobService";
import {Table, DropdownButton, ButtonGroup, Dropdown} from "react-bootstrap";


class JobListComponent extends Component {

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
            <main>
                <section className={'section-outside'}>
                    <div className='container-breadcrumb'>
                        <ol className="breadcrumb">
                            <li className='active'>CAREERS</li>
                        </ol>
                    </div>
                    <div className='container-button'>
                        <button type="submit" className="btn btn-secondary" id="btn_login" style={{marginRight: '10px'}}>SIGN IN</button>
                        <DropdownButton as={ButtonGroup} title="REGISTER" id="bg-vertical-dropdown-3" variant="secondary">
                            <Dropdown.Item eventKey="1">AS A USER</Dropdown.Item>
                            <Dropdown.Item eventKey="2">AS A COMPANY</Dropdown.Item>
                        </DropdownButton>
                    </div>
                </section>

                <div className='container'>
                    <div className="mainbody">

                        <h2 className="text-center">Jobs List</h2>

                        <Table striped bordered hover size="sm" id="sortTable">
                            <thead>
                                <tr>
                                    <th>Job ID</th>
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
                                                <td>{job.jobId}</td>
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