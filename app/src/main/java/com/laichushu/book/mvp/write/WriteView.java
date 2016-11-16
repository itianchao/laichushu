package com.laichushu.book.mvp.write;

/**
 * 写作
 * Created by wangtong on 2016/11/16.
 */

public interface WriteView {
    void getDataSuccess(WriteModle model);
    void getDataFail(String msg);
}
