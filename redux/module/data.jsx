import axios from 'axios'

// actions
const LOAD_POSTER_DATA = "LOAD_POSTER_DATA";
const LOAD_QUIZ_DATA = "LOAD_QUIZ_DATA";
const LOAD_COMMENT_DATA = 'LOAD_COMMENT_DATA';

// action creators
const load_poster_data = (data) => {
	return { type: LOAD_POSTER_DATA, data }
}
const load_quiz_data = (data) => {
	return { type: LOAD_QUIZ_DATA, data }
}
const load_comment_data = (data) => {
	return { type: LOAD_COMMENT_DATA, data }
}
// initialState
const initialState = {
    poster_data:[],
    quiz_data:[],
    comment_data:[],
}
// middle ware
const load_poster_dataDB = () => {
    return function(dispatch, getState, {history}){
        axios.get('http://mbti.govpped.com:7070/main/list')
        .then((response) => {
            console.log(response.data);
            dispatch(load_poster_data(response.data));
        })
        .catch((error) => {
            console.log(error);
        })
    }
}


const load_quiz_dataDB = (poster_id) => {
    return function(dispatch, getState, {history}){
        axios.get(`http://mbti.govpped.com:7070/survey/list/${poster_id}`)
        .then((response) => {
            dispatch(load_quiz_data(response.data));
        })
        .catch((error) => {
            console.log(error);
        })
    }
}

const count_answer = (poster_id, answer_id) => {
    return function(dispatch, getState, {history}){
        axios.put(`http://mbti.govpped.com:7070/main/list/${answer_id}`)
        .then((response) => {
            console.log(response);
        })
        .catch((error) => {
            console.log(error);
        })
        axios.put(`http://mbti.govpped.com:7070/survey/list/${answer_id}`)
        .then((response) => {
            console.log(response);
            history.replace(`/quiz/${poster_id}/result/${answer_id}`)

        })
        .catch((error) => {
            console.log(error);
        })
    }
}

const load_comment_dataDB = (poster_id) => {
    return function(dispatch, getState, {history}){
        axios.get(`http://mbti.govpped.com:7070/comment/list/${poster_id}`)
        .then((response) => {
            // console.log(response);
            dispatch(load_comment_data(response.data));
        })
        .catch((error) => {
            console.log(error);
        })
    }
}


// reducer
export default function reducer(state = initialState, action = {}){ 
	switch(action.type){

    	case "LOAD_POSTER_DATA" : {
        	return {...state, poster_data: [...action.data]};
        }
    	case "LOAD_QUIZ_DATA" : {
        	return {...state, quiz_data: [...action.data]};
        }
    	case "LOAD_COMMENT_DATA" : {
        	return {...state, comment_data: [...action.data]};
        }

    default: return state;
	}
};


// action creator export
const actionCreators = {
    load_poster_dataDB,
    load_quiz_dataDB,
    count_answer,
    load_comment_dataDB,
};

export { actionCreators };