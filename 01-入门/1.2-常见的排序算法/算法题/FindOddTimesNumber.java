/**
 * 题目：在一个数组中，只有一个数字出现了奇数次，其他数字出现了偶数次，请找到这个数字
 *
 * 要求：
 * - 时间复杂度：O(n)
 * - 空间复杂度：O(1)
 *
 * 示例：
 * 输入：[1, 2, 3, 2, 1]
 * 输出：3
 *
 * 输入：[5, 5, 7, 7, 7, 9, 9]
 * 输出：7
 *
 * 提示：
 * - 利用异或运算的性质：a ^ a = 0，a ^ 0 = a
 * - 相同的数字异或结果为0，所有出现偶数次的数字会相互抵消
 */
public class FindOddTimesNumber {

    /**
     * 找到数组中唯一出现奇数次的数字
     * @param arr 输入数组
     * @return 出现奇数次的数字
     */
    public static int findOddTimesNumber(int[] arr) {
        int eor = 0;
        for(int num:arr){
            eor ^= num;
        }
        //若这个数字（假设为n）不为0，则答案为n^0=n；若为0，则答案为0^0=0
        return eor;
    }

    // 测试方法
    public static void main(String[] args) {
        // 测试用例1：3 出现 1 次（奇数次）
        int[] arr1 = {1, 2, 3, 2, 1};
        System.out.println("测试用例1：");
        System.out.println("数组：[1, 2, 3, 2, 1]");
        System.out.println("出现奇数次的数字：" + findOddTimesNumber(arr1));
        System.out.println("期望输出：3");
        System.out.println();

        // 测试用例2：7 出现 3 次（奇数次）
        int[] arr2 = {5, 5, 7, 7, 7, 9, 9};
        System.out.println("测试用例2：");
        System.out.println("数组：[5, 5, 7, 7, 7, 9, 9]");
        System.out.println("出现奇数次的数字：" + findOddTimesNumber(arr2));
        System.out.println("期望输出：7");
        System.out.println();

        // 测试用例3：只有一个元素
        int[] arr3 = {10};
        System.out.println("测试用例3：");
        System.out.println("数组：[10]");
        System.out.println("出现奇数次的数字：" + findOddTimesNumber(arr3));
        System.out.println("期望输出：10");
        System.out.println();

        // 测试用例4：多个数字，8 出现 5 次（奇数次）
        int[] arr4 = {1, 1, 2, 2, 3, 3, 8, 8, 8, 8, 8};
        System.out.println("测试用例4：");
        System.out.println("数组：[1, 1, 2, 2, 3, 3, 8, 8, 8, 8, 8]");
        System.out.println("出现奇数次的数字：" + findOddTimesNumber(arr4));
        System.out.println("期望输出：8");
    }
}
