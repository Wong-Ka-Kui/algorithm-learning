/**
 * 归并排序 (Merge Sort)
 *
 * 时间复杂度：O(n log n)
 * 空间复杂度：O(n)
 * 稳定性：稳定
 *
 * 算法思路：
 * 1. 分解：将数组递归地分成两半
 * 2. 解决：递归地对两个子数组进行归并排序
 * 3. 合并：将两个已排序的子数组合并成一个有序数组
 */
public class MergeSort {
    public static void mergeSort(int[] arr) {
        // 先做边界判断
        if(arr==null || arr.length<2){
            return;
        }
        process(arr,0,arr.length-1);
    }
    private static void process(int[] arr,int left,int right){
        if(left == right){
            return;
        }
        int middle = left + (right-left)/2;
        process(arr, left, middle);
        process(arr, middle+1, right);
        merge(arr,left,middle,right);
    }
    private static void merge(int[] arr,int left,int middle,int right){
        //创建辅助数组，用于存放合并后的结果
        int[] help = new int[right-left+1];
        //三个指针
        int i = 0;
        int p0 = left;
        int p1 = middle+1;
        while(p0<=middle && p1<=right){
            //(arr[p0] <= arr[p1])?，使用<=，可以保证排序的稳定性
            help[i++] = (arr[p0] <= arr[p1])? arr[p0++] : arr[p1++];
        }
        while(p0 <= middle){
            help[i++] = arr[p0++];
        }
        while(p1 <= right){
            help[i++] = arr[p1++];
        }
        for(int j=0;j<help.length;j++){
            arr[left+j] = help[j];
        }
    }

    // 测试方法
    public static void main(String[] args) {
        int[] arr = {64, 25, 12, 22, 11};
        System.out.println("排序前：");
        printArray(arr);

        mergeSort(arr);

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
