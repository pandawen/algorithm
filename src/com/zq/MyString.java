package com.zq;

import java.util.*;

public class MyString {

    /**
     * 给定一个只包含大写英文字母的字符串,给出重新排列的所有不同的排列数
     * 例:
     * 输入 ABA 输出 3
     * 输入 ABCDEFGHHA 输出 907200
     */
    public static int cntS(String s){
        //思路:由于只用给出数量,所以其实就是对原字符串的数组做个全排列,然后把重复元素相对位置重复的情况除掉
        //其实就是A(n,n)/A(m,m)
        // n是所有字符数,m是重复的字符数,要分别统计出来,比如A有两个就除以一次A22,B有三个就除以一次A33
        HashMap<Character, Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();
        int len = chars.length;
        char temp =0;
        for (int i = 0; i < len; i++) {
            temp=chars[i];
            if(map.containsKey(temp)){
                map.put(temp,map.get(temp)+1);
            }else
                map.put(temp,1);
        }
        int totalNum=1;
        for (int i = 2; i <= len; i++) {
            totalNum*=i;
        }
        Iterator<Map.Entry<Character, Integer>> iterator = map.entrySet().iterator();
        while ((iterator.hasNext())){
            Map.Entry<Character, Integer> next = iterator.next();
            if(next.getValue()>1){
                int dupNum=1;
                for (int i = 2; i <= next.getValue(); i++) {
                    dupNum*=i;
                }
                totalNum/=dupNum;
            }
        }
        return totalNum;

    }
    /**
     * 逆置一个数组
     * @param s
     * @return
     */
    public static String reverseString(String s) {

        if(s==null || s.length()<=1)
            return s;
        /**典型三种方法
         * 1.通过转为数组前后字符交换
         * 2.通过栈
         * 3.通过StringBuffer或StringBuilder
         */
//        char[] chars = s.toCharArray();
//        int j=chars.length-1;//数组最后一个元素下标
//        char temp = 0;
//        for (int i = 0; i < j; i++,j--) {
//            temp = chars[i];
//            chars[i]=chars[j];
//            chars[j]=temp;
//        }
//        return new String(chars);
//
//        char[] chars = s.toCharArray();
//        Stack<Character> stack = new Stack<Character>();
//        for (Character character :chars){
//            stack.push(character);
//        }
//        int length = stack.size();
//        for (int i = 0; i < length; i++) {
//            chars[i]=stack.pop();
//        }
//        return new String(chars);

        StringBuilder builder = new StringBuilder(s);
        builder.reverse();
        return builder.toString();


    }

    /**
     * 对字符中的
     各个英文字符（大小写分开统计），数字，空格进行统计，并按照统计个数由多到少输出,
     如果统计的个数相同，则按照ASII码由小到大排序输出 。如果有其他字符，则对这些字符不用进行统计。
     * @param s
     * @return
     */
    public static TreeSet<Map.Entry<Character,Integer>> countCharOfString(String s){
        HashMap<Character, Integer> map = new HashMap<>();
        TreeSet<Map.Entry<Character,Integer>> treeSet =
                new TreeSet<>(new Comparator<Map.Entry<Character,Integer>>() {
                    @Override
                    public int compare(Map.Entry<Character,Integer> c, Map.Entry<Character,Integer> v) {
                        int cnt_sort=v.getValue()-c.getValue();
                        return cnt_sort!=0?cnt_sort:(c.getKey()-v.getKey());
                    }
                });
        char[] chars = s.toCharArray();
        char t = 0;
        for (int i = 0; i < chars.length; i++) {
            t = chars[i];
            if(t>='A' && t<='Z' || t>='a' && t<='z' || t>='0'&& t<='9' || t==' '){
                if(map.containsKey(t)){
                    map.put(t,map.get(t)+1);
                }else{
                    map.put(t,1);
                }
            }
        }
        for (Map.Entry<Character,Integer> E :map.entrySet()) {
            treeSet.add(E);
        }

        return treeSet;
    }

    /**
     * 输入描述:
     输入一个字符串
     输出描述:
     字符中所有出现的数字前后加上符号“*”，其他字符保持不变
     * @param s
     * @return
     */
    public static String MarkNum(String s) {
        char[] chars = s.toCharArray();
        StringBuilder builder = new StringBuilder();
        int num = chars.length;
        char temp=0;
        boolean preNm=false;//标记前一个是否是数字,默认不是
        for (int i = 0; i < num ; i++) {
            temp = chars[i];
            if(temp>='0' && temp<='9'){
                if(!preNm){
                    builder.append('*');
                    preNm=true;
                }
            }
            else{
                if(preNm){
                    builder.append('*');
                    preNm=false;
                }

            }
            builder.append(temp);
        }
        if(temp>='0' && temp<='9'){
            builder.append('*');//最后一个是数字则加上*
        }
        return builder.toString();
    }
}
