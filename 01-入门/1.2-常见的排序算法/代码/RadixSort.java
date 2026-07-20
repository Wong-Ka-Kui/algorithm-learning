/**
 * 基数排序 (Radix Sort)
 *
 * 时间复杂度：O(d * (n + k))，其中 d 是最大数字的位数，k 是基数（十进制为10）
 * 空间复杂度：O(n + k)
 * 稳定性：稳定
 *
 * 算法思路：
 * 1. 基数排序是一种非比较型整数排序算法
 * 2. 按照低位先排序，然后收集；再按照高位排序，然后再收集；以此类推，直到最高位
 * 3. 有时候有些属性是有优先级顺序的，先按低优先级排序，再按高优先级排序
 * 4. 最后的次序就是高优先级高的在前，高优先级相同的低优先级高的在前
 *
 * 核心步骤：
 * 1. 找出数组中的最大值，确定最大位数
 * 2. 从最低位开始，按照每一位的数字进行计数排序
 * 3. 从最低位排序一直到最高位排序完成后，数组就变成一个有序序列
 */
public class RadixSort {
    // 基数排序算法
    // 提示：
    // 1. 先找到数组中的最大值，确定最大位数
    // 2. 从个位开始，对每一位进行计数排序
    // 3. 重复步骤2，直到最高位
    public static void radixSort(int[] arr) {
        if(arr==null || arr.length<2){
            return;
        }
        radixSort(arr,0,arr.length-1,maxBit(arr));
    }
    private static int maxBit(int[] arr){
        int max = Integer.MIN_VALUE;
        for(int i=0;i<arr.length;i++){
            max = Math.max(max,arr[i]);
        }
        int maxBit = (max+"").length();
        return maxBit;
    }
    private static void radixSort(int[] arr,int left,int right,int digit){
        final int radix = 10;
        int i = 0,j = 0;
        //准备辅助空间
        int[] bucket = new int[right-left+1];
        for(int d=1;d<=digit;d++){
            int[] count = new int[radix];

            //count[i]表示当前进位上，数值小于等于i的数字有几个
            for(i=left;i<=right;i++){
                j = getDigit(arr[i],d);
                count[j]++;
            }
            //count[i]变成前缀和版本
            for(i=1;i<radix;i++){
                count[i] = count[i] + count[i-1];
            }
            //从右往左出桶
            for(i=right;i>=left;i--){
                j = getDigit(arr[i],d);
                bucket[count[j]-1] = arr[i];
                count[j]--;
            }
            //将当前结果写回arr[]数组
            for(i=left,j=0;i<=right;i++,j++){
                arr[i] = bucket[j];
            }
        }
    }

    // 获取某个数字指定位上的数值（d=1表示个位，d=2表示十位，d=3表示百位...）
    private static int getDigit(int num, int d) {
        // 例如：getDigit(123, 1) 返回 3（个位）
        //       getDigit(123, 2) 返回 2（十位）
        //       getDigit(123, 3) 返回 1（百位）
        return ((num / ((int) Math.pow(10, d - 1))) % 10);
    }

    private static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    // 测试方法
    public static void main(String[] args) {
        int[] arr = {170, 45, 75, 90, 802, 24, 2, 66};
        System.out.println("排序前：");
        printArray(arr);

        radixSort(arr);

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
