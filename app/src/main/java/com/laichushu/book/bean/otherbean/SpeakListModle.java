package com.laichushu.book.bean.otherbean;

import com.laichushu.book.bean.netbean.BaseModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 发现 - 课程 - 讲义列表 数据模型
 * Created by wangtong on 2017/1/10.
 */

public class SpeakListModle extends BaseModel {

    /**
     * success : true
     * data : {"handOutsList":[{"handOutsId":"35","name":"璇剧▼娴嬭瘯璁蹭箟-1","lessonId":"25","url":"http://101.254.183.67:9980/group1/M00/00/31/wKiTPlh0ebmAeYKeAAbD49OEsfg287.pdf"},{"handOutsId":"34","name":"璇剧▼娴嬭瘯4璁蹭箟-2","lessonId":"15","url":"http://101.254.183.67:9980/group1/M00/00/31/wKiTPlh0eZOAINgOAAbD49OEsfg896.pdf"},{"handOutsId":"33","name":"璇剧▼娴嬭瘯4璁蹭箟-1","lessonId":"15","url":"http://101.254.183.67:9980/group1/M00/00/31/wKiTPlh0eYeAF7eUAAbD49OEsfg322.pdf"},{"handOutsId":"32","name":"璇剧▼娴嬭瘯浜旇涔�-2","lessonId":"21","url":"http://101.254.183.67:9980/group1/M00/00/31/wKiTPlh0eSWAIQ-sAAbD49OEsfg029.pdf"},{"handOutsId":"31","name":"璇剧▼娴嬭瘯浜旇涔�-1","lessonId":"21","url":"http://101.254.183.67:9980/group1/M00/00/31/wKiTPlh0eRiAOyVSAAbD49OEsfg316.pdf"},{"handOutsId":"29","name":"璁蹭箟娴嬭瘯","lessonId":"18","url":"http://101.254.183.67:9980/group1/M00/00/2E/wKiTPlhmFbSAQGReAAAygmd3--c22.docx"},{"handOutsId":"28","name":"娴嬭瘯闂pc.docx","lessonId":"18","url":"http://101.254.183.67:9980/group1/M00/00/2E/wKiTPlhmEY-AN3nIABvFSfCsStM66.docx"},{"handOutsId":"27","name":"钀ユ敹绛栫暐.docx","lessonId":"19","url":"http://101.254.183.67:9980/group1/M00/00/2E/wKiTPlhkt5yAS3lLAADzAme9a5c66.docx"},{"handOutsId":"26","name":"鏂板缓 Microsoft Word 鏂囨。 - 鐢ㄤ簬鍚堝苟.docx","lessonId":"12","url":"http://101.254.183.67:9980/group1/M00/00/2D/wKiTPlhiLC-AYxp5AAHtis6W7XE02.docx"},{"handOutsId":"23","name":"鏂板缓鏂囨湰鏂囨。.txt","lessonId":"19","url":"http://101.254.183.67:9980/group1/M00/00/2C/wKiTPlhiE3aAWjrbAAABcnum5Yo734.txt"},{"handOutsId":"22","name":"鏂板缓鏂囨湰鏂囨。.txt","lessonId":"19","url":"http://101.254.183.67:9980/group1/M00/00/2C/wKiTPlhiExWAE5SPAAABcnum5Yo487.txt"},{"handOutsId":"19","name":"鏂板缓 Microsoft Word 鏂囨。 - 鐢ㄤ簬鍚堝苟.docx","lessonId":"19","url":"http://101.254.183.67:9980/group1/M00/00/2C/wKiTPlhiEjaAIJAfAAHtis6W7XE45.docx"},{"handOutsId":"17","name":"鏂板缓鏂囨湰鏂囨。 (2).txt","lessonId":"12","url":"http://101.254.183.67:9980/group1/M00/00/2C/wKiTPlhiEbiAbKmnAAAA3YlqzVI646.txt"},{"handOutsId":"16","name":"鏂板缓鏂囨湰鏂囨。.txt","lessonId":"19","url":"http://101.254.183.67:9980/group1/M00/00/2C/wKiTPlhiEa2AUgwLAAABcnum5Yo465.txt"},{"handOutsId":"13","name":"鏂板缓鏂囨湰鏂囨。.txt","lessonId":"13","url":"http://101.254.183.67:9980/group1/M00/00/2C/wKiTPlhiEV-AfX_8AAABcnum5Yo530.txt"},{"handOutsId":"12","name":"鏂板缓鏂囨湰鏂囨。 (2).txt","lessonId":"19","url":"http://101.254.183.67:9980/group1/M00/00/2C/wKiTPlhiEUuALo2BAAAA3YlqzVI426.txt"},{"handOutsId":"11","name":"鏂板缓鏂囨湰鏂囨。 (3).txt","lessonId":"19","url":"http://101.254.183.67:9980/group1/M00/00/2C/wKiTPlhiESKAZeg4AAAR-yszDFI722.txt"},{"handOutsId":"10","name":"鏂板缓鏂囨湰鏂囨。.txt","lessonId":"19","url":"http://101.254.183.67:9980/group1/M00/00/2C/wKiTPlhiEQyAeSOJAAABcnum5Yo407.txt"},{"handOutsId":"9","name":"鍚堝悓妯℃澘娴嬭瘯.docx","lessonId":"17","url":"http://101.254.183.67:9980/group1/M00/00/2C/wKiTPlhiEO-AUeakAAAygmd3--c12.docx"},{"handOutsId":"6","name":"鍚堝悓妯℃澘娴嬭瘯.docx","lessonId":"16","url":"http://101.254.183.67:9980/group1/M00/00/2C/wKiTPlhiEEGAYuVrAAAygmd3--c50.docx"}],"totalNum":24}
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
         * handOutsList : [{"handOutsId":"35","name":"璇剧▼娴嬭瘯璁蹭箟-1","lessonId":"25","url":"http://101.254.183.67:9980/group1/M00/00/31/wKiTPlh0ebmAeYKeAAbD49OEsfg287.pdf"},{"handOutsId":"34","name":"璇剧▼娴嬭瘯4璁蹭箟-2","lessonId":"15","url":"http://101.254.183.67:9980/group1/M00/00/31/wKiTPlh0eZOAINgOAAbD49OEsfg896.pdf"},{"handOutsId":"33","name":"璇剧▼娴嬭瘯4璁蹭箟-1","lessonId":"15","url":"http://101.254.183.67:9980/group1/M00/00/31/wKiTPlh0eYeAF7eUAAbD49OEsfg322.pdf"},{"handOutsId":"32","name":"璇剧▼娴嬭瘯浜旇涔�-2","lessonId":"21","url":"http://101.254.183.67:9980/group1/M00/00/31/wKiTPlh0eSWAIQ-sAAbD49OEsfg029.pdf"},{"handOutsId":"31","name":"璇剧▼娴嬭瘯浜旇涔�-1","lessonId":"21","url":"http://101.254.183.67:9980/group1/M00/00/31/wKiTPlh0eRiAOyVSAAbD49OEsfg316.pdf"},{"handOutsId":"29","name":"璁蹭箟娴嬭瘯","lessonId":"18","url":"http://101.254.183.67:9980/group1/M00/00/2E/wKiTPlhmFbSAQGReAAAygmd3--c22.docx"},{"handOutsId":"28","name":"娴嬭瘯闂pc.docx","lessonId":"18","url":"http://101.254.183.67:9980/group1/M00/00/2E/wKiTPlhmEY-AN3nIABvFSfCsStM66.docx"},{"handOutsId":"27","name":"钀ユ敹绛栫暐.docx","lessonId":"19","url":"http://101.254.183.67:9980/group1/M00/00/2E/wKiTPlhkt5yAS3lLAADzAme9a5c66.docx"},{"handOutsId":"26","name":"鏂板缓 Microsoft Word 鏂囨。 - 鐢ㄤ簬鍚堝苟.docx","lessonId":"12","url":"http://101.254.183.67:9980/group1/M00/00/2D/wKiTPlhiLC-AYxp5AAHtis6W7XE02.docx"},{"handOutsId":"23","name":"鏂板缓鏂囨湰鏂囨。.txt","lessonId":"19","url":"http://101.254.183.67:9980/group1/M00/00/2C/wKiTPlhiE3aAWjrbAAABcnum5Yo734.txt"},{"handOutsId":"22","name":"鏂板缓鏂囨湰鏂囨。.txt","lessonId":"19","url":"http://101.254.183.67:9980/group1/M00/00/2C/wKiTPlhiExWAE5SPAAABcnum5Yo487.txt"},{"handOutsId":"19","name":"鏂板缓 Microsoft Word 鏂囨。 - 鐢ㄤ簬鍚堝苟.docx","lessonId":"19","url":"http://101.254.183.67:9980/group1/M00/00/2C/wKiTPlhiEjaAIJAfAAHtis6W7XE45.docx"},{"handOutsId":"17","name":"鏂板缓鏂囨湰鏂囨。 (2).txt","lessonId":"12","url":"http://101.254.183.67:9980/group1/M00/00/2C/wKiTPlhiEbiAbKmnAAAA3YlqzVI646.txt"},{"handOutsId":"16","name":"鏂板缓鏂囨湰鏂囨。.txt","lessonId":"19","url":"http://101.254.183.67:9980/group1/M00/00/2C/wKiTPlhiEa2AUgwLAAABcnum5Yo465.txt"},{"handOutsId":"13","name":"鏂板缓鏂囨湰鏂囨。.txt","lessonId":"13","url":"http://101.254.183.67:9980/group1/M00/00/2C/wKiTPlhiEV-AfX_8AAABcnum5Yo530.txt"},{"handOutsId":"12","name":"鏂板缓鏂囨湰鏂囨。 (2).txt","lessonId":"19","url":"http://101.254.183.67:9980/group1/M00/00/2C/wKiTPlhiEUuALo2BAAAA3YlqzVI426.txt"},{"handOutsId":"11","name":"鏂板缓鏂囨湰鏂囨。 (3).txt","lessonId":"19","url":"http://101.254.183.67:9980/group1/M00/00/2C/wKiTPlhiESKAZeg4AAAR-yszDFI722.txt"},{"handOutsId":"10","name":"鏂板缓鏂囨湰鏂囨。.txt","lessonId":"19","url":"http://101.254.183.67:9980/group1/M00/00/2C/wKiTPlhiEQyAeSOJAAABcnum5Yo407.txt"},{"handOutsId":"9","name":"鍚堝悓妯℃澘娴嬭瘯.docx","lessonId":"17","url":"http://101.254.183.67:9980/group1/M00/00/2C/wKiTPlhiEO-AUeakAAAygmd3--c12.docx"},{"handOutsId":"6","name":"鍚堝悓妯℃澘娴嬭瘯.docx","lessonId":"16","url":"http://101.254.183.67:9980/group1/M00/00/2C/wKiTPlhiEEGAYuVrAAAygmd3--c50.docx"}]
         * totalNum : 24
         */

        private int totalNum;
        private ArrayList<HandOutsListBean> handOutsList;

        public int getTotalNum() {
            return totalNum;
        }

        public void setTotalNum(int totalNum) {
            this.totalNum = totalNum;
        }

        public ArrayList<HandOutsListBean> getHandOutsList() {
            return handOutsList;
        }

        public void setHandOutsList(ArrayList<HandOutsListBean> handOutsList) {
            this.handOutsList = handOutsList;
        }

        public static class HandOutsListBean {
            /**
             * handOutsId : 35
             * name : 璇剧▼娴嬭瘯璁蹭箟-1
             * lessonId : 25
             * url : http://101.254.183.67:9980/group1/M00/00/31/wKiTPlh0ebmAeYKeAAbD49OEsfg287.pdf
             */

            private String handOutsId;
            private String name;
            private String lessonId;
            private String url;
            private boolean isSelect;

            public boolean isSelect() {
                return isSelect;
            }

            public void setSelect(boolean select) {
                isSelect = select;
            }

            public String getHandOutsId() {
                return handOutsId;
            }

            public void setHandOutsId(String handOutsId) {
                this.handOutsId = handOutsId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getLessonId() {
                return lessonId;
            }

            public void setLessonId(String lessonId) {
                this.lessonId = lessonId;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
