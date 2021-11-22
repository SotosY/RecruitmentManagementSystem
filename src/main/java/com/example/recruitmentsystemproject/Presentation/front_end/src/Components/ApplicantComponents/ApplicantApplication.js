import React, {useState, useEffect} from 'react';
import {getJobs} from "../../Services/UserService";
import {Button, Col, Form, Dropdown, Table, DropdownButton} from "react-bootstrap";
import '../css/Table.css';
import "tailwindcss/tailwind.css";
import {MdFormatLineSpacing} from "react-icons/md";
import {BiSelectMultiple} from "react-icons/bi";
import ReactPaginate from "react-paginate"
import 'bootstrap/dist/css/bootstrap.min.css'
import _ from "lodash/fp";
import {useHistory} from "react-router-dom";

const ApplicantApplication = () => {

    const [data, setData] = useState([]);
    const [paginatedData, setPaginatedData] = useState([]);
    const [query, setQuery] = useState("");
    const [filter, setFilter] = useState(["title"]);
    const [size, setSize] = useState("40px");
    const [sort, setSort] = useState("ASC");
    const history = useHistory();

    const columns = ["jobId", "title", "department", "company", "location", "managedBy" , "expiryDate", "startingDate"]
    const pageSize = 10;
    const pageCount = data? Math.ceil(data.length/pageSize) :0;

    useEffect(() => {
        getJobs().then((res => {
                const data = res.data;
                setData(data);
                setPaginatedData(_(data).slice(0).take(pageSize).value());
            }
        ))
    }, [])

    function search(rows) {
        return rows.filter(
            (row) =>
                filter.some(column => row[column].toString().toLowerCase().indexOf(query.toLowerCase()) > -1 )
        );
    }

    function getColumnName(num) {
        return columns[num]
    }

    function handleSorting(col){
        if (sort === "ASC"){
            const sorted =[...paginatedData].sort((a,b) =>
                a[col].toString().toLowerCase() > b[col].toString().toLowerCase() ? 1: -1
            );
            setPaginatedData(sorted);
            setSort("DESC");
        } else {
            const sorted =[...paginatedData].sort((a,b) =>
                a[col].toString().toLowerCase() < b[col].toString().toLowerCase() ? 1: -1
            );
            setPaginatedData(sorted);
            setSort("ASC");
        }
    }

    function handleChecked(column){
        const checked = filter.includes(column)
        setFilter(prevState => checked ? prevState.filter(sc => sc !== column): [...prevState, column])
    }

    function handleAllChecked(){
        setFilter(columns)
    }

    function handlePageClick(data){
        let currentPage = data.selected + 1
        console.log(currentPage)
        pagination(currentPage)
    }

    function handleJobInfo(id){
        history.push({pathname:`/careers/applicant/application/job/${id}`,
        state:id})
    }

    /**
     * Code adapted from tutorial at https://www.youtube.com/watch?v=kEd81ZirrCY
     */
    const pagination = (pageNo) => {

        const startIndex = (pageNo - 1) * pageSize;
        const paginatedPost = _(data).slice(startIndex).take(pageSize).value();
        setPaginatedData(paginatedPost);
    }

    /**
     * Code adapted from examples at https://stackoverflow.com/questions/58601704/adding-a-icon-to-react-bootstrap-dropdown [Accessed: 20 November 2021]
     */
    const CustomToggle = React.forwardRef(({ children, onClick }, ref) => (
        <div
            href=""
            ref={ref}
            onClick={e => {
                e.preventDefault();
                onClick(e);
            }}
        >
            {<MdFormatLineSpacing className={"btn-actions"} size="16%" style={{float:"right"}}/>}
            {children}
        </div>
    ));

    /**
     * Code adapted from examples at https://www.powerupcloud.com/securing-spring-boot-and-react-js-with-spring-security-using-jwt-authentication/ [Accessed: 15 November 2021]
     */
    function handleLogout() {
        localStorage.clear();
        window.location.href = "/careers/login";
    }

    {

        return (
            <div>
                <div className='container'>
                    <section>
                        <div className='container-breadcrumb'>
                            <ol className="breadcrumb">
                                <li><a href="http://localhost:3000/careers/applicant/dashboard">APPLICANT DASHBOARD</a></li>
                                <li className='active'>APPLICATION</li>
                            </ol>
                        </div>
                        <div className='container-button'>
                            <button type="submit"
                                    className="btn btn-secondary"
                                    variant="primary"
                                    id="submit"
                                    href="javascript:void(0);"
                                    onClick={handleLogout}
                            >LOG OUT</button>
                        </div>
                    </section>

                    <div className="container-md">
                        <div className="filter-container">
                            <Form.Control
                                className="input-search"
                                type="text"
                                placeholder="Filter by"
                                id='lastname'
                                value={query}
                                onChange={(e) => setQuery(e.target.value)}
                            />

                            <div className="input-checkbox">
                                <Form.Check label="Job ID"
                                            className="inline"
                                            checked={filter.includes(getColumnName(0))}
                                            onChange={(e) => {{handleChecked(getColumnName(0))}
                                } }/>

                                <Form.Check label="Job Title"
                                            className="inline"
                                            checked={filter.includes(getColumnName(1))}
                                            onChange={(e) => {{handleChecked(getColumnName(1))}
                                } } />

                                <Form.Check label="Department"
                                            className="inline"
                                            checked={filter.includes(getColumnName(2))}
                                            onChange={(e) => {{handleChecked(getColumnName(2))}
                                } } />

                                <Form.Check label="Company"
                                            className="inline"
                                            checked={filter.includes(getColumnName(3))}
                                            onChange={(e) => {{handleChecked(getColumnName(3))}
                                } } />

                                <Form.Check label="Location"
                                            className="inline"
                                            checked={filter.includes(getColumnName(4))}
                                            onChange={(e) => {{handleChecked(getColumnName(4))}
                                } } />

                            </div>
                            <div className="input-btn">
                                <Dropdown onSelect={eventKey => setSize(eventKey)}>
                                    <BiSelectMultiple className={"btn-actions"} size="16%" style={{marginLeft:"55%",float:"left", display:"inline-block"}} onClick={handleAllChecked}/>
                                            <Dropdown.Toggle as={CustomToggle} id="dropdown-custom-components" ></Dropdown.Toggle>
                                        <Dropdown.Menu>
                                            <Dropdown.Item eventKey="40px">
                                                Condensed view
                                            </Dropdown.Item>
                                            <Dropdown.Item eventKey="48px">
                                                Regular view
                                            </Dropdown.Item>
                                            <Dropdown.Item eventKey="56px">
                                                <div></div>
                                                Relaxed view
                                            </Dropdown.Item>
                                        </Dropdown.Menu>
                                </Dropdown>
                            </div>

                        </div>
                            <Table  striped bordered hover size="sm" id="sortTable">
                                <thead>
                                    {/*<tr>{data[0] && columns.map((heading) => <th>{heading}</th>)}</tr>*/}
                                    <th style={{textAlign:"right", paddingRight:"10px", borderLeft: "lightgray solid 1px"}} onClick={() => handleSorting(getColumnName(0))}>
                                        ID</th>
                                    <th className="th" onClick={() => handleSorting(getColumnName(1))}>
                                        Job Title
                                    <i className={`-sort-${sort.toLowerCase()}`}></i>
                                    </th>
                                    <th className="th" onClick={() => handleSorting(getColumnName(2))}>
                                        Department
                                        <i className={`-sort-${sort.toLowerCase()}`}></i>
                                    </th>
                                    <th className="th" onClick={() => handleSorting(getColumnName(3))}>
                                        Company
                                        <i className={`-sort-${sort.toLowerCase()}`}></i>
                                    </th>
                                    <th className="th" onClick={() => handleSorting(getColumnName(5))}>
                                        Managed By
                                        <i className={`-sort-${sort.toLowerCase()}`}></i>
                                    </th>
                                    <th className="th" onClick={() => handleSorting(getColumnName(4))}>
                                        Location
                                        <i className={`-sort-${sort.toLowerCase()}`}></i>
                                    </th>
                                    <th style={{textAlign:"right", paddingRight:"10px", borderLeft: "lightgray solid 0.5px"}} onClick={() => handleSorting(getColumnName(6))}>
                                        Deadline Date
                                    </th>
                                    <th style={{textAlign:"right", paddingRight:"10px",borderLeft: "lightgray solid 1px", borderRight: "lightgray solid 1px"}} onClick={() => handleSorting(getColumnName(7))}>
                                        Starting Date
                                    </th>
                                </thead>
                                <tbody style={{lineHeight: size}}>
                                {search(paginatedData).map(row =>
                                    <tr key={row.jobId} onClick={() => handleJobInfo(row.jobId)}>
                                        <td style={{textAlign:"right", paddingRight:"10px"}}>{row.jobId}</td>
                                        <td>{row.title}</td>
                                        <td>{row.department}</td>
                                        <td>{row.company}</td>
                                        <td>{row.managedBy}</td>
                                        <td>{row.location}</td>
                                        <td style={{textAlign:"right", paddingRight:"10px"}}>{row.expiryDate}</td>
                                        <td style={{textAlign:"right", paddingRight:"10px"}}>{row.startingDate}</td>
                                </tr>)}
                                </tbody>
                            </Table>

                        <div>
                            <ReactPaginate
                            previousLabel={"Previous"}
                            nextLabel={"Next"}
                            breakLabel={"..."}
                            pageCount={pageCount}
                            marginPagesDisplayed={3}
                            pageRangeDisplayed={3}
                            onPageChange={handlePageClick}
                            containerClassName={"pagination justify-content-center"}
                            pageClassName={"page-item"}
                            pageLinkClassName={"page-link"}
                            previousClassName={"page-item"}
                            previousLinkClassName={"page-link"}
                            nextClassName={"page-item"}
                            nextLinkClassName={"page-link"}
                            activeClassName={"active"}
                            />
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default ApplicantApplication;