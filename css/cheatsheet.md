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

### flex 및 display: 태그 내 component들에 대한 정렬 방식
```css
display: flex;
<!-- 하기 태그 사용 권장 -->
flex-direction: row;  // column; row-reverse; column-reverse;
display : inline | block | contents | grid | table | none
<!-- 각각 span(no width) / width포함 / 다음 child 끌어올림 / block level grid / table처럼 / 안보임 등-->
<!-- block (버튼 등 독립적으로 끌어올림) / flex 많이 사용 중-->
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
  background-size : 120% 120%;
  background-position-x : ~ ;
  // linear gradient 적용시, 산출 식에 따라 배경 바깥의 범위는 repeat되는 현상 확인
}
- linear gradient배경의 복제 문제는 배경 grid가 padding box 까지만 인식되고, 바깥의 영역은 background가 아님에 따름. => 하기 링크 참고해서 적용
- 통합 background적용시, size expanding만 해도 커버 가능. (20px 상당 커버 필요)
- 단색일 경우, 문제 없음(배경색 복제해도 동일하게 인식
[stackoverflow - weird effect when applying transparent border](https://stackoverflow.com/questions/31115024/weird-effect-when-applying-transparent-border-over-an-element-with-a-gradient-ba)
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
align-content : ; 

align-items : ;

align-self : auto | stretch | center | flex-start | flex-end | baseline | initial | inherit;
<!-- 컨테이너 내 해당 component의 위치 선택. flex-start/ flex-end / center 정도-->

```

### 정렬된 컨텐츠 및 박스 디자인하기
```jsx
// background-color..

// box border, color


// text border, font, size, options, align
 font-family : Apple SD Gothic Neo, "Malgun Gothic"; // 가능한 폰트 부터 순차 적용
 
```
[코딩팩토리-세로 가운데 정렬 트릭](https://www.codingfactory.net/10835)


### 트릭들
```jsx
// text gradient
// button color gradient
background : linear-gradient(135deg, rgba(124,217,162,0.85) 0%, rgba(238,244,248,0.97)50%, rgba(149,191,240,0.94) 100%  );
// transition
```

> 버튼을 이용하는 방법 - react, styled-components
```jsx
// 평시
background: #38d9a9; 
// 버튼 위에 마우스 hover, 약간 옅은 색 표기
&:hover{ 
  background: #63e6be;
}
// 버튼 클릭 중일 때, 짙은 색으로 강조
&:active{
  background: #20c997;
}
cursur: pointer; 
display :block; // width 전체 차지
transform : translate(-50%, 50%); // 위치 보정. 버튼의 가운데 정렬이 힘드니, 통상 옵션에 translate 이용에 -50%, 50% 위치만큼 강제로 이동시킴. 
// z-index와 함께 작용하여 grid에 겹치게 버튼 위치 가능.
 
${props => props.open && css`
  // 버튼 클릭 시 open(true | false ) 전달, 색상 변경
  background : #ff6b6b; // 붉은 계통의 기본색
  &:hover { // 마우스 hover시 옅은 붉은색
    background : #ff8787;
  }
  &:active { // 마우스 클릭 중일 때 보다 짙은 계통
    background: #fa5252;
  }
  // 클릭시 회전
  transform : translate(-50%, 50%) rotate(45deg); 
`}
```
! z-index와 transform : translate( px | %, px | %) 조합 유의할 것 - grid경계 또는 특정 위치로 버튼을 강제위치 시킬 수 있다. 


### 뉴모피즘 New Morphism
```
const AmazingBox = styled.div`
  width: 1200px;
  height : 480px;
   
  background: rgba(236,240,243,1.0);
  
  // 박스 그림자를 이용해 튀어나와보이게 연출
  box-shadow : 12px 12px 16px 0 rgba(255,255,255,0.5),
    12px -12px 16px 0 rgba(255,255,255,0.5),
    -12px 12px 16px 0 rgba(255,255,255,0.5),
    -12px -12px 16px 0 rgba(255,255,255,0.5);
  // box-shadow: [horizontal offset] [vertical offset] [blur radius] [optional spread radius] [color];
`
```

### 이미지를 해당 구획 안에서 가운데 정렬
```
width: 48px;
height: 48px;
svg {
  position: relative;
  top :50%;
  left : 50%;
  transform:translate(-50%, -50%);
}
```
- transform을 사용 가능해야 하는 한계가 있지만..
- 상대적인 위치 => top, left로 중점에서 시작 후,
- transform >translate로 해당 도형의 크기에 비례해서 -50%씩 각각 이동.크.


### input box의 더러운 border를 숨기고 싶어요

```jsx
const InputBoxStyled= styled.input`
  border :0px;
  background : rgba(236,240,243, 1.0);
  position : relative;
  top: 0%;
  width: 280px;
  height : 78px;
  margin : 0 0 ;
  margin-left : 20px;
  
  &:focus {
    outline: none ; // border두께가 아닌 outline으로 적용 유의
  }
`;

```

### 원형 버튼에 react-router-dom의 link를 달아주고 싶어요
! Nesting an html button in an html a (or vice-versa) is not valid html  [stackoverflow](https://stackoverflow.com/questions/42463263/wrapping-a-react-router-link-in-an-html-button)
- react-router-dom에서 제공하는 Link의 경우, 당연하게도 <a> tag를 내재하고 있다.
- 해서 button과 Link를 조합할경우, `will render in a web browser, but it is not semantic, accessible, or valid html` 라고 한다
- 2차 참고 [validator.w3.org]()

=> Link 안에 div를 만들어줘서 형태를 잡고, Link 내부의 <a> tag를 찾아서 div와 외부 형태를 일치시켜주면 원형 링크가 만들어진다. 
  
```jsx
const IconStyled = styled.div`
  width: 48px;
  height: 48px;
  border-radius: 50%; // 원형으로 만들경우 50%
  background:rgba(226,230,233,1.0);
  
  svg{ // 내부에 이미지 삽입했던 경우
    position:relative;
    top : 50%;
    left : 50%;
    transform : translate(-50%, -50%);
    margin:auto;
  }
`
 
const CircularLink = (props) => {
   return (
    <Link to = "/google" style {{height:"48px", borderRadius:"50%"}}> 
      <IconStyled>
        <MdOutlineEmail fontSize={"1.2em"} color = {"rgba(0,0,0,0.55)"}/>
      </IconStyled>
  </Link>
  );  
}
```
- html tag안에서 직접 스타일 제어한건 아쉽지만.. 굳이 분리한다면 외부를 styled-component로 한번 더 감싸거나, link를 styled-component의 link로 덮어쓰면 되겠다.


### React에서 동작이 이상한 경우

- height: 100%; 가 동작하지 않아요

```css

width : 100%;
height : 100vh; //vh로 하면 잘 동작한다.

```
