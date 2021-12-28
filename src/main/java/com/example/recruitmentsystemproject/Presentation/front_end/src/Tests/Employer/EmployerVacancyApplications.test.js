import ReactDom from "react-dom";
import {BrowserRouter} from "react-router-dom";
import EmployerVacancy from "../../Components/EmployerComponents/EmployerVacancy";
import React from "react";
import EmployerVacancyApplications from "../../Components/EmployerComponents/EmployerVacancyApplications";

//Renders page
it('renders Employer Vacancy Applications page without crashing', () => {
    const div = document.createElement('div');

    ReactDom.render(
        <BrowserRouter>
            <EmployerVacancyApplications />
        </BrowserRouter>,
        div);

    ReactDom.unmountComponentAtNode(div);
});