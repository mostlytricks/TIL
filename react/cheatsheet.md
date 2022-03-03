# React

## TOC
- 0. [개요](#description)
- 1. [훅..훅훅훅..](#hooks)
  -  [useReducer](#useReducer)

- 2. [Link & Route](#)
  - [react-router-dom](#react-router-dom) 

## Description
- 표준 문서 해독 및 hooks / 기타 자주 사용되는 package들 예제 모음 목적
- 묘수 풀이. 
<br/>
<br/>




## Hooks

### useReducer
문서는 여기 -> [문서](https://reactjs.org/docs/hooks-reference.html#usereducer)
- 개요 : useState의 대체. 
  - 앞선 상태가 다음 상태에 영향 주거나 여러개의 sub-value에 연계될때 사용. 
  - dispatch를 이용해서 deep update 이용 가능


<details>
<summary> 상세 </summary>
  
```jsx
const initialState = {count: 0};

function reducer(state, action) {
  switch (action.type) {
    case 'increment':
      return {count: state.count + 1};
    case 'decrement':
      return {count: state.count - 1};
    default:
      throw new Error();
  }
}

function Counter() {
  const [state, dispatch] = useReducer(reducer, initialState);
  return (
    <>
      Count: {state.count}
      <button onClick={() => dispatch({type: 'decrement'})}>-</button>
      <button onClick={() => dispatch({type: 'increment'})}>+</button>
    </>
  );
}
```
</details/>

> 
>
  
  
## 3. 직접 구성한 예제
---

### axios 
> api 호출 부, 실제 display 부분을 분리하여 관리하는게 편리함
> 
> getWeather로 감싸지 않는경우, axios 호출이 interval하게 실행되는 현상 확인


##### API Calls
```jsx
const WeatherAPI() =>{
  const [weatherData, setWeatherData] = useState();
  
  const url = "https:// {... 중략}";
  const getWeather = () =>{
    axios.get(url).then((responseData)=>{
      const data = responseData.data;
      setWeatherData(data); // useState 사용
    }).catch((err)=> console.log(err))
  }
  
  useEffect(()=>{
    getWeather();
  },[]) // 최초 마운트시 1회 요청
  
  return(<WeatherComponent>
    <WeatherDisplay data = {weatherData}/> {/* props로 다음 컴포넌트에 전달, 데이터 수신 시 re-rendering*/}
  </WeatherComponent>)
}

```

##### Return in UI
```jsx
const WeatherDisplay=(prop)=>{
  const {weather, main, sys, name} = prop.data;
  if (weather!=null){ // prop 전달 시, re-rendering에 의해 갱신되며 조건문 재확인
    url = `{...생략}`
    return(<>
      {weather.wantToCheck}
    </>)
  } else {
    return <h3>No Info.</h3>
  }
}

```

[참고 - fetch api data with axios](https://levelup.gitconnected.com/fetch-api-data-with-axios-and-display-it-in-a-react-app-with-hooks-3f9c8fa89e7b)




## Link, Router

### react-router-dom

- 개요 : 컴포넌트 단위로 페이지 라우팅 지원
- 참고 - [velopert님의 velog](https://velog.io/@velopert/react-router-v6-tutorial)
- 사용법
  - App등 라우팅 지원할 최상위 component를 <BrowserRouter>로 감싸기   
  - 상기 컴포넌트의 내부에 상세와 같이 구성


<details>
<summary> 상세 </summary>
  
```jsx
import { Route, Routes} from 'react-router-dom';
import { Link } from 'react-router-dom';

  
import PageA from './PageA';
import PageB from './PageB';

const App = () =>{
  return (
    <Routes>
      <Route path ="/" element = {<PageA />}/>
      <Route path = "/bb" element = {<PageB />}/>
      {/*<div></div> 이 태그는 여기서 사용 시 에러를 발생 시킨다. routes안은 route만 허용.*/}
      <Route path = "/bb/:username" element={<PageB />} />
    </Routes>
    <div>이 구획은 Route된 컴포넌트 하단에 try ~ catch ~ finally의 finally처럼 붙는다.</div>
    <Link to = "/">집가자 </Link> {/* 하이퍼 링크 형태의 이동 링크가 나타난다.*/}
  )
}
```
- url parameter 이용시, 각 컴포넌트에서는 하기와 같이 사용
  
```jsx
import { useParams } from 'react-router-dom'; 
...
  
const Page =() =>{
  const params = useParams(); // url에서 전달한 파라미터가 {key: val, ...} 형태로 존재
  return ...
  }
```
 
- react router 내부에서는 :username과 같이 사용하자 
  
  
<details>
  
#### useLocation  
  
#### useNavigate
```jsx
import { Outlet, useNavigate } from 'react-router-dom';

const Layout = () => {
  const navigate = useNavigate();

  const goBack = () => {
    // 이전 페이지로 이동
    navigate(-1);
  };

  const goArticles = () => {
    // articles 경로로 이동
    navigate('/articles');
  };

  return (
    <div>
      <header style={{ background: 'lightgray', padding: 16, fontSize: 24 }}>
        <button onClick={goBack}>뒤로가기</button>
        <button onClick={goArticles}>게시글 목록</button>
      </header>
      <main>
        <Outlet />
      </main>
    </div>
  );
};
export default Layout;
```
  
- -1 : 뒤로가기 / 1 : 앞으로 가기 (이미 뒤로가기가 진행된 경우) / -2 : 뒤로 두 번.. 등
- 주소 입력 시 해당 경로로 이동 가능
 
#### NavLink
- 활성화된 링크에 다른 css적용 기법
  
#### Navigate
- 로그인이 안된 경우 로그인 페이지 제공 등, 컴포넌트를 화면에 보여주는 순간 사용
- 

  
  
  
  
  
## 트릭

- 주요한 트릭은 styled-component와 연계 필요, css의 cheatsheet.md 참고할 것

### component를 해동해서 뿌려주기.
```jsx
const vehicleType= {
    1 : <DateSelector/>,
    2 : "O_o",
    3 : "PhoneCall",
    4 : "Line",
    5 : "People",
    6 : "Activity",
    7 : "Messages",
}

const Vehicle = (props) =>{

    const selectedVehicle = props.inlineId +props.trackId*5;
    console.log(selectedVehicle)
    return(
    <VehicleStyled>
        {vehicleType[selectedVehicle]}
    </VehicleStyled>
    );
}
```
- 앞선 배열에 key : value로 담아놓은 component를 그대로 뿌릴 수 있다!
