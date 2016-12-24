package com.laichushu.book.mvp.mine;

import com.laichushu.book.bean.netbean.PersonalCentreResult;

/**
 * 个人中心
 * Created by wangtong on 2016/12/24.
 */

public interface MineView {
    void getDataSuccess(PersonalCentreResult result);
    void getDataFail(String msg);
}
