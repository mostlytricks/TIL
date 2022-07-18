# Chapter 11 web app

- Controller -> service -> repository -> (DB)로 이어지는 domain 관점 개발내용 확인해볼 것
- Controller : mvc의 controller / 서비스 호출 및 view에 전달
- Service : Biz, transaction 시작. Repository호출
- Repoistory : JPA 실제 사용 계층, entity manager 이용한 엔터티 저장 및 호출
- domain : 엔터티 모인 계층

### 회원 기능 예시

```java
// 회원 엔터티 코드
@Entity
public class Member{
  
  @Id @Generatedvalue
  @Column(name = "MEMBER_ID")
  private Long id;
  
  private String name;
  
  @Embedded
  private Address address;
  
  @OneToMany(mappedBy = "member")
  private List<Order> orders = new ArrayList<Order>();
  
  (...)
}
```

```java
// 회원 리포지토리 코드
@Repository
public class MemberRepository{
  
  @PersistenceContext
  EntityManager em;
  
  public void save(Member member){
    em.persist(member);
  }
  
  public Member findOne(Long id){
    return em.find(Member.class, id);
  }
  
  public List<Member> findAll() {
    return em.createQuery("select m from Member m", Member.class).getResultList();
  }
  
  public List<Member> findByName
}

```
- 엔터티 저장과 관리 기능에 유의할 것. spring-jpa에서 interface 생성시 사용가능 메서드들이 확인됨
- @PersistenceContext 유의. 스프링 / J2EE 컨테이너 사용시 컨테이너가 직접 EM관리함. 컨테이너에서 제공하는 EM사용할 것
- 
