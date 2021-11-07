import './css/App.css';
import 'bootstrap/dist/css/bootstrap.min.css'
import UserListComponent from "./Components/UserListComponent";
import Header from "./Components/header";

function App() {
  return (
    <div className="App">
      <div className="header">
        <Header/>
      </div>
      <div className="container">
        <div className="mainbody">
          <UserListComponent/>
        </div>
        <div className="footer">Footer</div>
      </div>
    </div>
  );
}

export default App;
