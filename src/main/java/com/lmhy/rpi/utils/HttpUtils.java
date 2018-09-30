package com.lmhy.rpi.utils;

import com.lmhy.rpi.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;


public class HttpUtils {
    private static Logger log = LoggerFactory.getLogger(HttpUtils.class);
    public static String get(String url) {
        String result = "";
        HttpURLConnection connection = null;
        try {
            URL urlObject = new URL(url);
            connection = (HttpURLConnection) urlObject.openConnection();
            connection.setRequestProperty("accept", "text/html");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible;MSIE 6.0;Windows NT 5.1;SV1)");
            connection.connect();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                result += "\r\n" + line;
            }
        } catch (Exception e) {
            log.error("[http] get error", e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return result;
    }

    public static String post(String url, Map<String, String> params) {
        String result = "";
        HttpURLConnection connection = null;
        try {
            StringBuilder sb = new StringBuilder();
            if (params != null && params.size() > 0) {
                for (Map.Entry<String, String> e : params.entrySet()) {
                    sb.append(e.getKey());
                    sb.append("=");
                    sb.append(e.getValue());
                    sb.append("&");
                }
                sb = sb.deleteCharAt(sb.length() - 1);
            }
            URL urlObj = new URL(url);
            connection = (HttpURLConnection) urlObj.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible;MSIE 6.0;Windows NT 5.1;SV1)");
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            PrintWriter printWriter = new PrintWriter(connection.getOutputStream());
            printWriter.print(sb.toString());
            printWriter.flush();
            // 定义输入流来读取URL的内容
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                result += "\r\n" + line;
            }


        } catch (Exception e) {
            log.error("[http] post error", e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return result;
    }
}
