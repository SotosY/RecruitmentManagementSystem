import ReactDom from "react-dom";
import {BrowserRouter} from "react-router-dom";
import Header from "../../Components/UserComponents/Header";
import React from "react";
import UserLogin from "../../Components/UserComponents/UserLogin";

//Renders page
it('renders Login page without crashing', () => {
    const div = document.createElement('div');

    ReactDom.render(
        <BrowserRouter>
            <UserLogin />
        </BrowserRouter>,
        div);

    ReactDom.unmountComponentAtNode(div);
});