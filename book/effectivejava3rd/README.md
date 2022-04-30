# effective java

- 2022.04.30 Starts

## TOC


## chap 2 객체 파괴와 생성

> 생성자 보다는 정적 팩터리 메서드

```java
BigInteger(int,int,Random);

BigInteger.probablePrime();
```
- 어느 것이 보다 효과적인 이름을 보여주는가 생각해봐야한다.
- 개발자가 API를 착각해서(생성자 사용시) 엉뚱한 호출을 해도, 이를 확인하는 데에는 시간이 필요하다.

🤔 개인 주석. BigInteger은 static 영역에 올려져 있기 때문에, 여느 코드에서건 BigInteger.로 호출하는게 가능하다! 
`String.format()`과 같은 맥락. 이를 저자는 정적 팩터리 메서드 (정적 영역에 올라간 팩토리 기능의 메서드) 라고 하는 듯하다.
강점은 역시 별도의 객체를 생성할 필요가 없다는 점. String객체를 매번 새로 만들어서 formatting 하는 건 아니잖아.

- 정적 팩토리 메서드를 이용하면 반환타입의 하위 타입 객체를 반환할 수 있다!

💬
  



🤔

##
