# 10장 클래스 Class
---

### 기억해둘 내용
- 클래스는 책임을 가능한 축소하여 가져가야한다.
  - 함수의 경우 행 수가 문제되었지만, 클래스는 책임의 범주.

- Single Responsibility Principle : 단일 책임 원칙
  - 클래스나 모듈을 변경할 이유는 단 하나여야한다. 

- Cohesion : 응집도
  - 클래스 내부에 인스턴스변수를 줄이고자.
  - 클래스의 모든 인스턴스 변수를 메서드마다 사용하는 경우, 응집도는 더 높아짐
  - 응집도가 높은 클래스를 선호. => 논리적인 단위로 묶이기 때문. 
    - 실제로는 너무 높은 응집도 갖는 클래스는 가능하지도, 바람직하지도 않음.
    - 가능한, 적정수준 까지 높이자 수준. 
```java
// topOfstack, elements의 사용빈도가 높음에 따라 높은 응집도.
public class Stack {
  private int topOfStack =0;
  List<Integer> elements = new LinkedList<Integer>();

  public int size() {
    return topOfStack;
  }

  public void push(int element) {
    topOfStack++;
    elements.add(element);
  }

  public int pop() throws PoppedWhenEmpty {
    if (topOfStack == 0){
      throw new PoppedWhenEmpty();
    }
    int element = elements.get(--topOfStack);
    elements.remove(topOfStack);
    return element;
  }
}
```
  - 특정 메서드만 사용하는 instance 변수가 많아지는 경우, 응집도가 높아지도록 변수와 메서드 적절히 분리. 
  - 리팩터링의 결과 분석 중.. 
    - 프로그램이 길어짐! (1page => 3page, 3~4 classes)
    - 변수명이 좀 더 서술적
- 변경이 필요한 class의 refactoring 예시
```java
public class Sql {
  public Sql(String table, Column[] columns)
  public String create()
  public String insert(Object[] fields)
  public String selectAll()
  public findByKey(String keyColumn, String keyValue)
  public String select (Column column, String pattern)
  public String select (Criteria criteria)
  private String columnList(Column[] columns) /* 코드 일부에만 제공되는 비공개 메서드, 개선의 여지가 있음*/
  private String valueList(Object[] fields, final Column[] columns)
  private String selectWithCriteria(String criteria)
  private String placeholderList(Column[] columns)
}
```
  - 상기 코드의 문제?
    - 새 sql 지원 시, 반드시 sql 클래스를 수정
    - 기존 sql 수정 시, 역시 sql 클래스 수정
    - SRP 위반! => 변경의 이유가 여러개. 
  - 왜 이렇게 구성되었나?
    - SQL이라는 하나의 도메인 내에, public으로 제공하는 쿼리 /인터페이스를 다 모아두었기 때문
    - 컨셉으로는 직관적이지만, 단일 소스 / 쿼리 변경시에 영향도 / 테스트 범위 증가
```java
abstract public class Sql {
  public Sql(String table, Column[] columns)
  abstract public String generate();
}

public class CreateSql extends Sql {
  public CreateSql(String table, Column[] columns)
  @Override public String generate()
}

public class SelectSql extends Sql {
  public SelectSql(String table, Column[] columns)
  @Override public String generate()
}

public class InsertSql extends Sql {
  public InsertSql(String table, Column[] columns, Object[] fields)
  @Override public String generate()
  private String valuesList(Object[] fields, final Column[] columns)
}
/*...*/
```
  - 추상 클래스를 이용해 각각 분리한 예제.
  - 각각의 SQL을 변경해도, 타 sql에 미치는 영향은 줄어들어.
  - 'update' 기능을 추가 시에도, 단순히 class 추가만 수행하면된다!
  - OCP(Open-Closed Principle) 만족 => 확장에 개방적, 수정에 폐쇄적. 


- SOLID => SRP, OCP, DRP(Dependency Inversion principle) + (2)
