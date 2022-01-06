import {Button, Form, Modal, Row} from "react-bootstrap";
import React, {useEffect, useState} from "react";
import {applyApplicantApplication, getApplicantApply} from "../../Services/ApplicantService";
import {useHistory, useLocation} from "react-router-dom";
import "tailwindcss/tailwind.css";
import '../css/Application.css';
import {hotjar} from "react-hotjar";
import {getLogout} from "../../Services/UserService";

// Get Applicant Apply page
const ApplicantApply = () => {

    const [application, setApplication] = useState("");
    const [applicationId, setApplicationId] = useState("");
    const [dateOfBirth, setDateOfBirth] = useState("");
    const [gender, setGender] = useState("");
    const [phoneNumber, setPhoneNumber] = useState("");
    const [education, setEducation] = useState("");
    const [experience, setExperience] = useState("");
    const [cv, setCV] = useState("");
    const [coverLetter, setCoverLetter] = useState("");

    const [url, setUrl] = useState("");
    const location = useLocation();
    const getId = location.state;

    const genderList = ["Please select a gender", "Male", "Female", "Other"]
    const [show, setShow] = useState(false);

    // Get Application form
    const getApplication = () => {

        // Calls getApplicantApply service
        getApplicantApply(getId).then((res) => {
            const data = res.data;
            setApplication(data)
            setApplicationId(data.applicationId)
            setDateOfBirth(data.applicant?.dateOfBirth)
            setGender(data.applicant?.gender)
            setPhoneNumber(data.applicant?.phoneNumber)
            setEducation(data.applicantResume?.education)
            setExperience(data.applicantResume?.experience)
            setCV(data.applicantResume?.cv)
            setCoverLetter(data.applicantResume?.coverLetter)
            setUrl(`http://localhost:3000/careers/applicant/application/job/${getId}`)
        })
    }

    // Apply application
    const applyApplication = (e) => {
        e.preventDefault();

        const application = {applicationId, dateOfBirth, gender, phoneNumber, education, experience, cv, coverLetter}

        // Calls applyApplicantApplication service
        applyApplicantApplication(application)
            .then(res => {
                console.log("Data added successfully", res.data);
            })

            .catch(onerror => {
                console.log('Something went wrong', onerror);
            });

    }

    // UseEffect functionality
    useEffect( () => {

        // Calls getApplication
        getApplication();

        // Initialize Hotjar
        hotjar.initialize(2738985, 6);

    }, [] )

    // Updates a field functionality
    function handleChange(event) {
        const value = event.target.value;
        setApplication({
            application,
            [event.target.name]: value
        });
    }

    // Logout functionality
    function handleLogout() {
        getLogout()
        localStorage.clear();
        window.location.href = "/careers/login";
    }

    // Scroll functionality
    const handleScroll = () => {
        window.scrollTo({top: 0, behavior: "smooth"})
    }

    // Modal close functionality
    const handleClose = () => {
        handleScroll();
        setShow(false);
    }

    // Modal show functionality
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
                            <li><a href="http://localhost:3000/careers/applicant/application">APPLICATION</a></li>
                            <li><a href={url}>JOB INFO</a></li>
                            <li className='active'>APPLY</li>
                        </ol>
                    </div>
                    <div className='container-button'>
                        <button type="submit"
                                className="btn btn-secondary"
                                variant="primary"
                                onClick={handleLogout}
                                id="submit">LOG OUT</button>
                    </div>
                </section>

                <Form>
                    <div className='container-md' style={{width:"80%", marginTop:"100px"}}>
                        <div className="container-md" style={{width:"500px"}}>
                            <Row className="mx-3 mt-4 mb-3">
                                <Form.Group controlId="col_lastname_password">
                                    <Form.Label>First Name:</Form.Label>
                                    <Form.Control style={{color:"#AAAAAA", borderStyle:"none", backgroundColor:"#F0FFF0"}}
                                                  type="text"
                                                  value={application.applicant?.firstName}>
                                    </Form.Control>
                                    <Form.Label className="mt-3">Last Name:</Form.Label>
                                    <Form.Control style={{color:"#AAAAAA", borderStyle:"none", backgroundColor:"#F0FFF0"}}
                                                  type="text"
                                                  value={application.applicant?.lastName}>
                                    </Form.Control>
                                    <Form.Label className="mt-3">Email:</Form.Label>
                                    <Form.Control style={{color:"#AAAAAA", borderStyle:"none", backgroundColor:"#F0FFF0"}}
                                                  type="text"
                                                  value={application.applicant?.user?.email}>
                                    </Form.Control>
                                    <Form.Label className="mt-3">Date of Birth:</Form.Label>
                                    <Form.Control type="date"
                                                  placeholder="Enter your address"
                                                  value={application.applicant?.dateOfBirth}
                                                  onChange={(e) => {setDateOfBirth(e.target.value); handleChange(e)}}/>
                                    <Form.Label className="mt-3">Gender:</Form.Label>
                                    <Form.Select type="text"
                                                 id='country'
                                                 value={application.applicant?.gender}
                                                 onChange={(e) => {setGender(e.target.value); handleChange(e)}}
                                    >
                                        {genderList.map((row,i) =>
                                            <option key={i} value={row.toString()}>{row.toString()}</option>
                                        )}
                                    </Form.Select>
                                    <Form.Label className="mt-3">Phone Number:</Form.Label>
                                    <Form.Control type="text" placeholder="Enter your country" value={application.applicant?.phoneNumber} onChange={(e) => {setPhoneNumber(e.target.value); handleChange(e)}}/>
                                </Form.Group>
                            </Row>
                        </div>
                    </div>

                    <h2 className="h2_profile_font" >Experience & Education Details</h2>
                    <div className='container-md' style={{width:"80%", marginTop:"20px", borderTop: "1px solid #B5DC10"}}>
                        <div className="container-md" style={{width:"500px"}}>
                            <Row className="mx-3 mt-4">
                                <Form.Group className="mt-4"  controlId="col_company_prof">
                                    <Form.Label>Education: (Optional)</Form.Label>
                                    <Form.Control className="textarea_box" as="textarea" type="text" placeholder="Say few things about your education..." value={application.applicantResume?.education} onChange={(e) => {setEducation(e.target.value); handleChange(e)}}/>
                                    <Form.Label className="mt-3">Experience: (Optional)</Form.Label>
                                    <Form.Control  as="textarea" className="textarea_box" type="text" placeholder="Say few things about your experience..." value={application.applicantResume?.experience} onChange={(e) => {setExperience(e.target.value); handleChange(e)}}/>
                                </Form.Group>
                            </Row>
                        </div>
                    </div>

                    <h2 className="h2_profile_font" >CV & Cover Letter</h2>
                    <div className='container-md' style={{width:"80%", marginTop:"20px", borderTop: "1px solid #B5DC10"}}>
                        <div className="container-md" style={{width:"500px"}}>
                            <Row className="mx-3 mt-4">
                                <Form.Group controlId="formFile" className="mt-4">
                                    <Form.Label>CV</Form.Label>
                                    <Form.Control type="file" onChange={(e) => {setCV(e.target.value); handleChange(e)}}/>
                                </Form.Group>
                                <Form.Group controlId="formFile" className="mt-4">
                                    <Form.Label>Cover Letter</Form.Label>
                                    <Form.Control type="file" onChange={(e) => {setCoverLetter(e.target.value); handleChange(e)}}/>
                                </Form.Group>
                                {/*<h2 className="mt-5">Or drag and drop files below</h2>*/}
                                {/*<div className="upload-drop-zone" id="drop-zone">*/}
                                {/*    Just drag and drop files here*/}
                                {/*</div>*/}
                            </Row>
                        </div>
                    </div>

                    <center className='mt-5'>
                        <button type="submit" className="btn btn-secondary" variant="primary" onClick={(e) => {applyApplication(e); handleShow(e)}} >Apply</button>
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

export default ApplicantApply