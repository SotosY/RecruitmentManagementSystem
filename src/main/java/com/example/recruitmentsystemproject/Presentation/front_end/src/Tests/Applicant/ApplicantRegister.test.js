import ReactDom from "react-dom";
import {BrowserRouter} from "react-router-dom";
import Header from "../../Components/UserComponents/Header";
import React from "react";
import ApplicantRegister from "../../Components/ApplicantComponents/ApplicantRegister";

//Renders page
it('renders Applicant Register page without crashing', () => {
    const div = document.createElement('div');

    ReactDom.render(
        <BrowserRouter>
            <ApplicantRegister />
        </BrowserRouter>,
        div);

    ReactDom.unmountComponentAtNode(div);
});