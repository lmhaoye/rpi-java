package com.lmhy.rpi.aliyun;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.alidns.model.v20150109.*;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.lmhy.rpi.utils.EnvUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Aliyun {
    private static Logger log = LoggerFactory.getLogger(Aliyun.class);

    private static final String REGION_ID = "cn-hangzhou";
    private static String accessKeyId;
    private static String accessKeySecret;
//    private static String accessKeyId = "LTAIgx4gRwOdGDyP";
//    private static String accessKeySecret = "lhO72l4zvD3C8prI1aWcfCROXdCrmX";

    private static final String DOMAIN = "lmhaoye.com";
    private static final String RECORD_ID = "4086802960029696";
    private static IAcsClient client;

    private static String lastIp = "";

    static {
        accessKeyId = EnvUtils.getProp("aliyun.accesskey.id");
        accessKeySecret = EnvUtils.getProp("aliyun.accesskey.secret");
        IClientProfile profile = DefaultProfile.getProfile(REGION_ID, accessKeyId, accessKeySecret);
        client = new DefaultAcsClient(profile);
        getDomainInfo();
    }

    public static String getDomainInfo() {
        DescribeDomainRecordInfoRequest request = new DescribeDomainRecordInfoRequest();
        request.setRecordId(RECORD_ID);
        DescribeDomainRecordInfoResponse response;
        try {
            response = client.getAcsResponse(request);
            System.out.println(JSON.toJSON(response));
            lastIp = response.getValue();
            log.info("[aliyun] ip get :" + lastIp);
            return lastIp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static boolean updateIp(String ip, boolean isDirect) {
        if (!isDirect) {
            getDomainInfo();
            if (ip.equals(lastIp)) {
                return true;
            }
        }
        UpdateDomainRecordRequest request = new UpdateDomainRecordRequest();
        request.setRecordId(RECORD_ID);
        request.setRR("rpi");
        request.setType("A");
        request.setValue(ip);

        UpdateDomainRecordResponse response;
        try {
            response = client.getAcsResponse(request);
            log.info("修改域名解析为：" + ip);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
