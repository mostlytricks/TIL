# 11장 시스템 System
---

### 기억해둘 내용

- 시스템 제작과 시스템 사용의 분리 
  - app 객체 제작, 의존성 연결하는 **준비 과정**
  - 이후 실제로 이용하는 **런타임 로직**
  - 

```java
//bad
public Service getService() {
  if (service == null)
    service = new MyServiceImpl(...); 
  return service;
}
```
  - 초기화 지연 기법.
    - 필요한 시점 (getService 호출 => service가 null인 최초 시점)까지 객체 생성 지연(new MyServiceImpl ~)
    - null pointer 반환하는 사례가 없음.
  - 문제점
    - 1. getService와 MyServiceImpl의 의존성
      - MyServiceImpl의 parameter를 수합하여, getService 접근시점에 제공해야만 해결가능
      - 상기 parameter가 getService에 꼭필요한가? 현행 para 없으면 컴파일 불가.
    - 2. 테스트의 난해
      - MyServiceImpl이 무거운 객체라면?
        - spring 테스트와 유사. Mock이용해서 테스트 전용 객체를 만들던지 해야함.
  
- 해결 기법
  - **Main 분리**
    - 생성 관련 코드를 main 또는 main에서 부르는 모듈로 이동
    - 나머지 시스템은 모두 객체 생성을 가정함
  - **Factory**
    - 객체 생성되는 시점을 app이 결정해야하는 경우.
      - ex) 주문 처리 시스템이 있을 때, LineItem instance를 새로 만들어서 order에 추가하는 경우. 객체는 실시간으로 만들어져야함.
```python
# Factory 예제 in wiki
class Pizza:
    HAM_MUSHROOM_PIZZA_TYPE = 0
    DELUXE_PIZZA_TYPE = 1
    SEAFOOD_PIZZA_TYPE= 2

    def __init__(self):
        self.__price = None

    def getPrice(self):
        return self.__price

class HamAndMushroomPizza(Pizza):
    def __init__(self):
        self.__price = 8.50

class DeluxePizza(Pizza):
    def __init__(self):
        self.__price = 10.50

class SeafoodPizza(Pizza):
    def __init__(self):
        self.__price = 11.50

class PizzaFactory:
    def createPizza(self, pizzaType):
        if pizzaType == Pizza.HAM_MUSHROOM_PIZZA_TYPE:
            return HamAndMushroomPizza()
        elif pizzaType == Pizza.DELUXE_PIZZA_TYPE:
            return DeluxePizza()
        elif pizzaType == Pizza.SEAFOOD_PIZZA_TYPE:
            return SeafoodPizza()
        else:
            return DeluxePizza()
# Usage
pizzaPrice = PizzaFactory().createPizza(Pizza.HAM_MUSHROOM_PIZZA_TYPE).getPrice()
print "$%.02f" % pizzaPrice
```

  - **의존성 주입**
    - 제어의 역전(IoC) 기법을 의존성 관리에 적용한 메커니즘

- 확장
  - AOP (Aspect-Oriented Programming)
    - 영속성 
      - => 프로그래머가 영속적으로 저자알 객체, 속성 선언후 영속성 프레임워크에 위임 
      - => AOP 프레임워크가 대상 코드에 영향 미치지 않는 상태로 동작방식 변경
