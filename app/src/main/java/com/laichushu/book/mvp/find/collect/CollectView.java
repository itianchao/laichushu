package com.laichushu.book.mvp.find.collect;

import com.laichushu.book.bean.JsonBean.RewardResult;

/**
 *
 * Created by wangtong on 2017/2/10.
 */

public interface CollectView {

    void loadCollectDataSuccess(RewardResult model, String type);

    void loadCollectDataFail(String msg, String type);
}
