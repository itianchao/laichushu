package com.pickerview.lib;

import java.io.Serializable;

/**
 * Created by PCPC on 2016/12/1.
 */

public class City implements Serializable {
    private String cityName, cityCode;

    public City(String cityName, String cityCode) {
        this.cityName = cityName;
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }
}
