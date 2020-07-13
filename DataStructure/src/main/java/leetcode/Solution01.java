package leetcode;

public class Solution01 {
    public static void main(String[] args) {
        int[] i = new int[]{3, 1, 3, 4, 2};
        System.out.println(findDuplicate(i));
    }

    public static int findDuplicate(int[] nums) {
//        int h = 0;
//        for(int i=0;i<nums.length;i++){
//            for (int k=i+1;k<nums.length;k++){
//                if(nums[k]==nums[i]){
//                    return h = nums[k];
//                }
//            }
//        }
//        return h;
        /**
         快慢指针思想, fast 和 slow 是指针, nums[slow] 表示取指针对应的元素
         注意 nums 数组中的数字都是在 1 到 n 之间的(在数组中进行游走不会越界),
         因为有重复数字的出现, 所以这个游走必然是成环的, 环的入口就是重复的元素,
         即按照寻找链表环入口的思路来做
         **/
        int fast = 0, slow = 0;
        while (true) {
            fast = nums[nums[fast]];//4 3 2
            slow = nums[slow];//3 4  2
            if (slow == fast) {
                fast = 0;
                while (nums[slow] != nums[fast]) {
                    fast = nums[fast];
                    slow = nums[slow];
                }
                return nums[slow];
            }
        }

    }
}
