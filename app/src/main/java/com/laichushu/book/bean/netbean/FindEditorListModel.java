package com.laichushu.book.bean.netbean;

import java.util.List;

/**
 * Created by PCPC on 2016/12/26.
 */

public class FindEditorListModel extends BaseModel{

    /**
     * success : true
     * data : [{"name":"马大哈","workingYears":0,"cityId":"0201","introduction":"","level":"","levelName":"","press":"河北教育出版社","pressId":"9","cooperateNum":0,"photo":"http://101.254.183.67:9980/group1/M00/00/2A/wKiTPlhc8UGAVkxwAAEpvVkWswE362.jpg","score":0,"scoreNum":0}]
     */

    private boolean success;
    private String errMsg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * name : 马大哈
         * workingYears : 0
         * cityId : 0201
         * introduction :
         * level :
         * levelName :
         * press : 河北教育出版社
         * pressId : 9
         * cooperateNum : 0
         * photo : http://101.254.183.67:9980/group1/M00/00/2A/wKiTPlhc8UGAVkxwAAEpvVkWswE362.jpg
         * score : 0
         * scoreNum : 0
         */

        private String name;
        private String id;
        private int workingYears;
        private String cityId;
        private String introduction;
        private String level;
        private String levelName;
        private String press;
        private String pressId;
        private int cooperateNum;
        private String photo;
        private int score;
        private int scoreNum;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getWorkingYears() {
            return workingYears;
        }

        public void setWorkingYears(int workingYears) {
            this.workingYears = workingYears;
        }

        public String getCityId() {
            return cityId;
        }

        public void setCityId(String cityId) {
            this.cityId = cityId;
        }

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getLevelName() {
            return levelName;
        }

        public void setLevelName(String levelName) {
            this.levelName = levelName;
        }

        public String getPress() {
            return press;
        }

        public void setPress(String press) {
            this.press = press;
        }

        public String getPressId() {
            return pressId;
        }

        public void setPressId(String pressId) {
            this.pressId = pressId;
        }

        public int getCooperateNum() {
            return cooperateNum;
        }

        public void setCooperateNum(int cooperateNum) {
            this.cooperateNum = cooperateNum;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public int getScoreNum() {
            return scoreNum;
        }

        public void setScoreNum(int scoreNum) {
            this.scoreNum = scoreNum;
        }
    }
}
