package com.rice.base64;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Random;
import java.util.UUID;

/**
 * @author wgz
 * @description
 * @date 2020/11/2 10:36
 */
public class Base64Demo {

    public static void main(String[] args) {
        decodeBasic(encodeBasic("Hello World?" + new Random().nextInt()));
        decodeUrl(encodeUrl("Hello World?" + new Random().nextInt()));
        decodeMime(encodeMime("Hello World?" + new Random().nextInt()));
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 10; ++i) {
            stringBuilder.append(UUID.randomUUID().toString());
        }
        decodeMime(encodeMime(stringBuilder.toString()));
    }

    private static void decodeMime(String s) {
        byte[] base64decodedBytes = Base64.getMimeDecoder().decode(s);
        System.out.println("转码后的字符串: "
                + new String(base64decodedBytes, StandardCharsets.UTF_8));
    }
    private static void decodeUrl(String s) {
        byte[] base64decodedBytes = Base64.getUrlDecoder().decode(s);
        System.out.println("转码后的字符串: "
                + new String(base64decodedBytes, StandardCharsets.UTF_8));
    }
    private static void decodeBasic(String s) {
        byte[] base64decodedBytes = Base64.getDecoder().decode(s);
        System.out.println("转码后的字符串: "
                + new String(base64decodedBytes, StandardCharsets.UTF_8));
    }

    private static String encodeMime(String s) {
        System.out.println("原始字符串: " + s);
        // MIME：输出隐射到MIME友好格式
        //      输出每行不超过76字符，并且使用'\r'并跟随'\n'作为分割
        //      编码输出最后没有行分割。
        String encode = Base64.getMimeEncoder().encodeToString(s.getBytes(StandardCharsets.UTF_8));
        System.out.println("Base64 编码字符串 (MIME) " + encode);
        return encode;
    }
    private static String encodeUrl(String s) {
        System.out.println("原始字符串: " + s);
        // URL：输出映射到一组字符A-Za-z0-9+_
        //      输出是URL和文件
        String encode = Base64.getUrlEncoder().encodeToString(s.getBytes(StandardCharsets.UTF_8));
        System.out.println("Base64 编码字符串 (URL) " + encode);
        return encode;
    }
    private static String encodeBasic(String s) {
        System.out.println("原始字符串: " + s);
        // 基本：输出被映射到一组字符A-Za-z0-9+/
        //      编码不添加任何行标，输出的解码仅支持A-Za-z0-9+/
        //      输出的内容不添加换行符，而且输出的内容由64个基本字符组成
        String encode = Base64.getEncoder().encodeToString(s.getBytes(StandardCharsets.UTF_8));
        System.out.println("Base64 编码字符串 (Basic) " + encode);
        return encode;
    }
}
