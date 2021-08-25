import axios from 'axios'

// actions
const LOAD_POSTER_DATA = "LOAD_POSTER_DATA";
const LOAD_QUIZ_DATA = "LOAD_QUIZ_DATA";

// action creators
const load_poster_data = (data) => {
	return { type: LOAD_POSTER_DATA, data }
}
const load_quiz_data = (data) => {
	return { type: LOAD_QUIZ_DATA, data }
}
// initialState
const initialState = {
    poster_data:[],
    quiz_data:[],
}
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


const load_quiz_dataDB = (poster_id, answer) => {
    console.log(poster_id, answer);
    return function(dispatch, getState, {history}){
        // axios.get('http://mbti.govpped.com:7070/survey/list')
        // .then((response) => {
        //     axios.put(`http://mbti.govpped.com:7070/main/list/${poster_id}`)
        //     .then(put_response_data => {
        //         console.log(response.data);
                // dispatch(load_quiz_data(response.data));
                // history.replace(`/quiz/${poster_id}/result/${answer}`);
            // })
            // .catch(error => {
            //     console.log(error);
            // })

        // })
        // .catch((error) => {
        //     console.log(error);
        // })
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
    default: return state;
	}
};


// action creator export
const actionCreators = {
    load_poster_dataDB,
    load_quiz_dataDB,
};

export { actionCreators };