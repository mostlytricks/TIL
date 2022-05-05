# effective java

- 2022.04.30 Starts
- 2022.05.05 keep-going.

## TOC
[chap2](#chap-2-객체-파괴와-생성)



--- 

## chap 2 객체 파괴와 생성

### 생성자 보다는 정적 팩터리 메서드

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

### 생성자에 매개변수 많다면...빌더!

- 빌더 패턴은 이제 익숙하지. 그럼그럼.
  - 점층적 생성자 패턴(생성자를 parameter수에 따라 여러개 구성+this.~로 연계) + 자바빈즈(기본값으로 init + setter이용)의 혼합
  - 필수 매개변수는 CLASS.Builder.builder()에서 잡아주고, 선택은 CLASS.Builder.alpha().omega()영역에서 잡아준다.

- 메서드 체이닝에 해당!
  - 예전 클린코드에서 열차 패턴인가? 하면 이건 아닌걸로. 동일한 객체의 스코프다. 

- 빌더 패턴의 유효성 검사하기 위해 불변식쓰라는데?
  - assertion을 통한 검사 또는 특정값이 참인지 검증하는 코드 넣을 것
  - 문제 생기면? IllegalArumentException 던져

📝 옮긴이 주석. 불변
- 어떠한 변경도 허용되지 않는 객체. String literal은 만들어지면 String pool안에 차곡차곡 저장되는데. 이런거. 
  
**주의** **사항**
- 빌더 만드는게 코스트가 있긴하다. 성능 중요한 환경 시 유의할 것
- 코드가 장황해지다 보니, 매개변수 4개 이상은 되어야 제 가치가 있다.
- 하지만 계층적인 구조 (피자> 뉴욕피자, 피자>칼조네 피자, 피자는 추상 클래스.) 같은 경우 매우 강력한 도구.
- 생성자나 정적 팩터리로 처리할 변수가 많다면 빌더 패턴이 차라리 낫다. 그러나 API에서 제공하는 매개변수가 많아지는 현상을 고려하면, 그냥 빌더로 시작해라.

### private 생성자나 열거 패턴 이용해 singleton 보장하자

> private... 생성자...?

- singleton :인스턴스를 오로지 하나만 생성 가능한 클래스.
  - 클래스를 싱글턴으로 만들면, 테스트가 어려워진다. mock으로 대체하기 어려워짐.
  - 생성자는 private으로 감추고, 접근 수단은 public static 멤버.

```java
// 첫번째 방법
public class Elvis{
    public static final Elvis INSTANCE = new Elvis(); /* 단일 인스턴스 보장, public으로 제공하여 접근지원 */
    private Elvis() { ... } /* 만드는건 여기서 가린다. 이미있는지 없는지 체크로직 포함해야한다.*/
    public void leaveTheBuilding() { ... } /**/
}

// 두번째 방법
public class Elvis {
    private static final Elvis INSTANCE = new Elvis(); /* 단일 인스턴스 보장, private으로 설정 */
    private Elvis() { ... }
    public static Elvis getInstance() { return INSTANCE }; /* 접근할 수 있는 getInstance 제공 */

    public void leaveTheBuilding() { ... }
}
```

- 상기 두가지를 직렬화 / 역직렬화 하다보면 가짜 Elvis가 매번 생성된다. 그래서 readResolve 메서드를 추가해서 기존 내용을 쓰레기통으로 보내고 진짜를 계속 이어줘야한다. 
-  serialize는 이후에 내용 상세화 예정. 

```java
private Object readResolve() {
    return INSTANCE;
}
```

- 바람직한 방법 : 그냥 열거형으로 제공해라.

```java
public enum Elvis {
    INSTANCE;
    public void leaveTheBuilding () { ... }
}
```

- ??? 
- 만드려는 싱글턴이 enum이외의 클래스를 상속해야 한다면...못쓴다고 한다.

> 위코드에서 Instnace는 어떤 값을 담을 수 있지? 의미가 있나? 상수 말고 객체가 담길 수 있나?
> Elvis.INSTANCE?
> 참고할 문서 : [getter-setter 개발로그](https://getter-setter.com/2019/07/31/class-%EC%8B%B1%EA%B8%80%ED%86%A4-vs-enum-%EC%8B%B1%EA%B8%80%ED%86%A4/)

- 일단 enum타입으로 선언한 객체간에 공유할 수 있는 영역...정도로 사용 가능할 듯 하다.
- 그냥 static에 저장하겠습니다와 차이를 못느끼긴하겠는데...사실 싱글턴이 그런거네. 


<br/>
<br/>

---
## chap 3

💬
  



🤔

##
