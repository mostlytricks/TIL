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

##### rotate
```java
public class RotateArray {
    int[][] test = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };

    public RotateArray() {
        int[][] ta = this.test;
        int[][] tt = new int[ta.length][ta[0].length];
        for (int i = 0; i < ta.length; i++) {
            for (int j = 0; j < ta[i].length; j++) {
                tt[j][3 - i] = ta[i][j];
            }
        }
        for (int i = 0; i < ta.length; i++) {
            for (int j = 0; j < ta[i].length; j++) {
                System.out.printf("%d ", tt[i][j]);
            }
            System.out.println();
        }
    }

}
```

- 기능 확인은 덜했지만.. 자바의 객체 값 copy때문에 주의할 지점이 있던 것으로 기억. 