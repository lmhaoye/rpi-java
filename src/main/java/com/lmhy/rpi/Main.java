package com.lmhy.rpi;


import com.lmhy.rpi.aliyun.Aliyun;
import com.lmhy.rpi.utils.IpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static Logger log = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        if (args==null || args.length==0) {
            log.warn("Please input params");
            return;
        }
        String action = args[0];
        switch (action) {
            case "ip": {
                String ip = IpUtils.getPublicIp();
                log.info("[ip]--->{}", ip);
            }
            break;
            case "alidns": {
                String ip = IpUtils.getPublicIp();
                boolean result = Aliyun.updateIp(ip, false);
                log.info("[alidns]修改解析结果：{}", result);
            }
        }
    }
}
