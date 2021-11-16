import React, {Component} from 'react';
import '../css/Breadcrumb.css';
import {Link} from "react-router-dom";
import {getApplicantDashboard} from "../../Services/ApplicantService";

class ApplicantDashboard extends Component {

    handleLogout() {
        localStorage.clear();
        window.location.href = "/careers/login";
    }

    constructor(props) {
        super(props);

    }


    render() {
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
                                    href="javascript:void(0);"
                                    onClick={this.handleLogout}
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
}

export default ApplicantDashboard;