import React, {Component} from 'react';
import {Form, Row} from "react-bootstrap";
import {Button} from "bootstrap";
import Logo from "../files/add_a_photo_grey.png";
import '../css/Profile.css';
import "tailwindcss/tailwind.css";


const ApplicantProfile = () => {

    function handleLogout() {
        localStorage.clear();
        window.location.href = "/careers/login";
    }

        return (
            <div>
                <div className='container'>
                    <section>
                        <div className='container-breadcrumb'>
                            <ol className="breadcrumb">
                                <li><a href="http://localhost:3000/careers/applicant/dashboard">APPLICANT DASHBOARD</a></li>
                                <li className='active'>PROFILE</li>
                            </ol>
                        </div>
                        <div className='container-button'>
                            <button type="submit"
                                    className="btn btn-secondary"
                                    variant="primary"
                                    href="javascript:void(0);"
                                    onClick={handleLogout}
                                    id="submit">LOG OUT</button>
                        </div>
                    </section>

                    <Form>
                        <div className='container-md' style={{width:"90%", marginTop:"40px"}}>
                            <Row className="mx-3 mt-4 mb-3">
                                <div className="column_picture">
                                    <Form.Group controlId="formPic" >
                                        <img className="img_picture"
                                        src={Logo}></img>
                                    </Form.Group>
                                </div>

                                <Form.Group className="column_right"  controlId="col_firstName_email">
                                    <Form.Label className="ml-4">First Name:</Form.Label>
                                    <Form.Control type="text" />
                                    <Form.Label className="mt-3">Email:</Form.Label>
                                    <Form.Control type="email" />
                                </Form.Group>

                                <Form.Group className="column_right"  controlId="col_lastname_password">
                                    <Form.Label>Last Name:</Form.Label>
                                    <Form.Control type="text"/>
                                    <Form.Label className="mt-3">Password:</Form.Label>
                                    <Form.Control type="password" />
                                </Form.Group>
                            </Row>
                        </div>

                        <h2 className="h2_profile_font" >Personal Details</h2>
                        <div className='container-md' style={{width:"90%", borderTop: "1px solid #B5DC10"}}>
                            <Row className="mx-3 mt-4">
                                <Form.Group className="column_50" controlId="col_address_country">
                                    <Form.Label>Address:</Form.Label>
                                    <Form.Control type="text" placeholder="Enter your address" />
                                    <Form.Label className="mt-3">Country:</Form.Label>
                                    <Form.Control type="text" placeholder="Enter your country" />
                                </Form.Group>

                                <Form.Group className="column_50"  controlId="col_business_company">
                                    <Form.Label>Date of Birth:</Form.Label>
                                    <Form.Control type="text" placeholder="Enter your Date of Birth" />
                                    <Form.Label className="mt-3">Gender:</Form.Label>
                                    <Form.Control type="text" placeholder="Enter your gender" />
                                </Form.Group>
                            </Row>

                            <Row className="mx-3 mt-4">
                                <Form.Group className="column_50" controlId="formGridEmail">
                                    <Form.Label>City:</Form.Label>
                                    <Form.Control type="text" placeholder="Enter city" />
                                    <Form.Label className="mt-3">Postcode:</Form.Label>
                                    <Form.Control className="mb-5"  type="text" placeholder="Enter postcode" />
                                </Form.Group>

                                <Form.Group className="column_50"  controlId="formGridPassword">
                                    <Form.Label>Phone Number:</Form.Label>
                                    <Form.Control type="text" placeholder="Enter your phone number" />
                                </Form.Group>
                            </Row>
                        </div>

                        <h2 className="h2_profile_font">Experience & Education Details</h2>
                        <div className='container-md' style={{width:"90%", borderTop: "1px solid #B5DC10"}}>
                            <Form.Group className="mx-3 mt-4"  controlId="col_company_prof">
                                <Form.Label>Education: (Optional)</Form.Label>
                                <Form.Control className="textarea_box" as="textarea" type="text" placeholder="Say few things about your education..." />
                                <Form.Label className="mt-3">Experience: (Optional)</Form.Label>
                                <Form.Control  as="textarea" className="textarea_box" type="text" placeholder="Say few things about your experience..." />
                            </Form.Group>
                        </div>

                        <center className='mt-5'>
                            <button type="submit" className="btn btn-secondary" variant="primary" >Save Details</button>
                        </center>

                    </Form>
                </div>
            </div>
        );

}

export default ApplicantProfile;