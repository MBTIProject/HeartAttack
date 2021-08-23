import React, { useState } from 'react'
import '../ResultPage.scss'
import { EditOutlined } from '@ant-design/icons'
const comment_temp_data = [
    {'key':1, 'user_name':'익명1호', 'content':'심리테스트 너무 재밌어요!'},
    {'key':2, 'user_name':'익명2호', 'content':'또 만들어주세요!'},
    {'key':3, 'user_name':'익명3호', 'content':'배고파요'},
]

const Comment = () => {
    const [comment, setComment] = useState('');

    const onChangeInput = (e) =>{
        setComment(e.target.value);
    }

    const commentSubmit = (e) => {

    }
    return (
        <div className="comment_border">

            <h3>댓글 입력</h3>
            <div style={{position:'relative'}}>
                <textarea className="comment_input" rows="7" cols="40" onChange={onChangeInput}/>
                <EditOutlined className="submit_button" onClick={commentSubmit}/>
            </div>
            

            {
                comment_temp_data.map(comment => {
                    return(
                        <div className='user_comment_border' key = {comment.key}>
                            <p>{comment.user_name}</p>
                            <p>{comment.content}</p>
                        </div>
                    )
                })
            }
        </div>
    )
}

export default Comment
