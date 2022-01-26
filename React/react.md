# React - Javascript -typescript 
- [참고](https://taesan94.tistory.com/179?category=424721)

### 기록 배경
- dispatch의 문법 및 개념이 이해


## Chap1
---
### 클로저 (Closer란?)



## Chap2
---
### Redux란 
- 정의
  - 전역 상태 관리를 위한 라이브러리
  - Store : 공유되는 상태를 모아두는 곳, component에서 필요한 Status 구독
  - 구현 배경: React에서 Virtual DOM 도입 후 실제 변경점만 DOM에 반영함에 따라, Store의 상태값별 변경점의 격리가 가능해짐
    - Flux => Redux
  
- 특성
  - Component는 Store내의 state 직접변경 x (immutable), 데이터 수신만함. 
  - Redux는 클로저를 이용하여 상태 숨기기 가능
  - *state 참조*하기 위해 이용되는 값은, *복사된 값*
  - 상태를 바꾸기 위한 코드 : reducer
  
- reducer
  - reducer(initState, Object)
  - Object<type, ... >
    - type : 변경 대상. 
    - Object.type 확인 후, 유효한 type의 경우에 상태 변경 + 리턴
    - type : 다른 말로 action. 어떤 유형의 동작 요청인지 확인 => 이후 state를 변경하여 copy 후 리턴.
  - dispatch : type이 담긴 object를 UI layer에서 던지는 행위
    - 그래서 dispatcher와 state를 따로 받구나!!
  
- 예제
  - **reducer**
``` jsx
// reducer 예제
// index.js
const reducer(state ={} , action){
    if (action.type === "INCREMENT"){
        return {
            ...state, //기존상태 복사
            count: state.count ? state.count +1 : 1 // count값이 있다면 +1, 없으면 1로 init
        } else if (action.type === "RESET") {
            return {
                ...state, 
                count: action.resetCount // 초기화로 지정된 변수로 저장. action이라는 객체 설계에 유의할 것
            }
        }
    }
    return state; // action.type이 모두 해당없는 경우, 그대로 리턴
}
// 예상 action 객체
action = {
    type : String = "INCREMENT",
    resetCount :int = 1
};
```
  - **dispatch**    
    - A의 상태를 Object의 Type에 따라 reducer(변경작업) 적용해줘!
    - Object<type, ... > => reducer
    - store의 변경된 상태를 전파하는 함수, 대상 필요. (subscribe 상태 모음)
```jsx
//self-redux.js
const dispatch = (action) =>{ // action은 type값임에 유의할것. para로 object의 type(action.type) 수신
    state = reducer (state, action);
    listners.forEach(fn =>fn()); // 상태 변경을 알리는 함수 전체 호출
}
const listeners =[];
const subscribe =(fn)=>{
    listeners.push(fn);
}

// index.js
store.dispatch({type:"INCREMENT"});
store.dispatch({type:"RESET"});
// store에 담긴 dispatcher에, action.type값이 있는 object를 전달
//=> index에서 dispatcher을 실행하며, 실제로는 dispatcher가 reducer를 부름.

const update=() =>{
    console.log(store.getState());
}
store.subscribe(update);
// update함수를 subscribe 시에 밀어넣음. 
```

-  기타
   - 재정리 : 왜 reducer은 index에 있는지?
     -  dispatcher은 공통적인 모듈(state와 action받아서 store에서 관리하고 처리.. )
     -  reducer은 type에 따라 state를 어떻게 바꿔줘!하고 명시하는 호출자가 전달할 값임.
  
   - 재정리2 : 왜 subscribe함수도 index.js에 있는지?
     - redux/ store의 상태가 변경되었을때, 어떻게 알려줘/ 어떤 함수실행해줘하고 역시 정의해서 전달 필요.

   - 참고 문서 [Why Front 머시기](https://itnext.io/why-every-beginner-front-end-developer-should-know-publish-subscribe-pattern-72a12cd68d44)


## Chap3
---
### VDOM 및 React에 대한 이해
- React 작동
  - jsx => Babel =>React.createElement( ) => VDOM =>(React) => Real DOM
  - Component 변경 시? 
    - New VDOM vs origin VDOM => 변경점만 갱신

- VDOM의 구조
  - tagName, props, children
  - React : one VDOM, children 통해 정보를 순차적으로 전달. // 트리구조로 저장.
  - react.createElement 가 핵심 (vdom객체 생성)

- Babel 및 createElement
  - Babel(createElement(type, props, children)) => type의 첫 글자에 따라 다르게 인식
    - type(소문자) => bulid-in component
    - type(대문자) => 사용자 컴포넌트
      - 개별로 만든 class 네이밍 룰 유의할 것

- React -> component
  - Class형 component
    - render() 함수 필수 구현
    - 생성자 통한 호출 필요 -> props 전달 시 유의 
```jsx
constructor(props){
  super(props);
}
```
    - life-cylce method 유의
      - componentDidMound() 등, render 직후 호출되는 함수 존재
  - 함수형 component
    - life cycle 없음 / hook 이용해 별도 구성 가능
    - VDOM 객체 return하는 함수들.
    - 상태를 내부에 useState()를 통해 구현 가능해지며 경계 모호해짐

- **State**
  - 상태 변경 시, React->render() 호출할 수 있게 제공되는 함수 이용해야함
  - Class형 : 객체 instance의 속성으로 상태 유지
  - 함수형 : hook이용해서 상태 관리

- Hook
  - 함수형 component에서 상태 가질 수 있도록 지원
  - useState() :  배열 return
```jsx
const result = useState(1);
const counter = result[0];
const setCounter = result[1];

// 상기 구조를 통상 하기와같이 분해하여 이용 중
const [counter, setCounter] = useState(1); 
```

  - 함수 호출 시마다 useState(1)이 진행되며 초기화 되어야 하는게 아닌가?
    - 함수형 component의 hook을 전역 배열에 넣고, 기존에 값이 있다면 초기화 하지 않고 return함.
    - hook은 코드의 최상위에서만 호출해야 함. [공식 문서](https://ko.reactjs.org/docs/hooks-rules.html)
      - 반복문, 조건문, 중첩합수 X -> 반복 시 동일 순서로 hook 호출 보장. -> useState, useEffect의 안정성 증대.
    - react component 안에서만 호출해야 함. 
      - java script 함수에서 호출하지말 것.
      - custom hook 만들어서 hook호출은 가능.
        - use~~로 시작하는 함수, useAlpha(parameter) => return isAlpha 형식이면 ok
        - useAlpha 내에 useEffect / useState사용 가능.
