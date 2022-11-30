### zookeeper 실행 시
- 그냥 실행해라. 자체 테스트 구축시에는 별거없다.
- zkServer.cmd //별도 설치


### kafka broker 실행 시
- kafka-server-start.bat or .sh 실행한다.
- config/server.properties 전달 유의.

- port #등 기본값 대로 


### consumer / producer
```
bin/kafka-console.producer.sh --bootstrap-server localhost:90092 --topic {name}

```
-상호작용 가능한 cli를 띄운다.

```
bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --topic quickstart --partitions 1

```
- 토픽의 partitions 수를 지정해서 생성.


```
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic {name} --from-beginning
```
- 처음부터 읽어들이기 + cli


### kafka management tools
- 토픽 생성, 데이터 보는 등 cli로는 제약이 있으니, 별도의 툴을 통해 모니터링 한다
- akhq, kafkamanager(mig.)


akhq -> 실행시
1) jar 설정
2) application.yml 

```
akhq:
  connections: 
    local:
      properties:
        bootstrap.servers: "localhost:9092"
        
micronaut:
  server:
    port: 8081
```
- 8080이 기본값이다.
- akhq~: 브로커를 명시해줘야한다.
