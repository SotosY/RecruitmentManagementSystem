import {Form, Row} from "react-bootstrap";
import Logo from "../files/add_a_photo_grey.png";
import React, {useState} from "react";
import '../css/Login-Register.css';
import "tailwindcss/tailwind.css";
import {getEmployerDetails} from "../../Services/EmployerService";

const EmployerProfile = () => {

    let [responseData, setResponseData] = React.useState('')

    const fetchData = (e) => {
        e.preventDefault()

        getEmployerDetails().then((res) => {
            setResponseData(res.data)
            console.log(res)
            })
            .catch((error) => {
                console.log(error)
            }
        )
    }


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
                            <li><a href="http://localhost:3000/careers/employer/dashboard">EMPLOYER DASHBOARD</a></li>
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

                            <Form.Group className="column_right_25"  controlId="col_company">
                                <Form.Label className="ml-4">Company ID:</Form.Label>
                                <Form.Control type="text" />
                                <Form.Label className="mt-3">Company:</Form.Label>
                                <Form.Control type="email" />
                            </Form.Group>

                            <Form.Group className="column_right_25"  controlId="col_firstName_email">
                                <Form.Label className="ml-4">First Name:</Form.Label>
                                <Form.Control type="text" />
                                <Form.Label className="mt-3">Email:</Form.Label>
                                <Form.Control type="email" />
                            </Form.Group>

                            <Form.Group className="column_right_25"  controlId="col_lastname_password">
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

                            <Form.Group className="column_50" controlId="col_city_postcode">
                                <Form.Label>City:</Form.Label>
                                <Form.Control type="text" placeholder="Enter city" />
                                <Form.Label className="mt-3">Postcode:</Form.Label>
                                <Form.Control className="mb-5"  type="text" placeholder="Enter postcode" />
                            </Form.Group>

                        </Row>

                    </div>

                    <h2 className="h2_profile_font">Company Details</h2>

                    <div className='container-md' style={{width:"90%", borderTop: "1px solid #B5DC10"}}>
                        <Row className="mx-3 mt-4">
                            <Form.Group className="column_50"  controlId="col_contactName_companyEmail">
                                <Form.Label>Contact Name:</Form.Label>
                                <Form.Control type="text" placeholder="Enter your date of birth" />
                                <Form.Label className="mt-3">Company Email:</Form.Label>
                                <Form.Control type="text" placeholder="Enter your company email" />
                            </Form.Group>

                            <Form.Group className="column_50"  controlId="col_businessType_telephone">
                                <Form.Label>Business Type:</Form.Label>
                                <Form.Control type="text" placeholder="Enter your business type" />
                                <Form.Label className="mt-3">Telephone:</Form.Label>
                                <Form.Control type="text" placeholder="Enter your telephone" />
                            </Form.Group>
                        </Row>

                        <Row className="mx-3 mt-3">
                            <Form.Group   controlId="col_companyProfile">
                                <Form.Label>Company Profile</Form.Label>
                                <Form.Control className="textarea_box" as="textarea" type="text" placeholder="Say a few things about the Company..." />
                            </Form.Group>
                        </Row>
                    </div>

                    <center className='mt-5'>
                        <button type="submit" className="btn btn-secondary" variant="primary" >Save Details</button>
                    </center>

                </Form>
            </div>
        </div>
    );

}

export default EmployerProfile;