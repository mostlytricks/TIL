# DFS


```python
def dfs_search(G, src):
    marked = {}
    node_from = {}

    stack = Stack()
    marked[src] = True
    stack.push(src)

    while not stack.is_empty():
        v= stack.pop()
        for w in G[v]:
            if not w in marked :
                node_from[w] = v
                marked[w] = True
                stack.push(w)

    return node_from
```
Graph, src에서 탐색 시작
node_from은 탐색의 순서를 기억하는 그래프, 각 노드가 어디서 출발했는지 기억함 유의.

```python
def bfs_search(G, src):
    marked ={}
    node_from ={}
    q = Queue()
    marked[src] = True
    q.enqueue(src)
    while not q.is_empty():
        v = q.dequeue()
        for w in G[v]:
            if not w in marked :
                node_from[w] =v
                marked[w] = True
                q.enqueue(w)

    return node_from
```

### 다익스트라
- [출처](https://velog.io/@tks7205/%EB%8B%A4%EC%9D%B5%EC%8A%A4%ED%8A%B8%EB%9D%BC-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-with-python)
```python
# n, m = map(int, input().split())
# k = int(input())                
INF = 1e8 # 무한 설정

graph = [[] for _ in range(n+1)] 

visited = [False] * (n+1)
distance = [INF] * (n+1)

for _ in range(m):
  u, v, w = map(int, input().split())  
  graph[u].append((v, w))             

def get_smallest_node():
  min_val = INF
  index = 0
  for i in range(1, n+1):
    if distance[i] < min_val and not visited[i]: 
      min_val = distance[i]
      index = i
  return index

def dijkstra(start):
  distance[start] = 0 
  visited[start] = True

  for i in graph[start]:
    distance[i[0]] = i[1] 
  
  for _ in range(n-1): 
    now = get_smallest_node() 
    visited[now] = True       

    for j in graph[now]:
      if distance[now] + j[1] < distance[j[0]]: 
        distance[j[0]]= distance[now] + j[1]   

dijkstra(k)
```
- 힙 이용한 구현도 있다.
