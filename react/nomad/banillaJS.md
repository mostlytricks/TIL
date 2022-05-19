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

## css ~ javascript 연계의 기법
1) 디자인은 css에 이관하고,class명의 등록을 javascript에서 제어하여 연결하는게 주류.
- a상태 <=> b상태를 오갈 때, 불필요하게 코드를 수정하거나 할필요 없이 클래스 명을 add, remove하는 것으로 커버 가능

```jsx
//not bad
window.addEventListener("resize", () => {});

const h1Title = document.querySelector("h1");

h1Title.addEventListener("click", () => {
  const clickedClass = "clicked";
  if (h1Title.className !== clickedClass) h1Title.className = clickedClass;
  else h1Title.className = null;
});

```

- class명을 직접 확인하고 조회하는 예제
- 좀 더 최근의 javascript ~ css는, 여기서 classList를 이용해 관리한다.

```jsx
window.addEventListener("resize", () => {});

const h1Title = document.querySelector("h1");
h1Title.addEventListener("click", () => {
  const clickedClass = "clicked";
  if (h1Title.classList.contains(clickedClass)) {
    h1Title.classList.remove(clickedClass);
  } else {
    h1Title.classList.add(clickedClass);
  }
});

``` 

```css
:root {
  --color1: #1abc9c;
  --color2: #3498db;
  --color3: #9b59b6;
  --color4: #f39c12;
  --color5: #e74c3c;
}

body {
  background-color: var(--color1);
}
h1 {
  color: #f3f3f3;
  transition: color 0.5s ease-in-out;
}

h1.clicked {
  color: #161616;
  transition: color 0.5s ease-in-out;
}
```

- 가장 아름답게 짜는법은 내장함수를 잘쓰는것. 이경우 토글.

```jsx
window.addEventListener("resize", () => {});

const h1Title = document.querySelector("h1");
h1Title.addEventListener("click", () => {
  const clickedClass = "clicked";
  h1Title.classList.toggle(clickedClass);
});
```

## html을 위한 몇가지 규칙
- form 안에 input이 있는경우,
- 1) input 안의 btn이 있고, 클릭 시 자동 submit
- 2) input에 type submit을 두고, 클릭시 자동 submit

```jsx
<!DOCTYPE html>
<html>
  <head>
    <title>Vanilla Challenge</title>
    <meta charset="UTF-8" />
  </head>

  <body>
    <form class="login-form">
      <input
        type="text"
        required
        maxlength="15"
        type="text"
        placeholder="What is your name?"
      />
      <button>Log In</button>
      <input type="submit" value="log in input btn" />
    </form>
    <script src="src/index.js"></script>
  </body>
</html>

```

## js에서 변수 받아서깔끔한 처리하기

```jsx
const loginForm = document.querySelector(".login-form");
const loginInput = document.querySelector(".login-form input");
const loginButton = loginForm.querySelector("button");
const greeting = document.querySelector(".greeting");

const HIDDEN_CLASSNAME = "hidden";

function onSubmit(e) {
  const username = loginInput.value;
  console.log(e);
  e.preventDefault();
  loginForm.classList.add(HIDDEN_CLASSNAME);
  greeting.innerText = `Hello ${username}`;
  console.log(greeting.innerText);
  greeting.classList.remove(HIDDEN_CLASSNAME);
}

loginForm.addEventListener("submit", onSubmit);

```
- `${varaible name}` 을 잘써볼것.
- display:none  vs visibility:hidden => 공간 점유의 문제. display none은 해당 태그를 통째로 날리고, vis~hidden은 그냥 숨긴다.

```jsx
const gameForm = document.querySelector(".game-form");
const maxLimit = gameForm.querySelector(".upper-limit");
const rollTheDice = gameForm.querySelector(".num");
const result = document.querySelector(".result");

let machineAnswer = -1;

const generateNumber = (max) => {
  max = parseInt(1) + parseInt(max);
  machineAnswer = Math.floor(Math.random() * max);
  console.log(machineAnswer);
};

const onSubmit = (e) => {
  e.preventDefault();
  const max = maxLimit.value;
  const roll = rollTheDice.value;
  result.classList.remove("hidden");

  generateNumber(max);
  result.querySelector(".choice").innerText = roll;
  result.querySelector(".machine").innerText = machineAnswer;
  checkAnswer(parseInt(roll), machineAnswer);
};

const checkAnswer = (roll, ans) => {
  if (roll === ans) {
    result.querySelector(".who-won").innerText = "You won!";
  } else {
    result.querySelector(".who-won").innerText = "You lost!";
  }
};
gameForm.addEventListener("submit", onSubmit);

```

- ceiling 사용이 보다 나았을듯.

## javascript에서 html 태그 만들어 컨텐츠 넣어주기

``` jsx
const images = ["0.jpeg", "1.jpeg"]
const chosenImage = images [Math.floor(Math.random()*2)];
const bgImage = document.createElement("img");
bgImage.src = `img/${chosenImage}`;
document.body.appendChild(bgImage);
```
- img tag를 생성해서 .src와 같이 객체 형태로 접근하는 점 유의할 것
- appendChild를 이론적으로는 알고 있었는데, 실제로 이렇게 쓰나? 하는 지점에서 착안 유의.
  