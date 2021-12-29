import React, {Component} from 'react';
import '../css/Breadcrumb.css';
import {Link} from "react-router-dom";
import {hotjar} from "react-hotjar";

// Get Employer Dashboard page
class EmployerDashboard extends Component {

    // ComponentDidMount functionality
    async componentDidMount() {

        // Initialize Hotjar
        hotjar.initialize(2738985, 6);

    }

    render() {
        return (
            <div>
                <div className='container'>
                    <section>
                        <div className='container-breadcrumb'>
                            <ol className="breadcrumb">
                                <li className='active'>EMPLOYER DASHBOARD</li>
                            </ol>
                        </div>
                        <div className='container-button'>
                            <button type="submit" className="btn btn-secondary" variant="primary" id="submit">LOG OUT</button>
                        </div>
                    </section>

                    <div className="row">
                        <Link to="/careers/employer/profile" className="column">
                            <h1>Profile</h1>
                        </Link>
                        <Link to="/careers/employer/vacancies" className="column">
                            <h1>Vacancies</h1>
                        </Link>
                        <Link to="/careers/employer/vacancy-history" className="column">
                            <h1>Vacancy History</h1>
                        </Link>
                    </div>
                </div>
            </div>
        );
    }
}

export default EmployerDashboard;