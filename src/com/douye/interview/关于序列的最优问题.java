package com.douye.interview;

import java.util.Arrays;

public class 关于序列的最优问题 {
    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] lis = {10, 2, 2, 5, 1, 7, 101, 18};
//        System.out.println(maxSubSeqSum(nums));
//        System.out.println(maxSubSeqSum02(nums));
        System.out.println(LIS(lis));
    }
    /**
     * 求取序列的最大连续子序列和
     * 1. 定义状态 dp[i] 表示以元素sequence[i-1]结尾的序列的最大连续子序列和
     * 2. 初始状态: dp[0] = 0, dp[1] = sequence[0]
     * 3. 状态转移：
     *      如果dp[i-1] < 0, 则dp[i] = sequence[1]
     *      如果dp[i-1] >= 0, 则dp[i] = sequence[1] + dp[i-1]
     */
    public static int maxSubSeqSum(int[] sequence) {
        int[] dp = new int[sequence.length+1];
        for (int i = 1; i < dp.length; i++) {
            if (dp[i-1] <= 0) dp[i] = sequence[i-1];
            else dp[i] = dp[i-1] + sequence[i-1];
        }
        Arrays.sort(dp);
        return dp[sequence.length];
    }
    // 优化
    public static int maxSubSeqSum02(int[] sequence) {
        int dp = 0;
        int max = dp;
        for (int i = 1; i < sequence.length+1; i++) {
            if (dp <= 0) dp = sequence[i-1];
            else dp = dp + sequence[i-1];
            max = Math.max(dp,max);
        }
        return max;
    }

    /**
     * int[] lis = {10, 2, 2, 5, 1, 7, 101, 18};
     * 最长上升子序列（最长递增子序列， Longest Increasing Subsequence， LIS）
     * 1. 定义状态 dp[i] 表示以元素sequence[i]结尾的序列的最长上升子序列长度
     * 2. 初始状态: dp[0] = 0, dp[1] = 1
     * 3. 状态转移：(求dp[i])
     *      遍历前i个元素，如果sequence[j]<sequence[i],dp[i] = dp[j] + 1
     */
    public static int LIS(int[] sequence) {
        int[] dp = new int[sequence.length];
        for (int i = 1; i < dp.length; i++) {
            dp[i] = 1;                                       // dp[1]=1  dp[2]=1
            for (int j = 0; j < i; j++) {                    // j=0,i=1  j=0 i=2
                if (sequence[j] < sequence[i]) {           // 10  10
                    dp[i] = Math.max(dp[i],dp[j] + 1);       //
                }
            }
        }
        System.out.println(Arrays.toString(dp));
        Arrays.sort(dp);
        return dp[sequence.length-1];
    }
}
