### 정규화
- 참고 : https://jiyoungtt.tistory.com/47

#### 1차 정규화
테이블의 컬럼이 하나의 atomic value를 갖도록, 테이블을 분해하는 것
ex) 유저 : A, 좋아하는 음식 : a,b,c인 컬럼이 있다면,
A:a, A:b, A:와 같이 분해

#### 2차 정규화
1차 정규화 진행한 테이블에 대해, 완전 함수 종속을 만족하도록 테이블을 분해하는 것.
-> 기본키의 부분집합이, 결정자가 되면 안된다! 
복합키 식별하고, 종속된 정보를 별도의 테이블로 분해하는 과정으로 생각할 것
(사람 이름, 연공) :PK -> 직급 : 속성과 같이 구성되어있을때 직급이 연공에 종속된다면? 분리할 수 있다. 


#### 3차 종규화
2차 정규화 진행된 테이블, 이행적인 종속을 없애도록 테이블을 분리하는 것
사용자 : A -> 거주지 : AA -> ZipCode : AAA와 같은 경우, 거주지와 zipcode는 종속되는 정보
사용자로 정보를 묶지 않고, 거주지 관련 테이블을 분리.
