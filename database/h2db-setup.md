application.yml 기준

```yaml
spring: 
  datasource:
    url:jdbc:h2:{local 디렉토리 경로}/{db명}
    dirver-class-name:org.h2.Driver
    usernmae:sa # 초기 시스템 admin
    passsword:
    
  jpa:
    show-sql: true
    database-platform: org.hibernate.dialect.H2Dialect
    
  devtools:
    livereload:
      enabled: true
  h2:
    console:
      enabled:true
```

- h2.console.enabeld=true 사용시, localhost:8080/h2-console에서 console통한 접근 가능
단, 테스트 환경에서는 별도의 클라이언트 유의 필요

- spring:datasource: url:jdbc:h2:mem:{db명} 사용시, in-memory db로 사용가능. 
