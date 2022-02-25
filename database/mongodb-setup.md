# MongoDB 셋업


### 계정 권한 설정

- shell > mongo > mongo terminal or ***Mongo Compass*** >_MONGOSH
```shell
# admin database
use admin 

# 생성 및 부여
db.createUser({
  user:<사용자명>, 
  pwd:<비밀번호>,
  roles:[
    { 
      "role" : "userAdminAnyDatabase",
      "db":"admin"
    },{
      "role" : "dbAdminAnyDatabase",
      "db":"admin"
    },{
      "role" : "readWriteAnyDatabase",
      "db":"admin"
    }
  ]
})

# 확인
db.auth(<사용자명>,<비밀번호>)
db.getUsers() 

## 계정 조회 및 auth > ok:1 확인시 문제 없음.
```
