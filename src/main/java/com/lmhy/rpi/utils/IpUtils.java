package com.lmhy.rpi.utils;

public class IpUtils {
    private static final String GETIP = "http://ident.me/";
    public static String getPublicIp(){
        return HttpUtils.get(GETIP);
    }
}
