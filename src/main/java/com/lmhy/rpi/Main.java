package com.lmhy.rpi;


import com.lmhy.rpi.aliyun.Aliyun;
import com.lmhy.rpi.utils.IpUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;

@Slf4j
public class Main {
    public static void main(String[] args) {
        if (ArrayUtils.isEmpty(args)) {
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
