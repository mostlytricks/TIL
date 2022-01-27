# 9장 단위 테스트 Unit Test
---

### 기억해둘 내용
- TDD의 진짜 법칙 세가지
  - 1) 실패하는 단위 테스트 작성 전까지 실제 코드를 작성하지 않는다.
  - 2) 컴파일은 실패하지 않으면서 실행이 실패하는 정도로만 단위 테스트를 작성한다.
    - ??
  - 3) 현재 실패하는 테스트를 통과할 정도로만 실제 코드를 작성한다.

- 깨끗한 테스트 코드 유지하기
- Build-Operate-Check pattern
  - [인용 출처](http://butunclebob.com/FitNesse.AcceptanceTestPatterns) : [블로그 이사](http://blog.cleancoder.com/uncle-bob/2016/10/26/DijkstrasAlg.html)
  - 테스트 자료 생성
  - 테스트 자료 조작(Operate)
  - 결과 확인
  - 예제 확인
```Java
public void testGetPageHierarchyAsXml() throws Exception {
  makePages("PageOne", "PageOne.ChildOne", "PageTwo"); // 자료 생성
  
  submitRequest("root", "type:pages"); // 수행

  assertResponseIsXML(); // 검증
  assertResponseContains(
    "<name>PageOne</name>", "<name>PageTwo</name>", "<name>ChildOne</name>"
  );
}

public void testSymbolicLinksAreNotInXmlPageHierarchy() throws Exception {
  WikiPage page = makePage("PageOne");
  makePage("PageOne.ChildOne","PageTwo");

  addLinkTo(page, "PageTwo", "SymPage");
  
  submitRequest("root", "type:pages");

  assertResponseIsXML();
  assertResponseContains(
    "<name>PageOne</name>", "<name>PageTwo</name>", "<name>ChildOne</name>"
  );
  assertResponseDoesNotContain("SymPage");
}

public void testGetDataAsXml() throws Exception {
  makePageWithContent("TestPageOne", "test page");

  submitRequest("TestPageOne", "type:data")

  assertResponseIsXML();
  assertResponseContains("test page", "<TEST")
}
```
- Test환경에 대한 고민
  - String Buffer을 쓸 경우 => 메모리 고려, MultiThread환경
  - 그러나 테스트 환경의 경우, 메모리 고려할 필요 적을 것
  - String +로 구성해도.. 크게 문제 없다!
```java
public String getState() {
  String state = "";
  state += heater ? "H" : "h"; 
  state += heater ? "B" : "b"; 
  ...
  // "Hbchl" 등, 결과 문자 리턴 => 직관성, 인코딩 축소와는 충돌하지만 test case 내 직관성 유지
  return state;
}
```
  - 단일 assert문. 


- F.I.R.S.T : test의 다섯 규칙
  - Fast 
    - 빨라야 자주 돌리게 된다. 
    - *느리면 사실 tomcat 내리고 올리고 내리고 올리고..
  - Independent 
    - 테스트 간 의존성 x, 독립적이고 순서 무관해야.
  - Repeatable
    - 반복 가능해야함
    - 실제 / QA / 네트워크 불안 등 상황별 예외가 아닌 모든 환경에 동일 수행 동일 결과
  - Timely
    - 적시.
    - 단위 테스트는 실제 구현 직전에 구현해야.

- DSL : Domain Specific Langauge를 만드는 관점으로 접근해야, 테스트 코드에 대한 이해도 높아짐.
  - Test API 구성 => DSL.
  - 
