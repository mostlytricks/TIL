# Database Concepts
---


## 용어
### View

### Materialized View



### Index

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

### Synonym

### Table Trigger

### Database Links
