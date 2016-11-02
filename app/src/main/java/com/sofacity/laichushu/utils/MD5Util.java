package com.sofacity.laichushu.utils;

import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class MD5Util {
    /**
     * md5加密方法
     * @param
     * @return
     */
    private static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++)
            resultSb.append(byteToHexString(b[i]));

        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n += 256;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static String MD5Encode(String origin) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if ("UTF-8" == null || "".equals("UTF-8"))
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes()));
            else
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes("UTF-8")));
        } catch (Exception exception) {
        }
        return resultString;
    }

    private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    /**
     * 获取数字签名
     * @param obj
     * @return
     */
    public static String getSign(Object obj){
        TreeMap<String,String> map = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareTo(s2);
            }
        });
        Class cla = obj.getClass();
        //获取所有的属性
        Field[] declaredFields = cla.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            //获取属性名字
            String name = declaredField.getName();
            //拿到bean类所有属性名字
            String value = getFieldValueByName(name, obj);
            if (!name.equals("sign")){
                map.put(name,value);
            }
        }
        map.put("token",SharePrefManager.getToken2());//加入token
        map.put("publickey",MD5Encode(SharePrefManager.getToken2()));//publickey
        //便利集合
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            sb.append(entry.getKey()+"="+entry.getValue()+"&");
        }
        sb.deleteCharAt(sb.lastIndexOf("&"));
        //Log.d("hhhhhh", "getSign: "+sb.toString());
        String sign = MD5Encode(sb.toString());
        return sign.toUpperCase();
    }
    /**
     * 获取数字签名
     * 不带Token值
     * @param obj
     * @return
     */
    public static String getSignNoToken(Object obj){
        TreeMap<String,String> map = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareTo(s2);
            }
        });
        Class cla = obj.getClass();
        //获取所有的属性
        Field[] declaredFields = cla.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            //获取属性名字
            String name = declaredField.getName();
            //拿到bean类所有属性名字
            String value = getFieldValueByName(name, obj);
            if (!name.equals("sign")){
                map.put(name,value);
            }
        }
        //便利集合
        map.put("publickey",MD5Encode(SharePrefManager.getToken2()));
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            sb.append(entry.getKey()+"="+entry.getValue()+"&");
        }
        sb.deleteCharAt(sb.lastIndexOf("&"));
        //Log.d("hhhhhh", "getSign: "+sb.toString());
        String sign = MD5Encode(sb.toString());
        return sign.toUpperCase();
    }

    /**
     * 获取class属性值
     * @param fieldName
     * @param o
     * @return
     */
    private static String getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[] {});
            String value = method.invoke(o, new Object[] {}).toString();
            return value;
        } catch (Exception e) {
            System.out.println("属性不存在");
            return null;
        }
    }
    /**
     * 转换 post 提交的 json
     */
    public static String getContent(Object json){
        return new Gson().toJson(json).replace("}", ",\"publickey\":" + "\"" + MD5Encode(SharePrefManager.getToken2()) + "\"" +",\"sign\":" + "\"" + getSign(json) + "\"" + "}");
    }
    /**
     * 转换 post 提交的 json
     * 不带Token值
     */
    public static String getContentNoToken(Object json){
        return new Gson().toJson(json).replace("}", ",\"publickey\":" + "\"" + MD5Encode(SharePrefManager.getToken2()) + "\"" +",\"sign\":" + "\"" + getSignNoToken(json) + "\"" + "}");
    }
}
