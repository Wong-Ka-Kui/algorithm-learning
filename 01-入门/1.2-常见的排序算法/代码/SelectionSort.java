/**
 * 选择排序 (Selection Sort)
 *
 * 时间复杂度：O(n²)
 * 空间复杂度：O(1)
 * 稳定性：不稳定
 *
 * 算法思路：
 * 1. 在未排序序列中找到最小（大）元素
 * 2. 将其放到已排序序列的末尾
 * 3. 重复上述步骤，直到所有元素均排序完毕
 */
public class SelectionSort {
    // 选择排序算法
    // 先在【0，n-1】部分找到最小值，放到第一位；再在【1，n-1】部分找到最小值，放到第二位；以此类推。。。
    public static void selectionSort(int[] arr) {
        // 先做边界判断，数组长度小于等于1的，直接返回
        if(arr==null || arr.length<2){
            return;
        }

        // 由于j = i+1，所以i的取值范围应该是【0,arr.length-2】
        // j的取值范围应该是【1，arr.length-1】
        for(int i=0;i<arr.length-1;i++){
            int minIndex = i;
            for(int j=i+1;j<arr.length;j++){
                minIndex = (arr[j] < arr[minIndex])? j:minIndex;
            }
            swap(arr,i,minIndex);
        }
    }
    private static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // 测试方法
    public static void main(String[] args) {
        int[] arr = {64, 25, 12, 22, 11};
        System.out.println("排序前：");
        printArray(arr);

        selectionSort(arr);

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
