import ReactDom from "react-dom";
import {BrowserRouter} from "react-router-dom";
import ApplicantApplication from "../../Components/ApplicantComponents/ApplicantApplication";
import React from "react";
import ApplicantApply from "../../Components/ApplicantComponents/ApplicantApply";

//Renders page
it('renders Applicant Apply page without crashing', () => {
    const div = document.createElement('div');

    ReactDom.render(
        <BrowserRouter>
            <ApplicantApply />
        </BrowserRouter>,
        div);

    ReactDom.unmountComponentAtNode(div);
});