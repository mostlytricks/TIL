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