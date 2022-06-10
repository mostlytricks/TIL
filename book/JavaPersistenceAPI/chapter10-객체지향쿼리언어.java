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
*/
