package com.bjtu.ourbt.util;

import java.io.InputStream;

/**
 * 这个类用来加载图片，因为路径的问题，必需要用这个类的方法来加载图片，不然发布成可运行的jar时就会出错，找不到图片
 */
public class ImageLoader {

    public static InputStream addImage(String path) throws NullImageException {
        InputStream in = ClassLoader.getSystemResourceAsStream(path);

        if (in != null) {
            return in;
        } else {
            throw new NullImageException("图片路径不对");
        }
    }

}
