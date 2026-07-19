/**
 * 小和问题
 *
 * 题目描述：
 * 在一个数组中，每一个数左边比当前数小的数累加起来，叫做这个数组的小和。
 * 求一个数组的小和。
 *
 * 例子：[1, 3, 4, 2, 5]
 * - 1左边比1小的数：没有
 * - 3左边比3小的数：1
 * - 4左边比4小的数：1、3
 * - 2左边比2小的数：1
 * - 5左边比5小的数：1、3、4、2
 * 所以小和为：1 + 1 + 3 + 1 + 1 + 3 + 4 + 2 = 16
 *
 * 算法思路：
 * 利用归并排序的思想，在合并过程中统计小和
 *
 * 时间复杂度：O(n log n)
 * 空间复杂度：O(n)
 */
public class SmallSum {

    // 求小和，既可以看某个数左边有几个数字比他小，也可以反过来看右边有几个数字比他大（本题思路）
    public static int smallSum(int[] arr) {
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
        //在merge的过程中，既排序，也顺便计算小和
        int i = 0;
        int p1 = left;
        int p2 = middle+1;
        int ans = 0;

        int[] help = new int[right-left+1];
        while(p1<=middle && p2<=right){
            // (arr[p1] < arr[p2])?只能是'<'，在等于的时候先让右侧加入help数组，不计算小和
            // (right-p2+1)*arr[p1]，分别表示右侧大于该数字的个数*该数字 = 本轮添加的小和
            ans += (arr[p1] < arr[p2])? (right-p2+1)*arr[p1] : 0;
            help[i++] = (arr[p1] < arr[p2])? arr[p1++] : arr[p2++];
        }
        while(p1 <= middle){
            help[i++] = arr[p1++];
        }
        while(p2 <= right){
            help[i++] = arr[p2++];
        }
        //将help数组的答案元素，复制到arr数组中，（只是体现归并排序，在小和问题中其实不一定需要）
        for(int j=0;j<help.length;j++){
            arr[left+j] = help[j];
        }
        return ans;
    }

    // 测试方法
    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 2, 5};
        System.out.println("数组：");
        printArray(arr);

        int result = smallSum(arr);
        System.out.println("小和为：" + result);
        System.out.println("预期结果：16");
    }

    // 打印数组的辅助方法
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
