# banilla js challenge, 

- 2022.05 ~


# Note ๐

## Memorable functions
```javascript
prompt(args);
// ์ค๋๋  ์์ฐ์, ํ์ ๋์์ ์๋ ฅ๊ฐ ๋ฐ๋ JS ๊ณ ์  ์ฐฝ
// modal๋ก ๋์ฒด
isNaN(args);
// not a number check
```

## Document Object

```jsx
// in console
document; 
// document object
```
- document.title ๋ฑ ์ ๊ทผ ๊ฐ๋ฅ, document๋ html์ ์ฒด๋ฅผ ๋ด๋ ๊ฐ์ฒด๋ค. 

```jsx
const title = document.getElementById("title");
console.dir(title);

title.innerText = "holy cow";
document.getElementByTagName("h1");

// querySelector์ css selector๋ก ์์นญ ๊ฐ๋ฅ.
document.querySelector(".title h1");
```

- javascript์ html ๋ช์ธ๋ ๋ณ๋๋ผ๊ณ  ์๊ฐํ ๊ฒ > web apis/ ๋๋ htmlheading elements ๊ฒ์ in MDN

```jsx
window.addEventListener("resize", ()=>{});
window.addEventListener("copy", ()=>{});
window.addEventListener("offline",()=>{});

window.addEventListener("online",()=>{});

h1.addEventListenr("click", () =>{});
h1.onclick = () =>{}; // ๋์ผ ๊ธฐ๋ฅ, ๊ทธ๋ฌ๋ remove Event Listenr ๋ฐ ์ด๋ฒคํธ ๋ฆฌ์ค๋ ๊ด๋ฆฌ๊ฐ ๋ ํธํ ๊ฒ์ ์์ ์ฝ๋.

document.body
document.head
document.title
// document์์ ์์ธ์ ์ผ๋ก ์ ๊ทผ ๊ฐ๋ฅํ ํ๊ทธ๋ค ์ ใก์ด. h1, p1๋ฑ์ ์ง์  ์ ๊ทผ ๋ชปํจ.
```

## css ~ javascript ์ฐ๊ณ์ ๊ธฐ๋ฒ
1) ๋์์ธ์ css์ ์ด๊ดํ๊ณ ,class๋ช์ ๋ฑ๋ก์ javascript์์ ์ ์ดํ์ฌ ์ฐ๊ฒฐํ๋๊ฒ ์ฃผ๋ฅ.
- a์ํ <=> b์ํ๋ฅผ ์ค๊ฐ ๋, ๋ถํ์ํ๊ฒ ์ฝ๋๋ฅผ ์์ ํ๊ฑฐ๋ ํ ํ์ ์์ด ํด๋์ค ๋ช์ add, removeํ๋ ๊ฒ์ผ๋ก ์ปค๋ฒ ๊ฐ๋ฅ

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

- class๋ช์ ์ง์  ํ์ธํ๊ณ  ์กฐํํ๋ ์์ 
- ์ข ๋ ์ต๊ทผ์ javascript ~ css๋, ์ฌ๊ธฐ์ classList๋ฅผ ์ด์ฉํด ๊ด๋ฆฌํ๋ค.

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

- ๊ฐ์ฅ ์๋ฆ๋ต๊ฒ ์ง๋๋ฒ์ ๋ด์ฅํจ์๋ฅผ ์์ฐ๋๊ฒ. ์ด๊ฒฝ์ฐ ํ ๊ธ.

```jsx
window.addEventListener("resize", () => {});

const h1Title = document.querySelector("h1");
h1Title.addEventListener("click", () => {
  const clickedClass = "clicked";
  h1Title.classList.toggle(clickedClass);
});
```

## html์ ์ํ ๋ช๊ฐ์ง ๊ท์น
- form ์์ input์ด ์๋๊ฒฝ์ฐ,
- 1) input ์์ btn์ด ์๊ณ , ํด๋ฆญ ์ ์๋ submit
- 2) input์ type submit์ ๋๊ณ , ํด๋ฆญ์ ์๋ submit

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

## js์์ ๋ณ์ ๋ฐ์์๊น๋ํ ์ฒ๋ฆฌํ๊ธฐ

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
- `${varaible name}` ์ ์์จ๋ณผ๊ฒ.
- display:none  vs visibility:hidden => ๊ณต๊ฐ ์ ์ ์ ๋ฌธ์ . display none์ ํด๋น ํ๊ทธ๋ฅผ ํต์งธ๋ก ๋ ๋ฆฌ๊ณ , vis~hidden์ ๊ทธ๋ฅ ์จ๊ธด๋ค.

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

- ceiling ์ฌ์ฉ์ด ๋ณด๋ค ๋์์๋ฏ.

## javascript์์ html ํ๊ทธ ๋ง๋ค์ด ์ปจํ์ธ  ๋ฃ์ด์ฃผ๊ธฐ

``` jsx
const images = ["0.jpeg", "1.jpeg"]
const chosenImage = images [Math.floor(Math.random()*2)];
const bgImage = document.createElement("img");
bgImage.src = `img/${chosenImage}`;
document.body.appendChild(bgImage);
```
- img tag๋ฅผ ์์ฑํด์ .src์ ๊ฐ์ด ๊ฐ์ฒด ํํ๋ก ์ ๊ทผํ๋ ์  ์ ์ํ  ๊ฒ
- appendChild๋ฅผ ์ด๋ก ์ ์ผ๋ก๋ ์๊ณ  ์์๋๋ฐ, ์ค์ ๋ก ์ด๋ ๊ฒ ์ฐ๋? ํ๋ ์ง์ ์์ ์ฐฉ์ ์ ์.
  