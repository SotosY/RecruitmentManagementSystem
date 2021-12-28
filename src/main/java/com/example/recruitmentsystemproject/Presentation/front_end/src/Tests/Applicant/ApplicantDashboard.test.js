//Renders page
import ReactDom from "react-dom";
import {BrowserRouter} from "react-router-dom";
import UserLogin from "../../Components/UserLogin";
import React from "react";
import ApplicantDashboard from "../../Components/ApplicantComponents/ApplicantDashboard";

//Renders page
it('renders Login page without crashing', () => {
    const div = document.createElement('div');

    ReactDom.render(
        <BrowserRouter>
            <ApplicantDashboard />
        </BrowserRouter>,
        div);

    ReactDom.unmountComponentAtNode(div);
});