package com.laichushu.book.bean.netbean;

/**
 * 创建模版
 * Created by wangtong on 2016/11/23.
 */

public class CoverMake_Paramet {
    private String id;//模版id
    private String authorId;//作者id
    private String name;//作品名称id
    private String type;//类型
    private String url;//制作url

    public CoverMake_Paramet(String id, String authorId, String name, String type, String url) {
        this.id = id;
        this.authorId = authorId;
        this.name = name;
        this.type = type;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
