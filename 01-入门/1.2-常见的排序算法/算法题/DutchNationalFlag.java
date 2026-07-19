/**
 * 荷兰国旗问题
 *
 * 问题描述：
 * 给定一个数组arr，和一个数num，请把小于num的数放在数组的左边，
 * 等于num的数放在数组的中间，大于num的数放在数组的右边。
 *
 * 要求：额外空间复杂度O(1)，时间复杂度O(n)
 */
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

    public static void main(String[] args) {
        int[] arr = {3, 5, 2, 7, 1, 8, 4, 6, 9, 5, 5};
        int num = 5;
        System.out.println("原始数组：");
        printArray(arr);

        partition(arr, num);

        System.out.println("\n划分后的数组（以" + num + "为基准）：");
        printArray(arr);
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
