import java.util.Arrays;
import java.util.Random;

/**
 * 对数器（Logarithmic Comparator）
 *
 * 什么是对数器？
 * 对数器是一种测试方法，用于验证自己实现的算法是否正确。
 * 核心思想：通过大量随机测试用例，对比自己的实现和正确答案（如Java内置API）的结果。
 *
 * 对数器的优势：
 * 1. 自动化测试 - 不需要手动构造测试用例
 * 2. 覆盖面广 - 随机数据可能触发边界情况
 * 3. 快速验证 - 几秒钟就能跑成千上万次测试
 * 4. 增强信心 - 通过大量测试后，可以确信代码的正确性
 *
 * 对数器的组成部分：
 * 1. 随机数组生成器 - 生成测试数据
 * 2. 自己实现的算法 - 待测试的方法
 * 3. 标准答案 - 已知正确的实现（如Arrays.sort()）
 * 4. 结果比对器 - 判断两个结果是否一致
 * 5. 测试循环 - 执行大量测试
 */
public class Comparator {

    // ==================== 1. 随机数组生成器 ====================

    /**
     * 生成随机数组
     * @param maxSize 数组最大长度
     * @param maxValue 数组元素最大值
     * @return 随机数组
     */
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        Random rand = new Random();

        // 随机生成数组长度：[0, maxSize]
        int[] arr = new int[rand.nextInt(maxSize + 1)];

        /* 旧的实现方式（使用 Math.random()）：
        // 解释：Math.random()是[0,1)，乘以(maxSize + 1)后为[0,maxSize + 1)，
        // 强转int之后（向下取整）即为[0,maxSize]
        int[] arr = new int[(int)((maxSize + 1) * Math.random())];
        */

        for(int i = 0; i < arr.length; i++) {
            // 随机生成元素值：[-maxValue, maxValue]
            // nextInt(maxValue + 1) 生成 [0, maxValue]
            // 减去 nextInt(maxValue + 1) 可能得到负数，范围是 [-maxValue, maxValue]
            arr[i] = rand.nextInt(maxValue + 1) - rand.nextInt(maxValue + 1);

            /* 旧的实现方式（使用 Math.random()）：
            arr[i] = (int)((maxValue + 1) * Math.random())
                   - (int)(maxValue * Math.random());
            */
        }

        return arr;
    }

    // ==================== 2. 待测试的算法（以冒泡排序为例） ====================

    /**
     * 冒泡排序 - 待测试的实现
     */
    public static void bubbleSort(int[] arr) {
        if(arr == null || arr.length < 2) {
            return;
        }

        for(int e = arr.length - 1; e >= 0; e--) {
            for(int i = 0; i < e; i++) {
                if(arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                }
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // ==================== 3. 标准答案（使用Java内置API） ====================

    /**
     * 标准排序算法 - 使用Java内置的Arrays.sort()
     * 这是已知正确的实现，作为对比的基准
     */
    public static void correctSort(int[] arr) {
        Arrays.sort(arr);
    }

    // ==================== 4. 结果比对器 ====================

    /**
     * 数组复制
     */
    public static int[] copyArray(int[] arr) {
        if(arr == null) {
            return null;
        }
        int[] copy = new int[arr.length];
        for(int i = 0; i < arr.length; i++) {
            copy[i] = arr[i];
        }
        return copy;
    }

    /**
     * 比较两个数组是否相等
     */
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if(arr1 == null && arr2 == null) {
            return true;
        }
        if(arr1.length != arr2.length) {
            return false;
        }

        for(int i = 0; i < arr1.length; i++) {
            if(arr1[i] != arr2[i]) {
                return false;
            }
        }

        return true;
    }

    /**
     * 打印数组
     */
    public static void printArray(int[] arr) {
        if(arr == null) {
            System.out.println("null");
            return;
        }
        for(int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // ==================== 5. 对数器主逻辑 ====================

    public static void main(String[] args) {
        int testTimes = 10000;      // 测试次数
        int maxSize = 100;          // 数组最大长度
        int maxValue = 100;         // 数组元素最大值
        boolean success = true;

        System.out.println("对数器测试开始...");
        System.out.println("测试次数：" + testTimes);
        System.out.println("数组最大长度：" + maxSize);
        System.out.println("元素最大值：" + maxValue);
        System.out.println("----------------------------");

        for(int i = 0; i < testTimes; i++) {
            // 1. 生成随机数组
            int[] arr1 = generateRandomArray(maxSize, maxValue);

            // 2. 复制数组（因为排序会修改原数组）
            int[] arr2 = copyArray(arr1);

            // 3. 用自己的算法排序
            bubbleSort(arr1);

            // 4. 用标准算法排序
            correctSort(arr2);

            // 5. 比对结果
            if(!isEqual(arr1, arr2)) {
                success = false;
                System.out.println("测试失败！第 " + (i + 1) + " 次测试出错");
                System.out.println("原始数组：");
                printArray(arr1);
                System.out.println("你的排序结果：");
                printArray(arr1);
                System.out.println("正确的排序结果：");
                printArray(arr2);
                break;
            }
        }

        if(success) {
            System.out.println("✓ 所有测试通过！");
            System.out.println("共测试 " + testTimes + " 次，全部正确！");
        }
    }
}
