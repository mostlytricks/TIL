# springboot와 aws로 혼자 구현하는 웹서비스 

### 개요
- 책 스터디하며 주요한 예시에 대한 코멘트 기록, 추후 설계시 다시 반영할 수 있도록 정리


### Spring의 Bean 주입 기법
@Autowired, @Setter, 생성자
- 가장 좋은 방법 : 생성자로 주입
  - lombok의 @RequiredArgsConstructor 사용 + private final ~ 사용 시, 생성자를 통한 의존성 주입
  - 
- 비권장 : @Autowired
- 


### Biz는 서비스가 아닌 도메인에 구성하라.

```java
@Transactoinal // transaction 보장
public Order cancelOrder(init OrderId){
  // 1) Order에 대한 데이터 조회 영역
  OrdersDto order = orderDao.selectOrders(orderId);
  BillingDto billing = billingDao.selectBilling(orderId);
  DeliveryDto delivery = deliveryDao.selectDelivery(orderId);
  
  // 2) 배송 취소 여부 확인
  String deliveryStatus = delivery.getStatus();
  
  // 3) 배송 취소시의 로직
  if ("IN_PROGRESS".equals(deliveryStatus)){
    delivery.setStatus("CANCEL");
    deliveryDao.update(delivery);
  }

  // 4) 테이블별 '취소' 관련 상태 업데이트
  order.setStatus("CANCEL");
  ordersDao.update(order);
  
  billing.setStatus("CANCEL");
  deliveryDao.update(billing);
  
  return order;
}
```
- 저자의 조언 : 이렇게 하면 정작 service가 데이터 호출, 도메인간 순서 보장만 하는게 아닌, 전체로직을 다 담당하게됨.
- 객체가 사실 무의미해지고, 데이터 덩어리를 호출해서 전체 처리 로직을 이곳에서 담당.

```java
@Transactional
public Order cancelOrder(int orderId){
  // 1) 데이터호출. Repository를 이용해서 pk기반 탐색. 큰 차이 없지 않나?
  Orders order = ordersRespository.findById(orderId);
  Billing billing = billingRepository.findByOrderId(orderId);
  Delivery delivery = deliveryRepository.findByOrderId(orderId);
  
  // 2~3) 취소 여부 확인과 취소 처리 > delivery 객체에 위임
  delivery.cancel()
  
  // 4) 테이블별 취소 상태 업데이트 > 객체별로 구성된 취소시의 로직에 위임.
  order.cancel();
  billing.cancel();
  
  return order;
}
```
- 결론: 객체는 데이터 호출해서 담기만 하면되는가? 아니다. 각각 중요한 처리로직을 들고 있어야된다.
- 취소처리{order, billing, delivery를 각각 처리하시오} 가 아닌, 취소 행위 {order.취소, billing.취소, delivery.취소} 와 같이 설계되어야 수정에 용이하다.


### 도메인 모델을 이용한 controller - service - dto 예시
```java
@RequiredArgsConstructor
@RestController
public class PostsApiController {
  
  private final PostsService postsService;
  
  @PostMapping("/api/v1/posts")
  public Long save(@RequestBody PostsSaveRequestDto requestDto){
    return postsService.save(requestDto);
  }
}
```
- 의존성 주입 방법 유의. 생성자 주입을통해사용한다. 
- api 요청 중 post요청에 대해, save를 실행하는것에 끝나지 않고 return 하고 있다. 응답 코드가 필요하다.
