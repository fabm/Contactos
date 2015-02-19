package pt.ipg.mcm.contactos.app;

import android.app.Application;
import android.test.ApplicationTestCase;
import junit.framework.Assert;
import pt.ipg.mcm.app.bd.AcessoBd;
import pt.ipg.mcm.app.bd.gen.Contacto;
import pt.ipg.mcm.app.bd.gen.ContactoDao;
import pt.ipg.mcm.app.bd.gen.DaoSession;

import static pt.ipg.mcm.app.bd.gen.ContactoDao.Properties.Nome;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {

  public static final String TEST = "test";
  public static final int ID_FRANCISCO = 0;
  public static final int ID_BRUNO = 1;

  public ApplicationTest() {
    super(Application.class);
  }

  public void testContact() {
    final AcessoBd acessoBd = new AcessoBd();

    acessoBd.init(this.getContext(), TEST);

    final DaoSession sessao = acessoBd.getSession();
    final Long[] ids = new Long[2];


    acessoBd.getSession().runInTx(new Runnable() {
      @Override
      public void run() {
        sessao.getContactoDao().deleteAll();
        Contacto contacto = new Contacto();
        contacto.setNome("Francisco");
        sessao.getContactoDao().insert(contacto);

        ids[ID_FRANCISCO] = contacto.getId();
        Assert.assertNotNull(ids[ID_FRANCISCO]);

        contacto = new Contacto();
        contacto.setNome("Bruno");
        sessao.getContactoDao().insert(contacto);

        ids[ID_BRUNO] = contacto.getId();
        Assert.assertNotNull(ids[ID_BRUNO]);
      }
    });

    acessoBd.close();

    acessoBd.init(this.getContext(), TEST);

    final ContactoDao contactoDao = acessoBd.getSession().getContactoDao();

    Assert.assertEquals(2, contactoDao.loadAll().size());

    Contacto contacto = contactoDao.queryBuilder()
        .where(Nome.eq("Francisco"))
        .build()
        .uniqueOrThrow();

    Assert.assertEquals(ids[ID_FRANCISCO], contacto.getId());

    contacto = contactoDao.queryBuilder()
        .where(Nome.eq("Bruno"))
        .build()
        .uniqueOrThrow();

    Assert.assertEquals(ids[ID_BRUNO], contacto.getId());

    acessoBd.close();
  }

}