package com.laichushu.book.mvp.mine.userinfo;

import com.laichushu.book.bean.netbean.HomeUserResult;

/**
 * Created by PCPC on 2016/11/30.
 */

public interface UserInfoView {
    void getUserHeadDateSuccess(HomeUserResult result);
    void getDataFail(String errorMsg);
}
