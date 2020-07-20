package com.zq;

import java.util.*;

public class Main {

    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        while(sc.hasNext()){
//            String s = sc.nextLine();
//            int num = MyString.cntS(s);
//            System.out.println(num);
//        }
        int[] ints = {11,3,15,2,7,2,9,13,44,2,1,4,8,5,7};
        MySort.mergeSort(ints);
        System.out.println(Arrays.toString(ints));;
        int index = MySort.binarSearch(ints, 7);
        System.out.println(index);
    }

}