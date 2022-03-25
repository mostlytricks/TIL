# 폰트는 어떻게 적용하는게 맞을까

```css
@import url('https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;600');
@import url('https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,300;1,300&display=swap');
```

### google font 사용하기
- 상기와 같이 .css 파일 내 지정 가능
- 서버 내 저장 불필요
- 폰트 탐색 : https://fonts.google.com/
  - select this style
  - <link> tag시 html에 작성
  - @import 이용시 css파일 내 작성

### 기타 주의사항
- 특정 weight로 저장 유의
- 이후 css에서 weight 변경해도 적용됨 확인. weight 별로 받아올 필요 있는지 추후 재확인 필요.

- react 내 사용시, index.html 위치한 곳에 등록하는 등 설정방법 추가 필요
- next.js에서 css기반 font적용시 이슈 사항 확인
  
### 주요 사용 폰트
  - 영어 : Playfair, Poppins


  
