import React from 'react'
import { HomeOutlined } from '@ant-design/icons'

const HomeButton = ({history}) => {
    return (
        <HomeOutlined 
        style={{
            fontSize:'30px',
            position:'absolute',
            top:'0px',
            left:'-50px',
            cursor:'pointer'
        }} 
        onClick={() => history.push('/')}/>
    )
}

export default HomeButton
