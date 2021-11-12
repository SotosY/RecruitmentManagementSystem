import React, {Component} from 'react';
import '../css/Breadcrumb.css';

class ApplicantDashboard extends Component {

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
                            <button type="submit" className="btn btn-secondary" variant="primary" id="submit">LOG OUT</button>
                        </div>
                    </section>
                    <h2 className="text-center">Dashboard</h2>
                </div>
            </div>
        );
    }
}

export default ApplicantDashboard;