package com.laichushu.book.mvp.mechanismtopiclist;

import java.util.ArrayList;
import java.util.List;

/**
 * 话题数据模型
 * Created by wangtong on 2016/11/26.
 */
public class MechanismTopicListModel {

    /**
     * success : false
     * errMsg : 错误信息
     */

    private boolean success;
    private String errMsg;
    private ArrayList<DataBean> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public ArrayList<DataBean> getData() {
        return data;
    }

    public void setData(ArrayList<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 22
         * teamId : 14
         * teamName :
         * title : 浠婂ぉ涓嶄笅闆紝鏄浠婂ぉ鏄櫞澶┿��
         * content : 浠婂ぉ涓嶄笅闆紝鏄ㄥぉ涔熶笉涓嬮洦锛屼粖澶╄兘涓嶈兘鍝楀摋鍟﹀暒锛岋紙涓嬪満闆烽樀闆ㄥ晩锛夛紝鎴戝拰鎴戠殑姝ユ垬杞﹀琚簡鍏櫨閲岋紝鍐嶈窇鍏櫨鑳戒笉鑳斤紝璺戝嚭杩欑伀杈ｈ荆鍦板お闃冲湴锛屽６閲岀殑姘村凡缁忎笉澶氫簡鎴樺弸浠湪浼犻�掞紝鎵嬩腑鐨勬灙鏄秺鏉ヨ秺娌夋洿瑕佺簿纭皠鍑伙紝姝ユ垬杞﹁繕鏄椃鍡峰彨浠栫殑閫熷害涓嶅噺锛岀湡鏄挶浠叏鐝殑鍝ヤ滑鍎块搧浜嗗績鐨勫ソ浼欒锛岃�佸ぉ鐖蜂粬涓轰粈涔堜粬灏辨槸涓嶄笅闆紝鍐蹭竴鍐插挶姹楁按婀块�忕殑杩峰僵缁垮啗琛ｏ紝鑰佸ぉ鐖峰揩鐐瑰揩鐐逛笅鍦哄ぇ闆ㄥ惂锛岄『渚挎祰娴囪缁冨満杈规棻浜嗙殑搴勭鍦帮紝鑰佸ぉ鐖蜂粬涓轰粈涔堜粬灏辨槸涓嶄笅闆紝鍐蹭竴鍐插挶姹楁按婀块�忕殑杩峰僵缁垮啗琛ｏ紝鑰佸ぉ鐖峰揩鐐瑰揩鐐逛笅鍦哄ぇ闆ㄥ惂锛岄『渚挎祰涓�娴囬偅鏃变簡鐨勫簞绋煎湴锛岀儹鈥︹�︾儹鈥︹�︹�︹�︼紝鏄ㄥぉ涓嶄笅闆ㄤ粖澶╀笉涓嬮洦锛屽厔寮熶滑鐨勬鎴樿溅涓�澶╀竴涓紝锛堜竴澶╀竴涓鎷挎荡鍟婏級锛屽鏋滈偅鏄庡ぉ鍚庡ぉ澶у悗澶╋紝瀹冩娲讳笉涓嬮洦锛屽挶灏卞ぉ澶╂尌姹楀闆ㄦ粙娑﹀ぇ鍦�
         * replyNum : 1
         * browseNum : 56
         * createDate : 30澶╁墠
         * topFlag : 0
         * starterId : 14
         * creatUserName : 鍝︾殑鐨勫摝
         * creatUserId : 112
         * type : 2
         * createrPhoto : group1/M00/00/0F/wKiTPlg4FM2ANV-AAACe5hadIhg819.jpg
         */

        private String id;
        private String teamId;
        private String teamName;
        private String title;
        private String content;
        private int replyNum;
        private int browseNum;
        private String createDate;
        private String topFlag;
        private String starterId;
        private String creatUserName;
        private String creatUserId;
        private int type;
        private String createrPhoto;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTeamId() {
            return teamId;
        }

        public void setTeamId(String teamId) {
            this.teamId = teamId;
        }

        public String getTeamName() {
            return teamName;
        }

        public void setTeamName(String teamName) {
            this.teamName = teamName;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getReplyNum() {
            return replyNum;
        }

        public void setReplyNum(int replyNum) {
            this.replyNum = replyNum;
        }

        public int getBrowseNum() {
            return browseNum;
        }

        public void setBrowseNum(int browseNum) {
            this.browseNum = browseNum;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getTopFlag() {
            return topFlag;
        }

        public void setTopFlag(String topFlag) {
            this.topFlag = topFlag;
        }

        public String getStarterId() {
            return starterId;
        }

        public void setStarterId(String starterId) {
            this.starterId = starterId;
        }

        public String getCreatUserName() {
            return creatUserName;
        }

        public void setCreatUserName(String creatUserName) {
            this.creatUserName = creatUserName;
        }

        public String getCreatUserId() {
            return creatUserId;
        }

        public void setCreatUserId(String creatUserId) {
            this.creatUserId = creatUserId;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getCreaterPhoto() {
            return createrPhoto;
        }

        public void setCreaterPhoto(String createrPhoto) {
            this.createrPhoto = createrPhoto;
        }
    }
}
