import React, { useState, useRef } from 'react'
import { EditOutlined } from '@ant-design/icons'
import '../ResultPage.scss'
import { actionCreators as userAction} from '../../../redux/module/user'
import { useDispatch } from 'react-redux'

const TextArea = ({poster_id}) => {
    const dispatch = useDispatch();
    const text_area_ref = useRef();
    const [comment, setComment] = useState('');

    const onChangeInput = (e) =>{
        setComment(e.target.value);
    }

    const commentSubmit = () => {
        dispatch(userAction.submit_commentDB(comment, poster_id));
        setComment('');
        text_area_ref.current.value = '';
    }

    return (
        <div style={{position:'relative'}}>
            <textarea ref={text_area_ref} className="comment_input" rows="7" cols="40" onChange={onChangeInput}/>
            <EditOutlined className="submit_button" onClick={commentSubmit}/>
        </div>
    )
}

export default TextArea
