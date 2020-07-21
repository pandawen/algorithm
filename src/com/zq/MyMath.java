package com.zq;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;

public class MyMath {
    /**
     * 最大公约数
     * @param a
     * @param b
     * @return
     */
    public  static int getCD(int a, int b) {
        //使较大值为a
        if(a<b){
            int t=a;
            a=b;
            b=t;
        }
        int t=a;
        //辗转相除法
        //原理:a,b的最大公约数与b,a%b相同

        while(a%b!=0){
            t=b;
            b=a%b;
            a=t;
        }
        return b;
    }

    /**
     * 最小公倍数
     * @param a
     * @param b
     * @return
     */
    public static int getCM(int a,int b){
        return a*b/getCD(a,b);
    }


    /**
     * 判断数origin是否是以end结尾的
     * @param origin
     * @param end
     * @return
     */
    public static Boolean ifEndWith(long origin,long end){
        long tempOri=origin,tempEnd=end;
        long i=0,j=0;
        while( tempEnd > 0 && i==j){
            i = tempOri % 10;
            j = tempEnd % 10;
            tempOri/=10;
            tempEnd/=10;
        }
        if(tempEnd == 0 && i==j){
//            System.out.println(origin+":::"+end);
            return true;
        }
        else
            return false;
    }

    /**
     * 考试题目和要点：

     1、中文大写金额数字前应标明“人民币”字样。中文大写金额数字应用壹、贰、叁、肆、伍、陆、柒、捌、玖、拾、佰、仟、万、亿、元、角、分、零、整等字样填写。（30分）

     2、中文大写金额数字到“元”为止的，在“元”之后，应写“整字，如￥ 532.00应写成“人民币伍佰叁拾贰元整”。在”角“和”分“后面不写”整字。（30分）

     3、阿拉伯数字中间有“0”时，中文大写要写“零”字，阿拉伯数字中间连续有几个“0”时，中文大写金额中间只写一个“零”字，如￥6007.14，应写成“人民币陆仟零柒元壹角肆分“。
     * @param num
     * @return
     */
    public static String NumToRMB(Double num){
        HashMap<Integer, String> map = new HashMap<>();
        map.put(0,"零");
        map.put(1,"壹");
        map.put(2,"贰");
        map.put(3,"叁");
        map.put(4,"肆");
        map.put(5,"伍");
        map.put(6,"陆");
        map.put(7,"柒");
        map.put(8,"捌");
        map.put(9,"玖");
        map.put(10,"分");
        map.put(11,"角");
        map.put(12,"元");
        map.put(13,"拾");
        map.put(14,"佰");
        map.put(15,"仟");
        map.put(16,"万");
        map.put(17,"拾");
        map.put(18,"佰");
        map.put(19,"仟");
        map.put(20,"亿");
        map.put(21,"拾");
        map.put(22,"佰");
        map.put(23,"仟");
        map.put(24,"万");


        BigDecimal decimal = new BigDecimal(num.toString());
        BigDecimal bigDecimal = decimal.multiply(new BigDecimal(100));
        long positveNm = bigDecimal.longValue();
        int temp=0;
        int unit=10;
        StringBuilder builder = new StringBuilder();
        while(positveNm > 0){
            temp = (int)(positveNm%10);
            builder.insert(0,map.get(temp)+map.get(unit++));
            positveNm/=10;
        }
        builder.insert(0,"人民币");
        int index2 = builder.indexOf("零角零分");
        if(index2 != -1) builder.replace(index2,index2+4,"整");
        int index8 = builder.indexOf("零分");
        if(index8 != -1) builder.delete(index8,index8+2);
        int index9 = builder.indexOf("零角");
        if(index9 != -1) builder.delete(index9,index9+2);

        int index3 = builder.indexOf("零万");
        if(index3 !=-1) builder.deleteCharAt(index3);

        int index4 = builder.indexOf("零亿");
        if(index4 !=-1) builder.deleteCharAt(index4);

        int index5 = builder.indexOf("零元");
        if(index5 !=-1) builder.deleteCharAt(index5);

        while(builder.indexOf("零仟")!=-1){
            builder.deleteCharAt(builder.indexOf("零仟")+1);
        }
        while(builder.indexOf("零佰")!=-1){
            builder.deleteCharAt(builder.indexOf("零佰")+1);
        }
        while(builder.indexOf("零拾")!=-1){
            builder.deleteCharAt(builder.indexOf("零拾")+1);
        }
        while(builder.indexOf("壹拾")!=-1){
            builder.deleteCharAt(builder.indexOf("壹拾"));
        }
        while(builder.indexOf("零零")!=-1){
            builder.deleteCharAt(builder.indexOf("零零"));
        }

        int index1 = builder.indexOf("零万");
        if(index1 !=-1) builder.delete(index1,index1+2);

        int index6 = builder.indexOf("零元");
        if(index6 !=-1) builder.deleteCharAt(index6);

        int index7 = builder.indexOf("零亿");
        if(index7 !=-1) builder.deleteCharAt(index7);



        return builder.toString();

    }

    /***
     * 两个超大整形相加
     * @param d1
     * @param d2
     * @return
     */
    public static String BigIntegerSum(String d1,String d2){
        char[] chars1 = new StringBuilder(d1).reverse().toString().toCharArray();
        char[] chars2 = new StringBuilder(d2).reverse().toString().toCharArray();
        StringBuilder result = new StringBuilder();
        int num1=chars1.length, num2=chars2.length;
        int temp1=0,temp2=0,temp3=0;
        boolean flag=false;
        char c=0;
        for (int i = 0; i < num1 || i < num2; i++) {
            if(i>=num1) temp1=0;
            else temp1 = chars1[i]-'0';
            if(i>=num2) temp2=0;
            else temp2 = chars2[i]-'0';

            temp3 = temp1+temp2;
            if(temp3<10 && flag){
                c = (char) (temp3 + '0' + 1 );
                if(c > '9'){
                    c = '0';
                }else {
                    flag=false;
                }
            }else if(temp3<10 && !flag){
                c = (char) (temp3 + '0');
            }else{
                if(flag){
                    c = (char) (temp3 % 10 + '0' + 1);
                }else{
                    c = (char)(temp3 % 10 +'0');
                    flag=true;
                }
            }
            result.append(c);
        }
        if(flag) result.append('1');

        return result.reverse().toString();
    }

    /**
     * 题目描述
     在命令行输入如下命令：

     xcopy /s c:\ d:\，

     各个参数如下：

     参数1：命令字xcopy

     参数2：字符串/s

     参数3：字符串c:\

     参数4: 字符串d:\

     请编写一个参数解析程序，实现将命令行各个参数解析出来。



     解析规则：

     1.参数分隔符为空格
     2.对于用“”包含起来的参数，如果中间有空格，不能解析为多个参数。比如在命令行输入xcopy /s “C:\program files” “d:\”时，参数仍然是4个，第3个参数应该是字符串C:\program files，而不是C:\program，注意输出参数时，需要将“”去掉，引号不存在嵌套情况。
     3.参数不定长
     4.输入由用例保证，不会出现不符合要求的输入




     输入描述:
     输入一行字符串，可以有空格

     输出描述:
     输出参数个数，分解后的参数，每个参数都独占一行

     * 不知道有人像我这样做吗
     * 这题难点就是引号外面的空格和引号内的不好区分,不然直接split了
     * 我的办法就是把引号外面的空格改成逗号,然后split就可以了
     * @param params
     * @return
     */
    public static String[] getParams(String params){
        char[] chars = params.toCharArray();
        int len = chars.length;
        boolean inQuotes=false;//是否在引号内
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            if(chars[i]=='\"') inQuotes=!inQuotes;//初次遇到切换为在引号内,再次见面切换为在号外
            if(!inQuotes){//不在引号内
                if(chars[i]==' ') builder.append(',');
                else builder.append(chars[i]);
            }else {
                if(chars[i]!='\"')
                    builder.append(chars[i]);

            }
        }
        String[] results = builder.toString().split(",");
        return results;
    }

    /**
     * 输入年月日,输出这是这一年的那一天
     */
    public static int countDays(int year,int month,int day){
        HashMap<Integer, Integer> month_day = new HashMap<>();
        month_day.put(1,31);
        month_day.put(3,31);
        month_day.put(4,30);
        month_day.put(5,31);
        month_day.put(6,30);
        month_day.put(7,31);
        month_day.put(8,31);
        month_day.put(9,30);
        month_day.put(10,31);
        month_day.put(11,30);
        month_day.put(12,31);
        int totalDays = 0;
        if(year%4==0 && year%100!=0 || year%400==0)
            month_day.put(2,29);
        else  month_day.put(2,28);
        for (int i = 1; i < month; i++) {
            totalDays+=month_day.get(i);
        }
        totalDays+=day;
        return totalDays;
    }
}
