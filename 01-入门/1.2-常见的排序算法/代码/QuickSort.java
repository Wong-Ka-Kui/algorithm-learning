import java.util.Random;

/**
 * 快速排序 (Quick Sort)
 *
 * 时间复杂度：O(n*logn) - 平均情况；O(n²) - 最坏情况
 * 空间复杂度：O(logn)
 * 稳定性：不稳定
 *
 * 算法思路：
 * 1. 选择一个基准元素（pivot）
 * 2. 将数组划分为两部分：小于基准的放左边，大于基准的放右边
 * 3. 递归地对左右两部分进行快速排序
 * 4. 合并结果（由于是原地排序，不需要额外合并步骤）
 */
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

    // 测试方法
    public static void main(String[] args) {
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("排序前：");
        printArray(arr);

        quickSort(arr);

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
