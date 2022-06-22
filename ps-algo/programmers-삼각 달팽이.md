# 삼각 달팽이
--- 
### Memo
##### 문제
- [programmers - 삼각 달팽이](https://programmers.co.kr/learn/courses/30/lessons/68645)

##### 구상
- 문제 탐색 목적 타인 코드 우선 확인함, 구현 방향성 유의하고 복기할 것
- 문제 해석 후 직접 구현이 메인인 문제. 베이스가 되는 그릇 생성하고, 이후 방위에 따른 순차적 값 부여 유의할 것.
- 벡터를 2차원 평면에서 어떻게 표현할 것인가? 고민해보자.

---
### Result
```python
def solution(n):
  [row, col, cnt]=[-1, 0, 1]
  board=[[None]* i fori in range(1,n+1)]
  print(board) #빈공간 생성
  for i in range(n):
    for _ in (range(n-i)): # 변의 길이 만큼 패턴 할당
      if i % 3==0:
        row +=1
      elif i %3 ==1:
        col +=1
      else:
        row-=1
        col-=1
      board[row][col]=cnt
      cnt+=1 # 숫자의 배정은 cnt로 별도 등록
  return list(chain(*board)) # *로board를 풀고, chain으로 단일 list가 되도록 결합
     
```
