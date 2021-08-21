import React from 'react'
import './MainPage.scss'

const poster_data_ary = [
    {'title': '연애 심리테스트', 'id':0},
    {'title': '재미있는 심리테스트', 'id':1},
    {'title': '사랑/연애 심리테스트', 'id':2},
    {'title': '연애/성격 심리테스트', 'id':3},
]

const MainPage = ({history}) => {

    return (
        <div className="mainpage_container">
           <img src="src\logo.png" style={{width:'8%'}}/>
           <h1> 당신을 심쿵하게 만드는 심리테스트</h1>
           <div className="polaroid_list">

            {
                poster_data_ary.map(v => {
                    return(
                        <div className="polaroid_outer" id ={v.id} key={v.id}>
                            <div className="polaroid_inner">
                                <img onClick={() => history.push(`/quiz/${v.id}`)} src="src\1.png"/>
                                <div className="polaroid_caption">  
                                    {v.title}
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
