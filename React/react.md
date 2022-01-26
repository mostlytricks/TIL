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

