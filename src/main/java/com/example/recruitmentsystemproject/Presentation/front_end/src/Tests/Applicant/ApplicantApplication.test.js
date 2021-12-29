//Renders page
import ReactDom from "react-dom";
import {BrowserRouter} from "react-router-dom";
import UserLogin from "../../Components/UserComponents/UserLogin";
import React from "react";
import ApplicantApplication from "../../Components/ApplicantComponents/ApplicantApplication";
import {act, render} from "@testing-library/react";
import JobListComponent from "../../Components/UserComponents/JobListComponent";

let container;

//Renders page
it('renders Applicant Application page without crashing', () => {
    const div = document.createElement('div');

    ReactDom.render(
        <BrowserRouter>
            <ApplicantApplication />
        </BrowserRouter>,
        div);

    ReactDom.unmountComponentAtNode(div);
});

//Shows list
describe("Applications component", () => {
    test("it shows a list of application", async() => {

        await act(async () => {
            render(
                <BrowserRouter>
                    <ApplicantApplication/>
                </BrowserRouter>,
                container);

        });
    });
});