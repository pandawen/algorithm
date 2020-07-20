package com.zq;

public class MySort {
    /**
     * 快速排序
     */
    public static void quickSort(int[] in, int start, int end){
        if(start<end){
            int dividNum = in[start];
            int i=start,j=end;
            while (i<j){
                while (i<j && dividNum <= in[j]){//大的留在右边
                    j--;
                }
                in[i++]=in[j];//完成了i的赋值,接下来移动左边,目标是j
                while(i<j && in[i] <= dividNum){
                    i++;
                }
                in[j--]=in[i];
            }
            in[i]=dividNum;
            quickSort(in,start,i-1);
            quickSort(in,i+1,end);
        }

    }

    /**
     * 冒泡排序
     */
    public static void bubbleSort(int[] in){
        int len = in.length;
        int temp = 0;
        boolean exchange = false;
        for (int i = 0; i < len - 1; i++) {
            exchange = false;//用于记录一趟是否发生过交换,默认没有
            for (int j = 0; j < len - 1 - i; j++) {
                if(in[j]>in[j+1]){
                    temp=in[j];
                    in[j]=in[j+1];
                    in[j+1]=temp;
                    exchange=true;
                }

            }
            if(!exchange) break;
        }
    }
    /**
     * 插入排序
     */
    public static void insertSort(int[] in){
        int len = in.length;
        int temp = 0;
        for (int i = 0, j; i < len; i++) {
            temp=in[i];//要插入的对象
            for (j = i; j > 0 && in[j-1] > temp; j--) {
                    in[j] = in[j-1];//往后挪,此时j-1空出来,j--后就是j
            }
            in[j] = temp;
        }
    }
}
