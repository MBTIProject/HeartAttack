import React from 'react'
import { useSelector } from 'react-redux'
import HomeButton from '../../Component/HomeButton'


const TestResult = ({history, answer_id}) => {

    const quiz_data = useSelector(state => state.data.quiz_data)
    return (
        <div style={{position:'relative', textAlign:'center', width:'600px'}}>
            <HomeButton history={history}/>
            <h2 style={{color:'var(--color-text)'}}>심리테스트 결과</h2>
            <h4>{quiz_data[answer_id - 1].answer_title}</h4>
            <p style={{fontWeight:'400'}}>{quiz_data[answer_id - 1].answer}</p>
        </div>
    )
}

export default TestResult
