package com.zq;

public class IngeniousAlgorithm {
    /**
     * 给定一个整数数组,找到一个具有最大和的连续子
     * 数组(子数组最少包含一个元素,返回其最大和
     * 例如:输入[-2,1,-3,4,-1,2,1,-5,4]
     * 输出:6
     * 连续子数组[4,-1,2,1]的和最大
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums){
//        暴力法
//        int len = nums.length;
//        int sum=0,maxsum=sum;
//        for (int i = 0; i < len; i++) {
//            sum=0;
//            for (int j = i; j < len; j++) {
//                sum+=nums[j];
//                maxsum=maxsum>sum?maxsum:sum;
//            }
//        }

        //巧妙法
        //只遍历一遍
        int i = 0;
        int sum = nums[0], maxsum = sum;
        for (int j = 1; j < nums.length; j++) {
            sum=sum>0?sum+=nums[j]:nums[j];//sum要是不大于0就把他抛弃了重来,因为他只会减小后面的值
            maxsum=maxsum<sum?sum:maxsum;
        }
        return maxsum;
    }
}
