package com.laichushu.book.bean.netbean;

import java.io.Serializable;

/**
 * Created by PCPC on 2016/11/25.
 */

public class GradeRemarksResult extends BaseModel implements Serializable {
    private boolean success;
    private String remarks,grade,typeName;
    private int type;

    public GradeRemarksResult(boolean success, String remarks, String grade, String typeName, int type) {
        this.success = success;
        this.remarks = remarks;
        this.grade = grade;
        this.typeName = typeName;
        this.type = type;
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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
