import React, {Component, useEffect, useState} from 'react';
import {Form} from "react-bootstrap";
import '../css/Login-Register.css';
import {Link, useHistory} from "react-router-dom";
import {registerApplicant} from "../../Services/UserService";
import {hotjar} from "react-hotjar";
import {useForm} from "react-hook-form";

const ApplicantRegister = () => {

    const history = useHistory();

    const {
        register,
        handleSubmit,
        formState: {errors},
    } = useForm();

    const saveApplicantDetails = (e) => {

        const firstName = e.firstName
        const lastName = e.lastName
        const email = e.email
        const password = e.password

        const user = {email, password, firstName, lastName}

        registerApplicant(user)
            .then(res => {
                console.log("Data added successfully", res.data);
                history.push('/careers/applicant/dashboard')
            })

            .catch(onerror => {
                console.log('Something went wrong', onerror);
                // history.push('/careers/applicant/dashboard')
            });
    }

    const onSubmit = (e) => {
        saveApplicantDetails(e)
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

                    <Form className='container-login' onSubmit={handleSubmit(onSubmit)}>

                        <h1>Register</h1>
                        <h2>Already have an account? <Link to='/careers/login' className='h2_clickable'>Sign in</Link></h2>

                        <div className='container-login-inside'>

                            <Form.Group className="mt-1" controlId="First name">
                                <Form.Label>First name</Form.Label>
                                <Form.Control type="text"
                                              placeholder="Enter your first name"
                                              id='firstName'
                                              {...register("firstName", {
                                                  required: "First Name is required",
                                                  pattern: {
                                                      value: /^[a-zA-Z]+$$/i,
                                                      message: "Must contain only letters"
                                                  }})}
                                />
                                {errors.firstName && (<small className="text-danger">{errors.firstName.message}</small>)}
                            </Form.Group>

                            <Form.Group className="mt-3" controlId="Last name">
                                <Form.Label>Last name</Form.Label>
                                <Form.Control type="text"
                                              placeholder="Enter your last name"
                                              id='lastName'
                                              {...register("lastName", {
                                                  required: "Last Name is required",
                                                  pattern: {
                                                      value: /^[a-zA-Z]+$$/i,
                                                      message: "Must contain only letters"
                                                  }})}
                                />
                                {errors.lastName && (<small className="text-danger">{errors.lastName.message}</small>)}
                            </Form.Group>

                            <Form.Group className="mt-3" controlId="formBasicEmail">
                                <Form.Label>Email</Form.Label>
                                <Form.Control type="email"
                                              placeholder="Enter your email"
                                              id='email'
                                              {...register("email", {
                                                  required: "Email is required",
                                                  pattern: {
                                                      value: /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i,
                                                      message: "Please enter a valid email address"
                                                  }}
                                              )}
                                />
                                {errors.email && (<small className="text-danger">{errors.email.message}</small>)}

                            </Form.Group>

                            <Form.Group className="mt-3" controlId="formBasicPassword">
                                <Form.Label>Password</Form.Label>
                                <Form.Control type="password"
                                              placeholder="Enter your password"
                                              id='password'
                                              {...register("password", {
                                                  required: "Password is required",
                                                  minLength: {
                                                      value: 8,
                                                      message: "Must be at least 8 characters"
                                                  }})}
                                />
                                {errors.password && (<small className="text-danger">{errors.password.message}</small>)}

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

export default ApplicantRegister;