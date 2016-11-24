package com.laichushu.book.bean.netbean;

/**
 * Created by PCPC on 2016/11/24.
 */

public class ProvinceCityBean {
    private String province, city, proCode, cityCode;

    public ProvinceCityBean(String province, String city, String proCode, String cityCode) {
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

    public String getProCode() {
        return proCode;
    }

    public void setProCode(String proCode) {
        this.proCode = proCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }
}
