/**
 * 冒泡排序 (Bubble Sort)
 *
 * 时间复杂度：O(n²)
 * 空间复杂度：O(1)
 * 稳定性：稳定
 *
 * 算法思路：
 * 1. 比较相邻的元素，如果前一个比后一个大，就交换它们
 * 2. 对每一对相邻元素做同样的工作，从开始第一对到结尾的最后一对
 * 3. 这步做完后，最后的元素会是最大的数
 * 4. 针对所有的元素重复以上的步骤，除了最后一个
 * 5. 持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较
 */
public class BubbleSort {
    // 冒泡排序算法
    // 先在【0，n-1】部分找到最大值，放到第n-1位；再在【0，n-2】部分找到最大值，放到第n-2位；以此类推。。。
    public static void bubbleSort(int[] arr) {
        if(arr==null || arr.length<2){
            return;
        }
        for(int e=arr.length-1;e>=0;e--){
            for(int i=0;i<e;i++){
                if(arr[i] > arr[i+1]){
                    swap(arr,i,i+1);
                }
            }
        }
    }
    private static void swap(int[] arr,int i,int j){
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    // 测试方法
    public static void main(String[] args) {
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("排序前：");
        printArray(arr);

        bubbleSort(arr);

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
