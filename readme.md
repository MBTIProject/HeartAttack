# :cupid: 심쿵 프로젝트

react, spring을 사용한 심리테스트 웹사이트를 소개하겠습니다. <br>
 
<br>

# :clipboard: 프로젝트 개요

<br>

>   <br>
>
> - 프로젝트 목적 :  
>   <br>
> - 작업 환경 : VSCode(Front) IntelliJ(Back)  
>   <br>
> - 참여자
>   - 프런트엔드 : 나재완(ABlued)
>   - 백엔드 : 손성진(alisyabob), 이지원(twosupport)
>     <br>
>     <br>
> - 사용 스택 
>   - 프런트엔드 
>       - Basic Lauguage : HTML, CSS, JavaScript 
>       - Framework : React 
>       - UI-Framework : Ant-design 
>       - Library : redux, redux-thunk
>       - ModuleBundler : Webpack
>   <br> 
>   <br> 
>   
>   - 백엔드  
>       - Basic Lauguage : Java
>       - core : Spring Boot, JPA, Spring Data JPA
>       - BuildTool : Gradle
>       - DB : Mysql
>   
>   <br>
<br>
<br>

:heavy_check_mark: 구현된 기능들
---
<br>

 - 심리테스트 전체 출력
 - 조회수 출력
 - 가장 많이 나온 유형 출력 
 - 댓글 입력 및 출력

<br>
<br>

:tv: 시연영상
---
[GD프로젝트 파이널 프로젝트(심쿵) 시연영상 보기[클릭]](https://www.youtube.com/watch?v=eHUybrn65yY)

<br>
<br>

# 🧾 Rest api 목록

프론트엔드와 백엔드와의 데이터 송수신은 rest api를 사용하여 json파일 형태로 주고받았습니다.<br>
이에 대한 코드는 
<details> 
<summary>이부분을 누르거나 </summary>

```
// redux/modules/data.jsx 에서

// middle ware
const load_poster_dataDB = () => {
    return function(dispatch, getState, {history}){
        axios.get('http://mbti.govpped.com:7070/main/list')
        .then((response) => {
            // console.log(response.data);
            dispatch(load_poster_data(response.data));
        })
        .catch((error) => {
            // console.log(error);
            alert('심리테스트 데이터를 받아오는데에 실패했습니다!', error);

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
            // console.log(error);
            alert('퀴즈 데이터를 받아오는데에 실패했습니다!', error);
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
            // console.log(error);
            alert('댓글 데이터를 받아오는데에 실패했습니다!',error);
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
        case "INCREASE_ANSWER_DATA" : {
            return{...state, quiz_data: [...action.data]}
        }
    default: return state;
	}
};
```

</details>

[여기](https://github.com/MBTIProject/HeartAttack/tree/main/redux/module)를 참조해주세요.



<br>
<style>
    table{
        border:gray 1px solid
    }
</style>
<table>
    <tr>
        <th>Name</th>
        <th>Method</th>
        <th>URL</th>
    </tr>
    <tr>
        <td>심리테스트 데이터 등록</td>
        <td>POST</td>
        <td>/main/list</td>
    </tr>
    <tr>
        <td>심리테스트 리스트 조회하기</td>
        <td>GET</td>
        <td>/main/list</td>
    </tr>
    <tr>
        <td>심리테스트 유형별 조회수</td>
        <td>PUT</td>
        <td>/main/list/{id}</td>
    </tr>
    <tr>
        <td>심리테스트 내용,결과 등록</td>
        <td>POST</td>
        <td>/survey/list</td>
    </tr>
    <tr>
        <td>심리테스트 내용, 결과 조회하기</td>
        <td>GET</td>
        <td>/survey/list/{poster_id}</td>
    </tr>
    <tr>
        <td>심리테스트를 진행한 전체인원 조회수</td>
        <td>PUT</td>
        <td>/survey/list/{id}</td>
    </tr>
    <tr>
        <td>댓글리스트 불러오기</td>
        <td>GET</td>
        <td>/comment/list/{poster_id}</td>
    </tr>
    <tr>
        <td>댓글등록</td>
        <td>POST</td>
        <td>/comment/list</td>
    </tr>
</table>
<br>
<br>

# 👀 와이어 프레임 및 UI/UX
<br>

🔗 [웹페이지 UI 간단하게 보기[클릭]](https://ovenapp.io/view/8S9HpSIGCPaHoZf7lW9kweBTQkOW1rIp/)
<br>
<br>

# 📑 DB-ERD
<br>

![DB-ERD](https://user-images.githubusercontent.com/53801395/136155693-2d7733db-e3be-4db3-bfeb-e2829b8a4e48.png)
<br>
<br>
<br>
<br>

# 📑 웹페이지 화면 소개
<br>
페이지 흐름도 : 메인 화면 -> 상세 화면 -> 결과 화면 -> 메인 화면 -> ...

<br>
<br>

- 메인화면
![메인 화면](https://user-images.githubusercontent.com/53801395/136224676-ab3b226f-4832-4f73-b89b-93ef6c47c14a.jpg)
<br>
<br>

- 상세화면
![상세 화면](https://user-images.githubusercontent.com/53801395/136224680-798edd64-8d6c-467a-b30b-d84259d3b9da.jpg)

<br>
<br>

- 결과화면
![결과 화면](https://user-images.githubusercontent.com/53801395/136224682-00f32550-9922-4e61-9e3b-7d0856987f45.jpg)

<br>
<br>

- 댓글화면
![댓글 화면](https://user-images.githubusercontent.com/53801395/136224668-3b9f75d7-8472-46f5-b699-e12aa6387cc4.jpg)


<br>
<br>
<br>
<br>
