package com.zq;

public class MySort {
    /**
     * 快速排序
     */
    private static void quickSort(int[] in, int start, int end){
        if(start<end){
            int dividNum = in[start];
            int i=start,j=end;
            while (i<j){
                while (i<j && dividNum <= in[j]){//大的留在右边
                    j--;
                }
                in[i]=in[j];//完成了i的赋值,接下来移动左边,目标是j
                while(i<j && in[i] <= dividNum){
                    i++;
                }
                in[j]=in[i];
            }
            in[i]=dividNum;
            quickSort(in,start,i-1);
            quickSort(in,i+1,end);
        }

    }
    public static void quickSort(int[] in){
        quickSort(in,0,in.length-1);
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
                    swap(in,j,j+1);
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
    /**
     * 希尔排序(缩小增量排序),或改进版的插入排序
     */
    public static void shellSort(int[] in){
        int len = in.length;
        int temp = 0;
        for (int gap = len/2; gap > 0 ; gap/=2) {
            for (int i = gap, j; i < len; i++) {
                temp=in[i];//要插入的对象
                for (j = i; j >= gap && in[j-gap] > temp; j-=gap) {//从gap开始,往前隔gap隔进行比较
                    in[j] = in[j-gap];
                }
                in[j] = temp;
            }
        }
    }
    /**
     * 简单选择排序
     */
    public static void selectSort(int[] in){
        int len = in.length;
        int temp=0;
        int minIndex = 0;
        for (int i = 0; i < len; i++) {
            minIndex = i;//记录最小值的下标
            for (int j = i+1; j < len; j++) {
                if(in[minIndex] > in[j]){
                    minIndex=j;
                }
            }
            swap(in,minIndex,i);
        }
    }

    /**
     * 堆排序
     */
    public static void heapSort(int[] in){
        //首先把数组看成完全二叉树
        //堆就是父节点大于子节点(大顶堆)或者反之(小顶堆)
        //初始化堆,从最后面开始调节堆
        //从最后一个非叶子结点开始调节,然后是倒数第二个...
        //有n个节点,则第n/2个是最后一个非叶子节点,下标n/2-1
        int n = in.length;
        for (int i = n/2-1; i >= 0 ; i--) {
            shiftDown(in,i,n-1);//向下调整堆,前提是下面的已经是堆了
        }


        for (int i = n-1; i > 0 ; i--) {
            swap(in,0,i);//每次都把最大的一个换到最后,删除出堆
            shiftDown(in,0,i-1);
        }
    }

    /**
     * 向下调整一次堆,在子节点们已经是堆的情况下,调节一次就成堆
     * @param in 调整哪个数组
     * @param start 从哪个下标开始调整
     * @param end 调节到哪个下标
     */
    private static void shiftDown(int[] in,int start,int end){
        int parent = start;//其实就是将start往下插
        int child = parent*2+1;//此为左孩子
        int temp = in[parent];
        while(child <= end){
            if(child < end && in[child] < in[child+1]){//由于是完全二叉树,只要左孩子不是最后一个节点,就会有右孩子
                child++;
            }
            if(temp < in[child]){
                in[parent] = in[child];//此处也可以通过swap子父节点来完成,最后就不需要赋值了,不过会浪费一点操作
                parent = child;//往下继续调节,直至他的左孩子都没了(叶子节点)
                child = parent*2+1;
            }else break;
        }
        in[parent] = temp;//插到退出的那个叶子结点上
    }

    private static void swap(int[] in, int i,int j){
        int temp = in[i];
        in[i] = in[j];
        in[j] = temp;
    }

    /**
     * 归并排序,内部通过一个同等大小数组帮忙
     */
    public static  void mergeSort(int [] in){
        int[] asssistArray = new int[in.length];
        mergeSort(in,0,in.length-1,asssistArray);
    }
    /**
     *
     * @param source 源数组
     * @param sourceStart   源数组开始下标
     * @param sourceEnd 原数组结束下标
     * @param assistArray    辅助数组
     */
    private static void mergeSort(int[] source,int sourceStart, int sourceEnd, int[] assistArray){
        if(sourceStart<sourceEnd){
            int mid = sourceStart + (sourceEnd - sourceStart)/2;
            mergeSort(source,sourceStart,mid,assistArray);//排左边
            mergeSort(source,mid+1,sourceEnd,assistArray);//排右边
            merge(source,sourceStart,mid+1,sourceEnd,assistArray);//这是真正起排序作用的部分
        }

    }

    /**
     * 合并一个前后各自有序的数组,需要一个辅助数组帮忙
     * @param source
     * @param sourceStart
     * @param sourceMid 与后面的形成有序
     * @param sourceEnd
     * @param assistArray
     */
    public static void merge(int[] source,int sourceStart, int sourceMid, int sourceEnd, int[] assistArray){
        if(sourceStart==sourceEnd) return;
        int start1=sourceStart, k=sourceStart, start2=sourceMid;
        while (start1<sourceMid && start2<=sourceEnd){
            assistArray[k++]=source[start1]<source[start2]?source[start1++]:source[start2++];
        }
        while(start1<sourceMid)  assistArray[k++]=source[start1++];
        while(start2<=sourceEnd) assistArray[k++]=source[start2++];
        for (int i = sourceStart; i <= sourceEnd; i++) {
            source[i]=assistArray[i];
        }
    }

    public static int binarSearch(int[] in, int target){
        int start=0,end=in.length-1;
        int mid;
        while(start<end){
            mid=(start+end)/2;
            if(in[mid]>target) end=mid-1;
            else if(in[mid]<target) start=mid+1;
            else return mid;
        }
        return -1;
    }

}
