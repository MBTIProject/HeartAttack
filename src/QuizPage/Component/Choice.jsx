import React from 'react'
import { useDispatch } from 'react-redux'
import { actionCreators as dataAction } from '../../../redux/module/data'
import { actionCreators as userAction } from '../../../redux/module/user'
import {quiz_data} from '../QuizData' 
import axios from 'axios'

const Choice = (props) => {
    const dispatch = useDispatch();
    const { quiz_items } = quiz_data
    console.log(props);
    const submit_answer = (answer) => {
        dispatch(userAction.submit_answer(answer))
        // dispatch(dataAction.load_quiz_dataDB(parseInt(props.poster_id) + 1, answer))
        // props.history.push(`/quiz/${props.poster_id}/result/${answer}`)
        const url = `http://mbti.govpped.com:7070/main/list/${parseInt(props.poster_id) + 1}`
        axios.put(url,)
        .then(response => {
            console.log(response.data);
        }).catch(error => {
            console.log(error);
        })
    }
    return (
        <div className="choice_border">
            {
                quiz_items[props.poster_id].choice.map((v,i) => {
                    return(
                        <li key={i} onClick={() => submit_answer(i+1)}>{v}</li>
                    )
                })
            }
        </div>
    )
}

export default Choice
