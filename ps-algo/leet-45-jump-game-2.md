# 45. Jump Game II
---
##### 문제
- [leetcode - 45](https://leetcode.com/problems/jump-game-ii/)

##### 구상
- 방문 가능한지 관리하는 배열 하나 두고.
- 0000 과 같이 종점 도달 불가능사례가 없다고 주어졌기 때문에, 최대 n-1까지만 탐색할 것
-  [0]과 같은 특이케이스 조심할 것

### Result 1

```java
class Solution {
    public int jump(int[] nums) {
        int[] leastNums= new int[nums.length];
        int[] visitable = new int[nums.length];
        int[] temp = new int[nums.length];
        visitable[0]=1;
        int cnt=0;
        int limit=0;
        for (int i= 0 ; i < nums.length; i ++){
            temp = visitable.clone();
            if (visitable[nums.length-1] == 1){
                return cnt;
            }
            for ( int j = 0; j< nums.length; j++){
                 // 현재 인덱스 + nums[idx] 인 애들 큐에 추가
                limit = Math.min(nums[j]+j+1, nums.length);
                if (visitable[j] ==1 ){
                    for (int k =j; k< limit; k ++){
                        temp[k]=1;
                    }
                }
            }
            visitable= temp.clone();
            cnt+=1;
        }
        return cnt;
        
    }
}
```
- 310ms -> 8.83% java보다 낫다
- 118.1md -> 5.15%java 보다 낫다.
- 배열 카피에서 불필요한 자원소모가 많은것같다. 최적화 고민좀해봐야.

### Review
- 문제 자체는 점화식 /DP 문제였네.

```java
//타인 코드
public int jump(int[] nums) {
	int n = nums.length;
	int[] ans = new int[n];
	// initialize the array with infinity
	Arrays.fill(ans,Integer.MAX_VALUE-1);
	ans[n-1] = 0; // opt(n) = 0 - initialization
	for (int i = n-2; i >= 0;i--) // simply iterate over the array and set the minimum at every index
		for (int jump = 1; jump <= nums[i] && i + jump < n; jump++)
			ans[i] = Math.min(ans[i], ans[i+jump]+1);
			
	return ans[0]; // return the first element of the array - opt(1)
}
```