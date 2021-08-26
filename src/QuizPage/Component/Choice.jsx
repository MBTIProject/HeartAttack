import React, { useEffect } from 'react'
import { useDispatch, useSelector } from 'react-redux'
import { actionCreators as dataAction } from '../../../redux/module/data'

const Choice = (props) => {
    const dispatch = useDispatch();
    const quiz_data = useSelector(state => state.data.quiz_data)
    console.log(props);

    useEffect(() => {
        dispatch(dataAction.load_quiz_dataDB(parseInt(props.poster_id)))
    }, [])

    const submit_answer = (answer) => {
        dispatch(dataAction.count_answer(props.poster_id, answer))
    }

    return (
        <div className="choice_border">
            {
                quiz_data.map((quiz,i) => {
                    return(
                        <li key={i} onClick={() => submit_answer(quiz.id)}>{quiz.selection}</li>
                    )
                })
            }
        </div>
    )
}

export default Choice
