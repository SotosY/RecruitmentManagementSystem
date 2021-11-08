import './css/App.css';
import 'bootstrap/dist/css/bootstrap.min.css'
import JobListComponent from "./Components/JobListComponent";
import Header from "./Components/Header";
import Footer from "./Components/Footer";

function App() {
  return (
    <div className="App">
        <Header/>
        <JobListComponent/>
        <Footer />
    </div>
  );
}

export default App;
