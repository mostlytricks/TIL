# Java tricks / cheetsheet

> 이거 답이 없네 싶던 형변환에 대한 기록들

[ToC]



---
### Array & List
##### int[] -> List<Integer>
```java
// java 17 확인 
List<Integer> temp = Arrays.stream(nums).boxed().toList();

// java 8 확인
Integer[] ints = new Integer[] {1,2,3,4,5};
List<Integer> list = Arrays.asList(ints);    
```
- primitives의 List<int> 같은게 지원이 안되어서, List전환이 이상했었다. `int holyCow[] ={1,2,3,4,5}` 같은건 Arrays.asList 안먹음.
- String[] strs={"1","2","3"}은 잘 바뀐다.
- Integer[] ints = new Integer[]{1,2,3,4,5} 도 잘바뀐다.  
    

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

    
--- 
### Data Structure / enum / class

##### enum
```java
enum Omega{
    RED(3), BETA(4),GAMMA(5),
    
    private final int value; // 고정된 상수값을 만든다. 여기서 지정한 타입으로 value 설정 가능
    Omega(int value){this.value = value;}
    public int getValue(){return value;}
}
//사용
    
Omega.values(); // 전체 배열, 내부 값이 아닌 앞의RED와 같은 const를 불러온다.
Omega.valueOf("BETA") //일치하는 애를 불러온다.
```
    
    
