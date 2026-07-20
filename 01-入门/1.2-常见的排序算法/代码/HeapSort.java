/**
 * 堆排序 (Heap Sort)
 *
 * 时间复杂度：O(n log n)
 * 空间复杂度：O(1)
 * 稳定性：不稳定
 *
 * 算法思路：
 * 1. 建堆：将无序数组构建成大根堆（或小根堆）
 * 2. 排序：将堆顶元素（最大值）与堆的最后一个元素交换，堆大小减1
 * 3. 调整：对新的堆顶元素执行 heapify 操作，维护堆的性质
 * 4. 重复步骤2-3，直到堆大小为1
 */
public class HeapSort {
    public static void heapSort(int[] arr) {
        if(arr==null || arr.length<2){
            return;
        }
        //建堆：将数组内元素形成一个大根堆
        for(int i=0;i<arr.length;i++){
            heapInsert(arr,i);
        }
        int heapSize = arr.length;

        //首次交换，初始化
        swap(arr,0,--heapSize);
        while(heapSize > 0){
            heapify(arr, 0,heapSize);
            //每次heapify完成后，再次进行交换
            swap(arr,0,--heapSize);
        }
    }
    private static void heapInsert(int[] arr, int index) {
        // 以大根堆为例：heapInsert保证用户每次新插入一个数字，我们都依然可以维护大根堆的结构
        // while循环，持续和自己父节点的值比较大小【当index=0时，(index-1)/2也是0，也会跳出while循环】
        while(arr[index] > arr[(index-1)/2]){
            swap(arr,index,(index-1)/2);

            //修改index的值，以继续while内的条件判断
            index = (index-1)/2;
        }
    }
    private static void heapify(int[] arr, int index, int heapSize) {
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
    private static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // 测试方法
    public static void main(String[] args) {
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("排序前：");
        printArray(arr);

        heapSort(arr);

        System.out.println("排序后：");
        printArray(arr);
    }

    // 打印数组的辅助方法
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
