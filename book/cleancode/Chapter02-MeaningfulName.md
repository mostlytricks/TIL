# Chapter 2 - 의미있는 이름
---

## 기억할만한 부분

- Key Rule
  - 의도 명확
  - 발음하기 쉬운 이름
  - 검색하기 쉬운 이름
  - 인코딩 지양(Interface of A → iA 지양)
  - 기억력 과신 지양 ( String k = 'https://this-is-the-core-value-of-this-function.html')
  - 한 개념에 한 단어( controller, manager, driver => controller)
  - 의미 있는 맥락 추가
    - 하지만 불필요한맥락은 제외 (MES인데 모든 변수에 MES_alpha_to_omega?)

- 예제
```java
//bad
int d;
 
//good
int elapsedTimeInDays;
int daysSinceCreation;
```

```java

//bad
public List<int[]>getThem(){
    List<int[]> list1 = newArrayList<int[]>();
    for(int[] x : theList()
        if(x[0] == 4)
            list1.add(x);
    return list1;
}
 
//good
public List<Cell> getFlaggedCells(){
    List<Cell> flaggedCells = new ArrayList<Cell>();
    for (Cell cell : gameBoard)
        if (cell.isFlagged())
            flaggedCells.add(cell);
    return flaggedCells;
}
```

- 주요 변경점
    - 이름  변경
    - List의 각 칸을, 정수 배열보다는 class로 변경
    - List의 각 공간 및 최종 함수의 결과물에 의미 명확
    - x[0] == 4 or x[0] ==FLAGGED와 같은 상수 X  
      - enum혹은 상수 지정 위치, 선택 옵션추가에 취약해짐 => 추상화해서 감싸는게 낫다!   
    - cell.isFlagged()로 값 감춤. 내부에는 4일 경우에 flagged이든 5이든 상관 없어짐.

```java
//bad
List<int[]> accountList = new ArrayList<int[]>();
 
//good
List<int[]> accountGroup = new ArrayList<int[]>();
```
  - 이름은 list와 같은 중의적 표현(개발자 관점) 배제할 것.
 
```

//baddest
getActiveAccount();
getActiveAccounts();
getActiveAccountInfo();
 
getActiveAccountsList();
getActiveTheAccount();
...
```
  - 불용어, Info ~ Data 등 의미 부여하기 힘든 이름 지양할 것 
    - 다루는 데이터 중에 Info 아닌게 어디있나. 
  - source, destination 정도의 명확한 용도 필요

```java
//bad
String firstName
String street
int houseNumber
String city
 
//good
String addrFirstName
String addrStreet
int addrHouseNumber
```


