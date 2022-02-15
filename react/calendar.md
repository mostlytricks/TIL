# 달력 제작 기록
--- 

> 전체 Grid > Week > Day로 내려가면서 구현

> 날짜 선택(date picker)시 달력 리프레시


### 상세
```jsx
// calendarGrid.js
/*react, react- datepicker,styled-components */

import CalendarCell


const CalendarGridStyled = styled.div`
    display : flex;
    flex-direction : column;
    margin : auto 0;
    border-radius : 12px;
    background : linear-gradient(135deg, rgba(255,252,245,0.8) 0%, rgba(253, 252, 245, 0.8) 100%);
`;

const DayList={
  0:"Sun", ... // 6까지 채움필요
}

const CalendarWeek =(prop) =>{
  return (
  <CalendarWeekStyled>
    {prop.week!=null ? prop.week.map( 
      (daydate, index) => (
       <CalenderCell key = {index} date= {daydate.date} day={daydate.day} month={daydate.month} curMonth={daydate.curMonth}/>)):null}
  <CalendarWeekStyled>
}


const CalendarGrid =() =>{
  const [startDAte, setStartDate] -useState(new Date());
  const [formCal, setFormCal] =useState();
  
  const buildCalendar  = (renewDate)=>{
    const dateWindow = renewDate;
    const currentMont = dateWindow.getMonth();
    dateWindow.setDate(1); 
    
    //일요일이 아닐경우, 하루씩 전날로 돌리며 달력 시작일 파악
    for(let i =0; i< 7; i++){
      if (dateWindow.getDay()!=0){
        dateWindow.setDate(dateWindow.getDate()-1);
      }
    }
    
    //달력 생성 시작
    let cal =[];
    let week = {key:0, content:[]};
    let cnt = 1;
    for(let  =0; i< 6; i++){ //달력 최장 길이 : 6줄
      if(currentMonth < dateWindow.getMonth()){ // 5,6번째 줄이 둘 다 다음 달일경우 5줄로 제한
        break;
      }
      for (let j=0; j< 7; j++){
        if(dateWindow.getDay() ==j){
          week.content.push({
            key:cnt, 
            date:dateWindow.getDate(),
            day:DayList[j],
            month:dateWindow.getMonth(),
            curMonth: currentMonth, // 실질 달과 표기값의 달이 다를 경우, 다른 css적용 목적
          })
        }else{
          //생략해도 된다.
        }
        cnt+=1;
      }
      cal.push(week);
      week={key:i+1, content:[]};
    }
    setFormCal(cal);
  }
  
  useEffect(()=>{
    const renewDate = new Date(startDate.valuOf()); //날짜 객체 복사
    buildCalendar(renewDate);
  },[startDate]) 
  
  return(
    <CalendarGridStyled>
      <div className = "innerGrid">
        <div className = "upperSide">
          <DatePicker selected ={startDate} onChange = {(date)=> setStartDate(date)}/>  
        </div>
        {formCal!=null ? formCal.map((week), index) => <CalendarWeek key = {index} week = {week.content}/>)
      </div>
    </CalendarGridStyled>
  );
}
export default CalendarGrid;
```

> 이하 각 셀단위, css적용에 유의
```jsx
//react, styled, styled-> {css} 등
const CalendarCellStyled = styled.div`
  width: 72px;
  height: 64px;
  
  // styled component, props 받아서 조건 부 빨간색
  ${props=>(props.day=="Sun") &&css`   
    color:red;
   `}
  ${props=>(props.day=="Sat") &&css`   
    color:blue;
   `}
  ${props=>(props.curMonth!=props.month) &&css`   
    opacity:0.3; // 해당월 아니면 회색음영 처리
   `}
';

```
