
# 13장 동시성 Concurrency
---

### 기억해둘 내용
- 동시성과 깔끔한 코드는 양립이 어려움 유의할 것
- **동시성 방어 원칙**
  - 단일 책임 원칙 : Single Responsibility Principle, SRP
    - **동시성 관련 코드는 분리**해서 구현할 것!
    - 동시성 코드는 독자적인 개발, 변경, 조율 주기 필요
    - 독자적인 난관 유의
    - 실패 사례를 예측하기 어려움
  - 따름 정리(corollary,계) : 자료 범위 제한
    - 자료 범위를 제한
    - 공유 객체를 사용하는 코드는 임계영역(critical section)을 synchronized 키워드로 보호 권장
    - ex) heap memory에 공통으로 접근하는 코드라던지.. (앞서 getList의 예제도 본 적 있음)
  - 따름 정리 : 자료 사본 사용
    - 특정 자료는 복사 => 읽기 전용으로 사용하는 것도 좋음.
    - 객체 복사의 시간과 부하 (+ Garbage Collection) vs 동기화에 따른 내부 deadlock 제거 
  - 따름 정리 : 쓰레드는 가능한 독립적으로 구현
- 안전한 Java library이용
  - thread-safe한 collection을 이용하자! 
    - ConcurrentHashMap
    - SynchronizedHashMap
    - Vector, HashTable, CopyOnWriteArrayList, CopyOnWriteArraySet, Stack
    - or `Collections.synchronizedCollection(Collection<T> c`)
    - or `java.util.concurrent`
      - ReentrantLock : Lock
      - Semaphore : Count 있는 lock, semaphore
      - CountDownLatch : 지정한 수 만큼 이벤트 발생한 뒤 대기 중인 스레드 모두 해제, 동시 경합

- 동시성 관련 용어 정리
  - Bound Resource
  - Mutual Exclusion
  - Starvation
  - DeadLock
  - Livelock : 락을 거는 단계에서 각 스레드가 서로를 방해. 서로 장기간 진행하지 못함.
- 주요 실행모델
  - Prodcuer-Consumer
    - Buffer 또는 Queue 에 Producer가 자원 사용 정보를 넣고, 이를 Consumer가 구독하며 하나씩 가져감.
    - 서로 정보를 보내는 절차가 있어, 동시에 시그널을 대기하는 경우도 발생 가능.
  - Readers-Writers
    - Reader가 주로 사용, Wirter가 간헐적으로 정보 갱신
    - **Throughput** 관리가 중요함!
    - 예상 답안
      - 읽을만치 읽고, reader가 없을때 쓰기 전략 -> writer의 starving 유의
  - Dining Philosophers
    - 데드락 설명할 때의 단골 문제. 젓가락 말고 포카락을 썼다면 해결되었을 것.
  
- 공유 객체 하나에는 메서드 하나만 이용할 것!
  - synchronized는 개별 메서드에 대한 보호
  - 공유 클래스 하나에 동기화된 메서드가 여러건일경우, 추적하기 어려워짐.
- more syncrhonized => more lock 
  - 임계영역만은 반드시 보호하도록 synchronized 축소
  - 그러나 임계영역이 커지면, 스레드간에 경쟁 늘어나니 적정선에서 끊어야함.

- 깔끔한 종료코드는 정말 어렵다
  - Dead lock 

- 스레드 코드의 테스트
  - 문제 노출하는 케이스 + 다양한 프로그램 설정.
  - 다시 구동했더니 잘돌아간다는 치명적인 문제 잠재
  - 말이 안되는 실패는 스레드 문제로 취급해라.
    - cosmic ray문제로 취급하기에는...
  - 프로세서 수보다 많은 스레드 구동해봐라.
    - 스레드 스와핑에서도 문제 발생 가능.
  - 다양한 플랫폼에서 돌려라
    - 저자도 OS X와 Windows XP에서 예외케이스가 다르게 발생함.
