package com.pickerview.lib;

import java.io.Serializable;

/**
 * Created by PCPC on 2016/12/1.
 */

public class Province implements Serializable{
    private String proName,proCode;

    public Province(String proName, String proCode) {
        this.proName = proName;
        this.proCode = proCode;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProCode() {
        return proCode;
    }

    public void setProCode(String proCode) {
        this.proCode = proCode;
    }
}
