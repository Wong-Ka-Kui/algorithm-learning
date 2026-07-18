/**
 * 题目：在一个有序数组中，找一个数字是否存在
 *
 * 要求：
 * - 时间复杂度：O(log n)
 * - 空间复杂度：O(1)
 *
 * 示例：
 * 输入：arr = [1, 3, 5, 7, 9, 11], target = 7
 * 输出：true（或返回索引 3）
 *
 * 输入：arr = [1, 3, 5, 7, 9, 11], target = 6
 * 输出：false（或返回 -1）
 *
 * 提示：
 * - 利用数组的有序性，使用二分查找
 * - 每次比较中间元素，将搜索范围缩小一半
 */
public class BinarySearch {

    /**
     * 在有序数组中查找目标值
     * @param arr 有序数组（升序）
     * @param target 目标值
     * @return 目标值的索引，不存在返回 -1
     */
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length-1;

        //while循环的条件必须是>=，因为即使是=时，所指向的元素也需要检查
        while(left <= right){
            int middle = left + (right-left)/2;
            if(arr[middle] == target){
                return middle;
            }else if(arr[middle] < target){
                left = middle+1;
            }else{
                right = middle-1;
            }
        }
        return -1; // 占位返回值
    }

    // 测试方法
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};

        // 测试用例1：查找存在的元素
        System.out.println("测试用例1：");
        System.out.println("数组：[1, 3, 5, 7, 9, 11, 13, 15, 17, 19]");
        System.out.println("查找 7：索引 = " + binarySearch(arr, 7));
        System.out.println("期望输出：3");
        System.out.println();

        // 测试用例2：查找不存在的元素
        System.out.println("测试用例2：");
        System.out.println("查找 6：索引 = " + binarySearch(arr, 6));
        System.out.println("期望输出：-1");
        System.out.println();

        // 测试用例3：查找第一个元素
        System.out.println("测试用例3：");
        System.out.println("查找 1：索引 = " + binarySearch(arr, 1));
        System.out.println("期望输出：0");
        System.out.println();

        // 测试用例4：查找最后一个元素
        System.out.println("测试用例4：");
        System.out.println("查找 19：索引 = " + binarySearch(arr, 19));
        System.out.println("期望输出：9");
        System.out.println();

        // 测试用例5：边界测试
        System.out.println("测试用例5：");
        System.out.println("查找 0：索引 = " + binarySearch(arr, 0));
        System.out.println("期望输出：-1");
        System.out.println();
        System.out.println("查找 20：索引 = " + binarySearch(arr, 20));
        System.out.println("期望输出：-1");
    }
}
