# Webdesign - Google search window review


## 개요
- 깔끔한 웹디자인으로 new-morphism 적용 방안 및 flew 이용한 배치 실제로 적용해봄
- component를 어떻게 쪼갤지, layer구조는 어떻게 가져갈지 적용
- 이미지 적용 및 react-ui component적용 등
- 구현 대상 [chaeyeon's tistory=> 디자인블로그](https://chaeyeon-chaeyeon.tistory.com/37)

## 기타 소감
- css에서 생각보다 찾아보고 적용하는 빈도가 많았는데, 어떤건 align-items가 생각처럼 동작 안된다던지. 실행해보고 수정하는 시간이 길어진듯
- react에서 component배치는 생각보다 직관적이고, context를 쓰지 않는다면 구획화도 간편한듯하다. 포토샵에 layer을 누진제로 적용하는 느낌.
- styled component는 생각보다 편리하다. 기존의 컴포넌트를 받아서 디자인을 덧입힐수도 있고, link, img, a 등 태그도 이래저래 가져오기 편리.



## 주요 소스 

> searchBox.js

- 가운데 검색 창, 이미지는 소스 구하기 애매해서 일부 미적용
- create-react-app에 추가한 세팅 기준, public > image > 필요한  

```jsx
const SearchBoxStyled = styled.div`
width : 400px;
height : 80px;
margin : 0 auto;
margin-bottom : auto;
margin-top : auto;

background:rgba(236,240,243,1.0);
border-radius : 40px;

// new morphism tricks
box-shadow : 12px 12px 12px 0 rgba(0,0,0,0.15),
            -12px -12px 12px 0 rgba(255,255,255,0.45);

// align-tiems : center;

display : flex;
flex-direction: row;
`;

const InputBoxStyled = styled.input`
    border : 0px;
    background : rgba(236,240,243,1.0);
    position : relative;
    top : 0%;
    width: 280px;
    height:76px;
    margin : 0 0;
    margin-left : 20px;
    // text-align: center;
    &:focus {
        outline: none;
    }
    `;
const SearchBox = () =>{
    const [searchInput, setSearchInput] = useState("");
    
    const onchange = (e) =>{
        setSearchInput(e.target.value);
    };

    const onsubmit = (e) =>{
        e.preventDefault();
        console.log(searchInput);
        alert(`this is wut i submitted :: ${searchInput}`);
    } 

    return (
    <SearchBoxStyled>
        <img src= "/logo512.png" style={{height:"50px", width:"50px", marginTop:"15px", marginLeft:"15px"}}/>
        <form onSubmit={onsubmit}>
            <InputBoxStyled placeholder="Search Atoom or type a URL" onChange ={onchange}></InputBoxStyled>
        </form>
    </SearchBoxStyled>
    );
}
export default SearchBox;
```

> googleSearchGrid.js

- 상단, 중단 하단으로 나누어 구성
  - 상단은 좌 우 끝단에 버튼 붙이고
  - 중단은 검색창 및 이어지는 버튼
  - 하단은 그냥 이미지만 가져다 놓기정도로.
- 웹디자인 시 그리드 잡아가는 예제로 삼기에 좋았다.
  - css > grid를 썼으면 좀 더 깔끔했을지도? flex 기반으로 가면서 순차적 적층만 머릿속에 떠올랐다.
  - 마지막 bottom부분 요소를 margin-top 말고 다른 방식으로 구성할 수 있었을까..?

```jsx
import React from "react";
import styled,{ css } from "styled-components";
import GoogleSearchGridMid from "./googleSearchGridMid";
import GoogleSearchGridTop from "./googleSearchGridTop";
import GoogleSearchGridBot from "./googleSearchGridBot";

const GoogleSearchGridStyled = styled.div`
    width : 1200px;
    height : 480px;
    margin : 0 auto;
    margin-bottom : auto;
    margin-top : auto;
    
    background:rgba(236,240,243,1.0);
    // border : 1px solid rgba(255,255,255,0.8);
    border-radius : 32px;


    // new morphism tricks
    box-shadow : 12px 12px 12px 0 rgba(0,0,0,0.15),
        -12px -12px 12px 0 rgba(255,255,255,0.45);

    display : flex;
    flex-direction : column;
    // align-items : center;
    
const GoogleSearchGrid = () =>{
    return (
        <GoogleSearchGridStyled>
            <GoogleSearchGridTop></GoogleSearchGridTop>
            <GoogleSearchGridMid></GoogleSearchGridMid>
            <GoogleSearchGridBot></GoogleSearchGridBot>
        </GoogleSearchGridStyled>
    )
}
export default GoogleSearchGrid;
`;

```

> googleSearchGridMid.js

- 검색창과 icon을 분리해두자 정작 깔끔해진 부분
- 이렇게 단위가 작아지니 디자인 담당하는 코드만 길어지는 느낌
- 만약 css파일로 관리했으면 대형참사 였겠지만, styled-component로 해당 소스 안에서 편집하니 편리하다는 생각..

```jsx
import React from "react";
import styled,{ css } from "styled-components";

import CircularLink from "../../atoms/circularLinkIcons";
import SearchBox from "./searchBox";


const GoogleSearchGridMidStyled = styled.div`
    position: relative;    
`;
const ShortCutList = styled.div`
    width: 400px; 
    margin : 0 auto;
    margin-top: 12px;
    display : flex;
    flex-direction : row;
    justify-content: center;  
`;

const GoogleSearchGridMid  =() =>{
    return(<GoogleSearchGridMidStyled>
        <SearchBox>
        </SearchBox>
        <ShortCutList>
            <CircularLink iconName="plus"/>
            <CircularLink iconName="image"/>
            <CircularLink iconName="bookMark"/>
            <CircularLink iconName="smile"/>
        </ShortCutList>
    </GoogleSearchGridMidStyled>)
}
export default GoogleSearchGridMid;

```


> atoms/circularLinkIcons.js

- 비슷한 유형의 아이콘이 많이 들어가서, atom으로 추출해보자 시도한 부분
- 정작 내부적으로는 급하게 짜면서 추상화가 덜되어 아쉬운데, 막상 함수형 컴포넌트에서 더 깔끔하게 추상화가 되는지 의문이다.

```jsx
import React, { useState } from "react";

import {
  BsImage,
  BsBookmark,
  BsEmojiSmile,
  BsInfoCircle,
} from "react-icons/bs";
import { AiOutlinePlus, AiOutlineSetting } from "react-icons/ai";
import { CgMenuGridO, CgSmile } from "react-icons/cg";
import {
  MdOutlineEmail,
  MdExpandMore,
  MdExpandLess,
  MdGpsFixed,
} from "react-icons/md";
import styled,{ css } from "styled-components";

import { Link } from "react-router-dom";

const IconStyled = styled.div`
  width: ${props=> props.size || "48px"};
  height: ${props=> props.size || "48px"};
  ${props=> console.log("in css "+ props.size)}

  ${props=> props.size&&(css`
    box-shadow : 12px 12px 12px 0 rgba(0,0,0,0.15),
        -12px -12px 12px 0 rgba(255,255,255,0.45);
  `)};

  border-radius: 50%;
  background: rgba(226, 230, 233, 1);

  svg {
    position: relative;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    margin: auto;
  }
`;
const CustomIcon = styled.img`
    opacity:0.55;
    height : 55%;
    width: 55%;

    position:relative;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    margin: auto;
`;
const CircularLink = (props) => {
    let InternalIcon=<MdOutlineEmail fontSize={"1.2em"} color={"rgba(0,0,0,0.55)"} />;
    
    if(props.iconName){
        switch (props.iconName) {
            case "mail" :
                InternalIcon= <MdOutlineEmail fontSize={"1.2em"} color={"rgba(0,0,0,0.55)"} />;
                break;
            case "image":
                InternalIcon= <BsImage color = {"rgba(0,0,0,0.55)"}></BsImage>;
                break;
            case "menu":
                InternalIcon= <CgMenuGridO color={"rgba(0,0,0,0.55)"}></CgMenuGridO>;
                break;
            case "plus":
                InternalIcon= <AiOutlinePlus color={"rgba(0,0,0,0.55)"}></AiOutlinePlus>;
                break;
            case "bookMark":
                InternalIcon= <BsBookmark fontSize={"1em"} color={"rgba(0,0,0,0.55)"}></BsBookmark>        
                break;
            case "smile":
                InternalIcon = <CgSmile  fontSize={"1.2em"}color={"rgba(0,0,0,0.55)"}></CgSmile>
                break;
            case "anchor":
                InternalIcon =<CustomIcon src="/images/baseline_anchor_black_24dp.png" />;
                break;

            default:
                InternalIcon= <MdOutlineEmail fontSize={"1.2em"} color={"rgba(0,0,0,0.55)"} />;
                break;
        }
    }
  return (
    <Link to="/google" style={{borderRadius:"50%", margin:"12px"}} >
      <IconStyled size= {props.size}>
        {/* <MdOutlineEmail fontSize={"1.2em"} color={"rgba(0,0,0,0.55)"} /> */}
        {InternalIcon}
      </IconStyled>
    </Link>
  );
};
export default CircularLink;


```





