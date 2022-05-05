# effective java

- 2022.04.30 Starts

## TOC


## chap 2 객체 파괴와 생성

> 생성자 보다는 정적 팩터리 메서드
```java
public static Boolean valueOf (boolean b) {
    return b ? Boolean.TRUE : Boolean.FALSE;
}
```
- boolean을 parameter로 받고, Boolean 객체 참조 되는 메서드로 제공된다.
- 사용법 : `Boolean.valueOf(bool)`
- String.format()은 사용이 불편하지 않나생각했는데, 지금 보니 객체의 사용 가능한 메서드를 모으는데는 보다 편리함이 있을지도..?

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

📝 정적 팩토리 메서드에 많이 쓰이는 이름들

```java
// from : param 하나 받아서 인스턴스 반환
Date d = Date.from(instant);
// of : param 여러개
Set<Rank> faceCards = EnumSet.of(JACK, QUEEN, KING);
// valueOf : from 과 of에서 보다 세부
BigInteger prime = BigInteger.valueOf(Integer.MAX_VALUE);
// instance or  getInstance : 인스턴스 반환하지만, 동일 para라고 동일 객체/인스턴스는아니다.
StackWalker luke  = StackWalker.getInstance(options)l

// create or newInstance  : 매번 새로운 인스턴스 생성 보장
Object newArray = Array.newInstance(classObject, arrayLen);
// getType : getInstnace와 유사, 다른 클래스에 팩터리 메서드 정의시 주로 이용함. A에서B의 기능 구현이라던지 이런건가..?
FileStore fs = Files.getFileStore(path);
// newType : newInstance와 동일, 마찬가지로 타 클래스에서 팩터리 메서드 정의시. 
BufferedRender br = Files.newBufferedReader(path);
// type : getType과 newType의 간소화
List<Complaint> litany = Collections.list(legacyLitany);
```

- get{Type} 등에 대한 주석 : 그 HttpHandler를 get할경우, static영역에서 Bean하나 생성해서 받아오는..그런건가?
- 마지막의  list를 Collections.list로 받는건 좀 이상한거 같다. 그냥 new list가 명시적으로 뚜렷하지 않나? Collections의 list로 객체 생성 권한을 이양한다는게... 굳이 여기서까지? 하는 생각. 
- python이었다면 a = list(vectorInstance) 이런느낌인가.

💬
  



🤔

##
