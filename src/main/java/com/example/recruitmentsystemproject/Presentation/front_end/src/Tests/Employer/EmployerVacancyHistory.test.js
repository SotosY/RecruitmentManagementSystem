import ReactDom from "react-dom";
import {BrowserRouter} from "react-router-dom";
import EmployerVacancy from "../../Components/EmployerComponents/EmployerVacancy";
import React from "react";
import EmployerVacancyHistory from "../../Components/EmployerComponents/EmployerVacancyHistory";

//Renders page
it('renders Employer Vacancy History page without crashing', () => {
    const div = document.createElement('div');

    ReactDom.render(
        <BrowserRouter>
            <EmployerVacancyHistory />
        </BrowserRouter>,
        div);

    ReactDom.unmountComponentAtNode(div);
});