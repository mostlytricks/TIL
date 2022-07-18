# Chap 12

### JpaRepository 상속 시 사용 가능한 메소드

- save(Entity | Child)
- delete(Entity) : 엔터티 하나 삭제, EntityManager.remove() 호출
- findOne(ID) : EntityManager.find()호출
- getOne(ID) : 엔터티를 프록시로 조회. EntityManager.getReference()호출
  - 엔터티 실제 사용하는 시점까지 데이터베이스 조회를 지연시킴. 클래스와 모양이 동일한 프록시 클래스를 만든다.   
- findAll() : 모든 엔터티 조회. sort / pageable을 파라미터로 제공 가능.

### 쿼리 메소드
메소드 이름만으로 쿼리를 생성하는 기능, JPA NamedQuery 호출, @Query 이용한 쿼리 정의 기능 등
- docs.spring.io/spring-data/jpa/docs 참고하여 지원 키워드 확인할 것
- And, Or, Is, Eqauls, Between, LessThan 등

#### 예문
- findByLastnameAndFirstname -> `where x.lastname = ?1 and x.firstname = ?2`
- findByLastnameOrFirstname
- findByFirstnameIs
- findByStartDataBetween
- findByAgeLessThan
- findByAgeGreaterThan
- findbyAgeGreaterThanEqual
- findByStartDateBefore
- findByStartDateAfter
- findByAgeIsNull
- findByAgeIsNotNull
- findByFirstnameLike
- findByFirstnameNotLike
- findByFirstnameStartingWith
- findByFirstnameEndingWith
- findByFirstnameContaining
- findByAgeOrderByLastnameDesc
- findByLastnameNot
- findByAgeIn(Collection age)
- findByAgeNotIn(Collection age)
- findByActiveTrue()
- findByActiveFalse()
- findByFirstnameIgnoreCase

엔터티 필드명 변경시 메소드 이름 직접 변경해야함 유의할 것.

```
// 인터페이스 작성 예
public interface MemberRepository extends Repository<Member, Long>{
  List<Member> findByEmailAndName(String email, String name);
}
```

#### 반환 타입
- 여러 건이면 Collection interface -> List<T>등 사용 가능
- 단 건일 경우 반환타입 지정
  
