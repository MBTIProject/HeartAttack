import React from 'react';
import './HomeButton.scss';
import { HomeOutlined } from '@ant-design/icons'

const HomeButton = ({history}) => {
    return (
        <HomeOutlined 
        className='home_button'
        onClick={() => history.push('/')}/>
    )
}

export default HomeButton
