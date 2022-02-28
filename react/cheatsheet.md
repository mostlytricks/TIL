# React

## TOC
- 0. [개요](#description)


## Description
- 표준 문서 해독 및 hooks / 기타 자주 사용되는 package들 예제 모음 목적
- 묘수 풀이. 
<br/>
<br/>




## Hooks

### useReducer
- [문서](https://reactjs.org/docs/hooks-reference.html#usereducer)



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

