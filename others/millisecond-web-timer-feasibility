# 웹에서 millisecond 정합성을 제공하는 시계를 만들 수 있을까?

### 0. 배경
- 웹에서 개인 대시보드 제작 중
- 시계를 만들다 보니 stopwatch를 넣고 싶은데
- 그렇다면 millisecond가 적합하지 않을까 생각
- ms단위로 숫자가 갱신된다면, 이 연산은 client에서 처리할텐데, 어떻게 처리해야 하나에 대한 의문


### 1. 결론
- 비슷한 사고를 한 멍청이가 있었다! [stackoverflow](https://stackoverflow.com/questions/5910646/efficient-milliseconds-display-in-html)
  - 어차피 사람의 눈이 60hz미만의 변화만 감지 가능하고
  - 모니터의 변경속도도 60hz ~ 144hz 내외
  - 애니메이션의 최소 인식 단위도 24hz 정도인 것을 감안하면,
- **굳이 0.001초 단위로 바꿀 필요가 없다. cpu테스트 정도 일뿐**
- 굳이 한다면 new Date().getMilliseconds 기능을 이용하자.
- front든 server시간을 보내 쏘던 지연, 불일치 사례는 유의하자.


### 2. 예제
```jsx
setInterval(count, INTERVAL);
const count = () =>{
  var ms = new Date().getMilliseconds() % 1000;
  domElements.innerHTML = ms < 100? (ms<10?'00' +ms:'0'+ms):ms;
  ms+=interval;
}
```
