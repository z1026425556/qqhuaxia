package com.pengcheng.util;

public class ArrayUtil {

    public static boolean intArrLookupInt(int[] intArr, int intt) {
        String b = intt + ""; // 先转换为String类型
        for (int i : intArr) {
            if (b.equals(i + "")) {
                return true;
            }
        }
        return false;
    }

}
