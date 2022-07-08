# Database Concepts
---


## 용어
### View
SELECT문을 가지고 있는 오브젝트. SELECT문을 저장해서, VIEW 조회 시 실행.
성능적인 문제는 동일하다.
-> SUMMARY TABLE 또는 MATERIALIZED VIEW를 사용

#### SUMMARY TABLE
미리 조인 작업 진행한 결과를 테이블로 저장하는 운영상의 기법.
그러나 BASE TABLE과 SUMMARY TABLE의 동기화는 별도로 고려해줘야한다.
-> TRIGGER 사용, DML 발생 시 함께 변경 / 서버에서 배치로 갱신요청 등


### Materialized View
데이터를 가지고 있는 VIEW. VIEW LOG라는 오브젝트 이용한 동기화 작업. 
원본데이터 대비 수정된 부분만 기록하여 동기화 (BLOCK STORAGE?)



### Index


#### INDEX UNIQUE SCAN
PK에 생성된 인덱스 기반 조회. INDEX상의 단일한 값을 '='로 조회 시.

#### INDEX RANGE SCAN
INDEX 수직 탐색 후, 필요한 범위 까지 확대

#### INDEX FULL SCAN
첫번째 리프 블록까지 수직탐색 한 뒤, 인덱스를 전체 탐색
경우에 따라 FULL SCAN 보다 성능이 밀릴 수 있다. (FULL SCAN은 MULTI BLOCK READ 가능) 

#### INDEX SKIP SCAN
MULTI COLUMN INDEX의 경우, INDEX(A,B) 에 대해 B를 탐색할때, 불필요한 구획 건너뛰며 탐색. 

### Hint

### Sequence
The sequence is a feature by some database products which just creates unique values, it just increments a value and returns it. 

```SQL
-- 생성
CREATE SEQUENCE ALPHA
  START WITH 100 -- 시작
  INCREMENT BY 1 -- 증감
  MAXVALUE 999 --최대
  CYCLE -- OR NOCYCLE 최대값 도달 시 CYCLE 또는 중지
  NOCACHE -- OR CACHE : 메모리에 저장 OR NOT
  NOORDER 
;
-- 조회 
SELECT ALPHA.NEXTVAL FROM DUAL; -- NEXTVAL 호출 시 SEQUENCE 값 증가됨 유의할것. SELECT도 동일함.
SELECT ALPHA.CURRVAL FROM DUAL;

-- 삭제
DROP SEQUENCE ALPHA
```
- unique한 값을 생성해주는 오라클 객체. 테이블과 별도로 저장/생성.

### Queue
- queue에 request 적재, dbms는 queue에 담긴 request 순차적 처리
- message queue. 카프카와 유사한가?

### Package
- procedure와 function 집합
- 선언부, 구현 부로 나뉨
```SQL
-- 선언부
CREATE OR REPLACE PACKAGE SCOTT.PACKAGE_TEST
AS
  PROCEDURE PROC_TEST;
  PROCEDURE PROC_TEST2(NAME IN VARCHAR2, AGE IN NUMBER);
  FUNCTION examFunc(getGender IN VARCHAR2) RETURN VARCHAR2;
END PACKAGE_TEST;
```

```SQL
-- 구현부
CREATE OR REPLACE PACKAGE BODY.SCOTT.PACKAGE_TEST
AS
  PRODCDURE PROC_TEST
  IS
  BEGIN
    INSERT INTO TABLE_TEST(COL1,COL2) VALUES('TEST',1);
  END PROC_TEST;
  (...)
  
  FUNCTION examFunc
  (getGender IN VARCHAR2)
  RETURN ARCHAR2
  IS
    setGender VARCHAR2(100);
  BEGIN
    IF (...)
  RETURN setGender;
  END examFunc;
END PACKAGE_TEST;
  
```

-- 호출
```SQL
BEGIN 
  PACKAGE_TEST.PROC_TEST;
END;

BEGIN 
  PACKAGE_TEST.PROC_TEST2('홍길동', 50);
END;

SELECT PACKAGE_TEST.examFunc('남') FROM DUAL;
```

### Procedure
일련의 작업을 수행토록 작성된 절차. (=PERSISTENT STORAGE MODULE)
리턴값을 안가질수도, 여러건 가질 수도 있다.

```sql
DECALRE
  OUT_TIER VARCHAR2(10);
BEGIN get_tier('faker', out_tier);
  DBMS_OUTPUT.PUT_LINE(OUT_TIER);
END;

```

### Function
각 프로세스 수행하기 위해 필요한 기능, 단일 리턴값


### Synonym
다른 유저의 객체 참조할때 주로 사용, 테이블의 이름을 설정함

```SQL
CREATE SYNONYM U1_STUDY FOR USER1.TESTSTUDY; -- 특정유저의 테이블을 U1_STUDY라는 이름으로 시노님 생성.

```



### Table Trigger

```SQL
CREATE OR REPLACE TRIGGER ALPHA
AFTER UPDATE -- 실행시점, BEFORE|AFTER INSERT|UPDATE|DELETE -- OF {COLUMN_NAME} ON {TABLE_NAME}
OF NAME
ON CUSTOMER
REFERENCING NEW AS N OLD AS O -- 기존값과 신규값의 약어
FOR EACH ROW -- 데이터를 행 마다 처리해줘야 하는 경우
BEGIN -- 실제 트리거 수행. 업데이트 시 CUSTOMER.NAME의 변경이 발생한다면, BUYER의 NAME도 바꿔라!
  UPDATE BUYER SET NAME=: N.NAME WHERE NAME=O.NAME;
END;

```


```SQL
SELECT * FROM ALL_TRIGGERS;
DROP TRIGGER {TRIGGER_NAME};
```


### Database Link
클라이언트/데이터베이스에서 네트워크상 다른 데이터베이스에 접속하기위한 설정 맡는 오라클 객체
1) oracle instance >=2
2) host_name, oracle_sid 구분되어야함
3) nls_character_set은 동일 
4) 연결되는 서버에는 listenr 반드시.


