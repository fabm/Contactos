package pt.ipg.mcm.app.bd.gen;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import pt.ipg.mcm.app.bd.gen.Contacto;

import pt.ipg.mcm.app.bd.gen.ContactoDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig contactoDaoConfig;

    private final ContactoDao contactoDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        contactoDaoConfig = daoConfigMap.get(ContactoDao.class).clone();
        contactoDaoConfig.initIdentityScope(type);

        contactoDao = new ContactoDao(contactoDaoConfig, this);

        registerDao(Contacto.class, contactoDao);
    }
    
    public void clear() {
        contactoDaoConfig.getIdentityScope().clear();
    }

    public ContactoDao getContactoDao() {
        return contactoDao;
    }

}
