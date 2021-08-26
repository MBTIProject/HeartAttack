import React from 'react'
import { HomeOutlined } from '@ant-design/icons'
import { useSelector } from 'react-redux';

const Quiz = (props) => {
    const poster_data = useSelector(state => state.data.poster_data)
    const poster_title = sessionStorage.getItem('title');
    const img_url = sessionStorage.getItem('img_url');

    return (
        <div className="Quiz_border">
            <img src={img_url} style={{height:'200px'}}/>
            <HomeOutlined 
                style={{fontSize:'30px'}} 
                className="home_button"
                onClick={() => props.history.push('/')}/>
            <h3>{poster_title}</h3>
            <p>{poster_data[props.poster_id - 1].quiz}</p>
        </div>
    )
}

export default Quiz
