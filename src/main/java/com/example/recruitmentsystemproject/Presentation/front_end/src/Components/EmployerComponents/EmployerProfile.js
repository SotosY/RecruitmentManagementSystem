import {Button, Form, Modal, Row} from "react-bootstrap";
import Logo from "../files/add_a_photo_grey.png";
import React, {useState, useEffect, useMemo} from "react";
import {useForm} from "react-hook-form";
import '../css/Login-Register.css';
import "tailwindcss/tailwind.css";
import {getEmployerDetails, saveEmployerProfileDetails} from "../../Services/EmployerService";
import {useHistory} from "react-router-dom";
import {hotjar} from "react-hotjar";

const EmployerProfile = () => {

    const [employer, setEmployer] = useState("");
    const [address, setAddress] = useState("");
    const [city, setCity] = useState("");
    const [country, setCountry] = useState("");
    const [postcode, setPostcode] = useState("");
    const [contactName, setContactName] = useState("");
    const [businessType, setBusinessType] = useState("");
    const [companyEmail, setCompanyEmail] = useState("");
    const [telephoneNumber, setTelephoneNumber] = useState("");
    const [companyProfile, setCompanyProfile] = useState("");
    const [show, setShow] = useState(false);

    const countryList = ["Please select a country","Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Antigua and Barbuda", "Argentina", "Armenia", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bhutan", "Bolivia (Plurinational State of)", "Bosnia and Herzegovina", "Botswana", "Brazil", "Brunei Darussalam", "Bulgaria", "Burkina Faso", "Burundi", "Cabo Verde", "Cambodia", "Cameroon", "Canada", "Central African Republic", "Chad", "Chile", "China", "Colombia", "Comoros", "Congo", "Costa Rica", "Côte D'Ivoire", "Croatia", "Cuba", "Cyprus", "Czech Republic", "Democratic People's Republic of Korea", "Democratic Republic of the Congo", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Fiji", "Finland", "France", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Greece", "Grenada", "Guatemala", "Guinea", "Guinea Bissau", "Guyana", "Haiti", "Honduras", "Hungary", "Iceland", "India", "Indonesia", "Iran (Islamic Republic of)", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Kuwait", "Kyrgyzstan", "Lao People’s Democratic Republic", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Mauritania", "Mauritius", "Mexico", "Micronesia (Federated States of)", "Monaco", "Mongolia", "Montenegro", "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Poland", "Portugal", "Qatar", "Republic of Korea", "Republic of Moldova", "Romania", "Russian Federation", "Rwanda", "Saint Kitts and Nevis", "Saint Lucia", "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South ?Sudan", "Spain", "Sri Lanka", "Sudan", "Suriname", "Swaziland", "Sweden", "Switzerland", "Syrian Arab Republic", "Tajikistan", "Thailand", "The former Yugoslav Republic of Macedonia", "Timor-Leste", "Togo", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United Republic of Tanzania", "United States of America", "Uruguay", "Uzbekistan", "Vanuatu", "Venezuela (Bolivarian Republic of)", "Viet Nam", "Yemen", "Zambia", "Zimbabwe"]

    const history = useHistory();

    const getEmployer = () => {
        getEmployerDetails().then((res) => {
            const data = res.data;
            setEmployer(data)
            setAddress(data.address)
            setCity(data.city)
            setCountry(data.country)
            setPostcode(data.postcode)
            setContactName(data.contactName)
            setBusinessType(data.businessType)
            setCompanyEmail(data.companyEmail)
            setTelephoneNumber(data.telephoneNumber)
            setCompanyProfile(data.companyProfile)
            console.log(data)
        })
            .catch((error) => {
                console.log(error)
            })
    }

    useEffect( () => {
        getEmployer();
        hotjar.initialize(2738985, 6);
    }, [] )

    const saveEmployerProfile = (e) => {
        e.preventDefault();

        const employer = {address, city, country, postcode, contactName, businessType, companyEmail, telephoneNumber, companyProfile}

        saveEmployerProfileDetails(employer)
            .then(res => {
                console.log("Data added successfully", res.data);
                history.push('/careers/employer/profile')
            })

            .catch(onerror => {
                console.log('Something went wrong', onerror);
            });

    }

    function handleChange(event) {
        const value = event.target.value;
        setEmployer({
            employer,
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
        handleScroll()
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
                                <Form.Control style={{color:"#AAAAAA", borderStyle:"none", backgroundColor:"#F0FFF0"}} type="text" value={employer.employerId} disabled={true}></Form.Control>
                                <Form.Label className="mt-3">Company:</Form.Label>
                                <Form.Control style={{color:"#AAAAAA", borderStyle:"none", backgroundColor:"#F0FFF0"}} type="text" value={employer.company} disabled={true}></Form.Control>
                            </Form.Group>

                            <Form.Group className="column_right_25"  controlId="col_firstName_email">
                                <Form.Label className="ml-4">First Name:</Form.Label>
                                <Form.Control style={{color:"#AAAAAA", borderStyle:"none", backgroundColor:"#F0FFF0"}} type="text" value={employer.firstName} disabled={true}></Form.Control>
                                <Form.Label className="mt-3">Email:</Form.Label>
                                <Form.Control style={{color:"#AAAAAA", borderStyle:"none", backgroundColor:"#F0FFF0"}} type="text" value={employer.user?.email} disabled={true}></Form.Control>
                            </Form.Group>

                            <Form.Group className="column_right_25"  controlId="col_lastname_password">
                                <Form.Label>Last Name:</Form.Label>
                                <Form.Control style={{color:"#AAAAAA", borderStyle:"none", backgroundColor:"#F0FFF0"}} type="text" value={employer.lastName} disabled={true}></Form.Control>
                                <Form.Label className="mt-3">Password:</Form.Label>
                                <Form.Control style={{color:"#AAAAAA", borderStyle:"none", backgroundColor:"#F0FFF0"}} type="password" value={employer.user?.password} disabled={true}></Form.Control>
                            </Form.Group>
                        </Row>
                    </div >

                    <h2 className="h2_profile_font" >Personal Details</h2>
                    <div className='container-md' style={{width:"90%", borderTop: "1px solid #B5DC10"}}>
                        <Row className="mx-3 mt-4">
                            <Form.Group className="column_50" controlId="col_address_country">
                                <Form.Label>Address:</Form.Label>
                                <Form.Control type="text" placeholder="Enter your address" value={employer.address} onChange={(e) => {setAddress(e.target.value); handleChange(e)}}/>
                                <Form.Label className="mt-3">Country:</Form.Label>
                                <Form.Select type="text"
                                             id='country'
                                             value={country}
                                             onChange={(e) => setCountry(e.target.value)}
                                >
                                    {countryList.map(row =>
                                        <option value={row.toString()}>{row.toString()}</option>
                                    )}
                                </Form.Select>
                            </Form.Group>

                            <Form.Group className="column_50" controlId="col_city_postcode">
                                <Form.Label>City:</Form.Label>
                                <Form.Control type="text" placeholder="Enter city" value={employer.city}  onChange={(e) => {setCity(e.target.value); handleChange(e)}}/>
                                <Form.Label className="mt-3">Postcode:</Form.Label>
                                <Form.Control className="mb-5"  type="text" placeholder="Enter postcode" value={employer.postcode} onChange={(e) => {setPostcode(e.target.value); handleChange(e)}}/>
                            </Form.Group>

                        </Row>

                    </div>

                    <h2 className="h2_profile_font">Company Details</h2>

                    <div className='container-md' style={{width:"90%", borderTop: "1px solid #B5DC10"}}>
                        <Row className="mx-3 mt-4">
                            <Form.Group className="column_50"  controlId="col_contactName_companyEmail">
                                <Form.Label>Contact Name:</Form.Label>
                                <Form.Control type="text" placeholder="Enter your date of birth" value={employer.contactName} onChange={(e) => {setContactName(e.target.value); handleChange(e)}}/>
                                <Form.Label className="mt-3">Company Email:</Form.Label>
                                <Form.Control type="text" placeholder="Enter your company email" value={employer.companyEmail} onChange={(e) => {setCompanyEmail(e.target.value); handleChange(e)}}/>
                            </Form.Group>

                            <Form.Group className="column_50"  controlId="col_businessType_telephone">
                                <Form.Label>Business Type:</Form.Label>
                                <Form.Control type="text" placeholder="Enter your business type" value={employer.businessType} onChange={(e) => {setBusinessType(e.target.value); handleChange(e)}}/>
                                <Form.Label className="mt-3">Telephone:</Form.Label>
                                <Form.Control type="text" placeholder="Enter your telephone" value={employer.telephoneNumber} onChange={(e) => {setTelephoneNumber(e.target.value); handleChange(e)}}/>
                            </Form.Group>
                        </Row>

                        <Row className="mx-3 mt-3">
                            <Form.Group   controlId="col_companyProfile">
                                <Form.Label>Company Profile</Form.Label>
                                <Form.Control className="textarea_box" as="textarea" type="text" placeholder="Say a few things about the Company..." value={employer.companyProfile} onChange={(e) => {setCompanyProfile(e.target.value); handleChange(e)}}/>
                            </Form.Group>
                        </Row>
                    </div>

                    <center className='mt-5'>
                        <button type="submit" className="btn btn-secondary" variant="primary" onClick={(e) => {saveEmployerProfile(e); handleShow(e)}} >Save Details</button>
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

export default EmployerProfile;