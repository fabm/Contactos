package pt.ipg.mcm.contactos.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import pt.ipg.mcm.app.bd.AcessoBd;
import pt.ipg.mcm.app.bd.gen.Contacto;

import java.util.ArrayList;
import java.util.List;


public class Contactos extends ActionBarActivity {


  public static final String CONTACTOS = "contactos";
  private List<String> list;
  private ArrayAdapter<String> adapter;

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_contactos, menu);

    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  private void updateListValues() {
    list = new ArrayList<>();
    AcessoBd acessoBd = new AcessoBd();
    acessoBd.init(this.getApplicationContext(), CONTACTOS);
    List<Contacto> contactos = acessoBd.getSession().getContactoDao().loadAll();
    for (Contacto contacto : contactos) {
      list.add(contacto.getNome());
    }
    acessoBd.close();
  }


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_contactos);

    Button btInserir = (Button) findViewById(R.id.btInserir);
    btInserir.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent();
        intent.setClass(Contactos.this, AddContacto.class);
        startActivityForResult(intent, AddContacto.REQUEST);
      }
    });

    final ListView listview = (ListView) findViewById(R.id.listView);
    updateListValues();
    adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
    listview.setAdapter(adapter);

  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    updateListValues();

    adapter.clear();

    for (int i = 0; i <list.size(); i++) {
        adapter.insert(list.get(i), i);
    }
    adapter.notifyDataSetChanged();
  }


}
