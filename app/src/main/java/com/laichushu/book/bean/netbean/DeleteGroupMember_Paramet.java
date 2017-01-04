package com.laichushu.book.bean.netbean;

/**
 * 发现 - 小组 - 删除小组成员
 * Created by wangtong on 2016/12/30.
 */

public class DeleteGroupMember_Paramet {

//    /**
//     * teamId : 282
//     * userId : 175
//     * memberId : 295
//     */
//
//    private String teamId;
//    private String userId;
//    private String memberId;

//    public DeleteGroupMember_Paramet(String teamId, String userId, String memberId) {
//        this.teamId = teamId;
//        this.userId = userId;
//        this.memberId = memberId;
//    }

//    public String getTeamId() {
//        return teamId;
//    }
//
//    public void setTeamId(String teamId) {
//        this.teamId = teamId;
//    }
//
//    public String getUserId() {
//        return userId;
//    }
//
//    public void setUserId(String userId) {
//        this.userId = userId;
//    }

//    public DeleteGroupMember_Paramet(String memberId) {
//        this.memberId = memberId;
//    }
//
//    public String getMemberId() {
//        return memberId;
//    }
//
//    public void setMemberId(String memberId) {
//        this.memberId = memberId;
//    }
    private String teamId;
    private String userId;

    public DeleteGroupMember_Paramet(String memberId, String userId) {
        this.memberId = memberId;
        this.userId = userId;
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

    private String memberId;

    public DeleteGroupMember_Paramet(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
}
