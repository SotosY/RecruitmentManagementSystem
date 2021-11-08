import React, {Component} from 'react';
import { AiOutlineCopyrightCircle } from "react-icons/ai";
import {IconContext} from "react-icons";
import "tailwindcss/tailwind.css"
import '../css/Footer.css';


class Footer extends Component {
    render() {
        return (
            <footer className='footer'>
                <p className="text-muted text-center text-small">
                    <small>
                        <IconContext.Provider value={{className: 'react-icons'}}>
                            <AiOutlineCopyrightCircle size={17} className='mx-1'/>
                        </IconContext.Provider> Cycom Business Solutions Ltd
                    </small>
                </p>
            </footer>
        );
    }
}

export default Footer;