# mass-apps > notification 리뷰

## 개요
- 구현대상 : [chaeyeon's블로그 > 디자인](https://chaeyeon-chaeyeon.tistory.com/37)의 뉴모피즘 중, 앱이 산개해 있는 건
- 각 column을 일종의 track으로 생각해서,track별로 app이 움직이고 app 클릭시 상호 작용 가능한 화면 구상 중
- 각 요소별로 작성 및 리뷰



## 주요 소스

> atoms/vehicles/notification.js
```
import React, {useState, useEffect} from "react";
import styled, {css} from "styled-components";
import Vehicle from "../../components/metropolice/vehicle";

import { FcGoogle } from "react-icons/fc";
import { BsTwitter } from "react-icons/bs";
import { GrMail } from "react-icons/gr";
import { AiFillInstagram } from "react-icons/ai";

const NotiStyled = styled.div`
    width : 100%;
    height : inherit;

    display: flex;
    flex-direction : column;
    color : rgba(38,38,38,1.0);
`;

const VehicleHeader = styled.h3`
    margin-left : auto;
    margin-right : auto;
    margin-top : 24px;
    margin-bottom : 8px;
    color : rgba(38,38,38,1.0);
`;

const NotiRow = styled.div`
    display: flex;
    flex-direction : row;
    margin-left : auto;
    margin-right: auto;
    margin-top : 4px;
    margin-bottom: 4px;
`
const NotiSlotStyled = styled.div`
    position: relative;
    height : 120px;
    width : 120px;
    box-shadow : 8px 8px 8px 0 rgba(0,0,0,0.15),
    -8px -8px 8px 0 rgba(255,255,255,0.35);
    border-radius :24px;
    margin : 8px;

    .test {
        background: linear-gradient(to right, #fbcac9, #8ca6ce);
        -webkit-background-clip: text;
        -webkit-text-fill-color: transparent;
    }
        svg{
        height : 48px;
        width : 48px;
        position: relative;
        top : 50%;
        left : 50%;
        transform : translate(-50%, -50%);

        ${(props)=>{
            if(props.appname ==="twitter")
                return (css`color : rgba(65,165,253,1.0);`)
            else if(props.appname ==="email") 
                return(css`color : rgba(120, 64, 239,1.0)`)
    }}
    }
    .noticedMsg{
        position : absolute;
        top : -10px;
        left : 65%;
        width: 56px;
        height : 56px;
        text-align : center;

        // transform : translate(-50%, 0%);
        border-radius : 50%;
        background : rgba(252,75,140);
        color : rgba(255,255,255);
        
        z-index: 99;
        box-shadow : 8px 8px 8px 0 rgba(0,0,0,0.15),
        -8px -8px 8px 0 rgba(255,255,255,0.35);

        h3 {
           margin-top : 8px;
           margin-bottom : 8px;
           font-size: 1.6em;
        }
    }
`;   


const FocusObject =(props) =>{ 
    const [unread, setUnread] = useState(0);
    useEffect(()=>{
        if (props.appname=="google"){
            setUnread(13);
        }
        console.log(props.appname, unread);
    },[])
    
    const onclick= (e)=>{
        const readMinus = unread-1;
        console.log(e.target, readMinus);
        if (readMinus >-1){
            setUnread(readMinus);
        }
    }
    return(
        <NotiSlotStyled appname = {props.appname} onClick={onclick}>
            {props.icon}
            {unread!=0 && <div className= "noticedMsg" opt= {"show"}><h3>{unread}</h3></div>}
        </NotiSlotStyled>)
}

const applist = ["instagram", "twitter","google" ,"email"];

const Notification = () =>{
    
    return (
    <NotiStyled>
        <VehicleHeader>New Notification</VehicleHeader>
        <NotiRow>
                <FocusObject appname = {applist[0]} icon = {<AiFillInstagram />}/>
                <FocusObject appname = {applist[1]} icon = {<BsTwitter/>}/>
        </NotiRow>
        <NotiRow>
                <FocusObject appname = {applist[2]} icon = {<FcGoogle/>}/>
                <FocusObject appname = {applist[3]} icon = {<GrMail/>}/>
        </NotiRow>
    </NotiStyled>
    );
}

export default Notification;
```

- 제일 간단하다 생각한 notification. 4개의 상호작용 가능한 app icon과 알람 수만 보여주면 되니.. 
  - useEffect사용시 빈 deps와 deps 없음의 용법을 반대로 생각해서 한참 헤맸다. 
  - useEffect(()=>{}, []) => 최초 로드에만. useEffect(()=>{}) : 리렌더링마다. 유의할 것.
  - 확실히 동작 구현은 찾아보면 useEffect선에서 아직 정리가능한데 (context는 먼 미래의 일인것으로)

- 디자인 구현이 매우 번잡한듯 하다. css적용 범위/적용 버전 고려하면 쉽지 않은듯.
  - 예를 들면 text alignment. text-align/ align-items/align-content / align-self 등 고려해봤지만 제일깔끔한건 안에 div / span 파고 margin넣기였다.
  - svg파일에 gradient넣으려했더니, 파일 직접 수정이 필요했다. 스킵.
  - color 속성은 적용되어 텍스트 gradient와 동일하게 적용하려 했으나 실패 등등.

- Java뺨치게장황해져간다. 어정쩡한 map써서 가독성 낮추느니, 간단한 컴포넌트는 직접 작성하는게 그리드 식별에 도움될지도.
