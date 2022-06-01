# grep tricks
---
### 기본
- grep : Global Regualar Expression Print
- 텍스트 파일에서 원하는 문자열이 들어간 행을 (1) 찾고 (2) 출력

```shell
-b : 문자와 일치하는 줄의 시작점 출력
-c : 문자와 일치하는 줄의 수 출력
-h : 여러 파일에서 문자열 찾을 때, 파일이름 붙는 것 방지
-i : 대소문자 구분 x
-n : 줄의 번호와 내용 같이 출력
-v : 해당 문자가 포함되지 않는 행 출력 #중요!
-w : 문자와 한 단어(w:word)로 일치해야 출력
-l : 문자가 들어간 파일 이름을 출력
-r : 하위 디렉토리에서 (r:recursive) 문자 탐색
-A : 특정문자 아래로 여러 행 탐색 및 출력
-B : 특정문자 위로 이하 동일
```

### tricks
```shell
grep "^<string>" : 해당 문자열로 행이 시작는 경우
grep "<string>&" : 해당 문자열로 행이 끝나는 경우
grep "<string> | <string2>" : 여러개 문자열 탐색 #중요!
grep "<string>" * : 현재 위치의 모든파일에서 해당 문자열 탐색 및 출력

grep "<string>" <filename1> | grep "string2" | grep "string3" : 파이프라인 이용한 순차적 필터링
grep "<string>" <filename1> >> <filename2> : filename2에 탐색한 내용 저장
```


# sed 
---
### 기본
- sed : stream 편집기, 원본 변경없이 출력 결과의 변경
- 사용법 : sed 's/찾을텍스트/바꿀텍스트/파일명'

```shell
-n : 
-e : 스크립트에 실행 가능한 명령어 추가. ex) sed -e "s/^$1,//" | tail -n 1 >> "README.md" #중요! #파이프라인 구축!

```
