import React, { useEffect } from 'react'
import '../ResultPage.scss'
import { useDispatch, useSelector } from 'react-redux'
import { actionCreators as dataAction } from '../../../redux/module/data'
import TextArea from './TextArea'

const Comment = ({poster_id}) => {
    const dispatch = useDispatch();
    const comment_data = useSelector(state => state.data.comment_data)

    useEffect(() => {
        dispatch(dataAction.load_comment_dataDB(poster_id))
    }, [])


    return (
        <div className="comment_border">

            <h3>댓글 입력</h3>
            <TextArea poster_id={poster_id}/>
            <span className="number_of_comment comment" >댓글 {comment_data.length}개</span>
            

            {
                comment_data.reverse().map((v,i) => {
                    return(
                        <>
                            <span className="comment" style={{textAlign:'start'}} >익명{comment_data.length - i}</span>
                            <div className='user_comment_border' >
                                <p>{v.comment}</p>
                            </div>
                        </>
                    )
                })
            }
        </div>
    )
}

export default Comment
