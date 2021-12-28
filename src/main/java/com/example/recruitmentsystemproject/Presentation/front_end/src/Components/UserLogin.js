import React, {useEffect, useState} from 'react';
import {Form} from "react-bootstrap";
import './css/Login-Register.css';
import {Link, Redirect, useHistory} from "react-router-dom";
import {getErrorLogin, getLogin, loginEmployer} from "../Services/UserService";
import {hotjar} from "react-hotjar";
import axios from "axios";

const UserLogin = () => {

    const [session, setSession] = useState()
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [roles, setRoles] = useState('');
    const history = useHistory();

    const getLoginHeader = () => {
        getLogin().then((res) => {
        })
    }

    useEffect( () => {
        getLoginHeader();
        hotjar.initialize(2738985, 6);
    }, [] )

    const loginUser = (e) => {
        e.preventDefault();

        const user = new FormData();
        user.append("username",username)
        user.append("password",password)
        console.log(user)
        loginEmployer(user)
            .then(res => {
                    console.log("Data added successfully", res.data);
                    history.push('/careers/applicant/dashboard')
            })

            .catch(onerror => {
                console.log('Something went wrong', onerror);
                history.push('/careers/applicant/dashboard')
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