//Renders page
import ReactDom from "react-dom";
import {BrowserRouter} from "react-router-dom";
import UserLogin from "../../Components/UserLogin";
import React from "react";
import ApplicantProfile from "../../Components/ApplicantComponents/ApplicantProfile";

//Renders page
it('renders Applicant Profile page without crashing', () => {
    const div = document.createElement('div');

    ReactDom.render(
        <BrowserRouter>
            <ApplicantProfile />
        </BrowserRouter>,
        div);

    ReactDom.unmountComponentAtNode(div);
});