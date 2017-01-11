package com.laichushu.book.mvp.find.coursera.note;

import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.otherbean.NoteListModle;

/**
 * 发现 - 课程 - 视频 - 笔记列表 view
 * Created by wangtong on 2017/1/11.
 */

public interface NoteView {

    void loadNoteListDataSuccess(NoteListModle model);

    void loadNoteListDataFail(String msg);

    void loadSaveNoteSuccess(RewardResult model, int finalType);

    void loadSaveNoteDataFail(String msg, int finalType);

    void loadDeleteNoteDataSuccess(RewardResult model);

    void loadDeleteNoteDataFail(String msg);
}
