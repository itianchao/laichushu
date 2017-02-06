package com.laichushu.book.bean.otherbean;

import com.laichushu.book.bean.netbean.BaseModel;

import java.util.ArrayList;

/**
 * 课程 - 视频 - 笔记 数据模型
 * Created by wangtong on 2017/1/10.
 */

public class NoteListModle extends BaseModel {

    /**
     * success : true
     * data : {"lessonNoteList":[{"noteId":"12","lessonId":"15","userId":"175","name":"绗竴璇�","remarks":"鍟婂搱鍝堝搱鍝�","createDate":1484019675000,"updateDate":1484019675000}],"totalNum":1}
     */

    private boolean success;
    private DataBean data;
    private String errMsg;

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * lessonNoteList : [{"noteId":"12","lessonId":"15","userId":"175","name":"绗竴璇�","remarks":"鍟婂搱鍝堝搱鍝�","createDate":1484019675000,"updateDate":1484019675000}]
         * totalNum : 1
         */

        private int totalNum;
        private ArrayList<LessonNoteListBean> lessonNoteList;

        public int getTotalNum() {
            return totalNum;
        }

        public void setTotalNum(int totalNum) {
            this.totalNum = totalNum;
        }

        public ArrayList<LessonNoteListBean> getLessonNoteList() {
            return lessonNoteList;
        }

        public void setLessonNoteList(ArrayList<LessonNoteListBean> lessonNoteList) {
            this.lessonNoteList = lessonNoteList;
        }

        public static class LessonNoteListBean {
            /**
             * noteId : 12
             * lessonId : 15
             * userId : 175
             * name : 绗竴璇�
             * remarks : 鍟婂搱鍝堝搱鍝�
             * createDate : 1484019675000
             * updateDate : 1484019675000
             */

            private String noteId;
            private String lessonId;
            private String userId;
            private String name;
            private String remarks;
            private String createDate;
            private String updateDate;

            public String getNoteId() {
                return noteId;
            }

            public void setNoteId(String noteId) {
                this.noteId = noteId;
            }

            public String getLessonId() {
                return lessonId;
            }

            public void setLessonId(String lessonId) {
                this.lessonId = lessonId;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getRemarks() {
                return remarks;
            }

            public void setRemarks(String remarks) {
                this.remarks = remarks;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public String getUpdateDate() {
                return updateDate;
            }

            public void setUpdateDate(String updateDate) {
                this.updateDate = updateDate;
            }
        }
    }
}
