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

