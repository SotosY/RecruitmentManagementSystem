import React, {Component, useEffect, useState, useRef, useLayoutEffect} from 'react';
import {Form, Modal, Row, Button} from "react-bootstrap";
import Logo from "../files/add_a_photo_grey.png";
import '../css/Profile.css';
import "tailwindcss/tailwind.css";
import {useHistory} from "react-router-dom";
import {getApplicantDetails, saveApplicantProfileDetails} from "../../Services/ApplicantService";
import {hotjar} from "react-hotjar";




const ApplicantProfile = () => {

    const [applicant, setApplicant] = useState("");
    const [address, setAddress] = useState("");
    const [city, setCity] = useState("");
    const [country, setCountry] = useState("");
    const [postcode, setPostcode] = useState("");
    const [dateOfBirth, setDateOfBirth] = useState("");
    const [gender, setGender] = useState("");
    const [education, setEducation] = useState("");
    const [experience, setExperience] = useState("");
    const [phoneNumber, setPhoneNumber] = useState("");

    const history = useHistory();

    const [show, setShow] = useState(false);

    const getApplicant = () => {
        getApplicantDetails().then((res) => {
            const data = res.data;
            setApplicant(data)
            setAddress(data.applicant.address)
            setCity(data.applicant.city)
            setCountry(data.applicant.country)
            setPostcode(data.applicant.postcode)
            setDateOfBirth(data.applicant.dateOfBirth)
            setGender(data.applicant.gender)
            setPhoneNumber(data.applicant.phoneNumber)
            setEducation(data.education)
            setExperience(data.experience)
            console.log(data)
        })
            .catch((error) => {
                console.log(error)
            })
    }

    useEffect( () => {
        getApplicant();
        hotjar.initialize(2738985, 6);
    }, [] )

    const saveApplicantProfile = (e) => {
        e.preventDefault();

        const applicant = {address, city, country, postcode, dateOfBirth, gender, phoneNumber, education, experience}

        saveApplicantProfileDetails(applicant)
            .then(res => {
                console.log("Data added successfully", res.data);
                history.push('/careers/applicant/profile')
            })

            .catch(onerror => {
                console.log('Something went wrong', onerror);
            });

    }

    function handleChange(event) {
        const value = event.target.value;
        setApplicant({
            applicant,
            [event.target.name]: value
        });
    }

    function handleLogout() {
        localStorage.clear();
        window.location.href = "/careers/login";
    }

    function isSet(actualVal, defaultVal){
        if(typeof actualVal != 'undefined'){
            return actualVal;
        }
        console.log('The actual value is undefined. This is the default one');
        return defaultVal;
    }

    const handleScroll = () => {
        window.scrollTo({top: 0, behavior: "smooth"})
    }

    const handleClose = () => {
        handleScroll();
        setShow(false);
    }

    const handleShow = (e) => {
        e.preventDefault();
        setShow(true);
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
                                    <Form.Control style={{color:"#AAAAAA", borderStyle:"none", backgroundColor:"#F0FFF0"}} type="text" value={applicant.applicant?.firstName} disabled={true}></Form.Control>
                                    <Form.Label className="mt-3">Email:</Form.Label>
                                    <Form.Control style={{color:"#AAAAAA", borderStyle:"none", backgroundColor:"#F0FFF0"}} type="text" value={applicant.applicant?.user?.email} disabled={true}></Form.Control>
                                </Form.Group>

                                <Form.Group className="column_right"  controlId="col_lastname_password">
                                    <Form.Label>Last Name:</Form.Label>
                                    <Form.Control style={{color:"#AAAAAA", borderStyle:"none", backgroundColor:"#F0FFF0"}} type="text" value={applicant.applicant?.lastName} disabled={true}></Form.Control>
                                    <Form.Label className="mt-3">Password:</Form.Label>
                                    <Form.Control style={{color:"#AAAAAA", borderStyle:"none", backgroundColor:"#F0FFF0"}} type="password" value={applicant.applicant?.user?.password} disabled={true}></Form.Control>
                                </Form.Group>
                            </Row>
                        </div>

                        <h2 className="h2_profile_font" >Personal Details</h2>
                        <div className='container-md' style={{width:"90%", borderTop: "1px solid #B5DC10"}}>
                            <Row className="mx-3 mt-4">
                                <Form.Group className="column_50" controlId="col_address_country">
                                    <Form.Label>Address:</Form.Label>
                                    <Form.Control type="text" placeholder="Enter your address" value={applicant.applicant?.address} onChange={(e) => {setAddress(e.target.value); handleChange(e)}} />
                                    <Form.Label className="mt-3">Country:</Form.Label>
                                    <Form.Control type="text" placeholder="Enter your country" value={applicant.applicant?.country} onChange={(e) => {setCountry(e.target.value); handleChange(e)}} />
                                </Form.Group>

                                <Form.Group className="column_50"  controlId="col_business_company">
                                    <Form.Label>Date of Birth:</Form.Label>
                                    <Form.Control type="text" placeholder="Enter your Date of Birth" value={applicant.applicant?.dateOfBirth} onChange={(e) => {setDateOfBirth(e.target.value); handleChange(e)}} />
                                    <Form.Label className="mt-3">Gender:</Form.Label>
                                    <Form.Control type="text" placeholder="Enter your gender" value={applicant.applicant?.gender} onChange={(e) => {setGender(e.target.value); handleChange(e)}} />
                                </Form.Group>
                            </Row>

                            <Row className="mx-3 mt-4">
                                <Form.Group className="column_50" controlId="formGridEmail">
                                    <Form.Label>City:</Form.Label>
                                    <Form.Control type="text" placeholder="Enter city" value={applicant.applicant?.city} onChange={(e) => {setCity(e.target.value); handleChange(e)}} />
                                    <Form.Label className="mt-3">Postcode:</Form.Label>
                                    <Form.Control className="mb-5"  type="text" placeholder="Enter postcode" value={applicant.applicant?.postcode} onChange={(e) => {setPostcode(e.target.value); handleChange(e)}} />
                                </Form.Group>

                                <Form.Group className="column_50"  controlId="formGridPassword">
                                    <Form.Label>Phone Number:</Form.Label>
                                    <Form.Control type="text" placeholder="Enter your phone number" value={applicant.applicant?.phoneNumber} onChange={(e) => {setPhoneNumber(e.target.value); handleChange(e)}} />
                                </Form.Group>
                            </Row>
                        </div>

                        <h2 className="h2_profile_font">Experience & Education Details</h2>
                        <div className='container-md' style={{width:"90%", borderTop: "1px solid #B5DC10"}}>
                            <Form.Group className="mx-3 mt-4"  controlId="col_company_prof">
                                <Form.Label>Education: (Optional)</Form.Label>
                                <Form.Control className="textarea_box" as="textarea" type="text" placeholder="Say few things about your education..." value={applicant.education} onChange={(e) => {setEducation(e.target.value); handleChange(e)}} />
                                <Form.Label className="mt-3">Experience: (Optional)</Form.Label>
                                <Form.Control  as="textarea" className="textarea_box" type="text" placeholder="Say few things about your experience..." value={applicant.experience} onChange={(e) => {setExperience(e.target.value); handleChange(e)}}/>
                            </Form.Group>
                        </div>

                        <center className='mt-5'>
                            <button type="submit" className="btn btn-secondary" variant="primary" onClick={(e) => {saveApplicantProfile(e); handleShow(e)}} >Save Details</button>
                        </center>

                        <Modal
                            show={show}
                            onHide={handleClose}
                        >
                            <Modal.Header closeButton onClick={handleClose}>
                                <Modal.Title>SUCCESS</Modal.Title>
                            </Modal.Header>
                            <Modal.Body>
                                Your details have been saved successfully!
                            </Modal.Body>
                            <Modal.Footer>
                                <Button variant="primary" onClick={handleClose}>OK</Button>
                            </Modal.Footer>
                        </Modal>

                    </Form>
                </div>
            </div>
        );

}

export default ApplicantProfile;