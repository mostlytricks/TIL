# banilla js challenge, 

- 2022.05 ~


# Note 📝

## Memorable functions
```javascript
prompt(args);
// 오늘날 안쓰임, 팝업 띄워서 입력값 받는 JS 고유 창
// modal로 대체
isNaN(args);
// not a number check
```

## Document Object

```jsx
// in console
document; 
// document object
```
- document.title 등 접근 가능, document는 html전체를 담는 객체다. 

```jsx
const title = document.getElementById("title");
console.dir(title);

title.innerText = "holy cow";
document.getElementByTagName("h1");

// querySelector은 css selector로 서칭 가능.
document.querySelector(".title h1");
```

- javascript의 html 명세는 별도라고 생각할것 > web apis/ 또는 htmlheading elements 검색 in MDN

```jsx
window.addEventListener("resize", ()=>{});
window.addEventListener("copy", ()=>{});
window.addEventListener("offline",()=>{});

window.addEventListener("online",()=>{});

h1.addEventListenr("click", () =>{});
h1.onclick = () =>{}; // 동일 기능, 그러나 remove Event Listenr 및 이벤트 리스너 관리가 더 편한 것은 위의 코드.

document.body
document.head
document.title
// document에서 예외적으로 접근 가능한 태그들 유ㅡ이. h1, p1등은 직접 접근 못함.
```