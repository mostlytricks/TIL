# Java ORM 표준 JPA 프로그래밍 

- 김영한 지음
- 시작일 : 2022.05.19 ~


## TOC
- [1장](#alpha)
- [2장]
- [3장](#chapter-3)
- [4장](#chapter-4)
- [5장](#chapter-5)

## 1장 JPA_소개 <a name = "alpha"/>

### Why JPA?
- CRUD SQL 작성 x
- 조회결과 자동 객체 맵핑.
- 테스트에 강점, 생산성과 유지보수 유리해짐.
- DB 변경 시 매우 편리.

### 이슈
- 성능 문제? > nativeSQL 지원, 직접 작성 가능
- DB 쿼리 힌트 사용
- DB 조회 성능 이슈 >> SQL 사용시 공통적으로 발생하는 문제.

### field 추가 시 포괄적으로 생기는 문제 시나리오
1) 등록 코드 변경
> 전화번호를 회원 관리 정보에 추가하자!
> member.tel 추가.
> insert sql 추가
> 회원 객체 > parameter(tel) > sql
> 완성!

2) 조회 코드 변경
> tel 조회 (데이터, ui)가 안된다!
> select sql 변경

3) 수정 코드 변경
> 수정도 안되네?

4) 연관된 객체
> 모든 member은 특정 team 소속 시..
> sql 변경
> 인줄 알았더니 method 추가 ( find, findWithTeam)
> 이 모든걸 어떻게 알아차리나? 실패 후 dao를 열어서 뜯어봐야 안다.

5) 결론
- 쿼리향 시스템의 문제. 모든 내용은 DAO를 열어서 정확히 어떤 sql이 실행되는건지 알아야만 한다. 
- 엔터티(member, Team 등 비즈니스 요구사항 모델링한 객체)를 신뢰할 수 없게된다. 

### 현실적인 문제
member.getTeam()은 가능하지만,
team.getMember()은 참조가 없을 경우 불가능.
테이블은 join이 좀 더 유연하게 쓸수 있다. 

객체 모델은 외래 키가 필요 없고 참조만 있으면 된다.

### 극복하기 어려운 연관관계 문제 - 패러다임 불일치

> SQL 직접 다룰 시, 처음 실행하는 Sql에 따라 객체 그래프를 어디까지 탐색할 수 있는지 정해진다.

- memeber.getOrder().getOrderItem()을 조회가능한가? 초기 쿼리 조회 시 테이블을 모두 join해두어야만 가능하다.

### 기타

> 통계쿼리 등 복잡한 sql은 오히려 native sql or mybatis등을 혼용하는게 좋은 선택지일 수 있다.



---

## 3장 JPA_소개 <a name = "chapter-3"/>

### Entity Manager
META-INF/persistence.xml 정보를 참조하여 EntityManagerFactory 생성함
```xml
<persistence-unit name = "jpabook">
  <properties>
    <property name = "javax.persistence.jdbc.driver" value = "org.h2.Driver"/>
    <property name = "javax.persistence.jdbc.user" value = "sa"/>
    <property name = "javax.persistence.jdbc.password" value = ""/>
    <property name = "javax.persistence.jdbc.url" value ="jdbc:h2:tcp://localhost/~/test"/>
  </properties>
<persistence-unit name = "jpabook">
  
```
- 기타 sql 표기 여부 등 옵션을 여기서 작성한다.
- hibernate.cfg.xml과 작성 방식이 사소하게 다름 유의 (value 전달 등)
- 내용은 동등해보인다. (~ session-factory)

```java
EntityManagerFactory mef = Persistence.createEntityManagerFactory("jpabook");
EntityManager em = emf.createEntityManager();
```

### EntityManagerFactory
- 엔터티 매니저 팩토리는 생성비용이 크며, 애플리케이션 전체에서 공유 / 통상 한 개만 만든다 (DB 하나면 팩토리 매니저 하나)
- thread safe

### EntityManager
- 여러 스레드가 동시 접근시, 동시성 문제 발생

### 영속성 컨텍스트 (Persistence Context) 
- 엔터티 영구 저장 환경
- 저장한다고 생각하자.

```java
em.persist(member);
em.remove(member);
```
- 엔터티 매니저 사용 > 회원 엔터티를 영속성 컨텍스트에 저장

### Entity life-cycle
1) 비영속
2) 영속
3) 준영속(detached) : 저장되었다가 분리된 상태
4) 삭제 : 삭제된 상태 > DB와 영속성 컨텍스트 모두에서 삭제함 유의할 것

### Entity 조회 
```java
Member member = new Member();
member.setId("member1");
em.persist(member);
```
- 1차 캐시, 회원 엔터티 저장된다. 아직 DB저장 X
- 조회 시, 1차캐시에서 우선탐색. 만약 없다면 DB에서 조회 > 1차 캐시에저장.
- Member a = em.find(Member.class, "key a")와 Member b = em.find(Member.class, "key a")는 동일성이 보장된다!(equal => true)


### Entity 등록
엔터티 매니저가 데이터 변경시 반드시 트랜젝션 시작해야함.
상기 em.persist()의 엔터티 제어/조회는 tx 없이도 가능했음 유의할 것

```java
EntityManager em = emf.createEntityManager();
EntityTransaction = transaction = em.getTransaction();

transaction.begin();
/*
~~~
*/
// 커밋 시점 insert sql을 db에 보냄.
transaction.commit();

```

### Entity 수정
SQL 기반의 수정(update)구문 관리 시, 항목 하나씩 늘어날 때마다 update 쿼리 재확인 필요.

- 변경감지(dirty checking) : 엔터티 변경사항 DB에 자동 반영 > em.update()같은 구문이 필요 없다! memberA.setAge(10); 이후 transaction.commit()만 수행되면 적용.
- 단, 영속 상태의 엔터티만 적용됨
- JPA의 기본전략은 엔터티의 모든 필드 업데이트 => 전송량이 증가하는 문제.
- 필드가 많거나 내용 크면, 수정된 데이터만 동적 update Sql생성 필요 => 하이버네이트 확장 기능

```java
@Entity
@org.hibernate.annotations.DynamicUpdate /* 동적 업데이트 > 수정된데이터만 사용해서 update sql 생성함*/
@Table(name = "Member")
public class Member{...}
```
- 자매품 : @org.hibernate.annotations.DynamicInsert

### Entity 삭제
- 조회 후 em.remove(memberA); => tx.commit() 시 삭제 쿼리도 보냄.
- 삭제된 엔터티는 재사용하지말자...!

### Flush
영속성 컨텍스트의 변경 내용 DB에 반영

- em.flush() 직접호출 / tx.commit() / jpql 쿼리 실행 시 자동호출

### Detach(준영속)
영속성 컨텍스트에서 분리한 것 
```java
em.detach(entity) // 특정 엔터티만 전환
em.clear() // 완전히 초기화
em.close() // 종료
```

em.detach(memberA) 사용 시 
- 1차 캐시부터 지연 Sql 저장소까지 모든 정보 제거 => 직후 tx.commit()하면 적용할 쿼리가 없다!

준영속 상태의 주요 특징
- 비영속이랑 큰차이 없다
- 그러나 ID가 있다!
- 지연로딩 할 수 없다.

### 병합(merge)
준영속 => 영속
`Member mergeMember = em.merge(member);`

---

## 4장 Entity 맵핑 <a name = "chapter-4"/>

### 개요
- 객체와 테이블 맵핑 : @Entity, @Table
- 기본 키 맵핑 : @Id
- 필드와 컬럼 : @Column
- 연관관계 : @ManyToOne, @JoinColumn

### @Entity(name ="") 
- JPA에서 사용할 엔터티 이름, 통상 클래스 이름 사용.
- default = class Name
- 기본 생성자 필수 (public Member() / protected Member() ) 
  >> 별도의 생성자 만들 경우, 반드시 기본 생성자 수기 추가할 것
- final, enum, interfae, inner class에는 사용 불가
- 저장 필드에 final쓰지말아라


### @Table(name= "{table_name}", catalog = "" , schema = "", uniqueConstraints = "")

### 기타 맵핑
```java
@Enumerated(EnumType.STRING)
private RoleType roleType;

@Temporal(TemporalType.TIMESTAMP)
private Date createdDate;

@Lob
private String description;

// Getter & Setter

public enum RoleType{
  ADMIN, USER
}
```

- roleType => enumtype 사용 시 별도 어노테이션 유의
- temporal => 날짜 타입 맵핑 시.
- description => 길이 제한 없는 필드는 varchar 대신 clob이 적합. @Lob이용해서 clob/blob타입 저장 지원


! HBM2DDL > create, create-drop, update같은 DLL 수정 옵션은 운영환경에서 절대로 사용하지말것.
영속성 컨텍스트에서 관리하도록 놔두고, 개발 단계에서만 생성해서 "어떤 쿼리를 실행하는지", 테이블을 즉각 생성함에 따라 맵핑 작성에 익숙해지도록 활용해라.

! 하기 옵션으로 camelCase의 java, snake_case의 db를 자동 맵핑토록 지원 가능하다. 이후 table =()를 명시적으로 지정안해도 번역 적용 .

```xml
<property name ="hibernate.ejb.naming_strategy"
          value = "org.hibernate.cfg.ImproveNamingStrategy" />
 
```

### 기본 키의 생성 전략

@Id 적용 가능한 타입 목록
> - 자바 기본형
> - 자바 Wrapper형
> - String
> - java.util.Date
> - java.sql.date
> - java.math.BigDecimal
> - java.math.BigInteger

* 직접 할당 `board.setId("id1") 등` 시 em.persist()로 저장하기 전에 수행해야한다.
** 상기 @Id 적용은 @GeneratedValue 추가해서 원하는 키생성 전략을 이용한다
*** 키 생성 전략을 사용하려면(@Id) 반드시 persistence.xml에 hibernate.id.new_generator_mapping = true속성 추가한다. 호환성으로 인해 default는 false

```java
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long Id;
// 생성 전략을 DB에 위임, auto_increment등 내부로직 사용해서 Id생성

...
@GeneratedValue (strategy = GenerationType.SEQUENCE,
                 generator = "BOARD_SEQ_GENERATOR")
// 별도로 등록한 board_seq_generator를 사용해 키 생성하는 sequence옵션.
// 오라클, postgreSQL, DB2, H2 (sequence 지원 DB)

```

### 주요 필드~ 컬럼 맵핑 레퍼런스 
- 145p, 필요시 참고

> @Column :컬럼 맵핑
> @Enumerated : 자바의 enum type 맵핑
> @Temporal : 날짜 타입 맵핑
> @Lob : Blob, Clob,
> @Transient : 특정 필드 DB 맵핑 x
> @Access : JPA가 엔터티 접근하는 방식 지정

```java
@Entity
public class Member{
  @Id 
  private String id;
  
  @Transient
  private String firstName;
  
  @Transient
  private String lastName
  
  @Access(AccessType.PROPERTY)
  public String getFullName() {
    return firstName + lastName;
  }
}
// 이경우 firstName, lastName은 DB에 저장 안됨
// Fullname이 accesstype.property로 명기되며 이 getter를 참조하여 DB에 저장
// Fullname을 DB에서 조회 가능 => 필드를 조합한 별도의 property (주민번호 뒷자리 + 인코딩 등)를 db에 담고 싶을때 참고할 것

```

---

## 5장 연관관계 매핑 기초 <a name = "chapter-5"/>
  
### 주요 개념
- 방향 : 단방향 /양방향
- 다중성 : N:1, 1:N, 1:1, N:M
- 주인 : 양방향 연관관계 > 누가 소유할 것인가 정의해야함

> 참조를 통한 연관관계는 항상 단방향임을 유의할 것 : 양방향 연관관계를 구현하려면, 각각 서로를 참조해야함.


```java
@ManyToOne
@JoinColumn(name = "TEAM_ID")
private Team team;
```
- 다대일 조인의 예시. annotation을 통해 특정 칼럼이 어떻게 연관되는지 명시해고, 해당 객체를 그대로 불러온다.
- JoinColumn : 외래키 맵핑시 사용
- ManyToOne : 다대일 관계 명시 유의할것.

### 사용 예제

```
public void testSave(){
  Team team1 = new Team("team1", "팀 1");
  em.persist(team1);
  
  Member member1 = new Member("member1", "회원1");
  member1.setTeam(team1); 
  em.persist(member1);
  
  Member member2 = new Member("member2", "회원2");
  member2.setTeam(team1);
  emp.persist(member2);
}
```
- 영속 상태의 엔터티만 저장가능함 유의할 것.


- 그러나 순수한 객체 관점에서 접근 시, 양방향으로 연결해주지 않으면 위험할 수 있다.
- 상기의 setTeam 관련건은 jpa사용에 따라 주인에만 값 저장이 처리됨 유의할 것.


### 양방향 관계 시 편의성 고려한 맵핑 방안
```java
public void setTeam(Team team) {
  if (this.team != null ) {
    this.team.getMembers().remove(this);
  }
  this.team = team; 
  team.getMembers().add(this);
}
```

- 관계 변경 후, 영속성 컨텍스트가 아직 살아있을 때 teamA getMembers() 했을 때 member1의 반환 유의해야함.
- member1 -> teamB를 했을 때 DB는 정상적으로 붙지만, teamA에서 해제는 누가하나? DB에는 어떻게 적용되나? 결국 연결은 확실히 해제하는게 안전하다.

###  요약

- 단방향 맵핑만으로 테이블과 객체의 연관관계 맵핑은 정의됨
- 단방향을 양방향으로 만들면 반대방향으로 객체 그래프 탐색 추가
- 양방향 연관관계 맵핑하려면 객체에서 양쪽 방향을 모두 관리해야함.

=> 양방향은 복잡하고 실수할 수 있다. 가능한 단방향으로 구성하고, 반대방향으로 객체 그래프 탐색 기능이 필요할 때 양방향 사용토록 코드 추가할 것.


## 6장 다양한 연관관계 맵핑 <a name = "chapter-6"/>

### 다중성의 종류

- 다대일(@ManyToOne)
- 일대다(@OneToMany)
- 일대일(@OneToOne)
- 다대다(@ManyToMany)


### 다대일 단방향[N:1]
회원.팀은 있지만 팀.회원은 없는경우. 회원-> 팀으로 단방향 참조/연관관계를 갖고, 해당관계의 주인은 회원이다.
```java
@ManyToOne
@JoinColumn(name = "TEAM_ID")
private Team team;
```
### 다대일 양방향
양방향은 외래 키가 있는 쪽이 연관관계의 주인
- JPA는 외래 키 관리 시, 연관관계의 주인만 사용.
- Team.members는 조회 위한 JPQL 이나 객체 그래프 탐색에만 사용. 

### 일대다 단방향
Java collection > Collection, List, Set, Map 중 사용 유의할 것

- 맵핑한 객체가 관리하는 외래키가 다른 테이블에 존재 >> update sql을 추가 실행해야 하는 제약
```java
public void testSave() {
  Member member1 = new Member("member1");
  Member member2 = new Member("member2");
  
  Team team1 = new Team("team1");
  team1.getMembers().add(member);
  team1.getMembers().add(member); // 여기까진 동일
  
  em.persist(member1); 
  em.persist(member2);
  em.persist(team1); // 이때 insert team1, update member1, update member2 수행
  transaction.commit();
}
```

수행되는 쿼리

```SQL
insert into Member(MEMBER_ID, username) values (null,?)
insert into Member(MEMBER_ID, username) values (null,?)
insert into Team (TEAM_ID, name) values (null,?)
update Member set TEAM_ID=? where MEMBER_ID=?
update Member set =TEAM_ID =? where MEMBER_ID=?
```
- member entity > team entity를 모르기 때문에, member entity 저장 시 member.team_id 가 저장되지 않는다!
- 그러나 이느 ㄴteam으 ㄹ저장할때 참조 확인해서 update를 수행

따라서 다른 테이블의 외래키 관리하는 과정으로 인해, 개발자가 의도적으로 persist하는 대상을 신경써야한다.
=> 일대다 단방향 보다는, 다대일 양방향 맵핑을 사용할 것 

### 일대일
일대일 주 테이블에 외래키 , 양방향 예제코드 

```java
@Entity
public class Member{
  @Id
  @GeneratedValue
  @Column(name = "MEMBER_ID")
  private Long id;
  
  private String username ; 
  
  @OneToOne
  @JoinColumn(name = "LOCKER_ID")
  private Locker locker;
}

@Entity
public class Locker {
  @Id 
  @GeneratedValue
  @Column(name = "LOCKER_ID")
  private Long id;
  
  private String name;
  
  @OneToOne(mappedBy= "locker")
  private Member member;
}
```

- 양방향 > 연관관계 주인 필요 > Member가 외래 키 가지고 있음 > Member.locker가 연관관계의 주인.
- Locker.member은 따라가는 입장 > mappedBy 선언.

### 다대다
관계형 데이터베이스는 정규화된 테이블 두개로 다대다 관계를 표현할 수 없다 (!)
Member > member_id, username
Product > product_id, name
상기 두개로는 연관관계 표현 어려움, bucket이 필요.
Member_Prodcut > Member_id(pk,fk), Product_id(pk,fk)
복합키 기반으로 동시 조회 가능토록 테이블 있어야 다대다 > 다대일/일대다로 풀어낼 수 있다.

그러나 객체는 객체 두개만으로 다대다 관계를 만들 수 있다 (!)
- 컬렉션을 사용 + @ManyToMany

### 복합 기본 키
Member_id, product_id로 이루어진 복합 기본키를 사용하는 예제
별도의 식별자 클래스 만들고, @IdClass사용해서 지정해줘야 한다.
이외에,
- Serializable 구현
- eqauls, hashcode 구현
- 기본 생성자
- public 
- @IdClass외에 @EmbeddedId 사용 가능



## 7장 고급 맵핑 <a name = "chapter-7">
  
### 조인전략
  
```java
@Entity 
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name= "DTYPE")
public abstract class item {
 (...)
}
  
 
@Entity
@DiscriminatorValue("M") 
public class Movie extends Item{
 (...)
}  
```
  - @inhertance(strategy = InheritanceType.JOINED) : 상속 맵핑은 부모 클래스에 inhertance 사용 필요. 
  - @DiscriminatorColumn(name = "DTYPE") : 부모클래스에 구분 컬럼 지정. 저장된 자식 테이블 구분 기준. dtype은 default.
  - @DiscriminatorValue("M") : 엔터티 저장 시 구분 컬럼에 입력할 값 지정. 이 경우 dtype에 M저장
  
```java
@Entity
@DiscriminatorValue("B")
@PrimaryKeyJoinColumn(name = "Book_ID")
public class Book extends Item{
  (...)
}
```
- 자식 테이블의 기본 키 컬럼명을 변경하는 경우. 
  - 조회할 때 조인을 많이 사용하게 되며 성능 관점 유의해야
  - 조회 쿼리 복잡해짐 (동일 값을 담는 pk 증대)
  
### 단일 테이블 전략
- null허용한 값을 이용
`@Inheritance(strategy = InheritanceType.SINGLE_TABLE)`
- @DiscriminatorColumn 반드시 사용해야함. 구분컬럼 꼭 필요.
  - 따로 사용치 않을 경우,Entity 이름을 등록.
  
### @MappedSuperclass

- 부모 클래스는 테이블과 맵핑 하지 않고, 부모클래스를 상속 받는 자식클래스 에게 맵핑 정보만 제공하고 싶은 경우.
- 추상 클래스와 비슷한 성질.
- 필요시 @AttributeOverrides / @AssociationOverride
  
  
### 식별관계/비식별관계
- 식별관계 : 부모 테이블의 기본 키 내려받아서 자식 테이블의 기본 키 / 외래키로 사용하는 관계
  ```
  parent : parent_id(pk), name
  child : parent_id(pk,fk), child_id(pk), name
  ```
- 비식별 관계 : 부모 테이블의 기본 키 받아서 자식 테이블의 외래 키로만 사용
  ```
  parent : parent_id(pk), name
  child : child_id(pk), parent_id(fk), name)
  ```

### 복합키
- @IdClass : 관계형 데이터베이스에 가까운
```java
@Entity
@IdClass(ParentId.class)  // 여기 유의
public class Parent{
  @Id
  @Column(name = "PARENT_ID1")
  private String id1;
  
  @Id
  @Column(name = "PARENT_ID2")
  private String id2;
  
  (...)
}
  
public class ParentId implements Serializable {
  private String id;
  private String id2;
  
  public ParentId() {
  }
  public ParentId(String id1, String id2){
    this.id1 = id1;
    this.id2 = id2;
  }
  
  @Override
  public boolean equals (Object o) {...}
  
  @Override
  public int hashCode() {...}
}
  
```
  - 조건
    - 식별자 클래스의 속성명, 엔터티에서 사용하는 식별자의 속성명이 같아야함 (private String id1)
    - Serializable interface를 구현해야 한다
    - eqauls, hashCode 를 구현해야 한다.
    - 기본 생성자 필수
    - 식별자는 public
  
  - 사용 방식
  
  ```java
  Parent parent = new Parent();
  parent.setId1("myId1");
  parent.setId2("myId2");
  parent.setName("parentName");
  em.persist(parent); // 이 시점 id1, id2를 사용해서 영속성 컨텍스트로 사용한다.
  ```
  
  ```java
  ParentId parentId = new ParentId("myId1", "myId2");
  Parent parent = em.find(Parent.class, parentId);
  // ParentId를 사용해서 조회한다.
  ```
  
- @EmbeddedId : 객체 지향에 보다 가까운 구조   
  
