import ReactDom from "react-dom";
import {BrowserRouter} from "react-router-dom";
import ApplicantRegister from "../../Components/ApplicantComponents/ApplicantRegister";
import React from "react";
import EmployerRegister from "../../Components/EmployerComponents/EmployerRegister";

//Renders page
it('renders Employer Register page without crashing', () => {
    const div = document.createElement('div');

    ReactDom.render(
        <BrowserRouter>
            <EmployerRegister />
        </BrowserRouter>,
        div);

    ReactDom.unmountComponentAtNode(div);
});