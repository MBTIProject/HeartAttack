import React from 'react'
import { useSelector } from 'react-redux';

const PopularTypes = () => {
    const quiz_data = useSelector(state => state.data.quiz_data)
    const sort_quiz_data = quiz_data.sort((a,b) => b.answer_cnt - a.answer_cnt);
    const popular_quiz_data = [sort_quiz_data[0], sort_quiz_data[1]];
    
    return (
        <div style={{textAlign:'center'}}>
            <h2 style={{color:'var(--color-text)'}}>가장 많이 나온 유형</h2>
            <div className="popular_quiz_board">
                {
                    popular_quiz_data.map((popular_post,i) => {

                        if(popular_post.answer_cnt == 0){
                            return(
                                <>
                                </>
                            )
                        }

                        return(
                            <div key={i}>
                                <h3>{popular_post.selection}</h3>
                                <h3>{i + 1}위 : {popular_post.answer_cnt}회</h3>
                            </div>
                        )
                    })
                }
            </div>
        </div>
    )
}

export default PopularTypes
