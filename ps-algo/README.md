# 주요 알고리즘 유의할 것
- DFS&BFS
- sorting
- 간선탐색


### DFS or BFS
- ? => a,b,c
- 이후 a간에 묶여있는지 (grid간 인접(network여부))탐색
- 이중 dfs 구현
  

### List, Array, Sort

- 행렬의 소팅 후 인덱스 추출
<details>
  <summary>t1</summary>

```java
// java 17 ++, if use 16 : intstream to array gonna be problem

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/*
* input = [[0,5,2,4,1],[5,0,3,9,6],[2,3,0,6,3],[4,9,6,0,3],[1,6,3,3,0]]
* output =[[1,2,0,4,3],[3,4,0,2,1]]
*
* */
public class howCanIDoDFS {


    @Test
    public void alphtToOmega (){
        System.out.println(11);
        int[][]dist = {{0,5,2,4,1},{5,0,3,9,6},{2,3,0,6,3},{4,9,6,0,3}, {1,6,3,3,0}};
        System.out.println(dist[0]);
        solution(dist);

    }

    int maxVal= 1;

    public int[][] solution(int[][] dist){

        List minMax = new ArrayList();
        minMax = findMaxDist(dist);

        int[] first = calculateAns(dist.length, (int) minMax.get(0), dist);
        int[] second =calculateAns(dist.length, (int) minMax.get(1), dist);
        int[][] answer = {first, second};

        return answer;
    }


    public List<Integer> findMaxDist(int[][] dist){
        List<Integer> maxPairIdx = new ArrayList();
        for (int[] vessel : dist){
            for (int val : vessel){
//                System.out.println(val);
                if (val>=maxVal){
                    maxVal= val;
                }
            }
        }
        for (int i = 0 ; i < dist.length ; i++){
            if(Arrays.stream(dist[i]).boxed().collect(Collectors.toList()).contains(
                    maxVal
            )){
                maxPairIdx.add(i);
            }
        }
        return maxPairIdx;
    };
    public int[] calculateAns(int length, int idx, int[][] dist){
        int[] shortest =new int[length];
        int[] sorted = Arrays.stream(dist[idx]).sorted().toArray();
        int temp =0;
        for (int i=0; i< length; i ++){
            temp = Arrays.stream(dist[idx]).boxed().toList().indexOf(sorted[i]);
            shortest[i]= temp;
            System.out.println(temp);
        }

        return shortest;
    }


}

```
</details>



