import React, {useEffect, useState} from 'react';
import _ from "lodash/fp";
import {Dropdown, Form, Table} from "react-bootstrap";
import {useHistory} from "react-router-dom";
import {getApplicationHistory} from "../../Services/ApplicantService";
import {MdFormatLineSpacing} from "react-icons/md";
import {BiSelectMultiple} from "react-icons/bi";
import ReactPaginate from "react-paginate";
import {hotjar} from "react-hotjar";

const ApplicantApplicationHistory = () => {

    const [applications, setApplications] = useState([]);
    const [paginatedData, setPaginatedData] = useState([]);
    const [query, setQuery] = useState("");
    const [filter, setFilter] = useState(["applicationId"]);
    const [size, setSize] = useState("40px");
    const [sort, setSort] = useState("ASC");
    const history = useHistory();

    const columns = ["applicationId", "applyDate", "applicationStatus"]
    const columnName = applications[0] && Object.keys(applications[0])
    const pageSize = 10;
    const pageCount = applications? Math.ceil(applications.length/pageSize) :0;

    const getApplication = () => {
        getApplicationHistory().then((res => {
                const data = res.data
                setApplications(data);
                setPaginatedData(_(data).slice(0).take(pageSize).value());
            }
        ))
            .catch((error) => {
                console.log(error)
            })
    }

    useEffect(() => {
        getApplication()
        hotjar.initialize(2738985, 6);
    }, [])


    function handleSearch(data) {
        return search(data)
    }

    function handleSorting(col){

        if (sort === "ASC"){
            const sorted =[...paginatedData].sort((a,b) =>
                a[col]?.toString().toLowerCase() > b[col].toString().toLowerCase() ? 1: -1
            );
            setPaginatedData(sorted);
            setSort("DESC");
        } else {
            const sorted =[...paginatedData].sort((a,b) =>
                a[col]?.toString().toLowerCase() < b[col].toString().toLowerCase() ? 1: -1
            );
            setPaginatedData(sorted);
            setSort("ASC");
        }
    }

    function handleChecked(column){
        const checked = filter.includes(column)
        setFilter(prevState => checked ? prevState?.filter(sc => sc !== column): [...prevState, column])
    }

    function handleAllChecked(){
        setFilter(columns)
    }

    function handlePageClick(data){
        let currentPage = data.selected + 1
        pagination(currentPage)
    }

    function getColumnName(num) {
        return columns?.[num]
    }

    function search(rows) {

        const col = filter;
        const lastCol = col.length-1

        // switch (col[lastCol]){
        //     case 'applicationId':
        //         return rows.filter(
        //             (row) =>
        //                 row.applicationId.toString().toLowerCase().indexOf(query.toLowerCase()) > -1
        //         );
        //     case 'status':
        //         return rows.filter(
        //             (row) =>
        //                 row.job?.status.toString().toLowerCase().indexOf(query.toLowerCase()) > -1
        //         );
        //     case 'title':
        //         return rows.filter(
        //             (row) =>
        //                 row.job?.title.toString().toLowerCase().indexOf(query.toLowerCase()) > -1
        //         );
        //     default:
        //         return rows.filter(
        //             (row) =>
        //                 row?.applicationId.toString().toLowerCase().indexOf(query.toLowerCase()) > -1 ||
        //                 row?.job?.status?.toString().toLowerCase().indexOf(query.toLowerCase()) > -1 ||
        //                 row?.job?.title?.toString().toLowerCase().indexOf(query.toLowerCase()) > -1 ||
        //                 row?.job?.managedBy?.toString().toLowerCase().indexOf(query.toLowerCase()) > -1
        //         );
        //
        // }
        return rows.filter(
            // (row) =>
            //     row?.applicationId.toString().toLowerCase().indexOf(query.toLowerCase()) > -1 ||
            //     row?.job?.status?.toString().toLowerCase().indexOf(query.toLowerCase()) > -1 ||
            //     row?.job?.title?.toString().toLowerCase().indexOf(query.toLowerCase()) > -1 ||
            //     row?.job?.managedBy?.toString().toLowerCase().indexOf(query.toLowerCase()) > -1
            //     row?.applyDate.toString().indexOf(query.toLowerCase()) > -1

            (row) =>
                filter.some(column => row[column]?.toString().toLowerCase().indexOf(query.toLowerCase()) > -1 )
        );

    }

    function handleLogout() {
        localStorage.clear();
        window.location.href = "/careers/login";
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

        return (
            <div className='container'>
                <div>
                    <section>
                        <div className='container-breadcrumb'>
                            <ol className="breadcrumb">
                                <li><a href="http://localhost:3000/careers/applicant/dashboard">APPLICANT DASHBOARD</a></li>
                                <li className='active'>APPLICATION HISTORY</li>
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
                </div>

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
                            <Form.Check label="Application ID"
                                        className="inline"
                                        checked={filter.includes(getColumnName(0))}
                                        onChange={(e) => {{handleChecked(getColumnName(0))}
                                        } }/>


                            <Form.Check label="Apply Date"
                                        className="inline"
                                        checked={filter.includes(getColumnName(1))}
                                        onChange={(e) => {{handleChecked(getColumnName(1))}
                                        } } />

                            <Form.Check label="Application Status"
                                        className="inline"
                                        checked={filter.includes(getColumnName(2))}
                                        onChange={(e) => {{handleChecked(getColumnName(2))}
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
                </div>
                <div className="container-md">
                    <Table  striped bordered hover size="sm" id="sortTable">
                        <thead>
                            <tr>
                            {/*<tr>{data[0] && columns.map((heading) => <th>{heading}</th>)}</tr>*/}
                                <th style={{textAlign:"right", paddingRight:"10px", borderLeft: "lightgray solid 1px"}} onClick={() => handleSorting(getColumnName(0))}>
                                    ID
                                    <i className={`-sort-${sort.toLowerCase()}`}></i>
                                </th>
                                <th className="th">
                                    Status
                                </th>
                                <th className="th">
                                    Job Title
                                </th>
                                <th className="th" >
                                    Company
                                </th>
                                <th className="th">
                                    Managed By
                                </th>
                                <th className="th" onClick={() => handleSorting(getColumnName(1))}>
                                    Apply Date
                                    <i className={`-sort-${sort.toLowerCase()}`}></i>
                                </th>
                                <th style={{textAlign:"right", paddingRight:"10px", borderLeft: "lightgray solid 0.5px"}}>
                                    Expiry Date
                                </th>
                                <th style={{textAlign:"right", paddingRight:"10px",borderLeft: "lightgray solid 1px", borderRight: "lightgray solid 1px"}} onClick={() => handleSorting(getColumnName(2))}>
                                    Application Status
                                    <i className={`-sort-${sort.toLowerCase()}`}></i>
                                </th>
                            </tr>
                        </thead>
                        <tbody style={{lineHeight: size}}>
                        { paginatedData &&
                            search(paginatedData).map(row =>
                            <tr key={row.applicationId}>
                                <td style={{textAlign:"right", paddingRight:"10px"}}>{row.applicationId}</td>
                                <td>{row.job?.status}</td>
                                <td>{row.job?.title}</td>
                                <td>{row.job?.company}</td>
                                <td>{row.job?.managedBy}</td>
                                <td>{row.applyDate}</td>
                                <td>{row.job?.expiryDate}</td>
                                <td style={{textAlign:"right", paddingRight:"10px"}}>{row.applicationStatus}</td>
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
        )

}

export default ApplicantApplicationHistory;