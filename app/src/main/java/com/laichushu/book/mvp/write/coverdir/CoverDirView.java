package com.laichushu.book.mvp.write.coverdir;

import com.laichushu.book.bean.JsonBean.PreviewCoverBean;

/**
 * 模版列表
 * Created by wangtong on 2016/11/23.
 */

public interface CoverDirView {

    void getDataSuccess(CoverDirModle modle);

    void getMakePreviewCover(PreviewCoverBean model);

    void getDataFail(String msg);

    void getDataFail2(String msg);

}
