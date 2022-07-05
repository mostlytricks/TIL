# Design Pattern에 대한 기록
- 설계 의도 : 디자인 패턴에 대한 기록/번역/학습기록

### Factory Pattern

```java
public class UserFactory{
  public User create(String name, int age){
    if 'a' :
      return new aUser(name);
    if 'b' :
      return new bUser(name);
    if 'c' :
      return new cUser(name);
    else :
      return new dUser(name);
  }
}

//
public void main(){
  UserFactory factory = new Factory();
  User user1 = factory.create('a', 19);
}
```
- 유저 객체를 각각 생성하지 않고, factory에 생성권한 위임
- 유저 객체가 추가되거나 변경되는 경우, factory만 수정하면 됨 + User interface를 통한 유연한 관리 가능.
