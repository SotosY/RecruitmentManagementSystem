import React, {useEffect, useState} from 'react';
import {Form} from "react-bootstrap";
import '../css/Login-Register.css';
import {Link, Redirect, useHistory} from "react-router-dom";
import {getErrorLogin, getLogin, loginEmployer} from "../../Services/UserService";
import {hotjar} from "react-hotjar";
import axios from "axios";

// Login Page functionality
const UserLogin = () => {

    const [session, setSession] = useState()
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [roles, setRoles] = useState('');
    const history = useHistory();

    // Get Login form
    const getLoginHeader = () => {

        // Calls getLogin service
        getLogin().then((res) => {
        })
    }

    // UseEffect functionality
    useEffect( () => {

        // Calls getLoginHeader
        getLoginHeader();

        // Initialize Hotjar
        hotjar.initialize(2805905, 6);

    }, [] )

    // Submit login details
    const loginUser = (e) => {
        e.preventDefault();

        const user = new FormData();
        user.append("username",username)
        user.append("password",password)

        // Calls login user functionality
        loginEmployer(user)
            .then(res => {
                    setRoles(res.data?.roles)

                    if (res.data?.roles === "EMPLOYER"){
                        history.push('/careers/employer/dashboard')
                    } else if (res.data?.roles === "APPLICANT") {
                        history.push('/careers/applicant/dashboard')
                    } else if (res.data?.roles === null){
                        history.push('/careers/login')
                    }

                console.log("Data added successfully", res.data);
            })

            .catch(onerror => {
                console.log('Something went wrong', onerror);
                // history.push('/careers/login')
            });
    }

        return (
            <main>
                <section className='section-outside'>
                    <ol className="breadcrumb">
                        <li><Link to="/careers" >CAREERS</Link></li>
                        <li className='active'>SIGN IN</li>
                    </ol>
                </section>

                <Form className='container-login'>

                    <h1>Sign In</h1>
                    <h2>Don't have an account? <Link to='/careers' className='h2_clickable'>Register</Link></h2>

                    {/*<div className='mt-4'>*/}
                    {/*    <div if={param.error} className="alert alert-danger">*/}
                    {/*        Invalid username and password.*/}
                    {/*    </div>*/}
                    {/*    <div if="${param.logout}" className="alert alert-success">*/}
                    {/*        You have been logged out.*/}
                    {/*    </div>*/}
                    {/*</div>*/}

                    <div className='container-login-inside'>
                        <Form.Group className="mt-1" controlId="formBasicEmail">
                            <Form.Label>Email</Form.Label>
                            <Form.Control
                                type="text"
                                placeholder="Enter your email"
                                value={username}
                                onChange={(e) => setUsername(e.target.value)}/>
                        </Form.Group>

                        <Form.Group className="mt-3" controlId="formBasicPassword">
                            <Form.Label>Password</Form.Label>
                            <Form.Control
                                type="password"
                                placeholder="Enter your password"
                                value={password}
                                onChange={(e) => setPassword(e.target.value)}
                            />
                        </Form.Group>
                        <Form.Group className="mt-3" controlId="formBasicCheckbox">
                            <Form.Check type="checkbox" label="Remember me" />
                        </Form.Group>

                        <center className='mt-5'>
                            <button type="submit" className="btn btn-secondary" variant="primary" onClick={(e) => loginUser(e)}>Sign In
                            </button>
                        </center>

                    </div>
                </Form>

            </main>
        );
}

export default UserLogin;