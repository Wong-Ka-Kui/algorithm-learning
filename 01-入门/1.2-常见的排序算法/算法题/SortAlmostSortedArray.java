import java.util.PriorityQueue;

/**
 * 堆排序扩展题目
 *
 * 题目描述：
 * 已知一个几乎有序的数组，几乎有序是指，如果把数组排好顺序的话，每个元素
 * 移动的距离可以不超过k，并且k相对于数组来说比较小。请选择一个合适的
 * 排序算法针对这个数据进行排序。
 *
 * 示例：
 * 输入：arr = [2, 1, 4, 3, 6, 5, 8, 7, 10, 9], k = 2
 * 输出：[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
 *
 * 解释：原数组中，元素2最终位置是索引1，移动距离不超过2
 *
 * 算法思路：
 * 利用小根堆来优化排序过程，因为每个元素移动距离不超过k，
 * 所以前k+1个元素中一定包含最小值。
 *
 * 时间复杂度：O(n log k)
 * 空间复杂度：O(k)
 */
public class SortAlmostSortedArray {

    /**
     * 对几乎有序的数组进行排序
     * @param arr 几乎有序的数组
     * @param k 元素移动的最大距离
     */
        // 提示：
        // 1. 可以使用Java的PriorityQueue（小根堆）
        // 2. 维护一个大小为k+1的小根堆
        // 3. 依次弹出堆顶元素放回数组
    public static void sortAlmostSortedArray(int[] arr, int k) {
        if (arr == null || arr.length < 2) {
            return;
        }
        //默认小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int index = 0;

        //Math.min主动防止k过大的情况，注意是k+1个元素
        for(;index<Math.min(arr.length, k+1);index++){
            heap.add(arr[index]);
        }
        int i = 0;
        for(;index < arr.length;i++,index++){
            //弹出的首位就是这k+1范围内的最小值
            arr[i] = heap.poll();
            //index是全局变量，之前已经初始化到第k位了
            heap.add(arr[index]);
        }
        while(!heap.isEmpty()){
            arr[i++] = heap.poll();
        }
    }

    // 测试方法
    public static void main(String[] args) {
        System.out.println("测试1：k=2的几乎有序数组");
        int[] arr1 = {2, 1, 4, 3, 6, 5, 8, 7, 10, 9};
        System.out.println("排序前：");
        printArray(arr1);

        sortAlmostSortedArray(arr1, 2);

        System.out.println("排序后：");
        printArray(arr1);

        System.out.println("\n测试2：k=3的几乎有序数组");
        int[] arr2 = {3, 2, 1, 5, 4, 7, 6, 8};
        System.out.println("排序前：");
        printArray(arr2);

        sortAlmostSortedArray(arr2, 3);

        System.out.println("排序后：");
        printArray(arr2);
    }

    // 打印数组的辅助方法
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
