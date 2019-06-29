package com.honeywen.push.test;

import java.io.File;

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

        System.out.println(s.hashCode());
        System.out.println(s.hashCode() % 100000);

        System.out.println("3e0a9f53e5d2b87a84c765764e3eaec6".length());
    }


    @org.junit.Test
    public void test3() {

        String path = "/Users/wangwei/test/a/b/c/";
        File file = new File(path);

        boolean flag = file.exists();
        System.out.println(flag);
        if (!flag) {
            flag = file.mkdirs();
        }
        System.out.println(flag);

    }
}
