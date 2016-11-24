package com.laichushu.book.bean.netbean;

/**
 * Created by PCPC on 2016/11/24.
 */

public class ProvinceCityBean {
    private String province, city;
    private int proCode, cityCode;

    public ProvinceCityBean(String province, String city, int proCode, int cityCode) {
        this.province = province;
        this.city = city;
        this.proCode = proCode;
        this.cityCode = cityCode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getProCode() {
        return proCode;
    }

    public void setProCode(int proCode) {
        this.proCode = proCode;
    }

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }
}
