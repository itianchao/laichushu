package com.laichushu.book.db;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * GreenDAO 操作数据库相关类的生成程序
 * Created by wt on 2016/10/21.
 */

public class CustomDAOGenerater {
    public static void main(String[] args) throws Exception {
        /**
         *  参数1：数据库版本
         *  参数2：数据库相关类文件所在的包名
         */
        Schema schema = new Schema(1, "com.laichushu.book.db");
        // 创建数据库表,参数为表名
        Entity entity = schema.addEntity("Search_History");
        // 为表添加字段
        entity.addIdProperty();// 该字段为id
        entity.addStringProperty("history");// String类型字段
        // 缓存表
        Entity cache = schema.addEntity("Cache_Json");
        // 为表添加字段
        cache.addIdProperty();// 该字段为id
        cache.addStringProperty("inter");// String类型字段
        cache.addStringProperty("json");// String类型字段
        //城市表
        Entity city = schema.addEntity("City_Id");
        // 为表添加字段
        city.addIdProperty();// 该字段为id
        city.addStringProperty("province");
        city.addStringProperty("city");
        city.addStringProperty("proCode");
        city.addStringProperty("cityCode");
        //想法表
        Entity idea = schema.addEntity("Idea_Table");
        idea.addIdProperty();// 该字段为id
        idea.addIntProperty("bookId");
        idea.addStringProperty("Uid");
        idea.addStringProperty("styleId");
        idea.addIntProperty("x");
        idea.addIntProperty("y");
        idea.addStringProperty("content");

        //第二个参数指定生成文件的本次存储路径,AndroidStudio工程指定到当前工程的java路径
        new DaoGenerator().generateAll(schema, "D:\\laichushu\\app\\src\\main\\java");
    }
}