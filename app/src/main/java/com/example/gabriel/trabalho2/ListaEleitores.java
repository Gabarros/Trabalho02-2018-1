package com.example.gabriel.trabalho2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import java.util.List;

import adapter.ClickRecyclerViewListener;
import adapter.EleitorAdapter;
import io.realm.Realm;
import model.Eleitor;

public class ListaEleitores extends AppCompatActivity implements ClickRecyclerViewListener {

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_eleitores);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        realm = Realm.getDefaultInstance();
        Button fab = (Button) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListaEleitores.this,EleitorDetalhe.class);
                intent.putExtra("id",0);
                startActivity(intent);
            }

        });
    }


    protected void onResume() {

        super.onResume();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvEleitores);

        recyclerView.setAdapter(new EleitorAdapter(getEleitores(),this,this));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public List<Eleitor> getEleitores(){

        return (List) realm.where(Eleitor.class).findAll();

    }

    @Override
    public void onClick(Object object) {
       Eleitor eleitor = (Eleitor) object;
        Intent intent = new Intent(ListaEleitores.this,EleitorDetalhe.class);
        intent.putExtra("id",eleitor.getId());
        startActivity(intent);
    }


    public void finish(){
        super.finish();
        realm.close();


    }
}