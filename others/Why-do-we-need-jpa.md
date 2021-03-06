# 왜 우리는 JPA가 필요한지에 대한 기록
- 버스 정산에 대해 생각해보면, 버스라는 하나의 도메인으로 묶일 수가 있나? 없다고 생각
- 버스는 요금(금액 및 결제), 사용자(카드 및 사용자 정보, 타각 시각 등), 노선 및 정류장, 소속 회사 등 다양한 계층 지니고 있음
- 이를 JPA 사용 또는 ORM관점이 아닌 DB관점에서 접근시, 여러 문제 발생

### Case 1.
##### 도입
> 어떤 경로(A) 버스들의 2022.01.12 ~ 2022.02.01 기간 동안, 특정 구간 (B), 정류장별(D) 승객 수(E) 및 금액(F)를 모니터링 할 시 , 어떻게 해야하나.
> 시스템 구성 관점 : front - controller - service - dao순으로 데이터 흐르니 문제 없겠지!
> 레거시 코드의 경우, dao 내에서 join하는 table수에 비례하는 길이의 쿼리를 직접 작성
    > 상기 테이블 A~F를 조인하니 대략 100줄 내외의 select쿼리
    > 개발 당시는 front페이지에서 조회/모니터링 목적으로 페이지 구성했으니 select로 끝이겠지만...
> 만약 해당 정보에 누락/운영상의 정보가 미반영(특정 정류장에 1명이 빠지거나, 요금제 오적용)시, 어떻게 대응할 것인가? 해당 특이점은 상기 모니터링용 페이지에서만 확인 가능
> - 상기 페이지의 Dao부분 쿼리를 뜯어보거나
> - A B C D E F를 각각 조회해서 어디가 빠졌는지 확인
    > 현업에서는 놀랍게 둘 다 실행한다. 
    > 생각해보면, 테이블간의 연결도를 낮추기 위해 FK를 줄이고, 배치 로직에 정산 추출을 태우며 이 문제는 덮인듯 보인다 (모니터링은 편해지고, 시스템이 뻗는 일은 줄어드니)
    ex)   A Table 실시간 집계 => 대사 /정산 수행 => B C D 참조하는 레포트 생성 등.
> 마스터 테이블과 앞선 모든 기술 문서를 살펴보고 변경할 시간적 여유따위없다. A~F가 이상하게 나와요 한마디면 검토해야지 뭐.
> 

###### 전개
> 문제는, 이후 si-sm 투트랙 운영에 따라 고도화가 된다
1. 초기 개발자가 초기 니즈에 따른 table 및 화면 설계 (굳이 table 변경을? select만 합시다 라던지)
2. 이 정보 수정하려면 어떡해요 하는걸 문서로만 남김
3. A 테이블을 참조하는 a 데이터가 잘못 나오는데요 까지는 개선
4. 상기 A~F까지 연계되는 데이터도, 어찌저찌 a데이터 밀어넣고 c밀어넣고 하며 개선
5. 어느날 생각치 못한 덜바뀌었어요 등장
   - A의 a와 C의 c4 값이 유사한데(심지어 필드명도 비슷)이걸 맞춰줘야하나? 하는 내적갈등
   - 에라 모르겠다 하고 C에 c5추가 (이게 정석)
6. 누적되다가 어느날 si재투입 후 차세대 시스템 제작
   - C의 c4랑 c5 차이가 뭔가요? 하면 4년전의 시스템과 8년전에 만든 시스템의 차이까지 대답이 나오는 것도 신기

> 그리고 문제가 차세대 이후에도, create나 table 변경(column추가 등)에는 항상 취약해진다.
1. 빈필드가 있는데 써도 될까요? => 갑자기 수백만건 로그쌓인 DB에 일괄 1 마이그레이션을 검토중
    - 말린 사람은 대응책 들고와야해서 다 입다물고 있음
    - 테이블 분리해서 연결하는건 리스크도 크고 DBA불러와야한다고 취소
2. 칼럼 추가하는데 어떻갈까요? => 과거데이터 모두 1로 커밋
    - 작업은 6개월차 사원이 하다가 엎어져서 다같이 리뷰


##### 결론
> 왜 JPA인가
- 상기 취약은 사실 RDB 자체의 한계.
- 좀 더 상위 계층에서 추상화 필요함 => Entity의 영속성 개념은 생각보다 강력하다.
  - C의 c5추가를 entity를 두고 vo에서 한다던지 하면, DB가지고 씨름할 일이 있었을까.
  - C의 c4와 A의 a를 일치시켜야 하는지 검증을, java 내에서 처리하면 얼마나 간편할지(틀려도 원복이라던지.)
> 시스템의 설계시점 컨셉이 유지되는건, 기껏해야 2년이라고 본다.
- 네이버 다음만해도 2년 내 얼마나 많은 ui나 작은 기능들이 추가되는가.
- 네이버 플레이스를 봐라. 다음맵은.
> 그 이후에는 그런 사업을 도저히 하지 않을 줄 알았는데.. 싶은 아이템들이 갑자기 양지로 툭툭 던져진다.
- 물론 결론은 기존 시스템에서 대응해주세요다. 만능 키인가.
- 버스 정산에서 K지역 확장 대응해주세요 (지역정산 시스템이 두갠데요? 몰라 그런거.)
- 플레이스에서 예약 핸들링 해주세요.
- 예약에서 이번에 스타벅스 연계건 처리해주세요.
- 결론은 JPA다.
- DB와 biz개발을 가능한 분리하는 것은, 개발측에서 유연성 증대. 

