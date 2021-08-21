import React from 'react'
import HomeButton from '../../Component/HomeButton'
const TestResult = ({history}) => {

    return (
        <div style={{position:'relative'}}>
            <HomeButton history={history}/>
            TestResult입니다!
        </div>
    )
}

export default TestResult
