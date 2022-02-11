# 49. Rotate Image
---
### Memo
##### 문제
- [leetcode - 48](https://leetcode.com/problems/rotate-image/)
##### 구상
- 3*3 행렬 기반으로 생각해서 알고리즘 산출
```
1 2 3          
4 5 6  ->  ... 
7 8 9     
```
- 다시보니 행렬의 대칭, 회전, 전환 문제였다
- 1) -45도 대칭(x,y)->(-y,-x)  => 2) y축 대칭 (-y,-x) -> (y,-x)

##### Q.
- Runtime 0 ms가 나왔다. 뭐지?
- Memory는 42.3MB로 최적화 여지가 남아있다

### Result
```java
class Solution {
    public void rotate(int[][] matrix) {
        int ml = matrix[0].length;
        int[][] temp= new int[ml][ml];
        for (int i =0 ; i < ml ; i++){
            temp[i] = matrix[i].clone(); 
        }
        for (int i =0; i < ml; i++){
            for (int j=0; j< ml; j++){
                matrix[i][j]= temp[ml-1-j][i];
            }
        }
    }
}
```
- 2차원 배열을 단순 clone() 했다가 값이 덮어 씌여서 의아해했다.
- 반복문으로 타서 각각 clone해주는게 일단 최선아닐까 싶은데 메모리를 줄일 여지가 있네.
- 메모리 효율 높이려면 메모리 내에 쓰인 값을 직접 shifting하는게 아닐까...?
