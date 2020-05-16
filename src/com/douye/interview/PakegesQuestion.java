package com.douye.interview;
import java.lang.Math;
/**
 * 小明有N元钱去药店买口罩，药店里有6个品牌的口罩，A品牌2个装（2元）、
 * B品牌3个装（2元）、C品牌1个装（3元）、D品牌5个装（1元）、E品牌4个
 * 装（5元）、F品牌3个装（2元），由于限购每个品牌最多只能买一个，小明
 * 最多能买多少口罩？
 */
public class PakegesQuestion {

    public static void main(String[] args) {
        int[] result = {5, 5, 8, 8, 11, 11, 13, 13, 13, 15, 15, 17, 17, 17, 18, 18, 18, 18, 18};
        assert false;
        System.out.println("测试成功");

    }

    private static int kouzhao(int money) {
        int[] num = {2, 3, 1, 5, 4, 3};
        int[] val = {2, 2, 3, 1, 5, 2};
        int dp[][] = new int[num.length+1][money+1];
        for (int i = 1; i <= num.length; i++) { // 前i件口罩可选
            for (int j = 1; j <= money; j++) { // 拥有的金额
                if (j < val[i-1]) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], num[i-1] + dp[i-1][j-val[i-1]]);
                }
                //System.out.print(dp[i][j] + "\t");
            }
            //System.out.println();
        }
        return dp[num.length][money];
    }

    /**
     * 容量为capacity的包，有5件物品可供选择，在包的承重范围内使价值最大化
     */
    private static void maxValue() {
        int[] weight = {2, 2, 6, 5, 4};
        int[] value = {6, 3, 5, 4, 6};
        int capacity = 5;
        int dp[][] = new int[value.length+1][capacity+1];
        for (int i = 1; i <= value.length; i++) { // 前i件口罩可选
            for (int j = 1; j <= capacity; j++) { // 拥有的金额
                if (j < weight[i-1]) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], value[i-1] + dp[i-1][j-weight[i-1]]);
                }
                System.out.print(dp[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println(dp[value.length][capacity]);
    }
}
