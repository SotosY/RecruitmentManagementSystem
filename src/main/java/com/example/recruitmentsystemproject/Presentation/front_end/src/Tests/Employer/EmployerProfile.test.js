import ReactDom from "react-dom";
import {BrowserRouter} from "react-router-dom";
import EmployerRegister from "../../Components/EmployerComponents/EmployerRegister";
import React from "react";
import EmployerProfile from "../../Components/EmployerComponents/EmployerProfile";

//Renders page
it('renders Employer Profile page without crashing', () => {
    const div = document.createElement('div');

    ReactDom.render(
        <BrowserRouter>
            <EmployerProfile />
        </BrowserRouter>,
        div);

    ReactDom.unmountComponentAtNode(div);
});