# spring boot 설치 시 주요 initial setup 관리 중

### TOC
---

<br/><br/>
### 최초 구동 시 DB 연결 경고
---

- h2 통한 내장 db 연결
- mongoDB등 기타 db 연결
- annotation 이용한 db 연결 미사용 설정

1. application.properties 에 db 연결 정보 추가

- 1) h2 db사용 
  - spring 사용 시 기본 지원하는 메모리 내장 db
  - 간단한 테스트 가능
  - h2 console은 웹브라우저 내 주소 입력하여 접근 가능
```
spring.h2.console.enabled = true
spring.h2.datasource.url = jdbc:h2:mem:testdb 
# test db부분에는 db명칭 입력 필요, 초기값은 재확인해서 기록 갱신 할 것
spring.h2.datasource.driverClassName = org.h2.Driver
```

2. spring 내 쿼리 실행 결과 출력
```
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.PostgreSQL9Dialect
#마지막 언어 부분은 원하는 쿼리 / 시스템 내 지원되는 양식 선택
spring.jpa.show_sql= true
```


3. 기타 DB 연결

```java
//MongoConfig.java
@Configuration
public class MongoConfig{
  @Bean
  public MongoClinet mongoClient() {
    return MongoClients.create();
  }
  @Bean
  public MongoTemplate mongoTemplate(){
    return new MongoTemplate(mongoClient(), databaseName: "_my_data_base")
  }
}
```

- application.properties에 작성 필요
```
spring.data.mongodb.authentication-database=admin
spring.data.mongodb.username=<usename>
spring.data.mongodb.password=<password>
spring.data.mongodb.database=<database-name>
spring.data.mongodb.port=27017 #default
spring.data.mongodb.host=localhost #or ip addr
```

> 상기 application.properties와 동등하게, MongoConfig.java파일로 관리가능
```java
// configs/MongoConfig.java
@Configuration
public Class MongoConfig{
  @Bean
   public MongoClient mongoClient(){
    return MongoClient.create();
   }
  @Bean
  public MongoTemplate mongoTemplate(){
    return new MongoTempalte(mongoClient(), "database-name";
  }
}
- application.properties와 MongoTemplate활용한 config 둘다 사용시, MongoTemplate이 우선 적용됨 확인.

```


<br/>
<br/>

### 초기 연결 시 Controller가 동작하지 않는다면 (GetMapping등 annotation이 동작 않는경우)
---
[참고](https://cceeun.tistory.com/183)
- application(spring application구문)하위의 패키지에 controller위치여부
- controller 별로 @Controller annotation있는지 확인 아니면 bean등록 안되기도.올바

