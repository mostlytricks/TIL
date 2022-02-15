# CSS Cheat Sheet
---
- 참고 : [생활코딩-css소개](https://opentutorials.org/course/2418/13667)
- 추가 : [생활코딩-css-웹레서피](https://opentutorials.org/module/2398/13570)
- 추가 중: [코딩팩토리-css](https://www.codingfactory.net/12334)

- 원전 
  - [adobe색상환](https://color.adobe.com/ko/create/color-wheel)
  - [mdn web docs](https://developer.mozilla.org/en-US/docs/Web/CSS/)


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

box-sizing : content-box | border-box | initial | inherit
<!-- 콘텐트 영역 기준 | 테두리 기준 | 기본값 설정 | 부모 요소의 속성값 상속 -->

border-bottom: 1px solid #e9ecef; 
<!-- 바닥에만 회색선 -->
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

### 이미지와 텍스트의 가운데 선 일치
> 전체 구획 크기 설정 후, flex option > center 정렬이 깔끔
>
> 만약 flex 미지원할 경우, text-align : center만 단독으로.
```jsx
.tempGrid{
  height : 36px;
  margin-bottom : auto;
  margin-top : auto;
  
  display: flex;
  align-items: center;
}
```


### 배경 적용 팁 
```jsx
body {
  background : rgba(255,255,255,0.7);
  // 글자 영향도 없게 투명도 적용시, rgba => 투명도에 값줘서 별도로 지정해야.
}
#case2 {
  background : white;
  opacity : 0.7;
  // 전체 투명도 적용시, 표기되는 폰트도 같이 투명해짐. 해당 component전체 투명도
}
#case3 {
  background : linear-gradient(135deg, rgba(124,217,162,0.85) 0%, rgba(238,244,248,0.97) 50%, rgba(149,191,240,0.94) 100%);
  // 배경 그라데이션 적용. powerpoint의 적용 양식과 유사하다.
}
```

## Alpha to Omega
--- 

### 격자 맞추기
```jsx
// going to do..
// display : flex.. flex direction.. 
```


### 단위박스 내에서 정렬하기
```jsx
// going to do..
// float... text-align.. align-items..
// margin auto.. padding auto?

```

### 정렬된 컨텐츠 및 박스 디자인하기
```jsx
// background-color..

// box border, color


// text border, font, size, options, align
 font-family : Apple SD Gothic Neo, "Malgun Gothic"; // 가능한 폰트 부터 순차 적용
 
```
[코딩팩토리-세로 가운데 정렬 트릭](https://www.codingfactory.net/10835)


### 트릭 들
```jsx
// text gradient
// button color gradient
background : linear-gradient(135deg, rgba(124,217,162,0.85) 0%, rgba(238,244,248,0.97)50%, rgba(149,191,240,0.94) 100%  );
// transition
```




