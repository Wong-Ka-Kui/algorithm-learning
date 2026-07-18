/**
 * 题目：局部最小值问题
 *
 * 问题描述：
 * 给定一个无序数组 arr，保证相邻的每两个数都不相等。
 * 要求找到任意一个局部最小值（极小值）的位置。
 *
 * 局部最小值定义：
 * - 如果 arr[0] < arr[1]，则 arr[0] 是局部最小值
 * - 如果 arr[n-1] < arr[n-2]，则 arr[n-1] 是局部最小值
 * - 如果 arr[i-1] > arr[i] < arr[i+1]，则 arr[i] 是局部最小值
 *
 * 要求：
 * - 时间复杂度：O(log n)（好于 O(n)）
 * - 空间复杂度：O(1)
 *
 * 示例：
 * 输入：arr = [6, 5, 3, 4, 6, 7, 8]
 * 输出：2（arr[2] = 3 是局部最小值）
 *
 * 输入：arr = [9, 8, 7, 6, 5, 4, 3]
 * 输出：6（arr[6] = 3 是局部最小值，整个数组递减，最后一个元素是局部最小）
 *
 * 提示：
 * - 不需要找到全局最小值，只需要找到任意一个局部最小值即可
 * - 可以使用二分思想：根据中间位置与相邻元素的关系，判断往哪个方向搜索
 * - 先检查两端是否是局部最小值
 * - 如果两端都不是，说明左端向右递减，右端向左递减，中间必然存在局部最小值
 */
public class LocalMinimum {

    /**
     * 找到数组中任意一个局部最小值的位置
     * @param arr 无序数组，保证相邻元素不相等
     * @return 局部最小值的索引
     */
    public static int findLocalMinimum(int[] arr) {
        //最先对arr数组本身做合法性校验
        if(arr==null || arr.length==0){
            return -1;
        }
        if(arr.length == 1){
            return 0;
        }
        
        //再判断数组两端是否是局部最小值，若不是的话【左侧递减，右侧递增】，则中间部分必然存在局部最小值
        int n = arr.length;
        if(arr[0]<arr[1]){
            return 0;
        }else if(arr[n-1]<arr[n-2]){
            return n-1;
        }

        //排除了两个端点之后，进入二分逻辑
        int left = 1;
        int right = n-2;
        while(left <= right){
            int middle = left + (right - left)/2;
            if(arr[middle]<arr[middle-1] && arr[middle]<arr[middle+1]){
                //极小值
                return middle;
            }else if(arr[middle] > arr[middle-1]){
                //递增，说明左边范围内一定存在极小值
                //由于采用【左闭右闭】的方式，middle本身已经被证否，所以middle-1
                right = middle - 1;
            }else{
                //只剩下递减分支
                left = middle + 1;
            }
        }

        return -1; // 占位返回值
    }

    // 测试方法
    public static void main(String[] args) {
        // 测试用例1：中间有局部最小值
        int[] arr1 = {6, 5, 3, 4, 6, 7, 8};
        System.out.println("测试用例1：");
        System.out.println("数组：[6, 5, 3, 4, 6, 7, 8]");
        int result1 = findLocalMinimum(arr1);
        System.out.println("局部最小值位置：" + result1 + "，值为：" + arr1[result1]);
        System.out.println("期望：位置 2，值为 3");
        System.out.println();

        // 测试用例2：整体递减，最后一个是局部最小
        int[] arr2 = {9, 8, 7, 6, 5, 4, 3};
        System.out.println("测试用例2：");
        System.out.println("数组：[9, 8, 7, 6, 5, 4, 3]");
        int result2 = findLocalMinimum(arr2);
        System.out.println("局部最小值位置：" + result2 + "，值为：" + arr2[result2]);
        System.out.println("期望：位置 6，值为 3");
        System.out.println();

        // 测试用例3：整体递增，第一个是局部最小
        int[] arr3 = {1, 2, 3, 4, 5, 6, 7};
        System.out.println("测试用例3：");
        System.out.println("数组：[1, 2, 3, 4, 5, 6, 7]");
        int result3 = findLocalMinimum(arr3);
        System.out.println("局部最小值位置：" + result3 + "，值为：" + arr3[result3]);
        System.out.println("期望：位置 0，值为 1");
        System.out.println();

        // 测试用例4：先升后降
        int[] arr4 = {4, 5, 6, 7, 3, 2, 1};
        System.out.println("测试用例4：");
        System.out.println("数组：[4, 5, 6, 7, 3, 2, 1]");
        int result4 = findLocalMinimum(arr4);
        System.out.println("局部最小值位置：" + result4 + "，值为：" + arr4[result4]);
        System.out.println("期望：位置 0 或 6 都可以");
        System.out.println();

        // 测试用例5：多个局部最小值
        int[] arr5 = {5, 3, 6, 2, 8, 1, 9};
        System.out.println("测试用例5：");
        System.out.println("数组：[5, 3, 6, 2, 8, 1, 9]");
        int result5 = findLocalMinimum(arr5);
        System.out.println("局部最小值位置：" + result5 + "，值为：" + arr5[result5]);
        System.out.println("期望：位置 1, 3, 5 中的任意一个");
        System.out.println();

        // 测试用例6：只有一个元素
        int[] arr6 = {10};
        System.out.println("测试用例6：");
        System.out.println("数组：[10]");
        int result6 = findLocalMinimum(arr6);
        System.out.println("局部最小值位置：" + result6 + "，值为：" + arr6[result6]);
        System.out.println("期望：位置 0，值为 10");
    }
}
