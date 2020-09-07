package DP;

import java.util.Arrays;

/**
 * 等差数列划分
 * 如果一个数列至少有三个元素，并且任意两个相邻元素之差相同，则称该数列为等差数列。
 * 例如，以下数列为等差数列:
 * 1, 3, 5, 7, 9
 * 7, 7, 7, 7
 * 3, -1, -5, -9
 * 以下数列不是等差数列。
 * 1, 1, 2, 5, 7
 * 数组 A 包含 N 个数，且索引从0开始。数组 A 的一个子数组划分为数组 (P, Q)，P 与 Q 是整数且满足 0<=P<Q<N 。
 * 如果满足以下条件，则称子数组(P, Q)为等差数组：
 * 元素 A[P], A[p + 1], ..., A[Q - 1], A[Q] 是等差的。并且 P + 1 < Q 。
 * 函数要返回数组 A 中所有为等差数组的子数组个数。
 * 示例:
 * A = [1, 2, 3, 4]
 * 返回: 3, A 中有三个子等差数组: [1, 2, 3], [2, 3, 4] 以及自身 [1, 2, 3, 4]。
 */
public class A0413 {
    public static void main(String[] args) {
//        int[] nums = {2,1,3,4,2,3};
        int[] nums = {1, 2, 3, 4};
        System.out.println(numberOfArithmeticSlices(nums));
    }

        // 暴力
    public static int numberOfArithmeticSlices(int[] A) {
        int len = A.length;
        if (len < 3)
            return 0;
        int count = 0;
        for (int s = 0; s < len - 2; s++) {
            int d = A[s+1] - A[s];
            for (int e = s + 2; e < len; e++) {
                int i;
                for (i = s + 1; i <= e; i++) {
                    if (A[i] - A[i-1] != d)
                        break;
                }
                if (i > e)
                    count ++;
            }
        }
        return count;
    }
    // 暴力优化
    public static int numberOfArithmeticSlices2(int[] A) {
        int len = A.length;
        if (len < 3)
            return 0;
        int res = 0;
         for (int i = 0; i < len - 2; i++) {
             int d = A[i+1] - A[i];
             for (int j = i + 2; j < len; j++) {
                 if (A[j] - A[j-1] == d)
                     res ++;
                 else
                     break;
             }
         }
         return res;
    }
    // 递归
    public static int numberOfArithmeticSlices3(int[] A) {
        int len = A.length;
        if (len < 3)
            return 0;
        backtrack(A, len - 1);
        return sum;
    }
    public static int sum = 0;
    // 返回数组A前i个数字等差数列个数
    public static int backtrack(int[] A, int i) {
        if (i < 2)
            return 0;
        int res = 0;
        if (A[i] - A[i-1] == A[i-1] - A[i-2]) {
            res = 1 + backtrack(A, i-1);
            sum += res;
        } else
            backtrack(A, i-1);
        return res;
    }
    // 动态规划
    public static int numberOfArithmeticSlices4(int[] A) {
        int len = A.length;
        if (len < 3)
            return 0;
        // dp保存以i结尾的等差数列个数
//        int[] dp = new int[len];
        int dp = A[2] - A[1] == A[1] - A[0] ? 1 : 0;
//        dp[2] = A[2] - A[1] == A[1] - A[0] ? 1 : 0;
        int sum = dp;
        for (int i = 3; i < len; i++) {
            if (A[i] - A[i-1] == A[i-1] - A[i-2]) {
                dp = dp + 1;
                sum += dp;
            } else
                dp = 0;
        }
        return sum;
    }
}
