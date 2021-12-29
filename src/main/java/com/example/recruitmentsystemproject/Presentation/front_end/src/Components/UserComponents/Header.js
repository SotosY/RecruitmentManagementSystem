import React, {Component} from 'react';
import {Nav, Navbar} from "react-bootstrap";
import "tailwindcss/tailwind.css";
import Logo from "../files/cycom_logo.png";
import '../css/Header.css';

class Header extends Component {

    render() {
        return (
                <header className='header'>
                    <Navbar className='m-3' bg="white" expand="xxl" fixed="top">
                        <div className='container-lg mx-auto'>
                            <Navbar.Brand href="#home">
                                <img
                                    src= {Logo}
                                    width="280"
                                    height="130"
                                    className="d-inline-block align-top"
                                />
                            </Navbar.Brand>
                            <Navbar.Toggle aria-controls="basic-navbar-nav" />
                            <Navbar.Collapse id="responsive-navbar-nav">
                                <Nav  className="me-auto">
                                    <Nav.Link className='nav-link' ref="#home">HOME</Nav.Link>
                                    <Nav.Link href="#aboutUs">ABOUT US</Nav.Link>
                                    <Nav.Link href="#solutions">SOLUTIONS</Nav.Link>
                                    <Nav.Link href="#services">SERVICES</Nav.Link>
                                    <Nav.Link href="#project">PROJECTS</Nav.Link>
                                    <Nav.Link href="#partners">PARTNERS</Nav.Link>
                                    <Nav.Link href="http://localhost:3000/careers">CAREERS</Nav.Link>
                                    <Nav.Link href="#contactus">CONTACT US</Nav.Link>
                                    <Nav.Link href="#language">LANGUAGE</Nav.Link>
                                </Nav>
                            </Navbar.Collapse>
                        </div>
                    </Navbar>
                </header>
        );
    }

}


export default Header;