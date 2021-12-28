
import ReactDom from "react-dom";
import {BrowserRouter} from "react-router-dom";
import EmployerRegister from "../../Components/EmployerComponents/EmployerRegister";
import React from "react";
import EmployerDashboard from "../../Components/EmployerComponents/EmployerDashboard";

//Renders page
it('renders Employer Dashboard page without crashing', () => {
    const div = document.createElement('div');

    ReactDom.render(
        <BrowserRouter>
            <EmployerDashboard />
        </BrowserRouter>,
        div);

    ReactDom.unmountComponentAtNode(div);
});