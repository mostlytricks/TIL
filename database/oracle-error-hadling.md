### ORA-01031 : 권한이 불충분합니다 / insufficient privileges
#### 개요
- 계정 A에서 Table a1, a2 보유
- 계정 B에서 해당 a1에 insert /update를 수행하는 procedure proc-a1 보유
- 개발 DB 재구성 과정 중, 계정 B의 A.a1, A.a2에 대한 insert/update 권한 미부여 확인됨
- B.proc-a1의 status는 invalid / compile 시 실패
- 해당 procedure를 실행시키는 버튼 클릭 시 오류 팝업

#### 조치
- 계정 A에 접속
- grant [insert|update] A.a1 on B 

#### 결과
- B.proc-a1의 status =valid로 변화
- 컴파일시 정상동작

#### 기타
- 오류 확인시 발생 위치 유의할 것.
- procedure 내 insert/update 구문이 오류 로그내 표기된 row에서 확인됨



#### ORA-01775 : 동의어가 순환고리 유형으로 정의되어 있습니다
#### 개요
- 계정 A의 SYNONYM S1에 대해, 계정 B가 조회권한이 없는 경우 확인됨

#### 조치
- 계정 A접속 /
- CREATE OR REPLACE PUBLIC SYNONYM S1 FOR A.S1
- PUBLIC SYNONYM에서 USER SYNONYM으로 재정의
- GRANT SELECT ON A.S1 ON B
- 조회 권한 부여
