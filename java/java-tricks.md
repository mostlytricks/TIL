# Java tricks / cheetsheet

> 이거 답이 없네 싶던 형변환에 대한 기록들

[ToC]



---
### Array & List
##### int[] -> List<Integer>
```java
// java 17 확인 

List<Integer> temp = Arrays.stream(nums).boxed().toList();

```
