// actions
const SUBMIT_ANSWER = "SUBMIT_ANSWER";
const SUBMIT_COMMENT = "SUBMIT_COMMENT";
// action creators
const submit_answer = (answer) => {
	return { type: SUBMIT_ANSWER, answer }
}
const submit_comment = (commentObject) => {
	return { type: SUBMIT_COMMENT, commentObject }
}
// initialState
const initialState = {
    answer:0,
}
//middle ware
const save_comment_dataDB = () => {
	return function(dispatch, getState, {history}) {
		
	}
}

// reducer
export default function reducer(state = initialState, action = {}){ 
	switch(action.type){
    	case "SUBMIT_ANSWER" : {
        	return {...state, answer: action.answer};
        }
    	case "SUBMIT_COMMENT" : {
        	return {...state, };
        }
    default: return state;
	}
};


// action creator export
const actionCreators = {
    submit_answer,
	save_comment_dataDB,
};

export { actionCreators };