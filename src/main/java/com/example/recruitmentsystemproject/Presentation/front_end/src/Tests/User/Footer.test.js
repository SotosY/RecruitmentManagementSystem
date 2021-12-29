import ReactDom from "react-dom";
import {BrowserRouter} from "react-router-dom";
import Footer from "../../Components/UserComponents/Footer";
import React from "react";

//Renders page
it('renders Footer page without crashing', () => {
    const div = document.createElement('div');

    ReactDom.render(
        <BrowserRouter>
            <Footer />
        </BrowserRouter>,
        div);

    ReactDom.unmountComponentAtNode(div);
});