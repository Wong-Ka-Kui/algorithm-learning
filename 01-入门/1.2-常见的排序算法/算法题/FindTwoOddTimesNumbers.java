/**
 * 题目：在一个数组中，有两个数字出现了奇数次，其他数字出现了偶数次，请找到这两个数字
 *
 * 要求：
 * - 时间复杂度：O(n)
 * - 空间复杂度：O(1)
 *
 * 示例：
 * 输入：[1, 2, 3, 2, 1, 4]
 * 输出：3 和 4
 *
 * 输入：[5, 5, 7, 7, 7, 9, 9, 9, 11, 11]
 * 输出：7 和 9
 *
 * 提示：
 * - 首先对所有数字进行异或，得到两个奇数次数字的异或结果（eor = a ^ b）
 * - 找到 eor 中任意一个为 1 的二进制位（表示 a 和 b 在该位不同）
 * - 根据这一位将数组分为两组，分别异或，得到两个数字
 */
public class FindTwoOddTimesNumbers {

    /**
     * 找到数组中两个出现奇数次的数字
     * @param arr 输入数组
     * @return 包含两个出现奇数次数字的数组
     */
    public static int[] findTwoOddTimesNumbers(int[] arr) {
        int eor = 0;
        for(int num:arr){
            eor ^= num;
        }
        //此时：eor = a ^ b
        //并且由于a != b，所以eor != 0，eor的二进制位上必然有一位为1
        //使用rightOne变量，记录eor最右侧的1（rightOne其他位上为0）
        int rightOne = eor & (~eor + 1);

        int onlyOne = 0;
        for(int num:arr){
            if((num & rightOne) == 0){
                onlyOne ^= num;
            }
        }
        return new int[]{onlyOne,onlyOne^eor};

    }

    // 测试方法
    public static void main(String[] args) {
        // 测试用例1：3 和 4 各出现 1 次（奇数次）
        int[] arr1 = {1, 2, 3, 2, 1, 4};
        System.out.println("测试用例1：");
        System.out.println("数组：[1, 2, 3, 2, 1, 4]");
        int[] result1 = findTwoOddTimesNumbers(arr1);
        System.out.println("出现奇数次的数字：" + result1[0] + " 和 " + result1[1]);
        System.out.println("期望输出：3 和 4（顺序可能不同）");
        System.out.println();

        // 测试用例2：7 出现 3 次，9 出现 3 次（奇数次）
        int[] arr2 = {5, 5, 7, 7, 7, 9, 9, 9, 11, 11};
        System.out.println("测试用例2：");
        System.out.println("数组：[5, 5, 7, 7, 7, 9, 9, 9, 11, 11]");
        int[] result2 = findTwoOddTimesNumbers(arr2);
        System.out.println("出现奇数次的数字：" + result2[0] + " 和 " + result2[1]);
        System.out.println("期望输出：7 和 9（顺序可能不同）");
        System.out.println();

        // 测试用例3：2 和 8 各出现 1 次
        int[] arr3 = {1, 1, 3, 3, 5, 5, 2, 8};
        System.out.println("测试用例3：");
        System.out.println("数组：[1, 1, 3, 3, 5, 5, 2, 8]");
        int[] result3 = findTwoOddTimesNumbers(arr3);
        System.out.println("出现奇数次的数字：" + result3[0] + " 和 " + result3[1]);
        System.out.println("期望输出：2 和 8（顺序可能不同）");
        System.out.println();

        // 测试用例4：6 出现 5 次，10 出现 3 次
        int[] arr4 = {6, 6, 6, 6, 6, 10, 10, 10, 2, 2, 4, 4};
        System.out.println("测试用例4：");
        System.out.println("数组：[6, 6, 6, 6, 6, 10, 10, 10, 2, 2, 4, 4]");
        int[] result4 = findTwoOddTimesNumbers(arr4);
        System.out.println("出现奇数次的数字：" + result4[0] + " 和 " + result4[1]);
        System.out.println("期望输出：6 和 10（顺序可能不同）");
    }
}
