package com.steffanbecker.pedidos.database.orm;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.DaoConfig;
import de.greenrobot.dao.Property;

import com.steffanbecker.pedidos.database.orm.Categories;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table CATEGORIES.
*/
public class CategoriesDao extends AbstractDao<Categories, Long> {

    public static final String TABLENAME = "CATEGORIES";

    /**
     * Properties of entity Categories.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Category_id = new Property(0, Long.class, "category_id", true, "CATEGORY_ID");
        public final static Property Category_name = new Property(1, String.class, "category_name", false, "CATEGORY_NAME");
        public final static Property Category_active = new Property(2, int.class, "category_active", false, "CATEGORY_ACTIVE");
    };


    public CategoriesDao(DaoConfig config) {
        super(config);
    }
    
    public CategoriesDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'CATEGORIES' (" + //
                "'CATEGORY_ID' INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: category_id
                "'CATEGORY_NAME' TEXT NOT NULL ," + // 1: category_name
                "'CATEGORY_ACTIVE' INTEGER NOT NULL );"); // 2: category_active
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'CATEGORIES'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Categories entity) {
        stmt.clearBindings();
 
        Long category_id = entity.getCategory_id();
        if (category_id != null) {
            stmt.bindLong(1, category_id);
        }
        stmt.bindString(2, entity.getCategory_name());
        stmt.bindLong(3, entity.getCategory_active());
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Categories readEntity(Cursor cursor, int offset) {
        Categories entity = new Categories( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // category_id
            cursor.getString(offset + 1), // category_name
            cursor.getInt(offset + 2) // category_active
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Categories entity, int offset) {
        entity.setCategory_id(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setCategory_name(cursor.getString(offset + 1));
        entity.setCategory_active(cursor.getInt(offset + 2));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Categories entity, long rowId) {
        entity.setCategory_id(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Categories entity) {
        if(entity != null) {
            return entity.getCategory_id();
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
