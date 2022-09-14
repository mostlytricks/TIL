### Docker 사용 기록
#### docker run -p 에서 port num 의미?

- [참고 : 월급쟁이의 경제적 자유](https://plusblog.co.kr/139)
```bash
docker run-it -p 5005:5005 centos 
# 호스트 운영체제의 포트와 컨테이너의 포트 맵핑

docker run -it -p 8088:80 webserver
# 호스트 8088 -> 컨테이너의 80

```

```Dockerfile
// Dockerfile의 expose 명시
EXPOSE 5005 
// 해당 도커 이미지는 5005포트를 공개예정으로 명시
// 그러나 실제 동작은 docker run -p 옵션이 필요함.
// docker run -P 사용 시, 호스트 운영체제의 임의의 포트가 expose구문에 명시된 포트로 맵핑됨.
// docker run -p // docker run -P는 상이함. 
```
