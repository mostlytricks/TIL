# Java ORM 표준 JPA 프로그래밍 

- 김영한 지음
- 시작일 : 2022.05.19 ~


## TOC
[1장](#alpha)
[2장]
[3장](#chapter-3)

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
```
- 엔터티 매니저 사용 > 회원 엔터티를 영속성 컨텍스트에 저장

### Entity life-cycle
1) 비영속
2) 영속
3) 준영속(detached) : 저장되었다가 분리된 상태
4) 삭제 : 삭제된 상태



