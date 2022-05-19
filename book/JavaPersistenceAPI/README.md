# Java ORM 표준 JPA 프로그래밍 

- 김영한 지음
- 시작일 : 2022.05.19 ~


## TOC
[1장](#alpha)


## 1장 JPA_소개 <a name = "alpha"/>

### Why JPA?
- CRUD SQL 작성 x
- 조회결과 자동 객체 맵핑.
- 테스트에 강점, 생산성과 유지보수 유리해짐.
- DB 변경 시 매우 편리.

### 이슈
- 성능 문제? > nativeSQL 지원, 직접 작성 가능
- DB 쿼리 힌트 사용
- DB 조회 성능 이슈 >> SQL 사용시 공통적으로 발생하는 문제.

### field 추가 시 포괄적으로 생기는 문제 시나리오
1) 등록 코드 변경
> 전화번호를 회원 관리 정보에 추가하자!
> member.tel 추가.
> insert sql 추가
> 회원 객체 > parameter(tel) > sql
> 완성!

2) 조회 코드 변경
> tel 조회 (데이터, ui)가 안된다!
> select sql 변경

3) 수정 코드 변경
> 수정도 안되네?

4) 연관된 객체
> 모든 member은 특정 team 소속 시..
> sql 변경
> 인줄 알았더니 method 추가 ( find, findWithTeam)
> 이 모든걸 어떻게 알아차리나? 실패 후 dao를 열어서 뜯어봐야 안다.

5) 결론
쿼리향 시스템의 문제. 모든 내용은 DAO를 열어서 정확히 어떤 sql이 실행되는건지 알아야만 한다. 
