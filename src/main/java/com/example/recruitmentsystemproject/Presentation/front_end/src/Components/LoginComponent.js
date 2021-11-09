import React, {Component} from 'react';
import {Form, Button} from "react-bootstrap";
import './css/Login-Register.css';

class LoginComponent extends Component {

    render() {
        return (

            <main>
                <section className='section-outside'>
                    <ol className="breadcrumb">
                        <li><a href="http://localhost:3000/careers">CAREERS</a></li>
                        <li className='active'>SIGN IN</li>
                    </ol>
                </section>

                <Form className='container-login'>

                    <h1>Sign In</h1>
                    <h2>Don't have an account? <span className='h2_clickable'>Register</span></h2>

                    {/*<div className='mt-4'>*/}
                    {/*    <div if="{param.error}" className="alert alert-danger">*/}
                    {/*        Invalid username and password.*/}
                    {/*    </div>*/}
                    {/*    <div if="${param.logout}" className="alert alert-success">*/}
                    {/*        You have been logged out.*/}
                    {/*    </div>*/}
                    {/*</div>*/}

                    <div className='container-login-inside'>
                        <Form.Group className="mt-1" controlId="formBasicEmail">
                            <Form.Label>Email</Form.Label>
                            <Form.Control type="email" placeholder="Enter your email" />
                        </Form.Group>

                        <Form.Group className="mt-3" controlId="formBasicPassword">
                            <Form.Label>Password</Form.Label>
                            <Form.Control onChange='' type="password" placeholder="Enter your password" />
                        </Form.Group>
                        <Form.Group className="mt-3" controlId="formBasicCheckbox">
                            <Form.Check type="checkbox" label="Remember me" />
                        </Form.Group>

                        <center className='mt-5'>
                            <button type="submit" className="btn btn-secondary" variant="primary">Sign In
                            </button>
                        </center>

                    </div>
                </Form>

            </main>
        );
    }
}

export default LoginComponent;