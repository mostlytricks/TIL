# 46. Permutations
---
### Memo
##### 문제
- [leetcode - 46](https://leetcode.com/problems/permutations/)
##### 구상
- DFS가 접근하기 편하다고 생각
- visited 배열, 방문 안했으면 체크하고 깊이 탐색
- temp 그릇으로 모았다가 1단위 완성시 정답지 기록

##### Q.
- 1) 임시 저장 / 정답지의 이원화로 인해 메모리 낭비 심함
- 2) 재귀탐색에서 visited의 stream 변환 -> avg -> getasdouble -> summation등 불필요한 조건 탐색 많음.
  - 이걸로 인해 지연하는 것으로 생각됨.  

- 의의: 그래도 예전에 비하면 코드도 식별하기 편하고 빨리 작성한듯..? dfs 예제로는 나쁘지 않았다
- 단순 dfs구현 보다는 조건 단순화 및 swap등 좀 더 간결한 로직 필요
---
### Result
```java
class Solution {
    int[] visited;
    List<List<Integer>> answer = new ArrayList<>();
    List<Integer> temp = new ArrayList<>();
    
    public List<List<Integer>> permute(int[] nums) {    
        this.visited = new int[nums.length];
        permutation(nums);
        return answer;
    }
    public void permutation (int[] nums){
        //if (Arrays.stream(visited).average().getAsDouble() == 1) { // 1차 개선 필요지점. 배열이 다찼는지 판단하는 간단한 기법?
        if (sumVisited() == nums.length){
          answer.add(new ArrayList<>(temp));
        } 
        for (int i = 0; i < nums.length; i ++) {
            if (visited[i] == 0 ) {
                visited[i] =1;
                temp.add(nums[i]);
                permutation(nums);
                visited[i]=0;
                temp.remove(temp.size()-1);
            }
        }
    }
    // 1차 개선 적용 후
    public int sumVisted() {
        int sum = 0;
        for (int k : this.visited){
          sum+=k;
        }
        return k;
    }
}    
```
- 1차 개선 내용
  - 매 permutation호출 마다 조회되는 배열 산출여부, 함수 단순화
  - 추가적으로 num.length를 함소 최초 호출 시 1회 실행하면 보다 간소화될 것으로 보임
  - runtime 2ms, better than 71%
  - memory 44.7MB, 추가 개선 
