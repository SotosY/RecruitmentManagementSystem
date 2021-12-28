import ReactDom from "react-dom";
import {BrowserRouter} from "react-router-dom";
import ApplicantProfile from "../../Components/ApplicantComponents/ApplicantProfile";
import React from "react";
import ApplicantJobInfo from "../../Components/ApplicantComponents/ApplicantJobInfo";

//Renders page
it('renders Applicant Job Info page without crashing', () => {
    const div = document.createElement('div');

    ReactDom.render(
        <BrowserRouter>
            <ApplicantJobInfo />
        </BrowserRouter>,
        div);

    ReactDom.unmountComponentAtNode(div);
});