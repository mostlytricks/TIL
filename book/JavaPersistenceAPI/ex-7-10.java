/*
Embedded Id를 이용하여 식별 관계 구성시, @MapsId를 사용한 예제

*/

//parent
@Entity
public class Parent{
  
  @Id @Column(name = "PARENT_ID")
  private String id;
  
  private String name;
  /* ... */
}

// child
@Entity
public class Child {
  @EmbeddedId
  private ChildId id;
  
  @MapsId("parentId") //ChildId.parentId가 맵핑된다.
  @ManyToOne
  @JoinColumn(name = "PARENT_ID")
  public Parent parent;
  
  private String name;
}

// 자식 ID
@Embeddable
public class ChildId implemenets Serializable {
  private String parentId ; // 상기 MapsId로 맵핑되는 값
  
  @Column(name = "CHILD_ID")
  private String id; 
  
  //eqauls, hashcode ... 
}

//손자
@Entity
public class GrandChild{
  @EmbeddedId
  private GrandChildId id;
  
  @MapsId("childId")
  @ManyToOne
  @JoinColumns({
    @JoinColumn(name = "PARENT_ID"), // name이 DB에 있는 Table name임을 유의하자. 
    @JoinColumn(name = "CHILD_ID")
  }) // child mapping시 필요한 값임을 유의할 것. child의 id로 연결하되, joincolumn내부에는 실제로 사용되는 pk를 개별적으로 입력해야한다.
  private Child child;
  
  private String name;
  /* ... */
}

// 손자 ID
@Embeddable
public class GrandChildId implements Serializable {
  private ChildId childId; 
  
  @Column(name = "GRANDCHILD_ID")
  private String id;
  
  // equals, hashCode dbdml
}
