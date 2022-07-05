

### 4.2 Interfaces

```typescript
type Player ={
  nickname:sring,
  healthBar :number
}
type Nickname =string
type Health = number

const alpha : Player ={
  nickname:"alpha",
  healthBar:10
}

type Team = "read"|"blue"|"yellow"

```
- type은 유연하게 사용가능하고, Nickname의 string 표기와 같이 alias처럼 사용도 가능
- team에서의 사용 방법을 잘보자. 저렇게 선택 옵션을 제한할 수도 있다!

```typescript
type Team = "read"|"blue"|"yellow"

interface Player{

  nickname:string,
  team: Team
}
```
- interface의 사용 예제. interface는 object의 모양을 정의한다. type처럼 사용도 가능

```typescript
interface User {
  name:string
}
interface Player extends User{
}
const alpha : Player ={
}

// type을 동등하게 사용시
type User ={
  name:srting
}
type Player = User &{ // type은 &로 받을 수 있다!
}

interface User{ //이 인터페이스를 사용할경우, 상단의 interface와 합쳐진다! 인터페이스는 타입과 달리 결합에 강함.
  omega :string
}

```


### 4.3 Interfaces part two

```typescript
abstract class User{
  constructor(
    protected firstName : string,
    protected lastName : string
  ){}
  abstract sayHi(name:string):string
  abstract fullName() :string
}

class Player extends User{
  fullName() {
    return `${this.firstName} ${this.lastName}`
  }
  sayHi(name:string){
    return ``
  }
}
```
- 추상 클래스 예제


```typescript
interface User{
  sayHi(name:string):string
  fullName():string
}
class Player implements User{
  (...)
}  

// type으로의 사용방안 유의할 것
function makeUser(user:User) : User{
  return ~
}

```
- interface 상속 시에는, 상속되는 변수를 private/protected로 사용할 수 없다는 한계 존재
- abstract class는 protected지원

