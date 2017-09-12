package com.bjtu.ourbt.ui;

import com.bjtu.ourbt.ui.Launch;

/**
 * 程序启动类
 */
public class Launch {

    public void open() {
        OurBt outBt = new OurBt();
        outBt.open();
    }

    /**
     * 程序入口
     */
    public static void main(String[] args) {
        try {
            Launch ourBt = new Launch();
            ourBt.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
