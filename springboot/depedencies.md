# 사용하는 Dependencies 해설
---
> Spring boot기준

> 기존 문서화가 부족해 하나씩검색하던것에서, 용도 및 활용 방안 재정리 목적

#### spring boot devtools
```xml
org.springframework.boot:spring-boot-devtools 
<!-- 소스 변경 시 감지하여 자동 재실행 > intelliJ의 경우 추가 설정 필요-->
```
- [intelliJ-추가설정](https://barbera.tistory.com/47)


#### spring data jpa
```xml
org.springframework.boot:spring-boot-starter-data-jpa
```
#### spring test
```xml
org.springframework.boot:spring-boot-starter-test
```

#### spring web
```xml
org.springframework.boot:spring-boot-starter-web

```
#### spring security
```
```

#### lombok
```xml
org.projectlombok:lombok
```

---
### DB관련

#### mysql driver
```xml

```

#### oracle driver
```xml
com.oracle:ojdbc7
<!-- 필요한 드라이버 명칭 재확인할것. -->
```

### mybatis
```xml
org.mybatis:mybatis
```

### hibernate
```xml
org.hibernate:hibernate-core
```

