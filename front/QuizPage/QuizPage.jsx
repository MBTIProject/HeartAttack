import React from 'react'
import './QuizPage.scss'
import Quiz from './Component/Quiz'
import Choice from'./Component/Choice'
const QuizPage = (props) => {

    return (
        <div className="container">
            <Quiz history={props.history} poster_id={props.match.params.id}/>
            <Choice history={props.history} poster_id={props.match.params.id}/>
        </div>
    )
}

export default QuizPage
