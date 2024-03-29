package com.steffanbecker.pedidos.database.orm;

import java.util.List;
import com.steffanbecker.pedidos.database.orm.DaoSession;
import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table ITENS.
 */
public class Itens {

    private Long item_id;
    private Long item_category_id;
    /** Not-null value. */
    private String item_name;
    private double item_value;
    private String item_image;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient ItensDao myDao;

    private Categories categories;
    private Long categories__resolvedKey;

    private List<Request_itens> for_item_id;

    public Itens() {
    }

    public Itens(Long item_id) {
        this.item_id = item_id;
    }

    public Itens(Long item_id, Long item_category_id, String item_name, double item_value, String item_image) {
        this.item_id = item_id;
        this.item_category_id = item_category_id;
        this.item_name = item_name;
        this.item_value = item_value;
        this.item_image = item_image;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getItensDao() : null;
    }

    public Long getItem_id() {
        return item_id;
    }

    public void setItem_id(Long item_id) {
        this.item_id = item_id;
    }

    public Long getItem_category_id() {
        return item_category_id;
    }

    public void setItem_category_id(Long item_category_id) {
        this.item_category_id = item_category_id;
    }

    /** Not-null value. */
    public String getItem_name() {
        return item_name;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public double getItem_value() {
        return item_value;
    }

    public void setItem_value(double item_value) {
        this.item_value = item_value;
    }

    public String getItem_image() {
        return item_image;
    }

    public void setItem_image(String item_image) {
        this.item_image = item_image;
    }

    /** To-one relationship, resolved on first access. */
    public Categories getCategories() {
        if (categories__resolvedKey == null || !categories__resolvedKey.equals(item_category_id)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            CategoriesDao targetDao = daoSession.getCategoriesDao();
            categories = targetDao.load(item_category_id);
            categories__resolvedKey = item_category_id;
        }
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
        item_category_id = categories == null ? null : categories.getCategory_id();
        categories__resolvedKey = item_category_id;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public synchronized List<Request_itens> getFor_item_id() {
        if (for_item_id == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            Request_itensDao targetDao = daoSession.getRequest_itensDao();
            for_item_id = targetDao._queryItens_For_item_id(item_id);
        }
        return for_item_id;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetFor_item_id() {
        for_item_id = null;
    }

    /** Convenient call for {@link AbstractDao#delete(Object)}. Entity must attached to an entity context. */
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.delete(this);
    }

    /** Convenient call for {@link AbstractDao#update(Object)}. Entity must attached to an entity context. */
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.update(this);
    }

    /** Convenient call for {@link AbstractDao#refresh(Object)}. Entity must attached to an entity context. */
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.refresh(this);
    }

}
