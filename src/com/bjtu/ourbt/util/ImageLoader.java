package com.bjtu.ourbt.util;

import java.io.InputStream;

/**
 * �������������ͼƬ����Ϊ·�������⣬����Ҫ�������ķ���������ͼƬ����Ȼ�����ɿ����е�jarʱ�ͻ�����Ҳ���ͼƬ
 */
public class ImageLoader {

    public static InputStream addImage(String path) throws NullImageException {
        InputStream in = ClassLoader.getSystemResourceAsStream(path);

        if (in != null) {
            return in;
        } else {
            throw new NullImageException("ͼƬ·������");
        }
    }

}
