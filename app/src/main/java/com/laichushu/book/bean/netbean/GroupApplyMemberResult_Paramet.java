package com.laichushu.book.bean.netbean;

/**
 * 小组成员 审核处理
 * Created by wangtong on 2016/12/30.
 */

public class GroupApplyMemberResult_Paramet {

    /**
     * teamId : 282
     * userId : 175
     * memberId : 295
     * result : 1 同意 0 拒绝
     */

    private String teamId;
    private String userId;
    private String memberId;
    private String result;

    public GroupApplyMemberResult_Paramet(String teamId, String userId, String memberId, String result) {
        this.teamId = teamId;
        this.userId = userId;
        this.memberId = memberId;
        this.result = result;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
