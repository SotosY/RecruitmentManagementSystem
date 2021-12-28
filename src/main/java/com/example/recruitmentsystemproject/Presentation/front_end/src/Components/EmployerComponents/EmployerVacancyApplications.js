import {useHistory, useLocation} from "react-router-dom";
import {
    acceptAnApplicant,
    getApplicationDetails,
    getVacancyHistoryPage,
    rejectAnApplicant
} from "../../Services/EmployerService";
import _ from "lodash/fp";
import React, {useEffect, useState} from "react";
import {MdFormatLineSpacing} from "react-icons/md";
import {Dropdown, Form, Table} from "react-bootstrap";
import {BiSelectMultiple} from "react-icons/bi";
import ReactPaginate from "react-paginate";
import {hotjar} from "react-hotjar";


const EmployerVacancyApplications = () => {

    const [applications, setApplications] = useState([]);
    const [paginatedData, setPaginatedData] = useState([]);
    const [query, setQuery] = useState("");
    const [filter, setFilter] = useState(["applicantId"]);
    const [size, setSize] = useState("40px");
    const [sort, setSort] = useState("ASC");
    const history = useHistory();
    const location = useLocation();
    const getId = location.state;

    const columns = ["applicantId", "firstName", "lastName", "email", "dateOfBirth", "cv", "coverLetter", "applicationStatus"]
    const pageSize = 10;
    const pageCount = applications? Math.ceil(applications.length/pageSize) :0;


    const getApplications = () => {
        getApplicationDetails(getId).then((res) => {
            const data = res.data
            setApplications(data)
            setPaginatedData(_(data).slice(0).take(pageSize).value());
        })
    }

    useEffect(() => {
        getApplications()
        hotjar.initialize(2738985, 6);
    }, [])

    function search(rows) {

        return rows.filter(
            // (row) =>
            //     filter.some(column => row[column]?.toString().toLowerCase().indexOf(query.toLowerCase()) > -1 )

                (row) =>
                    row.applicant?.applicantId.toString().toLowerCase().indexOf(query.toLowerCase()) > -1 ||
                    row.applicant?.firstName.toString().toLowerCase().indexOf(query.toLowerCase()) > -1 ||
                    row.applicant?.lastName?.toString().toLowerCase().indexOf(query.toLowerCase()) > -1 ||
                    row.applicant?.user?.email.toString().toLowerCase().indexOf(query.toLowerCase()) > -1 ||
                    row.applicant?.dateOfBirth?.toString().toLowerCase().indexOf(query.toLowerCase()) > -1 ||
                    row.cv?.toString().toLowerCase().indexOf(query.toLowerCase()) > -1 ||
                    row.coverLetter?.toString().toLowerCase().indexOf(query.toLowerCase()) > -1 ||
                    row.applicationStatus?.toString().toLowerCase().indexOf(query.toLowerCase()) > -1
        );
    }

    function getColumnName(num) {
        return columns[num]
    }

    function handleSorting(col){
        if (sort === "ASC"){
            const sorted =[...paginatedData].sort((a,b) =>
                a[col]?.toString().toLowerCase() > b[col]?.toString().toLowerCase() ? 1: -1
            );
            setPaginatedData(sorted);
            setSort("DESC");
        } else {
            const sorted =[...paginatedData].sort((a,b) =>
                a[col]?.toString().toLowerCase() < b[col]?.toString().toLowerCase() ? 1: -1
            );
            setPaginatedData(sorted);
            setSort("ASC");
        }
    }

    function handleRefreshPage() {
        window.location.reload();
    }

    function handleAccept(applicationId, applicantId){

        const data = {applicationId,applicantId};

        acceptAnApplicant(data)
        handleRefreshPage()
    }

    function handleReject(applicationId, applicantId){

        const data = {applicationId,applicantId};

        rejectAnApplicant(data)
        handleRefreshPage()
    }

    function handleChecked(column){
        const checked = filter.includes(column)
        setFilter(prevState => checked ? prevState.filter(sc => sc !== column): [...prevState, column])
    }

    function handleAllChecked(){
        setFilter(columns)
    }

    function handleCv(file){
        history.push(file);
    }

    function openLink(url, newTab) {
        newTab
            ? window.open(url.toString(), "_blank", "noopener noreferrer")
            : (window.location.href = url);
    }

    function handlePageClick(data){
        let currentPage = data.selected + 1
        console.log(currentPage)
        pagination(currentPage)
    }

    /**
     * Code adapted from tutorial at https://www.youtube.com/watch?v=kEd81ZirrCY
     */
    const pagination = (pageNo) => {

        const startIndex = (pageNo - 1) * pageSize;
        const paginatedPost = _(applications).slice(startIndex).take(pageSize).value();
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

    return (
        <div>
            <div className="container">
                <section>
                    <div className='container-breadcrumb'>
                        <ol className="breadcrumb">
                            <li><a href="http://localhost:3000/careers/employer/dashboard">EMPLOYER DASHBOARD</a></li>
                            <li><a href="http://localhost:3000/careers/employer/vacancy-history">VACANCY HISTORY</a></li>
                            <li className='active'>APPLICATION DETAILS</li>
                        </ol>
                    </div>
                    <div className='container-button'>
                        <button type="submit"
                                className="btn btn-secondary"
                                variant="primary"
                                id="submit"
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
                            {/*<Form.Check label="Applicant ID"*/}
                            {/*            className="inline"*/}
                            {/*            checked={filter.includes(getColumnName(0))}*/}
                            {/*            onChange={(e) => {{handleChecked(getColumnName(0))}*/}
                            {/*            } }/>*/}

                            {/*<Form.Check label="Name/Surname"*/}
                            {/*            className="inline"*/}
                            {/*            checked={filter.includes(getColumnName(1))}*/}
                            {/*            onChange={(e) => {{handleChecked(getColumnName(1))}*/}
                            {/*            } } />*/}

                            {/*<Form.Check label="Email"*/}
                            {/*            className="inline"*/}
                            {/*            checked={filter.includes(getColumnName(3))}*/}
                            {/*            onChange={(e) => {{handleChecked(getColumnName(3))}*/}
                            {/*            } } />*/}

                            {/*<Form.Check label="Status"*/}
                            {/*            className="inline"*/}
                            {/*            checked={filter.includes(getColumnName(7))}*/}
                            {/*            onChange={(e) => {{handleChecked(getColumnName(7))}*/}
                            {/*            } } />*/}
                        </div>
                        <div className="input-btn">
                            <Dropdown onSelect={eventKey => setSize(eventKey)}>
                                {/*<BiSelectMultiple className={"btn-actions"} size="16%" style={{marginLeft:"55%",float:"left", display:"inline-block"}} onClick={handleAllChecked}/>*/}
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
                                <tr>
                                {/*<tr>{data[0] && columns.map((heading) => <th>{heading}</th>)}</tr>*/}
                                    <th style={{textAlign:"right", paddingRight:"10px", borderLeft: "lightgray solid 1px"}} onClick={() => handleSorting(getColumnName(0))}>
                                        ID
                                        <i className={`-sort-${sort.toLowerCase()}`}></i>
                                    </th>
                                    <th className="th">
                                        First Name
                                    </th>
                                    <th className="th">
                                        Last Name

                                    </th>
                                    <th className="th">
                                        Email

                                    </th>
                                    <th className="th">
                                        Date Of Birth
                                    </th>
                                    <th className="th">
                                        CV
                                    </th>
                                    <th style={{textAlign:"right", paddingRight:"10px", borderLeft: "lightgray solid 0.5px"}}>
                                        Cover Letter
                                    </th>
                                    <th style={{textAlign:"right", paddingRight:"10px",borderLeft: "lightgray solid 1px", borderRight: "lightgray solid 1px"}} onClick={() => handleSorting(getColumnName(7))}>
                                        Status
                                        <i className={`-sort-${sort.toLowerCase()}`}></i>
                                    </th>
                                    <th>
                                        Actions
                                    </th>
                                </tr>
                            </thead>
                            <tbody style={{lineHeight: size}}>
                            { paginatedData &&
                            search(paginatedData).map(row =>
                                <tr key={row.applicant?.applicantId}>
                                    <td style={{textAlign:"right", paddingRight:"10px"}}>{row.applicant?.applicantId}</td>
                                    <td>{row.applicant?.firstName}</td>
                                    <td>{row.applicant?.lastName}</td>
                                    <td>{row.applicant?.user?.email}</td>
                                    <td>{row.applicant?.dateOfBirth}</td>
                                    <td><a href={row.applicantResume?.cv} target="_blank" rel="nore" style={{color:"#B5DC10"}}>{row.applicantResume?.cv}</a></td>
                                    <td style={{textAlign:"right", paddingRight:"10px"}}><a href={row.applicantResume?.coverLetter} target="_blank" rel="nore" style={{color:"#B5DC10"}}>{row.applicantResume?.coverLetter}</a></td>
                                    <td style={{textAlign:"right", paddingRight:"10px"}}>{row.applicationStatus}</td>
                                    <td align="center">

                                        <button
                                            onClick={() => handleAccept(row.applicationId,row.applicant?.applicantId)}
                                            style={{marginRight:"25px"}}
                                            className="btn btn-secondary"
                                            variant="primary"
                                            id="submit"
                                        >ACCEPT
                                        </button>
                                        <button
                                            onClick={() => handleReject(row.applicationId,row.applicant?.applicantId)}
                                            className="btn btn-danger btn-reject"
                                            variant="primary"
                                            id="submit"
                                        >
                                            REJECT
                                        </button>
                                    </td>
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
                    <div>

                    </div>
                </div>

            </div>
        </div>
    )
}

export default EmployerVacancyApplications