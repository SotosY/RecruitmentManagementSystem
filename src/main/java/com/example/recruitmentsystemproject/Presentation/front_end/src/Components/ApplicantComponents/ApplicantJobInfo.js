import React, {Component, useEffect, useState} from 'react';
import '../css/JobInfo.css';
import {getJobDetails} from "../../Services/ApplicantService";
import {useHistory, useLocation} from "react-router-dom";
import {HiOutlineChevronRight} from "react-icons/hi";
import {HiOutlineChevronDoubleRight} from "react-icons/hi";
import {hotjar} from "react-hotjar";
import {getLogout} from "../../Services/UserService";

// Get Job Details page
const ApplicantJobInfo = () => {

    const [job, setJob] = useState("");
    const location = useLocation();
    const history = useHistory();
    const getId = location.state;

    // Get Job details
    const getJob = () => {

        // Calls Jobs Details service
        getJobDetails(getId).then((res) => {
            const data = res.data;
            setJob(data)
        })
    }

    // UseEffect functionality
    useEffect( () => {

        // Calls getJob
        getJob();

        // Initialize Hotjar
        hotjar.initialize(2805905, 6);

    }, [] )

    // Shows Job details
    function handleJobInfo(id){
        history.push({pathname:`/careers/applicant/application/job/${id}/application`,
            state:id})
    }

    // Logout functionality
    function handleLogout() {
        getLogout()
        localStorage.clear();
        window.location.href = "/careers/login";
    }

        return (
            <div>
                <div className='container'>
                    <section>
                        <div className='container-breadcrumb'>
                            <ol className="breadcrumb">
                                <li><a href="http://localhost:3000/careers/applicant/dashboard">APPLICANT DASHBOARD</a></li>
                                <li><a href="http://localhost:3000/careers/applicant/application">APPLICATION</a></li>
                                <li className='active'>JOB INFO</li>
                            </ol>
                        </div>
                        <div className='container-button'>
                            <button type="submit"
                                    className="btn btn-secondary"
                                    variant="primary"
                                    onClick={handleLogout}
                                    id="submit">LOG OUT</button>
                        </div>
                    </section>
                    <div className="container-md">
                        <div className="mt-4" style={{backgroundColor:"#DDDDDD", height:"auto"}}>
                            <div className="inline">
                                <h1 className="h2-title" style={{padding:"40px",fontSize:"30px", textAlign:"center"}}>{job.title}</h1>
                            </div>
                            <div className="inline" style={{height:"max-content", float:"right"}}>
                                <button type="submit" variant="secondary" className="btn btn-apply" style={{float:"right", marginTop:"37px"}} onClick={() => handleJobInfo(getId)}>APPLY<HiOutlineChevronRight size="20" className="btn-icon"/></button>
                            </div>
                        </div>
                        <div>

                        </div>
                        <div className="mt-3" style={{textAlign:"left"}}>
                            <div tabIndex="0" className="inline">
                                <h1 className="h2-title" style={{marginLeft:"10px"}}>ID:</h1>
                                <h2 className="h2-value" style={{marginLeft:"10px"}}>{job.jobId}</h2>
                            </div>
                            <div tabIndex="0" className="inline">
                                <h1 className="h2-title">Company:</h1>
                                <h2 className="h2-value">{job.company}</h2>
                            </div>
                            <div tabIndex="0" className="inline">
                                <h1 className="h2-title">Salary:</h1>
                                <h2 className="h2-value">€{job.salary}</h2>
                            </div>
                            <div tabIndex="0" className="inline">
                                <h1 className="h2-title">Starting Date:</h1>
                                <h2 className="h2-value">{job.startingDate}</h2>
                            </div>
                            <div tabIndex="0" className="inline">
                                <h1 className="h2-title">Location:</h1>
                                <h2 className="h2-value">{job.location}</h2>
                            </div>
                            <div tabIndex="0" className="inline">
                                <h1 className="h2-title">Managed By:</h1>
                                <h2 className="h2-value">{job.managedBy}</h2>
                            </div>
                            <div tabIndex="0" className="container-md">
                                <h1 className="h2-title" style={{marginTop:"50px",fontSize:"18px", textAlign:"left", color:"#B5DC10"}}>JOB DESCRIPTION</h1>
                                <div className="container-md" style={{borderTop:"solid #B5DC10 1px", height:"auto"}}>
                                    <p>{job.description}</p>
                                </div>
                                <h1 className="h2-title" style={{marginTop:"40px",fontSize:"18px", textAlign:"left", color:"#B5DC10"}}>JOB REQUIREMENTS</h1>
                                <div className="container-md" style={{borderTop:"solid #B5DC10 1px", height:"auto"}}>
                                    <p>{job.requirements}</p>
                                </div>
                                <h1 className="h2-title" style={{marginTop:"40px",fontSize:"18px", textAlign:"left", color:"#B5DC10"}}>ESSENTIAL CRITERIA </h1>
                                <div className="container-md" style={{borderTop:"solid #B5DC10 1px", height:"auto"}}>
                                    <p>{job.essentialCriteria}</p>
                                </div>
                                <h1 className="h2-title" style={{marginTop:"40px",fontSize:"18px", textAlign:"left", color:"#B5DC10"}}>DESIRABLE CRITERIA</h1>
                                <div className="container-md" style={{borderTop:"solid #B5DC10 1px", height:"auto"}}>
                                    <p>{job.desirableCriteria}</p>
                                </div>
                                <h1 className="h2-title" style={{marginTop:"40px",fontSize:"18px", textAlign:"left", color:"#B5DC10"}}>SALARY & BENEFITS</h1>
                                <div className="container-md" style={{borderTop:"solid #B5DC10 1px", height:"auto"}}>
                                    <p>{job.salaryAndBenefits}</p>
                                </div>
                            </div>
                        </div>



                    </div>
                </div>
            </div>
        );
}

export default ApplicantJobInfo;