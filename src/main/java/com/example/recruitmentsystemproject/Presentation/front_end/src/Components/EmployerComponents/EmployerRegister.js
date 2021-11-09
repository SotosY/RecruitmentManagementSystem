import React, {Component} from 'react';
import {DropdownButton, Form, Dropdown, InputGroup} from "react-bootstrap";
import '../css/Login-Register.css';
import "tailwindcss/tailwind.css";

class EmployerRegister extends Component {
    render() {
        return (
            <div>
                <main>
                    <section className='section-outside'>
                        <ol className="breadcrumb">
                            <li><a href="http://localhost:3000/careers">CAREERS</a></li>
                            <li className='active'>REGISTER</li>
                        </ol>
                    </section>

                    <Form className='container-login'>

                        <h1>Register</h1>
                        <h2>Already have an account? <span className='h2_clickable'>Sign in</span></h2>

                        <div className='container-login-inside'>

                            <Form.Group className="mt-1" controlId="First name">
                                <Form.Label>First name</Form.Label>
                                <Form.Control type="text" placeholder="Enter your first name" />
                            </Form.Group>

                            <Form.Group className="mt-3" controlId="Last name">
                                <Form.Label>Last name</Form.Label>
                                <Form.Control type="text" placeholder="Enter your last name" />
                            </Form.Group>

                            <Form.Group className="mt-3" controlId="Company">
                                <Form.Label>Company</Form.Label>
                                <Form.Control type="text" placeholder="Enter the company name" />
                            </Form.Group>

                            <Form.Group className="mt-3" controlId="formBasicEmail">
                                <Form.Label>Email</Form.Label>
                                <Form.Control type="email" placeholder="Enter your email" />
                            </Form.Group>

                            <Form.Group className="mt-3" controlId="formBasicPassword">
                                <Form.Label>Password</Form.Label>
                                <Form.Control onChange='' type="password" placeholder="Enter your password" />
                            </Form.Group>

                            <center className='mt-5'>
                                <button type="submit" className="btn btn-secondary" variant="primary">Sign Up</button>
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
}

export default EmployerRegister;