import React from 'react';
import {Route} from "react-router-dom";
import {ConnectedRouter} from 'connected-react-router'
import {history} from '../redux/configureStore'
import MainPage from './MainPage/MainPage'
import QuizPage from './QuizPage/QuizPage';
import ResultPage from './ResultPage/ResultPage';
import './root.scss';

const App = () => {
    return(
    <>
        <ConnectedRouter history={history}>	
            <Route exact path="/" component={MainPage}/>
            <Route exact path="/quiz/:id" component={QuizPage} />
            <Route path="/quiz/:id/result/:answer" component={ResultPage}/>
        </ConnectedRouter>    
    </>
    )
}

export default App;