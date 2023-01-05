import React, { useEffect } from 'react'
import './MainPage.scss'
import { CaretRightOutlined } from '@ant-design/icons'
import { actionCreators as dataAction } from '../../redux/module/data'
import { useDispatch, useSelector } from 'react-redux'

const MainPage = ({history}) => {
    const dispatch = useDispatch();
    const poster_data = useSelector(state => state.data.poster_data);


    useEffect(() => {
        dispatch(dataAction.load_poster_dataDB());
    },[])


    const move_quiz_page = (poster_id, poster_title, img_url) => {
        sessionStorage.setItem('title', poster_title);
        sessionStorage.setItem('img_url', img_url);
        history.push(`/quiz/${poster_id}`);
    }

    
    return (
        <div className="mainpage_container">
           <img src="src\logo.png" style={{width:'8%'}}/>
           <h1> 당신을 심쿵하게 만드는 심리테스트</h1>
           <div className="polaroid_list">
               {
                   poster_data.map((v,i) => {
                       return(
                        <div className="polaroid_outer" id ={v.poster_id} key={i}>
                            <div className="polaroid_inner">
                                <img onClick={() => move_quiz_page(v.poster_id,v.poster_title, v.img_url)} src={v.img_url}/>
                                <div className="polaroid_caption">  
                                    <span>{v.poster_title}<CaretRightOutlined style={{color:'green'}}/>{v.poster_cnt}</span>
                                </div>
                            </div>
                        </div>
                       )
                   })
               }
            </div>
        </div>
    )
}

export default MainPage
