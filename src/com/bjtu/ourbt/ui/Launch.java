package com.bjtu.ourbt.ui;

import com.bjtu.ourbt.ui.Launch;

/**
 * ����������
 */
public class Launch {

    public void open() {
        OurBt outBt = new OurBt();
        outBt.open();
    }

    /**
     * �������
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
