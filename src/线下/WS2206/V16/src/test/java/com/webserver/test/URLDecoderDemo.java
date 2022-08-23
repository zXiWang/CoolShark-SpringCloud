package com.webserver.test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class URLDecoderDemo {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String line="/loginUser?username=%E5%BC%AF%E5%BC%AF&password=111";
        line=URLDecoder.decode(line,"UTF-8");
        System.out.println(line);
    }
}
