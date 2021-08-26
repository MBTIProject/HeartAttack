import React from 'react'
import PopularTypes from './Component/PopularTypes'
import TestResult from './Component/TestResults'
import Comment from './Component/Comment'

const ResultPage = (props) => {
    return (
        <div className="container" style={{height:'100vh',justifyContent:"space-between"}}>
            <TestResult history={props.history}  answer_id={props.match.params.answer}/>
            <PopularTypes/>
            <Comment poster_id={props.match.params.id}/>
        </div>
    )
}

export default ResultPage
