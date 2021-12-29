import ReactDom from "react-dom";
import {BrowserRouter} from "react-router-dom";
import Header from "../../Components/UserComponents/Header";
import React from "react";

//Renders page
it('renders Header without crashing', () => {
    const div = document.createElement('div');

    ReactDom.render(
        <BrowserRouter>
            <Header />
        </BrowserRouter>,
        div);

    ReactDom.unmountComponentAtNode(div);
});