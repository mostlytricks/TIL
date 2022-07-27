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

### 기타 최적화 관련

- [쿼리 최적화를 위한 7가지 체크리스트](https://medium.com/watcha/%EC%BF%BC%EB%A6%AC-%EC%B5%9C%EC%A0%81%ED%99%94-%EC%B2%AB%EA%B1%B8%EC%9D%8C-%EB%B3%B4%EB%8B%A4-%EB%B9%A0%EB%A5%B8-%EC%BF%BC%EB%A6%AC%EB%A5%BC-%EC%9C%84%ED%95%9C-7%EA%B0%80%EC%A7%80-%EC%B2%B4%ED%81%AC-%EB%A6%AC%EC%8A%A4%ED%8A%B8-bafec9d2c073)

#### select 시에는 필요한 칼럼만

#### 조건 부여시(where절) 기존 DB값에 연산 최소화
```SQL
-- bad
select m.id, (...)
from movei m
inner join rating r on m.id = r.movie_id
where floor(r.value/2) =2
group by m.id

-- good
select m.id, (...)
from movie m
inner join rating r on m.id = r.movie_id
where r.value between 4 and 5
group by m.id
```
- 기존 인덱스 사용하지 못하고, full scan 하면서 r.value에 대한 연산 및 값 비교해야함


#### like 사용시 와일드카드는 String 앞단 지양할 것

```sql
-- bad
select g.value genre, count(r.movie_id) r_cnt
from rating r
inner join genre g on r.movie_id = g.movie_id
where g.value like '%comedy' -- 이 경우 full scan
group by g.value

-- good / 문자열에 대한 부분탐색, value를 직접 비교하는 것보다 나은 결과
select g.value genre, count(r.movie_id) r_cnt
from rating r
inner join genre g on r.movie_id = g.movie_id
where g.value like 'romantic%' or g.value like 'comed%' 
group by g.value
```

#### select distinct, union distinct 지양할 것
- 중복값 제거는 높은 코스트. 테이블 크기 줄이거나 대체할 것. exists활용 권장
```sql
-- bad
SELECT DISTINCT m.id, title 
FROM movie m  
INNER JOIN genre g 
ON m.id = g.movie_id;
-- good
SELECT m.id, title 
FROM movie m  
WHERE EXISTS (SELECT 'X' FROM rating r WHERE m.id = r.movie_id);
```

#### 가능한 group by 연산의 having보다는 where사용할 것
- 처리되는 데이터 크기가 보다 작아짐.

#### 테이블 3개 이상 inner join 시, from절 내 크기가 큰테이블 -> 작은 테이블 순서 배치할 것
- query planner에 따라 효과적인 순서 탐색 후 재배치 해주기도 함.
- 그러나 join되는 테이블 수가 많아지면, planning의 비용이 커짐 -> 최적화가 충분히 되지 않는 경우가 있다.
- 
