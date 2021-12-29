import React, {useEffect, useState} from "react";
import {getVacancyPage, saveEmployerProfileDetails, saveJobDetails} from "../../Services/EmployerService";
import {Button, Form, Modal, Row} from "react-bootstrap";
import '../css/Vacancy.css';
import {useHistory} from "react-router-dom";
import {hotjar} from "react-hotjar";
import {useForm} from "react-hook-form";

// Get Employer Vacancy page
const EmployerVacancy = () => {

    const [job, setJob] = useState("");
    const [jobId, setJobId] = useState("");
    const [show, setShow] = useState(false);
    const history = useHistory();

    const {
        register,
        handleSubmit,
        formState: {errors},
        reset
    } = useForm();

    // Get Job form
    const getJob = () =>{

        // Calls getVacancyPage service
        getVacancyPage().then((res) => {
            const data = res.data;
            setJob(data)
            setJobId(data.jobId)
        })
    }

    // UseEffect functionality
    useEffect( () => {

        // Calls getJob
        getJob();

        // Initialize Hotjar
        hotjar.initialize(2738985, 6);

    }, [] )

    // Save Job's details
    const saveJob = (e) => {

        const title = e.title
        const department = e.department
        const managedBy = e.managedBy
        const location = e.location
        const salary = e.salary
        const activeDate = e.activeDate
        const expiryDate = e.expiryDate
        const startingDate = e.startingDate
        const description = e.description
        const requirements = e.requirements
        const essentialCriteria = e.essentialCriteria
        const desirableCriteria = e.desirableCriteria
        const salaryAndBenefits = e.salaryAndBenefits

        const job = {jobId, title, department, managedBy, location, salary, activeDate, expiryDate,startingDate, description, requirements, essentialCriteria, desirableCriteria, salaryAndBenefits}

        // Calls saveJobDetails service
        saveJobDetails(job)
            .then((res) => {
                console.log("Data added succesfully", res.data)
            })
            .catch(onerror => {
                console.log('Something went wrong', onerror);
            });
    }

    // onSubmit functionality
    const onSubmit = (e) => {
        saveJob(e)
        handleShow(e)
    }

    // Logout functionality
    function handleLogout() {
        localStorage.clear();
        window.location.href = "/careers/login";
    }

    // Reload page functionality
    function handleRefreshPage() {
        window.location.reload();
    }

    // Scroll functionality
    const handleScroll = () => {
        window.scrollTo({top: 0, behavior: "smooth"})
    }

    // Modal close functionality
    const handleClose = () => {
        handleScroll()
        reset()
        handleRefreshPage()
        setShow(false)
    }

    // Modal show functionality
    const handleShow = (e) => {
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
                                onClick={handleLogout}
                                id="submit">LOG OUT</button>
                    </div>
                </section>

                <Form onSubmit={handleSubmit(onSubmit)}>
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
                                <Form.Control type="text"
                                              placeholder="Enter a title"
                                              {...register("title", {
                                                  required: "Job Title is required",
                                                  pattern: {
                                                      value: /^[a-zA-Z\s]*$/,
                                                      message: "Must contain only letters"
                                                  }})}
                                />
                                {errors.title && (<small className="text-danger">{errors.title.message}</small>)}

                            </Form.Group>
                            <Form.Group className="column_50"  controlId="col_contactName_companyEmail">
                                <Form.Label>Department:</Form.Label>
                                <Form.Control type="text"
                                              placeholder="Enter the department"
                                              {...register("department", {
                                                  required: "Department is required",
                                                  pattern: {
                                                      value: /^[a-zA-Z\s]*$/,
                                                      message: "Must contain only letters"
                                                  }})}
                                />
                                {errors.department && (<small className="text-danger">{errors.department.message}</small>)}
                            </Form.Group>
                        </Row>
                        <Row className="mx-3 mt-4">
                            <Form.Group className="column_33" controlId="col_address_country">
                                <Form.Label>Managed By:</Form.Label>
                                <Form.Control type="text"
                                              placeholder="Enter managed by name"
                                              {...register("managedBy", {
                                                  required: "Managed By is required",
                                                  pattern: {
                                                      value: /^[a-zA-Z\s]*$/,
                                                      message: "Must contain only letters"
                                                  }})}
                                />
                                {errors.managedBy && (<small className="text-danger">{errors.managedBy.message}</small>)}

                                <Form.Label className="mt-3">Post Date:</Form.Label>
                                <Form.Control style={{color:"#AAAAAA", borderStyle:"none", backgroundColor:"#F0FFF0"}} disabled={true} value={job.postDate} />
                                <Form.Label className="mt-3">Starting Date:</Form.Label>
                                <Form.Control type="date"
                                              placeholder="Enter the starting date"
                                              {...register("startingDate", {
                                                  required: "Starting Date is required",
                                                 })
                                              }
                                />
                                {errors.startingDate && (<small className="text-danger">{errors.startingDate.message}</small>)}

                            </Form.Group>

                            <Form.Group className="column_33" controlId="col_city_postcode">
                                <Form.Label>Location:</Form.Label>
                                <Form.Control type="text"
                                              placeholder="Enter the location"
                                              {...register("location", {
                                                  required: "Location is required",
                                                  pattern: {
                                                      value: /^[a-zA-Z\s]*$/,
                                                      message: "Must contain only letters"
                                                  }})}
                                />
                                {errors.location && (<small className="text-danger">{errors.location.message}</small>)}

                                <Form.Label className="mt-3">Active Date:</Form.Label>
                                <Form.Control type="date"
                                              placeholder="Enter the activation date"
                                              {...register("activeDate", {
                                                  required: "Active Date is required",
                                              })
                                              }
                                />
                                {errors.activeDate && (<small className="text-danger">{errors.activeDate.message}</small>)}
                            </Form.Group>

                            <Form.Group className="column_33" controlId="col_city_postcode">
                                <Form.Label>Salary:</Form.Label>
                                <Form.Control type="text"
                                              placeholder="Enter the salary"
                                              {...register("salary", {
                                                  required: "Salary is required",
                                                  pattern: {
                                                      value: /^[0-9]*$/,
                                                      message: "Must contain only numbers"
                                                  }})}
                                />
                                {errors.salary && (<small className="text-danger">{errors.salary.message}</small>)}
                                <Form.Label className="mt-3">Expiry Date:</Form.Label>
                                <Form.Control type="date"
                                              placeholder="Enter the expiration date"
                                              {...register("expiryDate", {
                                                  required: "Expiry Date is required",
                                              })
                                              }
                                />
                                {errors.expiryDate && (<small className="text-danger">{errors.expiryDate.message}</small>)}
                            </Form.Group>
                        </Row>

                    </div>

                    <h2 style={{marginTop:"80px"}} className="h2_profile_font">Job Description & Requirements</h2>

                    <div className='container-md' style={{width:"90%", borderTop: "1px solid #B5DC10"}}>
                        <Row className="mx-3 mt-4">
                            <Form.Group   controlId="col_companyProfile">
                                <Form.Label>Description:</Form.Label>
                                <Form.Control className="textarea_box"
                                              as="textarea" type="text"
                                              placeholder="Enter the description"
                                              {...register("description", {
                                                  required: "Description is required"
                                                  }
                                                  )}
                                />
                                {errors.description && (<small className="text-danger">{errors.description.message}</small>)}
                            </Form.Group>
                        </Row>

                        <Row className="mx-3 mt-4">
                            <Form.Group controlId="col_companyProfile">
                                <Form.Label>Requirements:</Form.Label>
                                <Form.Control className="textarea_box"
                                              as="textarea"
                                              type="text"
                                              placeholder="Enter the requirements"
                                              {...register("requirements", {
                                                  required: "Requirements are required"
                                                })
                                              }
                                />
                                {errors.requirements && (<small className="text-danger">{errors.requirements.message}</small>)}
                            </Form.Group>
                        </Row>
                    </div>

                    <h2 style={{marginTop:"80px"}} className="h2_profile_font">Job Criteria & Benefits</h2>

                    <div className='container-md' style={{width:"90%", borderTop: "1px solid #B5DC10"}}>
                        <Row className="mx-3 mt-4">
                            <Form.Group className="column_50"  controlId="col_contactName_companyEmail">
                                <Form.Label>Essential Criteria:</Form.Label>
                                <Form.Control className="textarea_box"
                                              as="textarea"
                                              type="text"
                                              placeholder="Enter the essential criteria"
                                              {...register("essentialCriteria", {
                                                  required: "Essential Criteria are required"
                                              })
                                              }
                                />
                                {errors.essentialCriteria && (<small className="text-danger">{errors.essentialCriteria.message}</small>)}
                            </Form.Group>

                            <Form.Group className="column_50"  controlId="col_businessType_telephone">
                                <Form.Label>Desirable Criteria:</Form.Label>
                                <Form.Control className="textarea_box"
                                              as="textarea"
                                              type="text"
                                              placeholder="Enter the desirable criteria"
                                              {...register("desirableCriteria", {
                                                  required: "Desirable Criteria are required"
                                              })
                                              }
                                />
                                {errors.desirableCriteria && (<small className="text-danger">{errors.desirableCriteria.message}</small>)}
                            </Form.Group>
                        </Row>

                        <Row className="mx-3 mt-4">
                            <Form.Group controlId="col_companyProfile">
                                <Form.Label>Salary & Other Benefits:</Form.Label>
                                <Form.Control className="textarea_box"
                                              as="textarea"
                                              type="text"
                                              placeholder="Enter the salary & other benefits"
                                              {...register("salaryAndBenefits", {
                                                  required: "Salary And Benefits are required"
                                              })
                                              }
                                />
                                {errors.salaryAndBenefits && (<small className="text-danger">{errors.salaryAndBenefits.message}</small>)}
                            </Form.Group>
                        </Row>
                    </div>

                    <center className='mt-5'>
                        <button type="submit" className="btn btn-secondary" variant="primary">Save Details</button>
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