package com.sofacity.laichushu.db;

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
        Schema schema = new Schema(1, "com.sofacity.laichushu.db");
        // 创建数据库表,参数为表名
        Entity entity = schema.addEntity("User");
        // 为表添加字段
        entity.addIdProperty();// 该字段为id
        entity.addStringProperty("name");// String类型字段
        entity.addIntProperty("age");//Int类型字段
        entity.addStringProperty("tel");// String类型字段

        // 生成数据库相关类
        //第二个参数指定生成文件的本次存储路径,AndroidStudio工程指定到当前工程的java路径
        new DaoGenerator().generateAll(schema, "S:\\laichushu\\app\\src\\main\\java");
    }
}
