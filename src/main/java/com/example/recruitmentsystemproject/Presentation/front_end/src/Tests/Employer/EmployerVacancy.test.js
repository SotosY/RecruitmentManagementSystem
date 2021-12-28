import ReactDom from "react-dom";
import {BrowserRouter} from "react-router-dom";
import EmployerRegister from "../../Components/EmployerComponents/EmployerRegister";
import React from "react";
import EmployerVacancy from "../../Components/EmployerComponents/EmployerVacancy";

//Renders page
it('renders Employer Vacancy page without crashing', () => {
    const div = document.createElement('div');

    ReactDom.render(
        <BrowserRouter>
            <EmployerVacancy />
        </BrowserRouter>,
        div);

    ReactDom.unmountComponentAtNode(div);
});