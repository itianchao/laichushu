package com.laichushu.book.bean.netbean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by PCPC on 2016/11/22.
 */

public class HomeUseDyrResult implements Serializable{


    /**
     * success : true
     * data : [{"id":"23","teamId":"14","title":"来世之人生赢家","content":"快来讨论下吧，很有意思的！","createDate":"2016-10-31 00:00:00","topFlag":"1","starterId":"14","creatUserId":"1","type":2},{"id":"24","teamId":"14","title":"今天好冷啊","content":"如何应对寒冬？","createDate":"2016-10-02 00:00:00","topFlag":"1","starterId":"14","creatUserId":"1","type":2},{"id":"22","teamId":"14","title":"今天不下雨，是说今天是晴天。","content":"今天不下雨，昨天也不下雨，今天能不能哗哗啦啦，（下场雷阵雨啊），我和我的步战车奔袭了八百里，再跑八百能不能，跑出这火辣辣地太阳地，壶里的水已经不多了战友们在传递，手中的枪是越来越沉更要精确射击，步战车还是嗷嗷叫他的速度不减，真是咱们全班的哥们儿铁了心的好伙计，老天爷他为什么他就是不下雨，冲一冲咱汗水湿透的迷彩绿军衣，老天爷快点快点下场大雨吧，顺便浇浇训练场边旱了的庄稼地，老天爷他为什么他就是不下雨，冲一冲咱汗水湿透的迷彩绿军衣，老天爷快点快点下场大雨吧，顺便浇一浇那旱了的庄稼地，热\u2026\u2026热\u2026\u2026\u2026\u2026，昨天不下雨今天不下雨，兄弟们的步战车一天一个，（一天一个桑拿浴啊），如果那明天后天大后天，它死活不下雨，咱就天天挥汗如雨滋润大地","createDate":"2016-10-27 00:00:00","topFlag":"0","starterId":"14","creatUserId":"1","type":2}]
     */

    private boolean success;
    private List<DataBean> data;

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
         * id : 23
         * teamId : 14
         * title : 来世之人生赢家
         * content : 快来讨论下吧，很有意思的！
         * createDate : 2016-10-31 00:00:00
         * topFlag : 1
         * starterId : 14
         * creatUserId : 1
         * type : 2
         */

        private String id;
        private String teamId;
        private String title;
        private String content;
        private String createDate;
        private String topFlag;
        private String starterId;
        private String creatUserId;
        private int type;

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
    }
}
