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
  
  public List<Member> findByName(String name){
    return em.createQuery("select m from Member m where m.name = :name", Member.class).setParameter("name", name).getResultList();
  }
}

```
- 엔터티 저장과 관리 기능에 유의할 것. spring-jpa에서 interface 생성시 사용가능 메서드들이 확인됨
- @PersistenceContext 유의. 스프링 / J2EE 컨테이너 사용시 컨테이너가 직접 EM관리함. 컨테이너에서 제공하는 EM사용할 것
- EntityManager Factory는 사용할일이 거의 없지만(상기 의존성 주입), 필요시 @PersistenceUnit을 사용한다.

```java
// 회원 서비스
@Service
@Transactional
public class MemberService{
  
  @Autowired
  MemberRepository memberRepository;
  
  /* 회원 가입 */
  public Long join(Member member){
    validateDuplicateMember(member);
    memberRepository.save(member);
    return member.getId();
  }
  
  private void validateDuplicateMember(Member member){
    List<Member> findMembers = memberRepository.findByName(member.getName());
    if(!findMembers.isEmpty()){
      throw new IllegalStateException("이미존재하는 회원 입니다.");
    }
  }
  
  /* 전체 회원 조회*/
  public List<Member> findMembers(){
    return memberRepository.findAll(); 
  }
  
  public Member findOne(Long memberId){
    return memberRepository.findOne(memberId);  
  }
}
```
- join이라는 동작을 수행하기위해, repository에 어떻게 순차적인 연결을 하는지 유의할 것.
- join 내부도 validateDuplicate 이라는 동작은 별도의 메서드로 분리해냈다. 단일책임원칙 유의
- @Service -> spring bean으로 등록
- @Transactional -> spring framework가 transaction 적용. 
- @Autowired -> 스프링 컨테이너가 스프링 빈 주입
  - lombok의 @RequiredArgConstructor, private final 이용해서 생성자를 통한 의존성 주입 기법 유의할 것. @Autowired는 사용 지양에 대한 코멘트들이 확인되고 있다.
