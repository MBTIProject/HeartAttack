import React, { useEffect } from 'react'
import { useDispatch, useSelector } from 'react-redux'
import { actionCreators as dataAction } from '../../../redux/module/data'
import { actionCreators as userAction} from '../../../redux/module/user'

const Choice = (props) => {
    const dispatch = useDispatch();
    const quiz_data = useSelector(state => state.data.quiz_data)

    useEffect(() => {
        dispatch(dataAction.load_quiz_dataDB(parseInt(props.poster_id)))
    }, [])

    const submit_answer = (selection_id) => {
        dispatch(userAction.submit_answerDB(props.poster_id, selection_id))
    }

    return (
        <div className="choice_border">
            {
                quiz_data.map((quiz,i) => {
                    return(
                        <li key={i} onClick={() => submit_answer(quiz.selection_id)}>{quiz.selection}</li>
                    )
                })
            }
        </div>
    )
}

export default Choice
