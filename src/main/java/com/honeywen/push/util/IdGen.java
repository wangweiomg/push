package com.honeywen.push.util;


/**
 * id生成器
 * @author wangwei
 * @date 2019/6/9
 */
public class IdGen {

    private static final SnowFlakeIdWorker worker = new SnowFlakeIdWorker(0, 0);

    public static long nextId() {
        return worker.nextId();
    }

}
