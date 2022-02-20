# Clean code TIL,
with Nomadcoder :p
<br/><br/>

- 개요 : clean code TIL
- 시작 : 2022.02.18 ~ 
- 종료(예정) : ?!
<br/><br/>




## Table of Contents
---
* [Day 0 - 사전 준비](#abstract)
* [Day 1 - 깨끗한 코드](#chapter-1-clean-code)
* [Day 2 - 의미 있는 이름](#chapter-2-name)



## Chapter 1 Clean Code
--- 
> 2022.02.19


### 기억 및 메모
> 궁극적으로 코드는 요구사항을 표현하는 언어라는 사실을 명심한다 (3p)
- 잘 작성된 코드는 가독성이 높다는 맥락과 연결되는 대목 아닐까.
 

> 르블랑의 법칙(leblanc's Law) - 나중은 결코 오지 않는다(4p)
- 프로젝트의 기간, 소스 반영전의 이해관계자를 오가며 검토할 경우 더더욱 그러는 듯 하다.
- 개발 반영 프로세스에서도 좀 더 기민한 대응이 가능해야하지 않나?


> 새로운 타이거 팀이 구성된다. (5p)
- si / sm의 현장을 보여주는 대목이라 다시 봐도 인상깊다. 세계와 현장을 아우른다니.


> SRP, OCP, DIP (19p)
- SRP : [Single Responsibility Principle](https://en.wikipedia.org/wiki/Single-responsibility_principle) - 단일 책임. 한 클래스는 하나의 책임만.
- OCP : [Open Closed Principle](https://en.wikipedia.org/wiki/Open%E2%80%93closed_principle) - 확장에는 개방정이지만, 변경에는 폐쇄적.
- DIP : [Dependency Inversion Principle](https://en.wikipedia.org/wiki/Dependency_inversion_principle) - 응집 대신 추상화를.
! SOLID principle의 일부. 유의할 것

<details>
<summary> **오늘의 소감 한 마디** </summary>

 - 다시 읽어도 도입부의 프로젝트 내부 묘사가 사실적이다. 타이거 팀은 과연 몇 년 걸려서 기존 기능을 커버하는 뛰어난 시스템을 만들까.
  
 - 대개는 마이그레이션이라며 병행 사용을 안고 가더라. 대체가.

 - 샘플 코드를 다른 언어로 작성해보며 따라가봄직 싶다. 아니면 예전에 작성한 코드를 발굴해본다던지.
</details>

<br/>
<br/>


## Chapter 2 Name
--- 
> 2022.02.20


### 기억 및 메모
> 문제는 코드의 단순성이 아니라 코드의 함축성이다 (23p)
- 간혹 a의b를 c를참조해서 d인케이스에처리하는 e다 같은 긴 이름의 메소드명은 과연 옳은 것일까? 아니면 분해가 덜된걸까?(class명이라던지)

> 변수 이름에 variable이라는 단어는 단연코 금물이다 (26p)
- 불과 2년전에 v_ , g_, t_로 구분하는 플젝을 본적이 있었는데. 

> 새로운 개발자가 들어오면 변수를 설명해준 다음 ... 우리가 만들어낸 발음을 알려줬다 (27p)
- 하면 안될 사례로 기록. 그러나 한국의 단어 음절을 1)영어로 번역 후 이름 사용 vs 2)한국단어 그대로의 초성만 가져옴의 관습 중, 무엇이 나은지는 의문이다.
- 영어가 모국어가 아닌 이들은 어떻게 변수명을 정립하는게 맞는가에 대한 고민이 필요하지 않을까? 물론 영어모르고 코딩은 무리긴 하더라도, 이름에 대한 규약은 필요할듯하다.

> 검색하기 쉬운 이름을 사용해라(28p)
- 마찬가지로 단순히 한글 명칭을 쓰면 검색하기 편하지 않을까 싶다. 심지어 한국어의 동음이의어를 고려하더라도. 용언에 대한 정립은 고민해봐야될지도
- 현행 영어로 1차 번역 -> 기존 관습적인 변수명과 비교 -> 이후 선언 및 사용하며 생각보다 변수명의 통일성이 줄어드는 듯.

> GSDAccountAddress 클래스를 사용할까? (37p)
- 이런 이름 많이 본 거 같다.
- Address의 구현체로 GSDAddress or GSDAccountAddress도 부적합할까...?


<details>
<summary> **오늘의 소감 한 마디** </summary>

 - 코딩은 영어공부가 절반이라더니, 그 맥락을 이해하는 것은 쉽지 않은 듯하다.
 
 - add, insert, append의 메서드가 각각 선언되었을 때, 정확한 늬앙스의 차이를 구분할 수 있을까? 직관적으로는 add는 연산, append는 리스트의 마지막에 추가, insert는 특정 인덱스에 추가로 생각되지만.. 
 막상 java List의 add는 그냥 하나 추가다.
 
 - 언어 및 이름이 주는 느낌을 다른 이들이 공감되는 선에서 코드에 녹여내는 역량이 클린 코드와 닿지 않나 생각된다.
 
 - 반면 Biz 영역의 용어를 사용하는 것에 대해도 논의가 필요하지 않나 생각된다. a+b의 합을 단순 add / calculate등으로 명시했지만 알고보니 '결산', '정산' 동작이었다면...?
 
</details>


<br/>
<br/>

### Memo.
---
#노개북 #노마드 북클럽
...!
