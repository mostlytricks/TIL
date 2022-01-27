# 8장 경계
---

### 기억해둘 내용
- 외부 코드 사용하기
  - Java는 Map이 변경된 이력이 있다. 부문별한 제네릭스의 사용은, 오히려 이후 코드 변경 시 복잡도 증가
```java
// bad
Map sensors = new HashMap();
Sensor s = (Sensor) sensors.get(sensorId);

// better
Map<String, Sensor> sensors = new HashMap<Sensor>();
Sensor s = sensors.get(sensorId);

// OOP - Generics의 사용여부를 Sensors 내부에서 결정, Map이 Sensor안으로 숨게됨.
public class Sensors{
    private Map sensors = new HashMap();

    public Sensor getById(String id) {
        return (Sensor) sensors.get(id);
    }
}

```
