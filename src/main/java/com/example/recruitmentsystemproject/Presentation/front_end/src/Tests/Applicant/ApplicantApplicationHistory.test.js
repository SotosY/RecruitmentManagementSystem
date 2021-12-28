import ReactDom, {unmountComponentAtNode} from "react-dom";
import {BrowserRouter} from "react-router-dom";
import ApplicantApplication from "../../Components/ApplicantComponents/ApplicantApplication";
import React from "react";
import ApplicantApplicationHistory from "../../Components/ApplicantComponents/ApplicantApplicationHistory";
import {act, render} from "@testing-library/react";

let container;

//Renders page
it('renders Applicant Application History page without crashing', () => {
    const div = document.createElement('div');

    ReactDom.render(
        <BrowserRouter>
            <ApplicantApplicationHistory />
        </BrowserRouter>,
        div);

    ReactDom.unmountComponentAtNode(div);
});

beforeEach(() => {
    container = document.createElement("div");
    document.body.appendChild(container);
});

afterEach(() => {
    unmountComponentAtNode(container);
    container.remove();
    container = null;
});

//Shows list
describe("Applications History component", () => {
    test("it shows a list of application history", async() => {

        await act(async () => {
            render(
                <BrowserRouter>
                    <ApplicantApplicationHistory/>
                </BrowserRouter>,
                container);
        });

        expect(container.textContent).toBe("");
    });
});