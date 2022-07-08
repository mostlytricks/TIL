# 기타 팁


### 인덱스 타는지 확인
```
EXPLAIN PLAN FOR -- 이후 PLAN에서조회 가능
SELECT * 
  FROM TEST2
 WHERE 1=1
  AND NAME ='1'
;

SELECT * FROM TABLE (DBMS_XPLAN.DISPLAY)
```
- DBEAVER의 실행계획 보기와 동등
- INDEX RANGE SCAN등의 탐색문구 확인 필요

