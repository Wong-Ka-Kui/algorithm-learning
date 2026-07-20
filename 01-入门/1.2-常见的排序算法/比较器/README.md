# 比较器（Comparator）

## 目录
- [什么是比较器？](#什么是比较器)
- [为什么需要比较器？](#为什么需要比较器)
- [比较器的基本使用](#比较器的基本使用)
  - [方法一：实现 Comparable 接口](#方法一实现-comparable-接口)
  - [方法二：传入 Comparator 对象](#方法二传入-comparator-对象)
- [比较器的返回值规则](#比较器的返回值规则)
- [常见使用场景](#常见使用场景)
  - [对基本类型数组排序](#对基本类型数组排序)
  - [对对象数组排序](#对对象数组排序)
  - [在堆中使用比较器](#在堆中使用比较器)
- [实用技巧](#实用技巧)

---

## 什么是比较器？

**比较器（Comparator）** 是 Java 中用于定义**自定义排序规则**的工具。

**核心作用**：
- 告诉排序算法：如何比较两个元素的大小
- 允许我们按照自己的需求对对象进行排序

**两种方式**：
1. **Comparable 接口**：在类内部定义比较规则
2. **Comparator 接口**：在类外部定义比较规则

---

## 为什么需要比较器？

### 问题场景

**场景1：基本类型数组**
```java
int[] arr = {3, 1, 4, 1, 5};
Arrays.sort(arr);  // 默认升序排序
// 结果：[1, 1, 3, 4, 5]

// 问题：如果想降序排序怎么办？❌ int[] 不支持比较器
```

**场景2：对象数组**
```java
class Student {
    String name;
    int age;
    int score;
}

Student[] students = {...};
Arrays.sort(students);  // ❌ 编译错误！不知道按什么规则排序
```

**比较器就是用来解决这些问题的！**

---

## 比较器的基本使用

### 方法一：实现 Comparable 接口

在类内部定义"自然排序"规则。

**示例：学生类按年龄排序**

```java
class Student implements Comparable<Student> {
    String name;
    int age;
    int score;

    public Student(String name, int age, int score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }

    @Override
    public int compareTo(Student other) {
        // 按年龄升序排序
        return this.age - other.age;
    }

    @Override
    public String toString() {
        return name + "(age=" + age + ", score=" + score + ")";
    }
}

// 使用
Student[] students = {
    new Student("Alice", 20, 85),
    new Student("Bob", 18, 92),
    new Student("Charlie", 22, 78)
};

Arrays.sort(students);  // 自动按年龄升序排序
System.out.println(Arrays.toString(students));
// 输出：[Bob(age=18, score=92), Alice(age=20, score=85), Charlie(age=22, score=78)]
```

**优点**：
- ✅ 代码简洁，直接调用 `Arrays.sort()`
- ✅ 定义了类的"自然顺序"

**缺点**：
- ❌ 只能定义一种排序规则
- ❌ 如果想按其他字段排序（如成绩），需要修改类代码

---

### 方法二：传入 Comparator 对象

在类外部定义比较规则，更灵活。

**示例1：按成绩降序排序**

```java
// 方式1：匿名内部类
Arrays.sort(students, new Comparator<Student>() {
    @Override
    public int compare(Student s1, Student s2) {
        // 按成绩降序排序
        return s2.score - s1.score;
    }
});

// 方式2：Lambda 表达式（推荐）
Arrays.sort(students, (s1, s2) -> s2.score - s1.score);

// 输出：[Bob(age=18, score=92), Alice(age=20, score=85), Charlie(age=22, score=78)]
```

**示例2：多条件排序**

```java
// 先按成绩降序，成绩相同则按年龄升序
Arrays.sort(students, (s1, s2) -> {
    if (s1.score != s2.score) {
        return s2.score - s1.score;  // 成绩降序
    }
    return s1.age - s2.age;  // 年龄升序
});
```

**优点**：
- ✅ 灵活！可以定义多种排序规则
- ✅ 不需要修改类代码
- ✅ Lambda 表达式简洁优雅

---

## 比较器的返回值规则

比较器的核心是 `compare(a, b)` 方法，返回值决定排序顺序：

| 返回值 | 含义 | 排序结果 |
|--------|------|----------|
| **负数** (< 0) | a < b | a 排在 b 前面 |
| **0** | a == b | a 和 b 顺序不变（保持稳定性） |
| **正数** (> 0) | a > b | a 排在 b 后面 |

**记忆技巧**：

```java
// 升序排序
(a, b) -> a - b

// 降序排序
(a, b) -> b - a
```

**详细解释**（以升序为例）：

```java
compare(3, 5):
  3 - 5 = -2 (负数) → 3 < 5 → 3 排前面 ✅

compare(5, 3):
  5 - 3 = 2 (正数) → 5 > 3 → 5 排后面 ✅

compare(3, 3):
  3 - 3 = 0 → 相等 → 保持原顺序 ✅
```

---

## 常见使用场景

### 对基本类型数组排序

**注意**：基本类型数组（`int[]`, `double[]` 等）**不支持** Comparator！

**解决方案**：

```java
// 方案1：先排序，再反转（仅适用于降序）
int[] arr = {3, 1, 4, 1, 5};
Arrays.sort(arr);  // [1, 1, 3, 4, 5]
// 手动反转
for(int i = 0; i < arr.length / 2; i++) {
    int temp = arr[i];
    arr[i] = arr[arr.length - 1 - i];
    arr[arr.length - 1 - i] = temp;
}
// 结果：[5, 4, 3, 1, 1]

// 方案2：转为包装类型（推荐）
Integer[] arr2 = {3, 1, 4, 1, 5};
Arrays.sort(arr2, (a, b) -> b - a);  // 降序
// 结果：[5, 4, 3, 1, 1]
```

### 对对象数组排序

**单条件排序**：
```java
// 按年龄升序
Arrays.sort(students, (s1, s2) -> s1.age - s2.age);

// 按成绩降序
Arrays.sort(students, (s1, s2) -> s2.score - s1.score);

// 按姓名字典序
Arrays.sort(students, (s1, s2) -> s1.name.compareTo(s2.name));
```

**多条件排序**：
```java
// 先按成绩降序，成绩相同按年龄升序
Arrays.sort(students, (s1, s2) -> {
    if (s1.score != s2.score) {
        return s2.score - s1.score;
    }
    return s1.age - s2.age;
});

// 使用 Comparator 链式调用（Java 8+，推荐）
Arrays.sort(students, 
    Comparator.comparing(Student::getScore).reversed()  // 成绩降序
              .thenComparing(Student::getAge));          // 年龄升序
```

### 在堆中使用比较器

**小根堆（默认）**：
```java
PriorityQueue<Integer> minHeap = new PriorityQueue<>();
minHeap.add(3);
minHeap.add(1);
minHeap.add(5);
System.out.println(minHeap.poll());  // 输出：1（最小值）
```

**大根堆**：
```java
// 方式1：使用 Comparator.reverseOrder()
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

// 方式2：自定义比较器
PriorityQueue<Integer> maxHeap2 = new PriorityQueue<>((a, b) -> b - a);

maxHeap.add(3);
maxHeap.add(1);
maxHeap.add(5);
System.out.println(maxHeap.poll());  // 输出：5（最大值）
```

**对象堆**：
```java
// 按成绩建立大根堆（成绩高的优先）
PriorityQueue<Student> pq = new PriorityQueue<>((s1, s2) -> s2.score - s1.score);

pq.offer(new Student("Alice", 20, 85));
pq.offer(new Student("Bob", 18, 92));
pq.offer(new Student("Charlie", 22, 78));

System.out.println(pq.poll());  // Bob(age=18, score=92) - 成绩最高
```

---

## 实用技巧

### 1. 避免整数溢出

**错误写法**：
```java
// ❌ 危险！可能溢出
Arrays.sort(arr, (a, b) -> a - b);

// 示例：a = -2147483648 (Integer.MIN_VALUE), b = 1
// a - b = -2147483648 - 1 = 2147483647 (溢出！变成正数)
// 结果：-2147483648 被认为"大于" 1，排序错误！
```

**正确写法**：
```java
// ✅ 使用 Integer.compare()
Arrays.sort(arr, (a, b) -> Integer.compare(a, b));

// 或者使用条件判断
Arrays.sort(arr, (a, b) -> {
    if (a < b) return -1;
    if (a > b) return 1;
    return 0;
});
```

### 2. 多条件排序的简化

**传统写法**：
```java
Arrays.sort(students, (s1, s2) -> {
    if (s1.score != s2.score) {
        return s2.score - s1.score;
    }
    if (s1.age != s2.age) {
        return s1.age - s2.age;
    }
    return s1.name.compareTo(s2.name);
});
```

**简化写法（Java 8+）**：
```java
Arrays.sort(students, 
    Comparator.comparing((Student s) -> s.score).reversed()
              .thenComparing(s -> s.age)
              .thenComparing(s -> s.name));
```

### 3. null 值处理

```java
// 处理可能为 null 的字段
Arrays.sort(students, (s1, s2) -> {
    if (s1.name == null && s2.name == null) return 0;
    if (s1.name == null) return 1;  // null 排后面
    if (s2.name == null) return -1;
    return s1.name.compareTo(s2.name);
});

// 或使用 Comparator.nullsLast()
Arrays.sort(students, 
    Comparator.comparing(Student::getName, Comparator.nullsLast(String::compareTo)));
```

### 4. 稳定性的重要性

当比较器返回 0（相等）时，**稳定排序**保证元素的相对顺序不变。

```java
Student[] students = {
    new Student("Alice", 20, 85),
    new Student("Bob", 20, 85),    // 与 Alice 年龄和成绩相同
    new Student("Charlie", 18, 90)
};

// 按成绩排序（稳定排序）
Arrays.sort(students, (s1, s2) -> s2.score - s1.score);

// 结果：Charlie, Alice, Bob
// Alice 和 Bob 成绩相同，保持原顺序（Alice 在前）
```

### 5. 调试技巧

```java
// 打印比较过程（调试用）
Arrays.sort(arr, (a, b) -> {
    int result = a - b;
    System.out.println("比较 " + a + " 和 " + b + " -> " + result);
    return result;
});
```

---

## 总结

| 场景 | 推荐方式 |
|------|---------|
| 基本类型升序 | `Arrays.sort(arr)` |
| 基本类型降序 | 转为包装类 + Comparator |
| 对象单条件排序 | Lambda：`(a, b) -> a.field - b.field` |
| 对象多条件排序 | `Comparator.comparing().thenComparing()` |
| 堆（小根） | `new PriorityQueue<>()` |
| 堆（大根） | `new PriorityQueue<>(Comparator.reverseOrder())` |

**核心记忆**：
- 升序：`(a, b) -> a - b`
- 降序：`(a, b) -> b - a`
- 返回负数 → a 排前面
- 返回正数 → a 排后面

比较器是排序算法中非常重要的工具，熟练掌握它能让你在处理复杂排序需求时游刃有余！🚀
