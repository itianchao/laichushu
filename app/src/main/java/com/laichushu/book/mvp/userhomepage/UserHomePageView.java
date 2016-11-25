package com.laichushu.book.mvp.userhomepage;

import com.laichushu.book.bean.netbean.HomeUseDyrResult;
import com.laichushu.book.bean.netbean.HomeUserResult;

/**
 * Created by PCPC on 2016/11/25.
 */

public interface UserHomePageView {
    void getUserHeadDateSuccess(HomeUserResult result);
    void getUserHomeDyDateSuccess(HomeUseDyrResult result);
    void getDataFail(String errorMsg);
}
