package com.steffanbecker.pedidos.database.orm;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.DaoConfig;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.IdentityScopeType;

import com.steffanbecker.pedidos.database.orm.Categories;
import com.steffanbecker.pedidos.database.orm.Itens;
import com.steffanbecker.pedidos.database.orm.Request_itens;
import com.steffanbecker.pedidos.database.orm.Requests;

import com.steffanbecker.pedidos.database.orm.CategoriesDao;
import com.steffanbecker.pedidos.database.orm.ItensDao;
import com.steffanbecker.pedidos.database.orm.Request_itensDao;
import com.steffanbecker.pedidos.database.orm.RequestsDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig categoriesDaoConfig;
    private final DaoConfig itensDaoConfig;
    private final DaoConfig request_itensDaoConfig;
    private final DaoConfig requestsDaoConfig;

    private final CategoriesDao categoriesDao;
    private final ItensDao itensDao;
    private final Request_itensDao request_itensDao;
    private final RequestsDao requestsDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        categoriesDaoConfig = daoConfigMap.get(CategoriesDao.class).clone();
        categoriesDaoConfig.initIdentityScope(type);

        itensDaoConfig = daoConfigMap.get(ItensDao.class).clone();
        itensDaoConfig.initIdentityScope(type);

        request_itensDaoConfig = daoConfigMap.get(Request_itensDao.class).clone();
        request_itensDaoConfig.initIdentityScope(type);

        requestsDaoConfig = daoConfigMap.get(RequestsDao.class).clone();
        requestsDaoConfig.initIdentityScope(type);

        categoriesDao = new CategoriesDao(categoriesDaoConfig, this);
        itensDao = new ItensDao(itensDaoConfig, this);
        request_itensDao = new Request_itensDao(request_itensDaoConfig, this);
        requestsDao = new RequestsDao(requestsDaoConfig, this);

        registerDao(Categories.class, categoriesDao);
        registerDao(Itens.class, itensDao);
        registerDao(Request_itens.class, request_itensDao);
        registerDao(Requests.class, requestsDao);
    }
    
    public void clear() {
        categoriesDaoConfig.getIdentityScope().clear();
        itensDaoConfig.getIdentityScope().clear();
        request_itensDaoConfig.getIdentityScope().clear();
        requestsDaoConfig.getIdentityScope().clear();
    }

    public CategoriesDao getCategoriesDao() {
        return categoriesDao;
    }

    public ItensDao getItensDao() {
        return itensDao;
    }

    public Request_itensDao getRequest_itensDao() {
        return request_itensDao;
    }

    public RequestsDao getRequestsDao() {
        return requestsDao;
    }

}