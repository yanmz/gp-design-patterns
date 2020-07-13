package leetcode;

public class Solution02 {
    public static void main(String[] args) {
        int[] i = new int[]{1, 2, 3, 4, 5, -1, -2, 3};
        System.out.println(subarraysDivByK(i, 3));
    }

    /**
     * 整体思路：
     * 将所有数变成K对应的余数
     * 处理方式：正数就为余数  负数就为 (余数 + K) % K
     * 通用方式: (num % K + K) % K 【不用区分正负数】
     * <p>
     * 通过求余数前缀和的进行中若有相同的余数那么做差就存在被整除的结果：
     * 例如：index= 0 1 2 3 4  5  6 7
     * A={1,2,3,4,5,-1,-2,3} K=3
     * =>preMod={1,2,3,1,2, 2, 1,3}
     * =>sumMod={1,0,0,1,0, 2, 0,0}
     * => sum[0:1] % 3 = 0
     * sum[0:2] % 3 = 0
     * sum[1:2] % 3 = 0
     * <p>
     * => 当前内容中余数出现的个数就是满足的个数
     **/
    public static int subarraysDivByK(int[] A, int K) {
        // 存储前缀和出现余数的个数
        int[] modK = new int[K];
        modK[0] = 1; // 因为当余数是0的时候，直接满足不用进行做差处理
        int count = 0;
        int sum = 0; // 进行前缀和计算
        for (int tmp : A) {
            sum = ((sum + tmp) % K + K) % K; // 余数处理
            System.out.print("sum: " + sum);
            count += modK[sum]; // 查看有多少个余数相同的个数
            System.out.println(" count:" + count);
            modK[sum]++; // 余数个数添加
        }
        return count;
    }
}
