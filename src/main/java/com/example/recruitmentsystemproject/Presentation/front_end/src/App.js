import './Components/css/App.css';
import 'bootstrap/dist/css/bootstrap.min.css'
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import JobListComponent from "./Components/JobListComponent";
import Header from "./Components/Header";
import Footer from "./Components/Footer";
import LoginComponent from "./Components/UserLogin";
import ApplicantDashboard from "./Components/ApplicantComponents/ApplicantDashboard";
import ApplicantProfile from "./Components/ApplicantComponents/ApplicantProfile";
import ApplicantApplication from "./Components/ApplicantComponents/ApplicantApplication";
import ApplicantJobInfo from "./Components/ApplicantComponents/ApplicantJobInfo";
import ApplicantApplicationHistory from "./Components/ApplicantComponents/ApplicantApplicationHistory";
import ApplicantRegister from "./Components/ApplicantComponents/ApplicantRegister";
import EmployerRegister from "./Components/EmployerComponents/EmployerRegister";
import EmployerDashboard from "./Components/EmployerComponents/EmployerDashboard";
import EmployerProfile from "./Components/EmployerComponents/EmployerProfile";
import EmployerVacancy from "./Components/EmployerComponents/EmployerVacancy";
import NotFound from "./Components/NotFound";



function App() {

  return (
    <div className="App">
        <Router>
            <Header />
                <div>
                    <Switch>
                        <Route exact path = "/careers" component={JobListComponent}/>
                        <Route exact path = "/careers/login" component={LoginComponent}/>
                        <Route exact path = "/careers/register/a" component={ApplicantRegister}/>
                        <Route exact path = "/careers/register/e" component={EmployerRegister}/>
                        <Route exact path = "/careers/applicant/dashboard" component={ApplicantDashboard}/>
                        <Route exact path = "/careers/applicant/profile" component={ApplicantProfile}/>
                        <Route exact path = "/careers/applicant/application" component={ApplicantApplication}/>
                        <Route exact path = "/careers/applicant/application-history" component={ApplicantApplicationHistory}/>
                        <Route exact path = "/careers/applicant/application/job/:id" component={ApplicantJobInfo}/>
                        <Route exact path = "/careers/employer/dashboard" component={EmployerDashboard}/>
                        <Route exact path = "/careers/employer/profile" component={EmployerProfile}/>
                        <Route exact path = "/careers/employer/vacancies" component={EmployerVacancy}/>
                        <Route path = "*" component={NotFound}/>
                    </Switch>
                </div>
            <Footer />
        </Router>
    </div>
  );
}

export default App;
