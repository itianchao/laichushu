package com.laichushu.book.mvp.find.coursera.note;

import android.text.TextUtils;

import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.NoteDelete_Paramet;
import com.laichushu.book.bean.netbean.NoteList_Paramet;
import com.laichushu.book.bean.netbean.NoteSave_Paramet;
import com.laichushu.book.bean.otherbean.NoteListModle;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.fragment.CourseNoteFragment;
import com.laichushu.book.utils.LoggerUtil;

/**
 * 发现 - 课程 - 视频 - 笔记
 * Created by wangtong on 2017/1/11.
 */

public class NotePresenter extends BasePresenter<NoteView> {
    private CourseNoteFragment mFragment;
    private String userId = ConstantValue.USERID;
    private String pageNo = "1";
    private String pageSize = ConstantValue.PAGESIZE;

    public NotePresenter(NoteView view) {
        attachView(view);
        mFragment = (CourseNoteFragment) view;
    }

    /**
     * 加载笔记列表 接口
     *
     * @param lessonId
     */
    public void loadNoteData(String lessonId) {
        LoggerUtil.e("笔记列表");
        NoteList_Paramet paramet = new NoteList_Paramet(lessonId, userId, pageNo, pageSize);
        addSubscription(apiStores.getLessonNoteList(paramet), new ApiCallback<NoteListModle>() {
            @Override
            public void onSuccess(NoteListModle model) {
                mvpView.loadNoteListDataSuccess(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.loadNoteListDataFail(code + "|" + msg);
            }

            @Override
            public void onFinish() {

            }
        });
    }

    /**
     * 保存笔记 or 修改笔记 接口
     *
     * @param noteId
     * @param lessonId
     * @param name
     * @param remarks
     */
    public void saveNote(String noteId, String lessonId, String name, String remarks) {
        LoggerUtil.e("保存笔记");
        NoteSave_Paramet paramet;
        int type;//0创建 1 修改
        if (TextUtils.isEmpty(noteId)) {//通过判断笔记Id确认是创建或是修改
            paramet = new NoteSave_Paramet(lessonId, userId, name, remarks);
            type = 0;
        } else {
            paramet = new NoteSave_Paramet(noteId, lessonId, userId, name, remarks);
            type = 1;
        }
        final int finalType = type;
        addSubscription(apiStores.getLessonNoteSave(paramet), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult model) {
                mvpView.loadSaveNoteSuccess(model,finalType);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.loadSaveNoteDataFail(code + "|" + msg,finalType);
            }

            @Override
            public void onFinish() {

            }
        });
    }

    /**
     * 删除笔记 接口
     *
     * @param noteId
     */
    public void deleteNote(String noteId) {
        LoggerUtil.e("删除笔记");

        NoteDelete_Paramet paramet = new NoteDelete_Paramet(noteId);
        addSubscription(apiStores.getLessonNoteDelete(paramet), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult model) {
                mvpView.loadDeleteNoteDataSuccess(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.loadDeleteNoteDataFail(code + "|" + msg);
            }

            @Override
            public void onFinish() {

            }
        });
    }
}
