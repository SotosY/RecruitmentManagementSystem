import React, {Component} from 'react';

class ApplicantApplication extends Component {

    render() {
        return (
            <div>
                <div className='container'>
                    <section>
                        <div className='container-breadcrumb'>
                            <ol className="breadcrumb">
                                <li><a href="http://localhost:3000/careers/applicant/dashboard">APPLICANT DASHBOARD</a></li>
                                <li className='active'>APPLICATION</li>
                            </ol>
                        </div>
                        <div className='container-button'>
                            <button type="submit" className="btn btn-secondary" variant="primary" id="submit">LOG OUT</button>
                        </div>
                    </section>
                    <h2 className="text-center">Application</h2>
                </div>
            </div>
        );
    }
}

export default ApplicantApplication;