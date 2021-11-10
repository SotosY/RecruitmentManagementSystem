import React, {Component} from 'react';
import './css/Error.css';
import {withRouter} from "react-router-dom";

class NotFound extends Component {

    constructor(props){
        super(props);
        this.goBack = this.goBack.bind(this);
    }

    goBack(){
        this.props.history.goBack();
    }

    render() {

        return (
            <div>
                <main className='container'>
                    <div className='container-error'>
                        <h1 className='h1-error'>Oops!</h1>
                        <h2 className='h2-error'>Something's went wrong here</h2>
                        <h3 className='h3-error'>We're working on in and we'll get it fixed as as soon as possible</h3>
                        <div style={{textAlign: 'center'}} className='mt-5'>
                            <button onClick={this.goBack} type="submit" className="btn btn-secondary" id="btn_goBack">Go Back</button>
                        </div>
                    </div>
                </main>
            </div>
        );
    }
}

export default withRouter(NotFound);