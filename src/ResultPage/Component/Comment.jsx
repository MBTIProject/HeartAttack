import React, { useState, useEffect, useRef } from 'react'
import '../ResultPage.scss'
import { EditOutlined } from '@ant-design/icons'
import { useDispatch, useSelector } from 'react-redux'
import { actionCreators as dataAction } from '../../../redux/module/data'
import { actionCreators as userAction} from '../../../redux/module/user'

const Comment = ({poster_id}) => {
    const dispatch = useDispatch();
    const comment_data = useSelector(state => state.data.comment_data)
    const [comment, setComment] = useState('');
    const comment_data_reverse = comment_data.reverse();
    const comment_data_length = comment_data.length;
    const text_area_ref = useRef();

    useEffect(() => {
        dispatch(dataAction.load_comment_dataDB(poster_id))
    }, [comment_data])

    const onChangeInput = (e) =>{
        setComment(e.target.value);
    }

    const commentSubmit = (e) => {
        dispatch(userAction.save_comment_dataDB(comment, poster_id));
        setComment('');
        text_area_ref.current.value = '';
    }
    return (
        <div className="comment_border">

            <h3>댓글 입력</h3>
            <div style={{position:'relative'}}>
                <textarea ref={text_area_ref} className="comment_input" rows="7" cols="40" onChange={onChangeInput}/>
                <EditOutlined className="submit_button" onClick={commentSubmit}/>
            </div>
            <span className="number_of_comment comment" >댓글 {comment_data_length}개</span>
            

            {
                comment_data_reverse.map((comment,i) => {
                    return(
                        <>
                            <span className="comment" style={{textAlign:'start'}} >익명{comment_data_length - i}</span>
                            <div className='user_comment_border' >
                                <p>{comment.comment}</p>
                            </div>
                        </>
                    )
                })
            }
        </div>
    )
}

export default Comment
