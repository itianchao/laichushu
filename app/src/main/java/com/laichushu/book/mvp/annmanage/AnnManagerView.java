package com.laichushu.book.mvp.annmanage;

import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.mvp.notice.NoticeModle;

/**
 * Created by PCPC on 2016/12/20.
 */

public interface  AnnManagerView {
    void getAnnanageDataSuccess(NoticeModle modle);
    void getDeleteAnnDateSuccess(RewardResult modle);
    void getDataFail(String msg);
    void shwoDialog();
    void dissmissDialog();
}
