import React, {Component, useState} from 'react';
import './css/Breadcrumb.css';
import {Table, DropdownButton, ButtonGroup, Dropdown, Form} from "react-bootstrap";
import {Link} from "react-router-dom";
import {getJobs} from "../Services/UserService";
import _ from "lodash/fp";
import { hotjar } from 'react-hotjar';


class JobListComponent extends Component {

    constructor(props) {
        super(props);

        this.state ={
                jobs:[],
                companies:[],
                groupByCompany:[],
                titles:[],
                groupByTitle:[],
                table:[],
                title:[],
                checked:[false, true]
        }
    }

    async componentDidMount() {

        await getJobs()
            .then((res) => {
                const data = res.data;
                this.setState({jobs: data})

                this.getCompany()
                this.getTitle()
                this.handleTables(1)
            });

        hotjar.initialize(2738985, 6);

        // Add an event
        hotjar.event(this.handleChange);
    }


    getCompany() {
        const company = this.state.jobs
            .map(dataItem => dataItem.company) // get all company names
            .filter((company, index, array) => array.indexOf(company) === index); // filter out duplicates

        this.setState({companies: company})
        console.log(this.state.companies)

        const byCompany = company
            .map((company, i)=> ({
                id: i,
                item1: company,
                item2: this.state.jobs.filter(item => item.company === company).length
            }));
        this.setState({groupByCompany: byCompany})
        console.log(this.state.groupByCompany)
    }

    getTitle() {
        const title = this.state.jobs
            .map(dataItem => dataItem.title) // get all job title names
            .filter((title, index, array) => array.indexOf(title) === index); // filter out duplicates

        this.setState({titles: title})
        console.log(this.state.titles)

        const byTitle = title
            .map((title, i)=> ({
                id: i,
                item1: title,
                item2: this.state.jobs.filter(item => item.title === title).length,
            }));

        this.setState({groupByTitle: byTitle})
        console.log(this.state.groupByTitle)
    }

    handleTables(num) {
        if (num == 1) {
            this.setState({table: this.state.groupByCompany})
            this.setState({title: ["Company","Job Positions"]})
        } else {
            this.setState({table: this.state.groupByTitle})
            this.setState({title: ["Job Title","Job Positions"]})
        }
    }

    handleChecked(num) {
        this.handleTables(num);
    }

    handleChange = () => {
        if (this.state.checked[0] == true) {
            this.setState({checked: [false, true]})
        } else {
            this.setState({checked: [true, false]})
        }
    }

    render() {

        return (
            <main>
                <section className='section-outside'>
                    <div className='container-breadcrumb'>
                        <ol className="breadcrumb">
                            <li className='active'>CAREERS</li>
                        </ol>
                    </div>
                    <div className='container-button'>
                        <Link to="/careers/login">
                            <button type="submit" className="btn btn-secondary" id="btn_login" style={{marginRight: '10px'}}>SIGN IN</button>
                        </Link>
                        <DropdownButton as={ButtonGroup} title="REGISTER" id="bg-vertical-dropdown-3" variant="secondary">
                                <Dropdown.Item as={Link} to={"/careers/register/a"} eventKey="1">AS AN APPLICANT</Dropdown.Item>
                                <Dropdown.Item as={Link} to={"/careers/register/e"} eventKey="2">AS AN EMPLOYER</Dropdown.Item>
                        </DropdownButton>
                    </div>
                </section>

                <div className='container'>
                    <div className="mainbody">

                        <div align="center" className="mt-5">

                            <h1 className="inline" style={{color:"#B5DC10"}}>
                                Group By:
                            </h1>

                            <Form.Check label="Job Title"
                                        className="inline"
                                        onClick={() => this.handleChecked(2)}
                                        onChange={this.handleChange}
                                        checked={this.state.checked[0]}
                                        />

                            <Form.Check label="Company"
                                        className="inline"
                                        onClick={() => this.handleChecked(1)}
                                        onChange={this.handleChange}
                                        checked={this.state.checked[1]}
                                        />
                        </div>

                        <Table striped bordered hover id="sortTable" style={{width:"60%", marginLeft:"20%", marginTop:"30px"}}>
                            <thead>
                                <tr>
                                    <th>{this.state.title[0]}</th>
                                    <th style={{textAlign:"center"}}>{this.state.title[1]}</th>
                                </tr>
                            </thead>
                             <tbody>
                                {
                                    this.state.table.map(
                                        position =>
                                            <tr key = {position.id} >
                                                <td>{position.item1}</td>
                                                <td style={{textAlign:"center", width:"30%"}}>{position.item2}</td>
                                            </tr>
                                    )
                                }
                            </tbody>
                        </Table>

                    </div>
                </div>
            </main>
        );
    }

}


export default JobListComponent;