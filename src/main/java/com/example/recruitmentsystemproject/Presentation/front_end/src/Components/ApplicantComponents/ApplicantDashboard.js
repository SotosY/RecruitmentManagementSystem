import React, {Component, useEffect, useState} from 'react';
import '../css/Breadcrumb.css';
import {Link} from "react-router-dom";
import {getApplicantDashboard} from "../../Services/ApplicantService";
import {hotjar} from "react-hotjar";

// Get Applicant Dashboard page
const ApplicantDashboard = () => {

    const [applicant, setApplicant] = useState("");

    // Get Applicant
    const getApplicant = () =>{

        // Calls getApplicantDashboard service
        getApplicantDashboard().then((res) =>{
        const data = res.data;
        setApplicant(data)
        })
    }

    //UseEffect functionality
    useEffect( () => {

        // Calls getApplicant
        getApplicant();

        // Initialize Hotjar
        hotjar.initialize(2738985, 6);

    }, [] )

    // Logout functionality
    function handleLogout() {
        localStorage.clear();
        window.location.href = "/careers/login";
    }

        return (
            <div>
                <div className='container'>
                    <section>
                        <div className='container-breadcrumb'>
                            <ol className="breadcrumb">
                                <li className='active'>APPLICANT DASHBOARD</li>
                            </ol>
                        </div>
                        <div className='container-button'>
                            <button type="submit"
                                    onClick={handleLogout}
                                    className="btn btn-secondary"
                                    variant="primary"
                                    id="submit">LOG OUT</button>
                        </div>
                    </section>

                    <div className="row">

                        <Link to="/careers/applicant/profile" className="column">
                            <h1>Profile</h1>
                        </Link>
                        <Link to="/careers/applicant/application" className="column">
                            <h1>Application</h1>
                        </Link>
                        <Link to="/careers/applicant/application-history" className="column">
                            <h1>Application History</h1>
                        </Link>
                    </div>
                </div>
            </div>
        );

}

export default ApplicantDashboard;