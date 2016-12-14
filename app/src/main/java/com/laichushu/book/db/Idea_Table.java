package com.laichushu.book.db;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "IDEA__TABLE".
 */
public class Idea_Table {

    private Long id;
    private Integer bookId;
    private String Uid;
    private String styleId;
    private Integer x;
    private Integer y;
    private String content;

    public Idea_Table() {
    }

    public Idea_Table(Long id) {
        this.id = id;
    }

    public Idea_Table(Long id, Integer bookId, String Uid, String styleId, Integer x, Integer y, String content) {
        this.id = id;
        this.bookId = bookId;
        this.Uid = Uid;
        this.styleId = styleId;
        this.x = x;
        this.y = y;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String Uid) {
        this.Uid = Uid;
    }

    public String getStyleId() {
        return styleId;
    }

    public void setStyleId(String styleId) {
        this.styleId = styleId;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}