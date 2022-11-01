# C# 사용 기록

- DataTable ~ DataRow / DataColumn의 사용방법 유의할 것
- LINQ?
- partial class 등의 특성 유의할 것
- delegate?
- []의 사용법

---

### LINQ
- Language Integrated Query.
- IENumerable Interface를 지원하는 모든 Collection 객체에 적용 가능. (Array, DataTable 등)


#### 사용 예제
```c#
int[] scores = { 92,93,12,3};

IEnumerable<int> scoreQuery = 
  from score in scores
   where score > 80
   select score;
   
// 기존 DataTable에서 답답했던 특정 column의 select까지 편리하게 지원

foreach (int i in scoreQuery){
  Console.Write(i);
}
//foreach에서 각요소 추출 가능
```

```c#
DataTable table = new DataTable();
table.Columns.Add("lot");
table.Columns.Add("data", typeof(double));

table.Rows.Add("A", 45.2);
/*
  ... 
*/

var query = 
  from dr in table.AsEnumerable() // DataTable객체에서 바로 .AsEnumerable()로 변환 유의
  where dr.Field<string>("lot") == "A" &&
    dr.Field<double>("data") >=45.5  // IEnumberable 객체에서는 .filed<>()로 직접 항을 지정하여 조건 적용 가능
  select new
  {
    lot = dr.Field<string>("lot"),
    data = dr.Field<double>("data")
  }
  
  
double max = table.AsEnumerable().Max(r=>r.Field<double>("data"));
// 통계치 등은 람다식 이용해서 특정 칼럼을 지정하는 방식으로 대응 가능.
```

--- 
### DataTable to string Array (string[])
- 특정 상황에 따라 item이 string[] 등 array 형태의 인풋을 요구하는 경우 사용할 것 (특정 column의 값만 추출하여 parameter로 넘기기 등)

```c#
DataTable targetList; // 실제로는 전달받아올것
string[] resultArray = new string[targetList.Rows.Count];
string targetColumn = "target"

int idx =0;
string value = String.Empty;
foreach (DataRow dr in targetList.Rows) // targetList로도 동작
{
  value = dr[targetColumn].ToString();
  resulteArray[n]= value;
  idx++;
}
```

### DataTable to List (specific column data) 
- 이것도 조금 트리키하다. python의 list comprehension 스럽긴한데 참고해두자.

```c#
DataTable dt= new DataTable();
List<string> sampleList = (from row in dt.AsEnumerable() select row.Field<string>("COL_NAME")).ToList();


```
- from a select b 기법 유의할 것
- Field사용이 numerator 사용 핵심으로 보인다.
- vector to List말고도 응용 가능할 것으로 보임.


---

### delegate
- 대리자, 파라미터와 함께 메서드 전달 시 사용
- ms 공식 문서 참조할 것


---

### [] 사용법

```c#
[OperationContract(alpha = beta)]
void Say(string sth);

(...)
```
메소드 앞에 대괄호로 작성된 하는 기법 : '사전 정의된 속성'
-> 런타임에 특정 정보를 전달하는 태그
-> 클래스, 메소드, 구조체, enum, assembly 등
-> 외부(DLL)로부터 선언된 메소드를 사용할 때 이용한다고 한다.
