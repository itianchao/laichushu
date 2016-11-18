package com.laichushu.book.bean.netbean;

import java.io.File;

/**
 * 保存新书 参数
 * Created by wangtong on 2016/11/18.
 */

public class ArticleSave_Paramet {
    private String userId;
    private String name;
    private String topCategoryId;
    private String subCategoryId;
    private String permission;
    private String introduce;
    private File file;

    public ArticleSave_Paramet(String userId, String name, String topCategoryId, String subCategoryId, String permission, String introduce, File file) {
        this.userId = userId;
        this.name = name;
        this.topCategoryId = topCategoryId;
        this.subCategoryId = subCategoryId;
        this.permission = permission;
        this.introduce = introduce;
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
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

    public String getTopCategoryId() {
        return topCategoryId;
    }

    public void setTopCategoryId(String topCategoryId) {
        this.topCategoryId = topCategoryId;
    }

    public String getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(String subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
}
