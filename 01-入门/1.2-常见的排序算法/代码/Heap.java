/**
 * 堆结构 (Heap Structure)
 *
 * 堆的性质：
 * - 完全二叉树结构
 * - 大根堆：每个节点的值都大于或等于其子节点的值
 * - 小根堆：每个节点的值都小于或等于其子节点的值
 *
 * 用数组表示堆：
 * - 父节点索引 i，左孩子索引 2*i+1，右孩子索引 2*i+2
 * - 子节点索引 i，父节点索引 (i-1)/2
 *
 * 时间复杂度：
 * - heapInsert：O(log n)
 * - heapify：O(log n)
 */
public class Heap {
    private int[] heap;      // 堆数组
    private int heapSize;    // 当前堆的大小
    private final int limit; // 堆的最大容量

    /**
     * 构造函数：初始化堆
     * @param limit 堆的最大容量
     */
    public Heap(int limit) {
        heap = new int[limit];
        heapSize = 0;
        this.limit = limit;
    }

    /**
     * 判断堆是否为空
     */
    public boolean isEmpty() {
        return heapSize == 0;
    }

    /**
     * 判断堆是否已满
     */
    public boolean isFull() {
        return heapSize == limit;
    }

    /**
     * 向堆中添加元素
     * @param value 要添加的值
     */
    public void push(int value) {
        if (heapSize == limit) {
            throw new RuntimeException("堆已满！");
        }
        heap[heapSize] = value;
        heapInsert(heap, heapSize);
        heapSize++;
    }

    /**
     * 弹出堆顶元素（最大值）
     * @return 堆顶元素
     */
    public int pop() {
        if (heapSize == 0) {
            throw new RuntimeException("堆为空！");
        }
        int result = heap[0];
        swap(heap, 0, --heapSize);
        heapify(heap, 0, heapSize);
        return result;
    }

    /**
     * 查看堆顶元素（不移除）
     * @return 堆顶元素
     */
    public int peek() {
        if (heapSize == 0) {
            throw new RuntimeException("堆为空！");
        }
        return heap[0];
    }

    /**
     * 修改堆中指定位置的值，并维护大根堆性质
     * @param index 要修改的位置
     * @param newValue 新的值
     */
    public void modify(int index, int newValue) {
        if (index < 0 || index >= heapSize) {
            throw new RuntimeException("索引越界！");
        }

        int oldValue = heap[index];
        heap[index] = newValue;

        if (newValue > oldValue) {
            // 新值变大了，需要向上调整
            heapInsert(heap, index);
        } else if (newValue < oldValue) {
            // 新值变小了，需要向下调整
            heapify(heap, index, heapSize);
        }
        // 如果相等，不需要调整
    }

    /**
     * heapInsert：向堆中插入元素，并维护堆的性质
     * @param arr 堆数组
     * @param index 新插入元素的索引
     */
    public static void heapInsert(int[] arr, int index) {
        // 以大根堆为例：heapInsert保证用户每次新插入一个数字，我们都依然可以维护大根堆的结构
        // while循环，持续和自己父节点的值比较大小【当index=0时，(index-1)/2也是0，也会跳出while循环】
        while(arr[index] > arr[(index-1)/2]){
            swap(arr,index,(index-1)/2);

            //修改index的值，以继续while内的条件判断
            index = (index-1)/2;
        }
    }
    private static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * heapify：从index位置开始向下调整，维护堆的性质
     * @param arr 堆数组
     * @param index 开始调整的索引
     * @param heapSize 堆的大小
     */
    public static void heapify(int[] arr, int index, int heapSize) {
        //左孩子的下标
        int leftIdx = 2*index + 1;
        while(leftIdx <heapSize){ //左孩子下标不越界：说明至少还有孩子
            //先找出左右孩子中，数值较大的孩子的下标（如果有右孩子）
            int biggerIdx = (leftIdx+1 < heapSize && arr[leftIdx+1] > arr[leftIdx])? leftIdx+1 : leftIdx;
            //再用自身和较大孩子的值作比较，得到相对更大值的下标
            biggerIdx = (arr[index] >= arr[biggerIdx])? index : biggerIdx;

            if(biggerIdx == index){
                //如果当前就是自己更大，则无需继续heapify
                break;
            }
            swap(arr, index, biggerIdx);
            index = biggerIdx;
            //同时还要一起更新leftIdx的值
            leftIdx = 2*index + 1;
        }
    }

    // 测试方法
    public static void main(String[] args) {
        System.out.println("=== 测试大根堆 ===\n");

        // 创建一个容量为10的大根堆
        Heap maxHeap = new Heap(10);

        // 测试1：依次插入元素
        System.out.println("测试1：依次插入 [3, 2, 5, 1, 4, 7, 6]");
        int[] testData = {3, 2, 5, 1, 4, 7, 6};
        for (int num : testData) {
            maxHeap.push(num);
            System.out.println("插入 " + num + " 后，堆顶元素：" + maxHeap.peek());
        }

        // 测试2：查看堆顶
        System.out.println("\n当前堆顶（最大值）：" + maxHeap.peek());

        // 测试3：依次弹出元素（应该按从大到小的顺序）
        System.out.println("\n测试2：依次弹出所有元素（应该从大到小）");
        while (!maxHeap.isEmpty()) {
            System.out.print(maxHeap.pop() + " ");
        }
        System.out.println();

        // 测试4：静态方法测试 heapInsert
        System.out.println("\n测试3：测试静态 heapInsert 方法");
        int[] arr = {3, 2, 5, 1, 4};
        System.out.println("原始数组：");
        printArray(arr);

        System.out.println("\n逐步构建大根堆：");
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
            System.out.print("第 " + (i+1) + " 步：");
            printArray(arr);
        }

        // 测试5：静态方法测试 heapify
        System.out.println("\n测试4：测试静态 heapify 方法");
        int[] arr2 = {1, 5, 3, 2, 4};
        System.out.println("假设数组 [1, 5, 3, 2, 4] 中，只有arr2[0]=1不符合大根堆性质");
        System.out.println("调整前：");
        printArray(arr2);

        heapify(arr2, 0, arr2.length);
        System.out.println("对位置0执行heapify后：");
        printArray(arr2);

        // 测试6：测试 modify 方法
        System.out.println("\n测试5：测试 modify 方法");
        Heap modifyHeap = new Heap(10);
        int[] modifyData = {10, 8, 9, 5, 6, 7, 4};
        for (int num : modifyData) {
            modifyHeap.push(num);
        }

        System.out.println("初始堆（已构建好的大根堆）：");
        modifyHeap.printHeap();
        System.out.println("堆顶：" + modifyHeap.peek());

        // 情况1：将某个值改大（向上调整）
        System.out.println("\n情况1：将 heap[3]=5 改为 15（变大，需要向上调整）");
        modifyHeap.modify(3, 15);
        modifyHeap.printHeap();
        System.out.println("堆顶：" + modifyHeap.peek() + "（15成为新的堆顶）");

        // 情况2：将某个值改小（向下调整）
        System.out.println("\n情况2：将 heap[0]=15 改为 3（变小，需要向下调整）");
        modifyHeap.modify(0, 3);
        modifyHeap.printHeap();
        System.out.println("堆顶：" + modifyHeap.peek() + "（10重新成为堆顶）");

        // 情况3：将某个值改为相等（不需要调整）
        System.out.println("\n情况3：将 heap[1]=9 改为 9（相等，不需要调整）");
        modifyHeap.modify(1, 9);
        modifyHeap.printHeap();
        System.out.println("堆顶：" + modifyHeap.peek() + "（堆结构不变）");

        System.out.println("\n=== 测试完成 ===");
    }

    /**
     * 打印当前堆的内容（用于调试）
     */
    public void printHeap() {
        System.out.print("堆内容：[");
        for (int i = 0; i < heapSize; i++) {
            System.out.print(heap[i]);
            if (i < heapSize - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    // 打印数组的辅助方法
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
