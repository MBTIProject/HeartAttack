import axios from "axios";
import { actionCreators as dataAction } from './data';

// actions
// action creators

// initialState
const initialState = {
}
//middle ware
const submit_commentDB = (comment, poster_id) => {
	return function(dispatch, getState, {history}) {
		axios.post('http://mbti.govpped.com:7070/comment/list',{
			'comment': comment,
			'poster_id': poster_id,
		})
		.then(response => {
			// console.log(response.data);
			dispatch(dataAction.load_comment_dataDB(poster_id));
		})
		.catch(error => {
			// console.log(error);
			alert('댓글 데이터를 DB에 저장하는데에 실패했습니다.',error);
		})
	}
}

const submit_answerDB = (poster_id, selection_id) => {
    return function(dispatch, getState, {history}){
        axios.put(`http://mbti.govpped.com:7070/main/list/${poster_id}`)
        .then((response) => {
            // console.log(response);
        })
        .catch((error) => {
            // console.log(error);
            alert('심리테스트 조회수를 갱신하는데에 실패했습니다!', error);
        })
        axios.put(`http://mbti.govpped.com:7070/survey/list/${((poster_id - 1) * 4) + parseInt(selection_id)}`)
        .then((response) => {
            // console.log(response);
			const _quiz_data = getState().data.quiz_data;
			_quiz_data[selection_id - 1].answer_cnt++;
			dispatch(dataAction.renew_answer_data(_quiz_data))
            history.replace(`/quiz/${poster_id}/result/${selection_id}`)
        })
        .catch((error) => {
            // console.log(error);
            alert('질문 조회수를 갱신하는데에 실패했습니다!', error);
        })
    }
}
// reducer
export default function reducer(state = initialState, action = {}){ 
	switch(action.type){
    default: return state;
	}
};


// action creator export
const actionCreators = {
	submit_commentDB,
	submit_answerDB,
};

export { actionCreators };