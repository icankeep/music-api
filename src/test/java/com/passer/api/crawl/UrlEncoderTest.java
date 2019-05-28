package com.passer.api.crawl;

import org.junit.Test;

import java.net.URLEncoder;

/**
 * @Author: passer
 * @Date: 19-5-27 下午1:59
 * @Version 1.0
 */
public class UrlEncoderTest {
    @Test
    public void testUrlEncoder() {
        String path = URLEncoder.encode("login?phone=213&password=131");
        System.out.println(path);
    }
}
