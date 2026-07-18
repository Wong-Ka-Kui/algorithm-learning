/**
 * 插入排序 (Insertion Sort)
 *
 * 时间复杂度：O(n²) - 最坏情况；O(n) - 最好情况（已排序数组）
 * 空间复杂度：O(1)
 * 稳定性：稳定
 *
 * 算法思路：
 * 1. 将数组分为已排序部分和未排序部分
 * 2. 初始时，第一个元素视为已排序
 * 3. 从第二个元素开始，依次将未排序元素插入到已排序部分的正确位置
 * 4. 插入时，从后向前扫描已排序部分，找到合适的位置插入
 */
public class InsertionSort {
    // 依次维护【0，1】，【0，2】，【0，3】。。。的排序
    // 单次维护的过程中从后往前扫描，不断交替元素，直到找到合适的插入点
    public static void insertionSort(int[] arr) {
        if(arr==null || arr.length<2){
            return;
        }
        for(int i=1;i<arr.length;i++){
            for(int j=i-1;j>=0;j--){
                if(arr[j] > arr[j+1]){
                    swap(arr,j,j+1);
                }else{
                    break;
                }
            }
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

        insertionSort(arr);

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
