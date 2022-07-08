/*
  JPQL관련 내용은 탐색 용이성 위해 분리하여 기록
*/

// 예제 10.1 회원 엔터티
@Entity(name="Member")
public class Member {
  
  @Column(name = "name")
  private String username;
}

String jpql = "select m from Member as m where m.username = 'kim'";
List<Member> resultList = em.createQuery(jpql, Member.class).getResultList();



/*
  Criteria : jpql생성하는 빌더 클래스. 
  query.select(m).where(...)와 같이 빌더 형식으로 jpql 작성 가능.
  => 컴파일 시점에 잘못작성된 쿼리 인식 가능, 자동완성 기능 활용, 동적 쿼리 작성 등.
  => 복잡하니 사용을 좀처럼 안한다.
*/

/*
  QueryDSL : 보다 간편, 오픈소스.
*/

// 예제 10.4 QueryDSL

//준비
JPAQuery query = new JPAQuery(em); 
QMember member = QMember.member;

List<Member> members =
    query.from(member)
    .where(member.username.eq("kim"))
    .list(member);
//멤버에서, username이 kim인 리스트. 


/*
  native SQL
  SQL 직접 사용. oracle에서만 사용하는 connect by, dbms특화 hint 등.
*/

// 예제 10.5 Native SQL
String sql = "SELECT ID, AGE, TEAM_ID, NAME FROM MEMBER WHERE NAME = 'KIM'";
List<Member> resultList = 
   em.createNativeQuery(sql, Member.class).getResultList();
// jpql과 거의 유사하지만, jpql과 달리 실제 table 명칭 및 column_name을 사용한다. 직접 쿼리를 만들어 실행하기 때문.



/*
  Mybatis 등과 혼용시 유의사항
  - 영속성 컨텍스틑 적절한 시점에 강제 플러쉬 해야한다.
  - JPA를 위회하는 SQL을 JPA가 인식하지 못하기 때문.
*/
