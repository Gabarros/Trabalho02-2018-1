package activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.gabriel.trabalho2.R;

public class Principal extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private String[] activities = {"ListaEleitores"};
    private String[] itemMenu = {"Adicionar Eleitor"};
    private String[] itemMenu2 = {"Adicionar Candidato"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itemMenu);

        ListView listView = (ListView) findViewById(R.id.listaMenu);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

        Intent intent = null;

        try {

            Class obj = Class.forName ("com.example.gabriel.trabalho2."+activities[position]);
            intent = new Intent(this, obj);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        startActivity(intent);

    }

}
