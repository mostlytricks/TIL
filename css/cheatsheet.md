# CSS Cheat Sheet
---
- 참고 : [생활코딩-css소개](https://opentutorials.org/course/2418/13667)
- 추가 : [생활코딩-css-웹레서피](https://opentutorials.org/module/2398/13570)




## 상세
---
### text-align : 문자열 정렬
```html
<style>
  p{
<!--     text-align: left; right; center; //텍스트 정렬 -->
    text-align: justify;
    border:1px solid gray;
  }
</style>

```
### float : 이미지 정렬 및 주변 tag 영향
```css
float : left; // right;
<!-- 이미지 정렬, 이후 text 바로 붙임, margin 사용 권장 -->

```

### flex : 태그 내 component들에 대한 정렬 방식
```css
display: flex;
<!-- 하기 태그 사용 권장 -->
flex-direction: row;  // column; row-reverse; column-reverse;
```




## 몇 가지 예제
--- 
### box-model
```jsx
border-width:10px;
border-style:solid;
border-color:red;
<!-- 하기와 동일 -->

border: 10px solid red; <!-- 테두리 두깨-->
padding: 20px; // 테두리 ~ 내용
margin : 40px; // 테두리 ~ 바깥 요소
width :100px;
height : 100px;
```

### media-query
```jsx
// 동적으로 확인 후 적용, max-width : 최대값, min-width:최소값.
// 상단부터 우선순위.
@media (max-width:600px){
  body{
    background-color: green;
  }
}
@media (max-width:500px){
  body{
    background-color: red;
  }
}
@media (min-width:601px){
  body{
    background-color: blue;
  }
}
```

