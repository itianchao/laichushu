package com.laichushu.book.bean.netbean;

import java.io.Serializable;

/**
 * Created by PCPC on 2016/11/25.
 */

public class GradeRemarksResult implements Serializable {
    private boolean success;
    private String remarks;

    public GradeRemarksResult(boolean success, String remarks) {
        this.success = success;
        this.remarks = remarks;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
