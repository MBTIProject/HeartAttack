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
>   - 백엔드
>     - Basic Lauguage : Java
>     - core : Spring Boot, JPA, Spring Data JPA
>     - BuildTool : Gradle
>     - DB : Mysql
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
<br>
<br>

# 본 원격저장소 사용법

## node.js 설치를 하시고 vscode에서 작업하는 것을 추천한다.

> - Cntr + Shift + ` : 터미널 열기
> - npm i : package.json 설치
> - npm run dev : 실행 명령어 ("script" 참조)

![화면 캡처 2021-06-17 191745](https://user-images.githubusercontent.com/53801395/122378199-b4cd4300-cfa0-11eb-8bba-c3292d5af458.jpg)

**프로젝트 실행 후 http://loaclhost:8080/** 링크를 [ctrl + Click]하면 해당 웹페이지로 브라우저를 띄운다.
<br>
<br>

# 커밋 사항

- 2021.08.15 Na [ 웹팩 초기 설정 ]
- 2021.08.17 Na [ 라우터 초기 설정 ]
- 2021.08.18 Na [ 지문 페이지 구현 완료 ]
- 2021.08.19 hyuna [ MainPage 구현 완료 ]
- 2021.08.19 Na [ MainPage와 QuizPage 최적화 완료 ]
- 2021.08.19 Na [ 댓글창 UI 구현 ]
- 2021.08.25 Na [ 메인 화면 Axios 비동기 통신 구현 및 사진 크기 조절 ]
- 2021.08.26 Na [ 비동기 통신 구현 완료 (댓글 기능 제외 구현 완료) ]
- 2021.08.26 Na [ 댓글 기능 구현 및 리팩토링 ]
