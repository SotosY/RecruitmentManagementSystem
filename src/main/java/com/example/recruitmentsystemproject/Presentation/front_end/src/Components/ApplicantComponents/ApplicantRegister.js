import React, {Component, useState} from 'react';
import {Form} from "react-bootstrap";
import '../css/Login-Register.css';
import {Link, useHistory} from "react-router-dom";
import {registerApplicant} from "../../Services/UserService";

const ApplicantRegister = () => {

    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const history = useHistory();

    const saveApplicantDetails = (e) => {
        e.preventDefault();

        const user = {email, password, firstName, lastName}
        registerApplicant(user)
            .then(res => {
                console.log("Data added successfully", res.data);
                history.push('/careers/applicant/dashboard')
            })

            .catch(onerror => {
                console.log('Something went wrong', onerror);
                history.push('/careers/applicant/dashboard')
            });
    }

        return (
            <div>
                <main>
                    <section className='section-outside'>
                        <ol className="breadcrumb">
                            <li><Link to="/careers">CAREERS</Link></li>
                            <li className='active'>REGISTER</li>
                        </ol>
                    </section>

                    <Form className='container-login'>

                        <h1>Register</h1>
                        <h2>Already have an account? <Link to='/careers/login' className='h2_clickable'>Sign in</Link></h2>

                        <div className='container-login-inside'>

                            <Form.Group className="mt-1" controlId="First name">
                                <Form.Label>First name</Form.Label>
                                <Form.Control type="text"
                                              placeholder="Enter your first name"
                                              id='firstName'
                                              value={firstName}
                                              onChange={(e) => setFirstName(e.target.value)}
                                />
                            </Form.Group>

                            <Form.Group className="mt-3" controlId="Last name">
                                <Form.Label>Last name</Form.Label>
                                <Form.Control type="text"
                                              placeholder="Enter your last name"
                                              id='lastName'
                                              value={lastName}
                                              onChange={(e) => setLastName(e.target.value)}
                                />
                            </Form.Group>

                            <Form.Group className="mt-3" controlId="formBasicEmail">
                                <Form.Label>Email</Form.Label>
                                <Form.Control type="email"
                                              placeholder="Enter your email"
                                              id='email'
                                              value={email}
                                              onChange={(e) => setEmail(e.target.value)}

                                />
                            </Form.Group>

                            <Form.Group className="mt-3" controlId="formBasicPassword">
                                <Form.Label>Password</Form.Label>
                                <Form.Control type="password"
                                              placeholder="Enter your password"
                                              id='password'
                                              value={password}
                                              onChange={(e) => setPassword(e.target.value)}
                                />
                            </Form.Group>

                            <center className='mt-5'>
                                <button type="submit" className="btn btn-secondary" variant="primary" onClick={(e) => saveApplicantDetails(e)}>Sign Up</button>
                            </center>

                            <center className='mt-4'>
                                <h2>By clicking the "Sign Up" button, you are creating
                                    an account, and you agree to the <span className='h2_clickable'>Terms of use</span>.
                                </h2>
                            </center>

                        </div>
                    </Form>

                </main>
            </div>
        );
}

export default ApplicantRegister;