# Chapter 7 - 오류 처리
---
## 기억할 만한 부분
- 오류코드 보다는 예외(throw error)
- Try Catch Finally First.
- 미확인된 예외의 이용.
  - 확인된 예외가 꼭필요한지 생각해야. 확인된 예외 던졌으나 3블록 위로 던져야한다면, 그사이 모든 메서드에 대해 exception 정의 필요. -> 캡슐화가 약해짐. 
- 예외에 의미 + 호출자를 고려한 예외 클래스 정의.
  - 개발자에게 유의미한 오류 정보 제공 필요.

- 그렇다면, 오류를 자주 유발하는 습관이 있나?
  - null return. 
    - 매번 null check를 하게 하는 원인. 아무리 정적 분석을 해도, 하나 빠져나가면 코스트가 커진다.
    - NullPointerException이 저 낮은데서 발생하면 사실 답도 없다.
    - list(nullable) => list, empty
```java 
//java에서는 emptyList() 가 있다!
Collections.emptyList();
```
  - null 전달.  
    - function adder(int a,int b) 에 adder(null, {1,2}) 같이 실수로 null을 전달한다면?
      - 처리기를 별도로 구현 등의 추가 작업 필요.