# 단위 테스트 시 적용 예

- intelliJ에서 유닛테스트를 틈틈히 하는중
- 어떻게 적용했는지 기록
- spring boot 에서 특정  mocking등 이후 추가할것

---
### 단위 테스트
- junit
- junit.TestCase
- assertj 등

```java
package ... ;

import junit.framework.TestCase;
import org.junit.Test

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;  // assertJ

/*
* Leet Code의 특정 문제를 테스트 케이스 작성하여 유사하게 돌리는 경우
*/
public class ValidSudoku extends TestCase { // junit -> assertTrue 등 이용 
  
  public boolean isValidSudoku(char[][] board) { // 대상 method
    return true;
  }
  
  @Test
  public void testIsValidSudoku() { // assertj에서 탐색하기 위해, 접두어 test 유의
    assertThat( actual : true ).isTrue(); // assertj -> assertThat
    assertTrue( condition : true); // junit -> assertTrue
  }
}

```


---
### Mocking


---
### Springboot/Spring

