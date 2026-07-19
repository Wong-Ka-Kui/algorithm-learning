# 1.2 常见的排序算法

## 目录
- [选择排序 (Selection Sort)](#选择排序-selection-sort)
  - [算法描述](#算法描述)
  - [复杂度分析](#复杂度分析)
  - [核心代码](#核心代码)
- [冒泡排序 (Bubble Sort)](#冒泡排序-bubble-sort)
  - [算法描述](#算法描述-1)
  - [复杂度分析](#复杂度分析-1)
  - [核心代码](#核心代码-1)
- [插入排序 (Insertion Sort)](#插入排序-insertion-sort)
  - [算法描述](#算法描述-2)
  - [复杂度分析](#复杂度分析-2)
  - [核心代码](#核心代码-2)
- [归并排序 (Merge Sort)](#归并排序-merge-sort)
  - [算法描述](#算法描述-3)
  - [复杂度分析](#复杂度分析-3)
  - [核心代码](#核心代码-3)
  - [算法拓展](#算法拓展)
    - [小和问题](#小和问题)
    - [逆序对问题](#逆序对问题)
    - [右侧小于当前元素的个数](#右侧小于当前元素的个数)
- [快速排序 (Quick Sort)](#快速排序-quick-sort)
  - [算法描述](#算法描述-4)
  - [复杂度分析](#复杂度分析-4)
  - [核心代码](#核心代码-4)
  - [算法题](#算法题-1)
    - [荷兰国旗问题](#荷兰国旗问题)
- [对数器：如何验证算法正确性](对数器/README.md)
- [异或交换详解](#异或交换详解)
  - [异或运算的基本性质](#异或运算的基本性质)
  - [交换原理示例](#交换原理示例)
  - [代码实现](#代码实现)
  - [局限性](#局限性)
  - [算法题](#算法题)
    - [题目1：找出唯一出现奇数次的数字](#题目1找出唯一出现奇数次的数字)
    - [题目2：找出两个出现奇数次的数字](#题目2找出两个出现奇数次的数字)

---

## 选择排序 (Selection Sort)

### 算法描述

选择排序是一种简单直观的排序算法。它的工作原理是：每次从未排序部分找到最小（或最大）的元素，将其放到已排序部分的末尾。

**算法步骤：**
1. 在未排序序列 `[0, n-1]` 中找到最小元素，放到第 0 位
2. 在未排序序列 `[1, n-1]` 中找到最小元素，放到第 1 位
3. 以此类推，直到所有元素排序完成

### 复杂度分析

- **时间复杂度**：O(n²) - 无论数据初始状态如何，都需要进行 n(n-1)/2 次比较
- **空间复杂度**：O(1) - 只需要常数个额外变量
- **稳定性**：不稳定 - 可能改变相同元素的相对位置

**不稳定示例**：对于 `[5, 8, 5, 2]`，第一次会把 2 和第一个 5 交换，导致两个 5 的相对位置改变。

### 核心代码

```java
public static void selectionSort(int[] arr) {
    // 边界判断
    if(arr == null || arr.length < 2) {
        return;
    }
    
    // i 的取值范围：[0, arr.length-2]
    for(int i = 0; i < arr.length - 1; i++) {
        int minIndex = i;
        // j 的取值范围：[i+1, arr.length-1]
        for(int j = i + 1; j < arr.length; j++) {
            minIndex = (arr[j] < arr[minIndex]) ? j : minIndex;
        }
        swap(arr, i, minIndex);
    }
}

public static void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
}
```

---

## 冒泡排序 (Bubble Sort)

### 算法描述

冒泡排序是一种简单的排序算法。它重复地遍历要排序的数列，一次比较两个相邻元素，如果顺序错误就交换它们。每一轮遍历后，最大的元素会"冒泡"到数组末尾。

**算法步骤：**
1. 在 `[0, n-1]` 范围内，比较相邻元素，较大者向后移动，最大值冒泡到第 n-1 位
2. 在 `[0, n-2]` 范围内重复上述过程，次大值冒泡到第 n-2 位
3. 以此类推，直到所有元素排序完成

### 复杂度分析

- **时间复杂度**：O(n²) - 最坏和平均情况；O(n) - 最好情况（已排序数组，可优化）
- **空间复杂度**：O(1) - 只需要常数个额外变量
- **稳定性**：稳定 - 相同元素不会交换位置

### 核心代码

```java
public static void bubbleSort(int[] arr) {
    // 边界判断
    if(arr == null || arr.length < 2) {
        return;
    }
    
    // e 从 arr.length-1 递减到 0，表示未排序部分的右边界
    for(int e = arr.length - 1; e >= 0; e--) {
        // i 从 0 到 e-1，比较相邻元素
        for(int i = 0; i < e; i++) {
            if(arr[i] > arr[i + 1]) {
                swap(arr, i, i + 1);
            }
        }
    }
}

// 使用位运算交换（不需要临时变量）
private static void swap(int[] arr, int i, int j) {
    arr[i] = arr[i] ^ arr[j];
    arr[j] = arr[i] ^ arr[j];
    arr[i] = arr[i] ^ arr[j];
}
```

---

## 插入排序 (Insertion Sort)

### 算法描述

插入排序是一种简单直观的排序算法，其工作原理类似于整理扑克牌。将数组分为已排序部分和未排序部分，每次从未排序部分取出一个元素，插入到已排序部分的正确位置。

**算法步骤：**
1. 初始时，第一个元素视为已排序（`[0]` 已排序）
2. 从第二个元素开始，依次维护 `[0,1]`、`[0,2]`、`[0,3]`...的排序状态
3. 对于每个待插入元素，从后向前扫描已排序部分
4. 如果已排序部分的元素大于待插入元素，则交换位置，继续向前扫描
5. 直到找到合适的插入位置（前面的元素小于等于待插入元素）

**形象比喻**：就像整理扑克牌，左手已经是排好序的牌，每次从右手抽一张牌，插入到左手的正确位置。

### 复杂度分析

- **时间复杂度**：
  - 最坏情况：O(n²) - 数组完全逆序，每个元素都要移动到最前面
  - 最好情况：O(n) - 数组已排序，每个元素只需比较一次
  - 平均情况：O(n²)
- **空间复杂度**：O(1) - 只需要常数个额外变量
- **稳定性**：稳定 - 相同元素的相对位置不会改变

**优势**：
- 对于小规模数据或接近有序的数据，插入排序效率很高
- 在数据基本有序的情况下，时间复杂度接近 O(n)
- 实现简单，常用于其他高级排序算法的优化（如快速排序的递归终止条件）

### 核心代码

```java
public static void insertionSort(int[] arr) {
    // 边界判断
    if(arr == null || arr.length < 2) {
        return;
    }
    
    // i 从 1 开始，依次维护 [0,i] 部分的有序性
    for(int i = 1; i < arr.length; i++) {
        // j 从 i-1 开始，从后向前扫描已排序部分
        for(int j = i - 1; j >= 0; j--) {
            // 如果前一个元素大于后一个元素，则交换
            if(arr[j] > arr[j + 1]) {
                swap(arr, j, j + 1);
            }
        }
    }
}

public static void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
}
```

**代码解析**：
- 外层循环：`i` 从 1 到 `arr.length-1`，表示当前要插入的元素位置
- 内层循环：`j` 从 `i-1` 递减到 0，从后向前扫描已排序部分
- 比较交换：如果 `arr[j] > arr[j+1]`，说明顺序错误，交换位置
- 持续比较：内层循环会将当前元素逐步移动到正确位置

**优化版本**（提前退出）：
```java
for(int i = 1; i < arr.length; i++) {
    for(int j = i - 1; j >= 0; j--) {
        if(arr[j] > arr[j + 1]) {
            swap(arr, j, j + 1);
        } else {
            break;  // 找到正确位置，提前退出
        }
    }
}
```
加入 `break` 可以在找到正确位置后立即停止，提高效率，特别是对于接近有序的数组。

---

## 归并排序 (Merge Sort)

### 算法描述

归并排序是一种基于**分治思想**的高效排序算法。它将数组递归地分成两半，分别排序后再合并成一个有序数组。

**算法步骤（分治三部曲）：**
1. **分解（Divide）**：将数组从中间分成两个子数组
2. **解决（Conquer）**：递归地对两个子数组分别进行归并排序
3. **合并（Combine）**：将两个已排序的子数组合并成一个有序数组

**递归树示例**（对于数组 `[64, 25, 12, 22, 11]`）：

```
              [64, 25, 12, 22, 11]
                      /\
            [64, 25, 12]   [22, 11]
               /\              /\
          [64, 25]  [12]    [22]  [11]
            /\
         [64]  [25]
```

递归到底后，开始合并：
```
         [64]  [25]  →  [25, 64]
              /\
       [25, 64]  [12]  →  [12, 25, 64]
                              /\
                    [12, 25, 64]  [11, 22]  →  [11, 12, 22, 25, 64]
```

**合并过程的核心**：双指针法
- 使用两个指针分别指向两个已排序子数组的开头
- 每次比较两个指针指向的元素，较小者放入结果数组，对应指针后移
- 重复直到其中一个子数组遍历完，将另一个子数组的剩余元素全部追加到结果数组

### 复杂度分析

- **时间复杂度**：O(n log n) - 无论最好、最坏、平均情况都是 O(n log n)
  - 根据 **Master 定理**：`T(n) = 2T(n/2) + O(n)`
    - `a = 2`（分成 2 个子问题）
    - `b = 2`（每个子问题规模是 n/2）
    - `d = 1`（合并操作是 O(n)）
    - 满足情况2：`log_b(a) = log_2(2) = 1 = d`
    - 结果：`T(n) = O(n^d * log n) = O(n log n)`
  - 递归深度：log n（每次对半分）
  - 每层合并：O(n)（遍历所有元素）
  - 总时间：O(n log n)
  
- **空间复杂度**：O(n) - 需要额外的辅助数组来存储合并结果
- **稳定性**：稳定 - 合并时相等元素保持原有顺序（关键是使用 `<=` 而非 `<`）

**优势**：
- 时间复杂度稳定，不受输入数据影响
- 适合大规模数据排序
- 可以用于外部排序（数据量大到内存放不下）

**劣势**：
- 需要额外 O(n) 的空间
- 对于小规模数据，常数因子较大，可能不如插入排序

### 核心代码

```java
public static void mergeSort(int[] arr) {
    // 边界判断
    if(arr == null || arr.length < 2) {
        return;
    }
    process(arr, 0, arr.length - 1);
}

// 递归处理：对 [left, right] 范围进行归并排序
private static void process(int[] arr, int left, int right) {
    // 递归终止条件：只有一个元素
    if(left == right) {
        return;
    }
    
    // 计算中点（防止溢出）
    int middle = left + (right - left) / 2;
    
    // 递归排序左半部分
    process(arr, left, middle);
    // 递归排序右半部分
    process(arr, middle + 1, right);
    // 合并两个有序部分
    merge(arr, left, middle, right);
}

// 合并：将 [left, middle] 和 [middle+1, right] 两个有序部分合并
private static void merge(int[] arr, int left, int middle, int right) {
    // 创建辅助数组，用于存放合并后的结果
    int[] help = new int[right - left + 1];
    
    // 三个指针
    int i = 0;           // help 数组的索引
    int p0 = left;       // 左半部分的指针
    int p1 = middle + 1; // 右半部分的指针
    
    // 双指针比较，较小者放入 help 数组
    while(p0 <= middle && p1 <= right) {
        // 使用 <= 保证稳定性（相等时左边优先）
        help[i++] = (arr[p0] <= arr[p1]) ? arr[p0++] : arr[p1++];
    }
    
    // 处理左半部分剩余元素
    while(p0 <= middle) {
        help[i++] = arr[p0++];
    }
    
    // 处理右半部分剩余元素
    while(p1 <= right) {
        help[i++] = arr[p1++];
    }
    
    // 将 help 数组拷贝回原数组
    for(int j = 0; j < help.length; j++) {
        arr[left + j] = help[j];
    }
}
```

**代码解析**：

1. **`mergeSort` 方法**：
   - 边界判断后调用递归方法 `process`

2. **`process` 递归方法**：
   - **终止条件**：`left == right` 表示只有一个元素，已经有序
   - **分解**：计算中点 `middle = left + (right - left) / 2`（防止整数溢出）
   - **递归**：分别对左右两部分调用 `process`
   - **合并**：调用 `merge` 合并两个有序部分

3. **`merge` 合并方法**：
   - **辅助数组**：`help` 大小为 `right - left + 1`，存储合并结果
   - **双指针**：`p0` 指向左半部分，`p1` 指向右半部分
   - **比较合并**：`arr[p0] <= arr[p1]` 时选择左边（**保证稳定性**）
   - **处理剩余**：两个 while 循环处理某一边剩余的元素
   - **拷贝回原数组**：`arr[left + j] = help[j]`（注意是 `left + j`，不是 `j`）

**关键细节**：
- ✅ `middle = left + (right - left) / 2` 防止 `left + right` 溢出
- ✅ `arr[p0] <= arr[p1]` 中的 `<=` 保证了稳定性
- ✅ `arr[left + j] = help[j]` 拷贝时要加上 `left` 偏移量

### 算法拓展

#### 小和问题

**题目描述**：

在一个数组中，每一个数左边比当前数小的数累加起来，叫做这个数组的小和。求一个数组的小和。

**例子**：`[1, 3, 4, 2, 5]`
- 1 左边比 1 小的数：没有
- 3 左边比 3 小的数：1
- 4 左边比 4 小的数：1、3
- 2 左边比 2 小的数：1
- 5 左边比 5 小的数：1、3、4、2

所以小和为：`1 + 1 + 3 + 1 + 1 + 3 + 4 + 2 = 16`

**解题思路**：

利用归并排序的思想，在合并过程中统计小和。核心是**思路转换**：原问题是"每个数的左边有多少个比它小的数"，可以反过来看成"每个数的右边有多少个比它大的数"。

**代码实现**：

```java
// 求小和，既可以看某个数左边有几个数字比他小，也可以反过来看右边有几个数字比他大（本题思路）
public static int smallSum(int[] arr) {
    // 边界判断
    if(arr == null || arr.length < 2) {
        return 0;
    }
    return process(arr,0,arr.length-1);
}

private static int process(int[] arr,int left,int right){
    if(left == right){
        return 0;
    }
    int middle = left + (right-left)/2;
    return process(arr, left, middle) + process(arr, middle+1, right) + merge(arr,left,middle,right);
}

private static int merge(int[] arr,int left,int middle,int right){
    //在merge的过程中，既排序，也顺便计算小和
    int i = 0;
    int p1 = left;
    int p2 = middle+1;
    int ans = 0;

    int[] help = new int[right-left+1];
    while(p1<=middle && p2<=right){
        // (arr[p1] < arr[p2])?只能是'<'，在等于的时候先让右侧加入help数组，不计算小和
        // (right-p2+1)*arr[p1]，分别表示右侧大于该数字的个数*该数字 = 本轮添加的小和
        ans += (arr[p1] < arr[p2])? (right-p2+1)*arr[p1] : 0;
        help[i++] = (arr[p1] < arr[p2])? arr[p1++] : arr[p2++];
    }
    while(p1 <= middle){
        help[i++] = arr[p1++];
    }
    while(p2 <= right){
        help[i++] = arr[p2++];
    }
    //将help数组的答案元素，复制到arr数组中，（只是体现归并排序，在小和问题中其实不一定需要）
    for(int j=0;j<help.length;j++){
        arr[left+j] = help[j];
    }
    return ans;
}
```

**复杂度分析**：
- **时间复杂度**：O(n log n) - 与归并排序相同
- **空间复杂度**：O(n) - 辅助数组

#### 逆序对问题

**题目描述**：

在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。

**例子**：`[7, 5, 6, 4]`

逆序对有：(7,5), (7,6), (7,4), (5,4), (6,4)

逆序对总数：5

**解题思路**：

利用归并排序的思想，在合并过程中统计逆序对。当左半部分的某个数大于右半部分的数时，由于左半部分是有序的，该位置之后的所有元素都会与右半部分的当前元素构成逆序对。

**代码实现**：

```java
// 求逆序对总数，主要就是当发现arr[p1]>arr[p2]的时候，记录下逆序对个数
public static int reversePairs(int[] arr) {
    // 边界判断
    if(arr == null || arr.length < 2) {
        return 0;
    }
    return process(arr,0,arr.length-1);
}

private static int process(int[] arr,int left,int right){
    if(left == right){
        return 0;
    }
    int middle = left + (right-left)/2;
    return process(arr, left, middle) + process(arr, middle+1, right) + merge(arr,left,middle,right);
}

private static int merge(int[] arr,int left,int middle,int right){
    int[] help = new int[right-left+1];
    int i = 0;
    int p1 = left;
    int p2 = middle+1;
    int ans = 0;
    while(p1<=middle && p2<=right){
        if (arr[p1] <= arr[p2]) {
            // 正常执行归并排序的流程
            help[i++] = arr[p1++];
        } else {
            // 左边 > 右边，发现构成逆序对
            // 因为左半部分是有序的，arr[p1...middle] 都 > arr[p2]
            // 所以共有 (middle - p1 + 1) 个逆序对
            ans += (middle - p1 + 1);
            help[i++] = arr[p2++];
        }
    }
    while(p1 <= middle){
        help[i++] = arr[p1++];
    }
    while(p2 <= right){
        help[i++] = arr[p2++];
    }
    for(int j=0;j<help.length;j++){
        arr[left+j] = help[j];
    }
    return ans;
}
```

**复杂度分析**：
- **时间复杂度**：O(n log n) - 与归并排序相同
- **空间复杂度**：O(n) - 辅助数组

#### 右侧小于当前元素的个数

**题目描述**：

给你一个整数数组 `nums`，按要求返回一个新数组 `counts`。数组 `counts` 有该性质：`counts[i]` 的值是 `nums[i]` 右侧小于 `nums[i]` 的元素的数量。

**例子**：`nums = [5, 2, 6, 1]`

输出：`[2, 1, 1, 0]`

解释：
- 5 的右侧有 2 个更小的元素 (2 和 1)
- 2 的右侧有 1 个更小的元素 (1)
- 6 的右侧有 1 个更小的元素 (1)
- 1 的右侧有 0 个更小的元素

**解题思路**：

这道题需要为每个元素统计其右侧小于它的元素个数，不能简单地返回一个总数。核心思路：
1. 创建索引数组，记录每个元素的原始位置
2. 使用归并排序对索引数组排序（按对应元素值排序）
3. 在合并过程中，当发现右半部分的元素小于左半部分的元素时，累加到对应位置的计数中

**代码实现**：

```java
public static int[] countSmaller(int[] nums) {
    if (nums == null || nums.length == 0) {
        return new int[0];
    }
    
    int n = nums.length;
    int[] counts = new int[n];  // 存储结果
    int[] indexes = new int[n]; // 索引数组
    
    // 初始化索引数组
    for (int i = 0; i < n; i++) {
        indexes[i] = i;
    }
    
    // 归并排序
    mergeSort(nums, indexes, counts, 0, n - 1);
    
    return counts;
}

private static void mergeSort(int[] nums, int[] indexes, int[] counts, int left, int right) {
    if (left >= right) {
        return;
    }
    
    int middle = left + (right - left) / 2;
    mergeSort(nums, indexes, counts, left, middle);
    mergeSort(nums, indexes, counts, middle + 1, right);
    merge(nums, indexes, counts, left, middle, right);
}

private static void merge(int[] nums, int[] indexes, int[] counts, int left, int middle, int right) {
    int[] helper = new int[right - left + 1];
    int i = 0;
    int p1 = left;
    int p2 = middle + 1;
    
    while (p1 <= middle && p2 <= right) {
        // 右半部分的元素小于左半部分的元素
        if (nums[indexes[p2]] < nums[indexes[p1]]) {
            // 右边有一个更小的元素，对左半部分所有剩余元素都有贡献
            helper[i++] = indexes[p2++];
        } else {
            // 左边元素较小或相等，此时右边已处理的元素都小于当前左边元素
            // p2 - (middle + 1) 就是右边已经处理过的、比当前左边元素小的个数
            counts[indexes[p1]] += p2 - (middle + 1);
            helper[i++] = indexes[p1++];
        }
    }
    
    // 处理左半部分剩余元素
    while (p1 <= middle) {
        // 右边所有元素都已处理过，都比这些剩余的左边元素小
        counts[indexes[p1]] += p2 - (middle + 1);
        helper[i++] = indexes[p1++];
    }
    
    // 处理右半部分剩余元素
    while (p2 <= right) {
        helper[i++] = indexes[p2++];
    }
    
    // 拷贝回索引数组
    for (int j = 0; j < helper.length; j++) {
        indexes[left + j] = helper[j];
    }
}
```

**算法详解**：

1. **索引数组技巧**：
   - 不直接排序原数组，而是排序索引
   - `indexes[i]` 表示当前位置对应原数组的哪个索引
   - 这样可以在排序过程中追踪每个元素的原始位置

2. **关键计数逻辑**：
   - 当 `nums[indexes[p2]] < nums[indexes[p1]]` 时，右边元素更小，先放入 helper
   - 当 `nums[indexes[p1]] <= nums[indexes[p2]]` 时，左边元素较小：
     - 此时右半部分中 `[middle+1, p2-1]` 的元素都已经被处理过
     - 这些元素都小于 `nums[indexes[p1]]`
     - 个数为：`p2 - (middle + 1)`
     - 累加到 `counts[indexes[p1]]`

3. **为什么是 `p2 - (middle + 1)`**：
   - `p2` 当前指向右半部分待处理位置
   - `middle + 1` 是右半部分起始位置
   - `[middle+1, p2-1]` 区间的元素都已被放入 helper，都小于当前左边元素
   - 区间长度 = `(p2 - 1) - (middle + 1) + 1 = p2 - middle - 1 = p2 - (middle + 1)`

**复杂度分析**：
- **时间复杂度**：O(n log n) - 归并排序
- **空间复杂度**：O(n) - 索引数组和辅助数组

**与前两题的对比**：
- **小和问题**：统计全局总和，一个数字
- **逆序对问题**：统计全局总数，一个数字
- **本题**：为每个位置统计个数，返回数组，需要使用索引数组技巧

---

## 异或交换详解

在冒泡排序的代码中，`swap` 方法使用了**异或位运算（XOR）**来交换两个元素，这种方法不需要临时变量。

### 异或运算的基本性质

异或运算（`^`）有以下重要特性：
- `a ^ a = 0` - 相同的数异或结果为 0
- `a ^ 0 = a` - 任何数与 0 异或结果是它本身
- `a ^ b = b ^ a` - 异或满足交换律
- `(a ^ b) ^ c = a ^ (b ^ c)` - 异或满足结合律

### 交换原理示例

假设我们要交换两个变量：`a = 甲`，`b = 乙`

**执行过程：**

1. **第一步**：`a = a ^ b`
   - `a = 甲 ^ 乙`（a 现在存储了甲和乙的异或结果）
   - `b = 乙`（b 保持不变）

2. **第二步**：`b = a ^ b`
   - `b = (甲 ^ 乙) ^ 乙`
   - 根据结合律：`b = 甲 ^ (乙 ^ 乙)`
   - 因为 `乙 ^ 乙 = 0`，所以：`b = 甲 ^ 0 = 甲`
   - **此时 b 已经成功变成了甲**

3. **第三步**：`a = a ^ b`
   - `a = (甲 ^ 乙) ^ 甲`
   - 根据交换律和结合律：`a = (甲 ^ 甲) ^ 乙`
   - 因为 `甲 ^ 甲 = 0`，所以：`a = 0 ^ 乙 = 乙`
   - **此时 a 已经成功变成了乙**

**结果**：`a = 乙`，`b = 甲` - 成功交换！

### 代码实现

```java
private static void swap(int[] arr, int i, int j) {
    arr[i] = arr[i] ^ arr[j];  // arr[i] 现在是两者的异或
    arr[j] = arr[i] ^ arr[j];  // arr[j] 变成原来的 arr[i]
    arr[i] = arr[i] ^ arr[j];  // arr[i] 变成原来的 arr[j]
}
```

### 局限性

**重要警告**：异或交换有一个严重的局限性 - **当 `i == j` 时（即两个索引指向同一内存位置），会将该位置的值变为 0**。

**原因分析**：
- 当 `i == j` 时，实际上是对同一个变量进行操作
- 第一步：`arr[i] = arr[i] ^ arr[i] = 0`（任何数与自己异或结果为 0）
- 此时该位置的值已经被破坏，后续操作无法恢复

**示例**：
```java
int[] arr = {5, 3, 8};
swap(arr, 1, 1);  // 尝试交换同一位置
// 结果：arr[1] 变成 0，而不是保持 3
```

**结论**：
- 异或交换虽然节省了临时变量，但**必须确保交换的是不同的内存位置**
- 在调用前需要检查 `i != j`，或者使用传统的临时变量交换方法
- 在冒泡排序中，由于我们交换的是 `arr[i]` 和 `arr[i+1]`，永远不会出现 `i == j` 的情况，所以是安全的

---

## 算法题

### 题目1：找出唯一出现奇数次的数字

**问题描述**：在一个数组中，只有一个数字出现了奇数次，其他数字出现了偶数次，请找到这个数字。

**要求**：
- 时间复杂度：O(n)
- 空间复杂度：O(1)

**示例**：
- 输入：`[1, 2, 3, 2, 1]`，输出：`3`
- 输入：`[5, 5, 7, 7, 7, 9, 9]`，输出：`7`

**解题思路**：

利用异或运算的性质：
- `a ^ a = 0` - 相同的数字异或结果为 0
- `a ^ 0 = a` - 任何数与 0 异或结果是它本身

所有出现偶数次的数字会两两抵消变成 0，最后只剩下出现奇数次的那个数字。

**代码实现**：

```java
public static int findOddTimesNumber(int[] arr) {
    int eor = 0;
    for (int num : arr) {
        eor ^= num;
    }
    // 若这个数字（假设为n）不为0，则答案为 n^0=n；若为0，则答案为 0^0=0
    return eor;
}
```

**算法解析**：
1. 初始化 `eor = 0`
2. 遍历数组，将每个数字与 `eor` 进行异或
3. 所有出现偶数次的数字会相互抵消，最终 `eor` 就是那个出现奇数次的数字

---

### 题目2：找出两个出现奇数次的数字

**问题描述**：在一个数组中，有两个数字出现了奇数次，其他数字出现了偶数次，请找到这两个数字。

**要求**：
- 时间复杂度：O(n)
- 空间复杂度：O(1)

**示例**：
- 输入：`[1, 2, 3, 2, 1, 4]`，输出：`3` 和 `4`
- 输入：`[5, 5, 7, 7, 7, 9, 9, 9, 11, 11]`，输出：`7` 和 `9`

**解题思路**：

这道题的关键在于如何将两个出现奇数次的数字（假设为 `a` 和 `b`）分离出来。

**步骤1：获取两个数字的异或结果**
- 对所有数字进行异或，结果 `eor = a ^ b`
- 所有出现偶数次的数字会抵消为 0

**步骤2：提取 eor 最右侧的 1**
- 由于 `a != b`，所以 `eor != 0`，其二进制表示中至少有一位为 1
- 这个为 1 的位表示 `a` 和 `b` 在该位上不同（一个是 0，一个是 1）
- 使用 `rightOne = eor & (~eor + 1)` 提取最右侧的 1

**步骤3：根据该位将数组分为两组**
- 一组：该位为 0 的数字（包含 `a` 或 `b` 之一）
- 另一组：该位为 1 的数字（包含 `a` 或 `b` 的另一个）
- 分别对两组进行异或，得到两个目标数字

**代码实现**：

```java
public static int[] findTwoOddTimesNumbers(int[] arr) {
    int eor = 0;
    for (int num : arr) {
        eor ^= num;
    }
    // 此时：eor = a ^ b
    // 并且由于 a != b，所以 eor != 0，eor 的二进制位上必然有一位为 1
    // 使用 rightOne 变量，记录 eor 最右侧的 1（rightOne 其他位上为 0）
    int rightOne = eor & (~eor + 1);
    
    int onlyOne = 0;
    for (int num : arr) {
        if ((num & rightOne) == 0) {
            onlyOne ^= num;
        }
    }
    return new int[]{onlyOne, onlyOne ^ eor};
}
```

**关键代码详解**：

#### `int rightOne = eor & (~eor + 1)` - 提取最右侧的 1

这行代码用于提取一个数字二进制表示中**最右侧的 1**，是一个经典的位运算技巧。

**原理解析**（以 `eor = 12` 为例）：

```
eor      = 12  = 0000 1100  （二进制）
~eor     = -13 = 1111 0011  （按位取反）
~eor + 1 = -12 = 1111 0100  （加1，这就是 eor 的补码）
```

**进行与运算**：
```
  0000 1100  (eor)
& 1111 0100  (~eor + 1)
-----------
  0000 0100  (结果 = 4，二进制为 00000100，只有第3位为1)
```

**为什么可以提取最右侧的 1？**

1. `~eor` 是将所有位取反
2. `~eor + 1` 是 eor 的**补码**（负数在计算机中的表示）
3. 在补码运算中，加 1 会导致：
   - 最右侧的 1 保持不变
   - 最右侧 1 右边的所有 0 变成 1（取反后）再进位变回 0
   - 最右侧 1 左边的所有位都被取反
4. 当 `eor` 与 `~eor + 1` 进行**与运算**时：
   - 只有原本最右侧的 1 位置上两者都为 1（保留）
   - 其他位至少有一个为 0（清零）

**更直观的例子**：

```
eor = 10 (二进制: 1010)
~eor     = 0101
~eor + 1 = 0110
eor & (~eor + 1) = 1010 & 0110 = 0010 (提取到最右侧的1)

eor = 7 (二进制: 0111)
~eor     = 1000
~eor + 1 = 1001
eor & (~eor + 1) = 0111 & 1001 = 0001 (提取到最右侧的1)
```

#### 后续分组逻辑

```java
int onlyOne = 0;
for (int num : arr) {
    if ((num & rightOne) == 0) {
        onlyOne ^= num;
    }
}
return new int[]{onlyOne, onlyOne ^ eor};
```

**逻辑分析**：

1. **`(num & rightOne) == 0`**：
   - 检查 `num` 在 `rightOne` 标记的那一位是否为 0
   - 这样将数组分为两组：该位为 0 的一组，该位为 1 的一组

2. **`onlyOne ^= num`**：
   - 对"该位为 0"的所有数字进行异或
   - 这一组包含 `a` 或 `b` 之一（假设是 `a`），以及一些出现偶数次的数字
   - 偶数次的数字会抵消，最终 `onlyOne = a`

3. **`onlyOne ^ eor`**：
   - 由于 `eor = a ^ b`，而 `onlyOne = a`
   - 所以 `onlyOne ^ eor = a ^ (a ^ b) = (a ^ a) ^ b = 0 ^ b = b`
   - 这就得到了另一个数字 `b`

**完整示例**（数组 `[1, 2, 3, 2, 1, 4]`，答案是 3 和 4）：

```
步骤1：eor = 1^2^3^2^1^4 = 3^4 = 0011 ^ 0100 = 0111 (十进制7)

步骤2：rightOne = 0111 & 1001 = 0001（提取最右侧的1）

步骤3：分组
- 最右侧位为0的：2(0010), 2(0010), 4(0100) → onlyOne = 4
- 最右侧位为1的：1(0001), 3(0011), 1(0001) → 隐含得到 3

步骤4：返回 [4, 4^7] = [4, 3]
```

**时间和空间复杂度分析**：
- **时间复杂度**：O(n) - 遍历数组两次
- **空间复杂度**：O(1) - 只使用了常数个变量

这个算法非常巧妙地利用了异或运算和位运算的特性，在常数空间内解决了问题！

---

## 快速排序 (Quick Sort)

### 算法描述

快速排序是一种高效的**分治算法**，由Tony Hoare于1960年提出。它的核心思想是：选择一个基准元素（pivot），通过一趟排序将数组分为两部分，使得左边部分的元素都小于基准，右边部分的元素都大于基准，然后递归地对左右两部分进行快速排序。

**算法步骤：**
1. **选择基准（pivot）**：从数组中选择一个元素作为基准（为避免极端情况，通常随机选择）
2. **分区（partition）**：重新排列数组，使得所有小于基准的元素放在基准左边，所有大于基准的元素放在基准右边
3. **递归排序**：递归地对基准左边和右边的子数组进行快速排序
4. **合并**：由于是原地排序，不需要额外的合并步骤

### 复杂度分析

- **时间复杂度**：
  - 平均情况：O(n log n) - 每次能比较均匀地划分数组
  - 最坏情况：O(n²) - 每次划分都极度不均（如已排序数组 + 固定选择首元素作为pivot）
  - **随机选择pivot可以有效避免最坏情况**
- **空间复杂度**：O(log n) - 递归调用栈的深度
- **稳定性**：不稳定 - 分区过程中会改变相同元素的相对位置

**为什么随机选择pivot？**
- 对于已排序或接近有序的数组，固定选择首/尾元素会导致极度不均的划分
- 随机选择可以将最坏情况的概率降到可忽略的程度

### 核心代码

```java
import java.util.Random;

public class QuickSort {
    //为了避免极端数据状况，所以基准值采用随机抽取
    public static final Random random = new Random();
    
    public static void quickSort(int[] arr) {
        if(arr==null || arr.length<2){
            return;
        }
        //方法重载
        quickSort(arr,0,arr.length-1);
    }
    
    private static void quickSort(int[] arr,int left,int right){
        if (left >= right) {
            return;
        }
        int p = partition(arr,left,right);
        quickSort(arr,left,p-1);
        quickSort(arr,p+1,right); 
    }
    
    private static int partition(int[] arr,int left,int right){
        int randomIdx = random.nextInt(right-left+1) + left;
        int p = arr[randomIdx];
        swap(arr,left,randomIdx);
        int i = left + 1;
        int j = right;
        while(true){
            while(i<=j && arr[i]<p){
                i++;
            }
            while(i<=j && arr[j]>p){
                j--;
            }
            //在i>=j的情况下，直接退出，避免交换已经处理好了的元素
            if(i >= j){
                break;
            }
            swap(arr,i,j);
            i++;
            j--;
        }
        //将基准放到正确位置
        swap(arr,left,j);
        return j;
    }
    
    private static void swap(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }    
}
```

**算法详解：**

1. **partition方法的双指针策略**：
   - 随机选择一个位置的元素作为pivot，与left位置交换
   - 使用两个指针 `i`（从左向右）和 `j`（从右向左）进行扫描
   - `i` 寻找 `>= pivot` 的元素，`j` 寻找 `<= pivot` 的元素
   - 当两者都找到时交换，继续扫描
   - 当 `i >= j` 时停止，将pivot与j位置交换，此时j就是pivot的最终位置

2. **关键边界处理**：
   - `i<=j` 的循环条件防止指针越界
   - `if(i >= j) break` 避免交换已经正确划分的元素
   - 最后 `swap(arr,left,j)` 将pivot放到正确位置（j位置及其左边都 <= pivot）

3. **递归范围**：
   - 左子数组：`[left, p-1]` - pivot左边的元素
   - 右子数组：`[p+1, right]` - pivot右边的元素
   - pivot本身已在正确位置，不需要再参与排序

---

**三路快排实现（优化重复元素情况）**：

当数组中存在大量重复元素时，三路快排的效率更高。它将数组划分为三个区域：`< pivot`、`== pivot`、`> pivot`，等于区域不需要再递归处理。

```java
public class QuickSort3Way {
    public static final Random random = new Random();
    
    public static void quickSort(int[] arr) {
        if(arr == null || arr.length < 2){
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }
    
    private static void quickSort(int[] arr, int left, int right) {
        if(left >= right) {
            return;
        }
        
        // 随机选择pivot
        int randomIdx = random.nextInt(right - left + 1) + left;
        int pivot = arr[randomIdx];
        
        // 三路划分，返回等于区域的左右边界
        int[] range = partition(arr, left, right, pivot);
        
        // 递归处理小于区域和大于区域，等于区域不需要处理
        quickSort(arr, left, range[0] - 1);   // 小于区域
        quickSort(arr, range[1] + 1, right);   // 大于区域
    }
    
    // 荷兰国旗三路划分，返回等于区域的[左边界, 右边界]
    private static int[] partition(int[] arr, int left, int right, int pivot) {
        int less = left - 1;      // 小于区域的右边界
        int more = right + 1;     // 大于区域的左边界
        int i = left;             // 当前遍历位置
        
        while(i < more) {
            if(arr[i] < pivot) {
                // 小于区域
                swap(arr, i++, ++less);
            } else if(arr[i] > pivot) {
                // 大于区域
                swap(arr, i, --more);
            } else {
                // 等于区域
                i++;
            }
        }
        
        // 返回等于区域的左右边界
        return new int[]{less + 1, more - 1};
    }
    
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
```

**三路快排的优势**：
- 对于有大量重复元素的数组，效率显著提高
- 等于区域不需要递归，减少了递归深度
- 最坏情况下（所有元素相同）时间复杂度从 O(n²) 降为 O(n)

### 算法题

#### 荷兰国旗问题

**问题描述**：

给定一个数组 `arr` 和一个数 `num`，请把小于 `num` 的数放在数组的左边，等于 `num` 的数放在数组的中间，大于 `num` 的数放在数组的右边。

**要求**：
- 额外空间复杂度：O(1)
- 时间复杂度：O(n)

**解题思路**：

这是**三路快排（3-way Quick Sort）**的核心思想。使用三个指针维护三个区域：
- `left`：小于区域的右边界（初始为 -1，表示小于区域为空）
- `right`：大于区域的左边界（初始为 arr.length，表示大于区域为空）
- `i`：当前遍历位置

根据 `arr[i]` 与 `num` 的大小关系，进行不同的操作：
1. **`arr[i] < num`**：属于小于区域，与 `left+1` 位置交换，`left++`, `i++`
2. **`arr[i] > num`**：属于大于区域，与 `right-1` 位置交换，`right--`，**i不动**（关键！）
3. **`arr[i] == num`**：属于等于区域，保持不动，只 `i++`

**为什么大于时i不动？**
- 因为从右边界交换过来的元素还没有被处理过，需要继续判断它属于哪个区域
- 而从左边界交换过来的元素是已经处理过的（i扫描过的），所以可以i++

**代码实现**：

```java
public class DutchNationalFlag {

    public static void partition(int[] arr, int num) {
        if (arr == null || arr.length < 2) {
            return;
        }
        //三个指针，分别表示左边界，右边界，当前遍历位置
        int left = -1;
        int right = arr.length;
        int i = 0;

        while(i < right){
            if(arr[i] < num){
                //小于：左边界和当前指针都右移
                swap(arr,i,left+1);
                i++;
                left++;
            }else if(arr[i] > num){
                //大于：只有右边界左移，当前指针不动，因为swap从右边界换来的元素还没有处理
                swap(arr,i,right-1);
                right--;
            }else{
                //等于：只移动当前指针
                i++;
            }
        }
    }
    
    private static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
```

**算法详解**：

1. **初始状态**：
   ```
   left = -1 (小于区域为空)
   right = arr.length (大于区域为空)
   i = 0 (从头开始遍历)
   
   [待处理区域: 0 ~ arr.length-1]
   ```

2. **遍历过程**（以 `[3, 5, 2, 7, 1, 8, 4, 6, 9, 5, 5]`，`num=5` 为例）：
   ```
   初始: [3, 5, 2, 7, 1, 8, 4, 6, 9, 5, 5]
          ^i                            ^right
   left=-1
   
   arr[0]=3 < 5: swap(0, 0), i++, left++
   [3, 5, 2, 7, 1, 8, 4, 6, 9, 5, 5]
       ^i  left=0                 ^right
   
   arr[1]=5 == 5: i++
   [3, 5, 2, 7, 1, 8, 4, 6, 9, 5, 5]
          ^i  left=0              ^right
   
   arr[2]=2 < 5: swap(2, 1), i++, left++
   [3, 2, 5, 7, 1, 8, 4, 6, 9, 5, 5]
             ^i  left=1           ^right
   
   ...继续处理，最终得到：
   [3, 2, 1, 4, 5, 5, 5, 6, 9, 8, 7]
    <5区域    =5区域    >5区域
   ```

3. **循环不变式**：
   - `[0, left]`：所有元素 < num
   - `[left+1, i-1]`：所有元素 == num
   - `[i, right-1]`：待处理区域
   - `[right, arr.length-1]`：所有元素 > num

4. **与快速排序的关系**：
   - 荷兰国旗问题是**三路快排**的partition过程
   - 传统快排将数组分为两部分：`< pivot` 和 `>= pivot`
   - 三路快排将数组分为三部分：`< pivot`、`== pivot`、`> pivot`
   - 当数组中有大量重复元素时，三路快排效率更高（等于区域不需要再递归）

**复杂度分析**：
- **时间复杂度**：O(n) - 遍历数组一次
- **空间复杂度**：O(1) - 只使用常数个额外变量

---

