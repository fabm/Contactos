package pt.ipg.mcm.contactos.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import pt.ipg.mcm.app.bd.AcessoBd;
import pt.ipg.mcm.app.bd.gen.Contacto;

public class AddContacto extends Activity {
  public static final int REQUEST = 1;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_contacto);
    Button button = (Button) findViewById(R.id.btOk);
    button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        EditText et = (EditText) findViewById(R.id.editText);
        String nome = et.getText().toString();
        AcessoBd acessoBd = new AcessoBd();
        acessoBd.init(getApplicationContext(), Contactos.CONTACTOS);
        Contacto contacto = new Contacto();
        contacto.setNome(nome);
        acessoBd.getSession().getContactoDao().insertInTx(contacto);
        acessoBd.close();
        AddContacto.this.finish();
      }
    });
  }


}
