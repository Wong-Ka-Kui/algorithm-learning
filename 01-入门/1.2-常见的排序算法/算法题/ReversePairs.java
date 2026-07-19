/**
 * 逆序对问题
 *
 * 题目描述：
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
 * 输入一个数组，求出这个数组中的逆序对的总数。
 *
 * 例子：[7, 5, 6, 4]
 * 逆序对有：(7,5), (7,6), (7,4), (5,4), (6,4)
 * 逆序对总数：5
 *
 * 算法思路：
 * 利用归并排序的思想，在合并过程中统计逆序对
 *
 * 时间复杂度：O(n log n)
 * 空间复杂度：O(n)
 */
public class ReversePairs {

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

    // 测试方法
    public static void main(String[] args) {
        int[] arr = {7, 5, 6, 4};
        System.out.println("数组：");
        printArray(arr);

        int result = reversePairs(arr);
        System.out.println("逆序对总数：" + result);
        System.out.println("预期结果：5");
    }

    // 打印数组的辅助方法
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
