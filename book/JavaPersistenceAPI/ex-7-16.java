/*
다대일 양방향 조인 테이블 맵핑 예제
@JoinTable 유의할 것
*/

//부모
@Entity
public class Parent{
  @Id
  @GeneratedValue
  @Column(name = "PARENT_ID")
  private Long id;
  private String name;
  
  @OneToMany(mappedBy = "parent")
  private List<Child> child = new ArrayList<Child>();

  /* ... */
}

// child
@Entity
public class Child {
  @Id 
  @GeneratedValue
  @Column(name = "CHILD_ID")
  private Long id;
  private String name;
  
  @ManyToOne(optional= false )
  @JoinTable(name = "PARENT_CHILD",
            joinColumns = @JoinColumn(name = "CHILD_ID"),
            inverseJoinColumns = @JoinColumn(name = "PARENT_ID")
            )
  private Parent parent;
  
  /*  ...  */
}
