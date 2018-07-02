package com.example.gabriel.trabalho2;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;


import java.util.List;

import adapter.ClickRecyclerViewListener;
import adapter.CandidatoAdapter;
import io.realm.Realm;
import model.Candidato;

public class ListaCandidatos extends AppCompatActivity implements ClickRecyclerViewListener {

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_eleitores);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        realm = Realm.getDefaultInstance();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListaCandidatos.this,CandidatoDetalhe.class);
                intent.putExtra("id",0);
                startActivity(intent);
            }

        });
    }



    protected void onResume() {

        super.onResume();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvCandidatos);

        recyclerView.setAdapter(new CandidatoAdapter(getCandidatos(),this,this));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public List<Candidato> getCandidatos(){

        return (List) realm.where(Candidato.class).findAll();

    }

    @Override
    public void onClick(Object object) {
        Candidato candidato = (Candidato) object;
        Intent intent = new Intent(ListaCandidatos.this, CandidatoDetalhe.class);
        intent.putExtra("id",candidato.getId());
        startActivity(intent);
    }


    public void finish(){
        super.finish();
        realm.close();


    }
}