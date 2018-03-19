package com.mw.dmp.helper;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.UUID;

/**
 * 生成token
 * 算法格式  uuid + timestamp + ip + useragent  sha256计算
 * 防止 token被劫持和伪造
 *
 * @author zangsh
 */
public class TokenUtils {

    public static void main(String[] args) {
        String s = getSHA256StrJava("测试SHA256");
        System.out.println(s);
    }

    public static String encode(Map<String, Object> params) {
        String uuid = UUID.randomUUID().toString();
        String timestamp = Long.toString(System.currentTimeMillis());
        //获取ip 和 useragent
        String ip = params.get("ip").toString();
        String userAgent = params.get("useragent").toString();
        //sha加密
        String shaText = uuid + timestamp + ip + userAgent;
        return getSHA256StrJava(shaText);
    }

    /**
     * 利用java原生的摘要实现SHA256加密
     * @param str 加密后的报文
     * @return
     */
    public static String getSHA256StrJava(String str) {
        MessageDigest messageDigest;
        String encodeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes("UTF-8"));
            encodeStr = byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodeStr;
    }

    /**
     * byte转16进制
     * @param digest
     * @return
     */
    private static String byte2Hex(byte[] digest) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < digest.length; i++){
            String temp = Integer.toHexString(digest[i] & 0xFF);
            if (temp.length() == 1){
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }
}
