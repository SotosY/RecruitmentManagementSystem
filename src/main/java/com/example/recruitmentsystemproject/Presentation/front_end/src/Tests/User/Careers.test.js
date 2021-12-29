import React from 'react';
import ReactDom, {unmountComponentAtNode} from 'react-dom';
import JobListComponent from "../../Components/UserComponents/JobListComponent";
import {act, render} from "@testing-library/react";
import {BrowserRouter} from "react-router-dom";
import Header from "../../Components/UserComponents/Header";
import Footer from "../../Components/UserComponents/Footer";

let container;

//Renders page
it('renders Careers page without crashing', () => {
    const div = document.createElement('div');

    ReactDom.render(
        <BrowserRouter>
            <JobListComponent />
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
describe("Careers component", () => {
    test("it shows a list of careers", async() => {

        const jobs = [{
            jobId: 2,
            employer: {
                employerId: 2,
                user: {
                    userId: 5,
                    email: "bob123@hotmail.com",
                    password: "Iamascientistpassword",
                    roles: "EMPLOYER",
                    active: true
                },
                firstName: "Renos",
                lastName: "Beckham",
                contactName: "Renos Beckham",
                company: "Cycom",
                companyEmail: "renos@google.com.cy",
                businessType: "CEO",
                telephoneNumber: "2212323443563",
                companyProfile: "I been working for 10 years",
                address: "Oroklini",
                country: "Cyprus",
                city: "Larnaca",
                postcode: "2345"
            },
            status: "Active",
            title: "Junior Software Engineer",
            department: "Programming",
            company: "Cycom",
            managedBy: "Aggelos Charisteas",
            location: "Nicosia",
            salary: "22000",
            postDate: "2021-12-04",
            activeDate: "2021-12-04",
            expiryDate: "2022-01-01",
            startingDate: "2022-03-23",
            description: "This is the description",
            requirements: "These are the requirements",
            essentialCriteria: "The essential criteria are",
            desirableCriteria: "The desirable criteria are",
            salaryAndBenefits: "The salary and benefits are"
        }]

        jest.spyOn(window, "fetch").mockImplementation(() => {
            const fetchResponse = {
                json: () => Promise.resolve(jobs)
            };
            return Promise.resolve(fetchResponse);
        });

        await act(async () => {
            render(
                <BrowserRouter>
                    <JobListComponent/>
                </BrowserRouter>,
                container);
        });

        expect(container.textContent).toBe("");
        window.fetch.mockRestore();
    });
});

