import React, {useEffect, useState} from 'react';
import {Form} from "react-bootstrap";
import '../css/Login-Register.css';
import "tailwindcss/tailwind.css";
import {Link, useHistory} from "react-router-dom";
import {registerEmployer} from "../../Services/UserService";
import {hotjar} from "react-hotjar";

const EmployerRegister = () => {

    const [firstName, setFirstname] = useState('');
    const [lastName, setLastname] = useState('');
    const [company, setCompany] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const history = useHistory();

    const saveEmployerDetails = (e) => {
        e.preventDefault();

        const user = {email, password, firstName, lastName, company}

        registerEmployer(user)
            .then(res => {
                console.log("Data added successfully", res.data);
                history.push('/careers/employer/dashboard')
            })

            .catch(onerror => {
                console.log('Something went wrong', onerror);
                // history.push('/careers/employer/dashboard')
            });
    }

    useEffect( () => {
        hotjar.initialize(2738985, 6);
    }, [] )


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
                                          id='firstname'
                                          value={firstName}
                                          onChange={(e) => setFirstname(e.target.value)}
                            />
                        </Form.Group>

                        <Form.Group className="mt-3" controlId="Last name">
                            <Form.Label>Last name</Form.Label>
                            <Form.Control type="text"
                                          placeholder="Enter your last name"
                                          id='lastname'
                                          value={lastName}
                                          onChange={(e) => setLastname(e.target.value)}
                            />
                        </Form.Group>

                        <Form.Group className="mt-3" controlId="Company">
                            <Form.Label>Company</Form.Label>
                            <Form.Select type="text"
                                          id='company'
                                          value={company}
                                          onChange={(e) => setCompany(e.target.value)}
                            >
                                <option>Please select..</option>
                                <option value="Cycom">Cycom</option>
                                <option value="Google">Google</option>
                                <option value="Microsoft">Microsoft</option>
                                <option value="Metaverse">Metaverse</option>
                                <option value="Admiral">Admiral</option>
                                <option value="Tesco">Tesco</option>
                            </Form.Select>
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
                            <button type="submit" className="btn btn-secondary" variant="primary" onClick={(e) => saveEmployerDetails(e)}>Sign Up</button>
                        </center>

                        <center className='mt-4'>
                            <h2>
                                By clicking the "Sign Up" button, you are
                                creating an account, and you agree to the
                                <span className='h2_clickable'> Terms of use</span>.
                            </h2>
                        </center>

                    </div>
                </Form>

            </main>
        </div>
    );
}

export default EmployerRegister;
