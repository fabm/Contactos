package pt.ipg.mcm.app.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import pt.ipg.mcm.app.bd.gen.DaoMaster;
import pt.ipg.mcm.app.bd.gen.DaoSession;

public class AcessoBd {
  private SQLiteDatabase db;
  private DaoSession session;

  public void init(Context context, String nomeBd) {
    DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, nomeBd, null);
    db = helper.getWritableDatabase();
    DaoMaster daoMaster = new DaoMaster(db);
    session = daoMaster.newSession();
  }


  public DaoSession getSession() {
    return session;
  }

  public SQLiteDatabase getDb() {
    return db;
  }

  public void close() {
    db.close();
  }

}
