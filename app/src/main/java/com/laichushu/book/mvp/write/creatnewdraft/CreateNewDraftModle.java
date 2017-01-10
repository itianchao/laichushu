package com.laichushu.book.mvp.write.creatnewdraft;

/**
 * 创建草稿 modle
 * Created by wangtong on 2016/11/17.
 */

public class CreateNewDraftModle {

    /**
     * success : true
     * data : group1/M00/00/09/wKiTPlgwIiyAat4CAADZDgGfzZE019.jpg
     */

    private boolean success;
    private String data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
