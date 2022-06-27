# 개요
---
## ToC

# 데이터베이스 관련 <a name = "db">
  
### 트랜잭션의 기본 속성, ACID
  
- 원자성(atomicity)
- 일관성(consistency)
- 격리성(isolation) 
- 지속성(durability)
  
> 트랜잭션이 반드시 ACID속성을 가질 필요는 없다. (데이터 중심 애플리케이션 설계, 93p)
  

### 트랜잭션 처리와 분석 시스템의 비교 (OLTP vs OLAP)
  
> OLTP : Online transaction processing => 질의당 적은 수의 레코드, 키 기준 데이터 조회, 웹 앱을 위한 데이터의 최신 상태 조회
  
> OLAP : Online Analytic processing => 많은 레코드의 집계(sum,count,etc), 대규모의 조회와 이벤트 스트림, 이벤트 이력의 시집. 페타바이트규모.
  
* OLTP를 분석 목적으로 사용하며, 데이터 웨어하우스가 대두됨. 
  
### 데이터 웨어하우징

- OLTP는 운영관점의 시스템. 여기에 대규모 분석 질의를 날리면? 뻗는다.
- 이를 피하기 위해 OLTP에 영향주지 않고 자유롭게 조회 가능한 dB구축. 
- **ETL** : OLTP => 데이터 웨어하우스. 데이터를 추출(Extract)해서 분석 편한 스키마로 변환(Transform)한 뒤 데이터 웨어하우스에 적재(Load)
- 이로 인해 데이터 웨어하우스는 여러 유형의 데이터를 담게 된다. (상거래 판매 데이터, 재고관리 상품 데이터, 운송 플랜을 위한 지리 정보 등) 
  
### 별 모양 스키마 in 데이터 웨어하우스
  
  
``` javascript
fact_sales : date_key, product_sk(dim_product), store_sk(dim_store), promotion_sk(dim_promotion) ...
  
dim_product : prodcut_sk, sku, description, brand, category, ...
  
```
- 모든걸 집계하는 facet table에서, 각각 정보를 참조하는 키만 끌고 온다. 
- 유연성은 극대화 되지만, fact table의 크기가 커진다
- 누가, 언제, 어디서, 무엇을, 어떻게, 왜 정보를 담을 수 있는 차원테이블 구성.
  
- 여기서 dim_product도 더 잘게 쪼개서 참조하면 **눈꽃송이 스키마**라 부른다! => 보다 정규화 되었지만, 분석가가 분석하기에는 좀 더 피로(join하고,,테이블 정의 다 찾아보고..)
  
  
  
### 데이터 부호화
  
- 직렬화(=부호화,마샬링) : 인메모리 표현에서 바이트열로 전환 
  - 대표주자 : JSON, XML, CSV
- 복호화(=역직렬화, 파싱, 언마샬링) 바이트열 표현에서 인메모리 표현
  
Q. 메모리 표현은 대체?
=> 객체, 구조체, 목록, 배열, 해시테이블, 트리 등의 저장 형태
=> 데이터를 파일에 쓰거나, 네트워크 전송을 위해 직렬화함. (json 등)


### REST vs SOAP

- REST : Http의 원칙을 토대로한 설계 철학. url을 이용한 리소스 식별.
- SOAP : Web Services Description Langauge(WSDL)이라 불리는 xml기반 언어를 이용한 api설계
  => 클라이언트가 로컬 클래스, 메서드 호출을 사용해 원격 서비스에 접근 가능.
  - 밴더간의 통합 어려워, ide및 밴더에 강하게 의존함
  - tibco같은 애들 말하는듯.
  
### 메시지 브로커
- 과거에는 TIBCO, IBM Websphere, webMethods과 같은 상용 기업 소프트웨어가 갓에
- 최근에는 RabbitMQ, ActiveMQ, HornetQ, Apache Kafka 와 같은 오픈 소프트웨어 구현이 대세
  (!)
- 통상의 기능
  - 프로세스 1 > 메세지 > 큐/토픽에 전송
  - 브로커 > 해당 큐/토픽 > 구독자 or 소비자에 전달.
  
  
  

# 기타 개념 <a name = "others">
  
### Domain Driven Design
  
  

