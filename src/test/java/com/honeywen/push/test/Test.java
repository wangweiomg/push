package com.honeywen.push.test;

/**
 * @author wangwei
 * @date 2019/5/16
 */
public class Test {


    @org.junit.Test
    public void test1() {

        int[] a = new int[]{1, 2, 4, 6, 6, 2, 1};

        int r = 0;
        for (int i = 0; i < a.length; i++) {
            r ^= a[i];
        }

        System.out.println(r);
    }


    @org.junit.Test
    public void test2() {
        String s = "gQEM8jwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyMjJzU2xFU1lleUQxMDAwME0wM24AAgSIp9pcAwQAAAAA";
        System.out.println(s.length());
    }
}