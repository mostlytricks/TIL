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
- 2) swap 필요
##### Q.
- Runtime 0 ms가 나왔다. 뭐지?
- Memory는 42.3MB로 최적화 여지가 남아있다

### Result
- 문제 잘못 보고 작성한 코드
```java
class Solution { //배열 카피하지 마라했는데 문제 안본 경우.
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

- fixed. swap이 되나 + swap 횟수 때문에 에둘러갔다.
```java
class Solution {
    public void rotate(int[][] matrix) {
        int ml = matrix[0].length;
        int temp =0;
        for (int i =0; i < ml; i++){
            for (int j=i+1; j< ml; j++){
                temp = matrix[i][j];
                matrix[i][j]=matrix[j][i];
                matrix[j][i]= temp;
            }
        }
        for (int i =0; i < ml; i++){
            for (int j=0; j< ml/2; j++){
                temp = matrix[i][j];
                matrix[i][j]=matrix[i][ml-1-j];
                matrix[i][ml-1-j]= temp;
            }
        }
    }
}
```
- 배열임을 이용해 swap 실행
- 단, `n*n` 횟수만큼 swap할경우 원상 복귀되거나(y=x) 추가 변형(y=0), 특정 행을 선별적으로 스왑했다.
    - y=x 대칭은 직선 위 대상으로만 스왑
    - y축 대칭은 절반만 실행.
- 정리하고 보니 서술에서 축 재확인 필요. matrix[0][1] => (1,0)인데..흠.
