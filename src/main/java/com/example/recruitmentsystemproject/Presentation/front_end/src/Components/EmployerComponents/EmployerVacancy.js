import React, {useEffect, useState} from "react";
import {getVacancyPage, saveEmployerProfileDetails, saveJobDetails} from "../../Services/EmployerService";
import {Button, Form, Modal, Row} from "react-bootstrap";
import '../css/Vacancy.css';
import {useHistory} from "react-router-dom";
import {hotjar} from "react-hotjar";


const EmployerVacancy = () => {

    const [job, setJob] = useState("");
    const [jobId, setJobId] = useState("");
    const [title, setTitle] = useState("");
    const [department, setDepartment] = useState("");
    const [managedBy, setManagedBy] = useState("");
    const [location, setLocation] = useState("");
    const [salary, setSalary] = useState("");
    const [activeDate, setActiveDate] = useState("");
    const [expiryDate, setExpiryDate] = useState("");
    const [startingDate, setStartingDate] = useState("");
    const [description, setDescription] = useState("");
    const [requirements, setRequirements] = useState("");
    const [essentialCriteria, setEssentialCriteria] = useState("");
    const [desirableCriteria, setDesirableCriteria] = useState("");
    const [salaryAndBenefits, setSalaryAndBenefits] = useState("");
    const [show, setShow] = useState(false);
    const history = useHistory();

    const getJob = () =>{
        getVacancyPage().then((res) => {
            const data = res.data;
            setJob(data)
            setJobId(data.jobId)
            console.log(data)
        })
    }

    useEffect( () => {
        getJob();
        hotjar.initialize(2738985, 6);
    }, [] )

    const saveJob = (e) => {

        e.preventDefault();

        const job = {jobId, title, department, managedBy, location, salary, activeDate, expiryDate,startingDate, description, requirements, essentialCriteria, desirableCriteria, salaryAndBenefits}

        saveJobDetails(job)
            .then((res) => {
                console.log("Data added succesfully", res.data)
            })
            .catch(onerror => {
                console.log('Something went wrong', onerror);
            });
    }

    function handleLogout() {
        localStorage.clear();
        window.location.href = "/careers/login";
    }

    const handleScroll = () => {
        window.scrollTo({top: 0, behavior: "smooth"})
    }

    const handleClose = () => {
        history.push("/careers/employer/vacancies");
        handleScroll()
        setShow(false)
    }

    const handleShow = (e) => {
        e.preventDefault();
        setShow(true);
    }

    return(
        <div>
            <div className='container'>
                <section>
                    <div className='container-breadcrumb'>
                        <ol className="breadcrumb">
                            <li><a href="http://localhost:3000/careers/employer/dashboard">EMPLOYER DASHBOARD</a></li>
                            <li className='active'>VACANCY</li>
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
                        <div className="inline" style={{width:"100%"}}>
                                    <div className="inline" style={{width:"20px", marginLeft:"26px"}}>
                                        <Form.Label style={{width:"max-content"}}>Job ID:</Form.Label>
                                    </div>
                                    <div className="inline" style={{width:"50px"}}>
                                        <Form.Control style={{marginLeft:"20%",color:"#AAAAAA", borderStyle:"none", backgroundColor:"#F0FFF0", width:"60px"}} type="text" disabled={true} value={job.jobId}></Form.Control>
                                    </div>
                                    <div className="inline" style={{width:"60%"}}>
                                        <h2 className="h2_profile_font" style={{fontWeight:"bold"}}>Add a Vacancy</h2>
                                    </div>
                        </div>
                    </div>

                    <h2 style={{marginTop:"80px"}} className="h2_profile_font">Job Details</h2>

                    <div className='container-md' style={{width:"90%", borderTop: "1px solid #B5DC10"}}>
                        <Row className="mx-3 mt-4">
                            <Form.Group className="column_50"  controlId="col_contactName_companyEmail">
                                <Form.Label>Job Title:</Form.Label>
                                <Form.Control type="text" placeholder="Enter a title" value={job.title} onChange={(e) => {setTitle(e.target.value)}}/>
                            </Form.Group>
                            <Form.Group className="column_50"  controlId="col_contactName_companyEmail">
                                <Form.Label>Department:</Form.Label>
                                <Form.Control type="text" placeholder="Enter the department" value={job.department} onChange={(e) => {setDepartment(e.target.value)}} />
                            </Form.Group>
                        </Row>
                        <Row className="mx-3 mt-4">
                            <Form.Group className="column_33" controlId="col_address_country">
                                <Form.Label>Managed By:</Form.Label>
                                <Form.Control type="text" placeholder="Enter managed by name" value={job.managedBy} onChange={(e) => {setManagedBy(e.target.value)}}/>
                                <Form.Label className="mt-3">Post Date:</Form.Label>
                                <Form.Control style={{color:"#AAAAAA", borderStyle:"none", backgroundColor:"#F0FFF0"}} disabled={true} value={job.postDate} />
                                <Form.Label className="mt-3">Starting Date:</Form.Label>
                                <Form.Control type="text" placeholder="Enter the starting date" value={job.startingDate} onChange={(e) => {setStartingDate(e.target.value)}} />
                            </Form.Group>

                            <Form.Group className="column_33" controlId="col_city_postcode">
                                <Form.Label>Location:</Form.Label>
                                <Form.Control type="text" placeholder="Enter the location" value={job.location} onChange={(e) => {setLocation(e.target.value)}} />
                                <Form.Label className="mt-3">Active Date:</Form.Label>
                                <Form.Control type="text" placeholder="Enter the activation date" value={job.activeDate} onChange={(e) => {setActiveDate(e.target.value)}} />
                            </Form.Group>

                            <Form.Group className="column_33" controlId="col_city_postcode">
                                <Form.Label>Salary:</Form.Label>
                                <Form.Control type="text" placeholder="Enter the salary" value={job.salary} onChange={(e) => {setSalary(e.target.value)}}/>
                                <Form.Label className="mt-3">Expiry Date:</Form.Label>
                                <Form.Control type="text" placeholder="Enter the expiration date" value={job.expiryDate} onChange={(e) => {setExpiryDate(e.target.value)}}/>
                            </Form.Group>
                        </Row>

                    </div>

                    <h2 style={{marginTop:"80px"}} className="h2_profile_font">Job Description & Requirements</h2>

                    <div className='container-md' style={{width:"90%", borderTop: "1px solid #B5DC10"}}>
                        <Row className="mx-3 mt-4">
                            <Form.Group   controlId="col_companyProfile">
                                <Form.Label>Description:</Form.Label>
                                <Form.Control className="textarea_box" as="textarea" type="text" placeholder="Enter the description" value={job.description} onChange={(e) => {setDescription(e.target.value)}}/>
                            </Form.Group>
                        </Row>

                        <Row className="mx-3 mt-4">
                            <Form.Group controlId="col_companyProfile">
                                <Form.Label>Requirements:</Form.Label>
                                <Form.Control className="textarea_box" as="textarea" type="text" placeholder="Enter the requirements" value={job.requirements} onChange={(e) => {setRequirements(e.target.value)}}/>
                            </Form.Group>
                        </Row>
                    </div>

                    <h2 style={{marginTop:"80px"}} className="h2_profile_font">Job Criteria & Benefits</h2>

                    <div className='container-md' style={{width:"90%", borderTop: "1px solid #B5DC10"}}>
                        <Row className="mx-3 mt-4">
                            <Form.Group className="column_50"  controlId="col_contactName_companyEmail">
                                <Form.Label>Essential Criteria:</Form.Label>
                                <Form.Control className="textarea_box" as="textarea" type="text" placeholder="Enter the essential criteria" value={job.essentialCriteria} onChange={(e) => {setEssentialCriteria(e.target.value)}}/>
                            </Form.Group>

                            <Form.Group className="column_50"  controlId="col_businessType_telephone">
                                <Form.Label>Desirable Criteria:</Form.Label>
                                <Form.Control className="textarea_box" as="textarea" type="text" placeholder="Enter the desirable criteria" value={job.desirableCriteria} onChange={(e) => {setDesirableCriteria(e.target.value)}}/>
                            </Form.Group>
                        </Row>

                        <Row className="mx-3 mt-4">
                            <Form.Group controlId="col_companyProfile">
                                <Form.Label>Salary & Other Benefits:</Form.Label>
                                <Form.Control className="textarea_box" as="textarea" type="text" placeholder="Enter the salary & other benefits" value={job.salaryAndBenefits} onChange={(e) => {setSalaryAndBenefits(e.target.value)}}/>
                            </Form.Group>
                        </Row>
                    </div>

                    <center className='mt-5'>
                        <button type="submit" className="btn btn-secondary" variant="primary" onClick={(e) => {saveJob(e); handleShow(e);}} >Save Details</button>
                    </center>

                    <Modal
                        show={show}
                        onHide={handleClose}
                    >
                        <Modal.Header closeButton onClick={handleClose}>
                            <Modal.Title>SUCCESS</Modal.Title>
                        </Modal.Header>
                        <Modal.Body>
                            Your vacancy have been saved successfully!
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

export default EmployerVacancy