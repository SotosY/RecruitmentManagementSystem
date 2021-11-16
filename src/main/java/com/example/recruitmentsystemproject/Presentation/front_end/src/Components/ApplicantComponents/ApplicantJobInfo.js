import React, {Component} from 'react';

class ApplicantJobInfo extends Component {

    handleLogout() {
        localStorage.clear();
        window.location.href = "/careers/login";
    }

    render() {
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
                                    href="javascript:void(0);"
                                    onClick={this.handleLogout}
                                    id="submit">LOG OUT</button>
                        </div>
                    </section>
                    <h2 className="text-center">Job Info</h2>
                </div>
            </div>
        );
    }
}

export default ApplicantJobInfo;