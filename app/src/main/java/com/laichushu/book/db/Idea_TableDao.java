package com.laichushu.book.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.laichushu.book.db.Idea_Table;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "IDEA__TABLE".
*/
public class Idea_TableDao extends AbstractDao<Idea_Table, Long> {

    public static final String TABLENAME = "IDEA__TABLE";

    /**
     * Properties of entity Idea_Table.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property BookId = new Property(1, Integer.class, "bookId", false, "BOOK_ID");
        public final static Property Uid = new Property(2, String.class, "Uid", false, "UID");
        public final static Property StyleId = new Property(3, String.class, "styleId", false, "STYLE_ID");
        public final static Property X = new Property(4, Integer.class, "x", false, "X");
        public final static Property Y = new Property(5, Integer.class, "y", false, "Y");
        public final static Property Content = new Property(6, String.class, "content", false, "CONTENT");
        public final static Property Progress = new Property(7, String.class, "progress", false, "PROGRESS");
    };


    public Idea_TableDao(DaoConfig config) {
        super(config);
    }
    
    public Idea_TableDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"IDEA__TABLE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"BOOK_ID\" INTEGER," + // 1: bookId
                "\"UID\" TEXT," + // 2: Uid
                "\"STYLE_ID\" TEXT," + // 3: styleId
                "\"X\" INTEGER," + // 4: x
                "\"Y\" INTEGER," + // 5: y
                "\"CONTENT\" TEXT," + // 6: content
                "\"PROGRESS\" TEXT);"); // 7: progress
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"IDEA__TABLE\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Idea_Table entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Integer bookId = entity.getBookId();
        if (bookId != null) {
            stmt.bindLong(2, bookId);
        }
 
        String Uid = entity.getUid();
        if (Uid != null) {
            stmt.bindString(3, Uid);
        }
 
        String styleId = entity.getStyleId();
        if (styleId != null) {
            stmt.bindString(4, styleId);
        }
 
        Integer x = entity.getX();
        if (x != null) {
            stmt.bindLong(5, x);
        }
 
        Integer y = entity.getY();
        if (y != null) {
            stmt.bindLong(6, y);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(7, content);
        }
 
        String progress = entity.getProgress();
        if (progress != null) {
            stmt.bindString(8, progress);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Idea_Table readEntity(Cursor cursor, int offset) {
        Idea_Table entity = new Idea_Table( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1), // bookId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // Uid
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // styleId
            cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4), // x
            cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5), // y
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // content
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7) // progress
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Idea_Table entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setBookId(cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1));
        entity.setUid(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setStyleId(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setX(cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4));
        entity.setY(cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5));
        entity.setContent(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setProgress(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Idea_Table entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Idea_Table entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
