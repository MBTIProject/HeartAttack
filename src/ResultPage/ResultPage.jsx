import React from 'react'

import PopularTypes from './Component/PopularTypes'
import TestResult from './Component/TestResults'
import Comment from './Component/Comment'
const ResultPage = (props) => {
    console.log(props.match.params);
    return (
        <div className="container" style={{height:'100vh',justifyContent:"space-between"}}>
            <TestResult history={props.history}/>
            <PopularTypes/>
            <Comment/>
        </div>
    )
}

export default ResultPage
