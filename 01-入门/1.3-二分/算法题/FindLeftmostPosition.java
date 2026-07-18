/**
 * 题目：在有序数组中，找大于等于某个数的最左侧位置
 *
 * 要求：
 * - 时间复杂度：O(log n)
 * - 空间复杂度：O(1)
 *
 * 示例：
 * 输入：arr = [1, 2, 2, 2, 3, 5, 7], target = 2
 * 输出：1（第一个 2 的位置）
 *
 * 输入：arr = [1, 2, 3, 5, 7], target = 4
 * 输出：3（第一个大于等于 4 的位置，即 5 的位置）
 *
 * 输入：arr = [1, 2, 3, 5, 7], target = 8
 * 输出：-1（不存在大于等于 8 的元素）
 *
 * 提示：
 * - 使用二分查找的变体
 * - 当找到等于 target 的元素时，不能立即返回，需要继续向左查找
 * - 当 arr[mid] >= target 时，记录位置并继续向左搜索
 */
public class FindLeftmostPosition {

    /**
     * 找到大于等于 target 的最左侧位置
     * @param arr 有序数组（升序）
     * @param target 目标值
     * @return 大于等于 target 的最左侧位置索引，不存在返回 -1
     */
    public static int findLeftmostPosition(int[] arr, int target) {
        //这道题，在找到target元素时不能直接返回，因为你不知道左边还有没有更合适的答案，所以必须到二分结束
        int left = 0;
        int right = arr.length - 1;
        int index = -1;
        while(left <= right){
            int middle = left + (right-left)/2;
            if(arr[middle] >= target){
                index = middle;
                right = middle - 1;
            }else{
                left = middle + 1;
            }
        }
        return index;
    }

    // 测试方法
    public static void main(String[] args) {
        // 测试用例1：查找重复元素的最左位置
        int[] arr1 = {1, 2, 2, 2, 3, 5, 7};
        System.out.println("测试用例1：");
        System.out.println("数组：[1, 2, 2, 2, 3, 5, 7]");
        System.out.println("查找 >= 2 的最左位置：" + findLeftmostPosition(arr1, 2));
        System.out.println("期望输出：1");
        System.out.println();

        // 测试用例2：查找不存在的元素（返回第一个大于它的位置）
        int[] arr2 = {1, 2, 3, 5, 7};
        System.out.println("测试用例2：");
        System.out.println("数组：[1, 2, 3, 5, 7]");
        System.out.println("查找 >= 4 的最左位置：" + findLeftmostPosition(arr2, 4));
        System.out.println("期望输出：3（元素 5 的位置）");
        System.out.println();

        // 测试用例3：查找比所有元素都大的值
        System.out.println("测试用例3：");
        System.out.println("数组：[1, 2, 3, 5, 7]");
        System.out.println("查找 >= 8 的最左位置：" + findLeftmostPosition(arr2, 8));
        System.out.println("期望输出：-1");
        System.out.println();

        // 测试用例4：查找比所有元素都小的值
        System.out.println("测试用例4：");
        System.out.println("数组：[1, 2, 3, 5, 7]");
        System.out.println("查找 >= 0 的最左位置：" + findLeftmostPosition(arr2, 0));
        System.out.println("期望输出：0（元素 1 的位置）");
        System.out.println();

        // 测试用例5：所有元素都相同
        int[] arr3 = {5, 5, 5, 5, 5};
        System.out.println("测试用例5：");
        System.out.println("数组：[5, 5, 5, 5, 5]");
        System.out.println("查找 >= 5 的最左位置：" + findLeftmostPosition(arr3, 5));
        System.out.println("期望输出：0");
    }
}
