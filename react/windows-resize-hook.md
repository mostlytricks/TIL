# react-how-to-handle-window-resize-event
---
> window resize는 html의 event listener / jquery의 특정 기능으로 해결가능했는데

> react에서는 어떻게 값을 가져다가 rerendering시킬까 => custom hook등록으로 해결

- 언제 필요했나?
  - 배경을 linear-gradient로 적용했더니, window resize시에 background 인식이 덜되는 현상
  - 특정 component가 초기 window size를 초과하는 경우, window를 키웠을 때 빈 영역 확인 등
- 대응 방안
  - window resize를 감지해서 rerendering하면 되지 않을까?
  - window resize를 react 내 hook 또는 state변경과 연동
 
 
#### 상세 코드

```jsx
// useWindowDimensions.js
import {useState, useEffect} from 'react';

const useWindowDimensions =() => { //custom hook
  const hasWindow = typeof window!== 'undefined';
  
  const getWindowDimensions=()=>{
    const width = hasWindow ? window.innerWidth : null;
    const height = hasWindow ? window.innerHeight : null;
    return{
      width, height;
    }
    
    const [windowDimensions, setWindowDimensions] = useState({width: hasWindow ? window.innerWidth : null, height:hasWindow ? window.innerHeight : null});
    // 기존 width : 0 , height : 0으로 처리했으나 최초 로드시 0으로 init은 문제가 있음
    // 내부에 함수calling으로 initiate시 warning 발생 확인 -> useState(getWindow..)
    // 우선 코드 복사로 대처.
    
    useEffect(()=>{
      if (hasWindow) {
        const handleResize= () =>{
          setWindowDimensions(getWindowDimensions());
        }
        window.addEventListener('resize', handleResize);
        
        return () => window.removeEventListener('resize', handleReize);
        //컴포넌트가 화면에서 사라질때 callback으로 event lister제거 해줘야함 유의. (clean-up)
      },[hasWindow]);
      return windowDimensions;
    }) 
  }
  return windowDimensions;
}
export default useDimensions;
```

```jsx
//사용 예
function App(){
  console.log(useWindowDimensions()); // width,height >> 변화시마다 보임
  return(<>
    {/*주화면*/}
  </>)
}

```

```jsx
// 실제 사용 방식 - 크기 변경에 따른 component display 여부
import styled,{css} from "styled-components";

const StyeldCalendar = styeld.div`
${prop => prop.windowSize && (prop.windowSize.width < 1140 && css` // window size가 있을 경우, width check
display :none;`}
`;

const Calendar=()=>{
  const windowSize = useWindowDimensions();

  return (
    <StyledCalendar windowSize ={windowSize}>//para 전달
    </StyledCalendar>
  )
}


```


#### 개선필요
- 이것만 올릴 경우, 작은 변화도 모두 캐치해서 event listening됨
- throttle혹은 감지 주기 제어 필요.

참고 : [stackoverflow get-viewport-window-height](https://stackoverflow.com/questions/36862334/get-viewport-window-height-in-reactjs)

참고2 : [medium debound-handle-browser-resize](https://medium.com/geekculture/debounce-handle-browser-resize-like-a-pro-994cd522e14b)
