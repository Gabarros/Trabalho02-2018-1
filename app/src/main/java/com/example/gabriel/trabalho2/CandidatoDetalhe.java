package com.example.gabriel.trabalho2;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.gabriel.trabalho2.R;

import io.realm.Realm;
import model.Candidato;

public class CandidatoDetalhe extends AppCompatActivity {

    EditText etNome, etNomePartido, etNumeroUrna, etCargo, etNumeroVotos, etEstado, etMunicipio2;

    Button btsalvar,btalterar, btdeletar;

    int id;
    Candidato candidato;
    private Realm realm;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidato_detalhe);

        etNome = (EditText)findViewById(R.id.etNome);
        etNomePartido = (EditText)findViewById(R.id.etNomePartido);
        etNumeroUrna = (EditText)findViewById(R.id.etNumeroUrna);
        etCargo = (EditText)findViewById(R.id.etCargo);
        etNumeroVotos = (EditText)findViewById(R.id.etNumeroVotos);
        etEstado = (EditText)findViewById(R.id.etEstado);
        etMunicipio2 = (EditText)findViewById(R.id.etMunicipio2);

        btsalvar = (Button) findViewById(R.id.bt_salvar_candidato);
        btalterar = (Button) findViewById(R.id.bt_alterar_candidato);
        btdeletar = (Button) findViewById(R.id.bt_deletar_candidato);

        Intent intent = getIntent();
        id = (int) intent.getSerializableExtra("id");
        realm = Realm.getDefaultInstance();

        if (id !=0) {
            btsalvar.setEnabled(false);
            btsalvar.setClickable(false);
            btsalvar.setVisibility(View.INVISIBLE);

            candidato = realm.where(Candidato.class).equalTo("id",id).findFirst();


            etNome.setText(candidato.getNome());
            etNomePartido.setText(candidato.getnomePartido());
            etNumeroUrna.setText(Integer.parseInt(String.valueOf(candidato.getNumeroUrna())));
            etCargo.setText(candidato.getCargo());
            etNumeroVotos.setText(Integer.parseInt(String.valueOf(candidato.getNumeroVotos())));
            etEstado.setText(candidato.getEstado());
            etMunicipio2.setText(candidato.getMunicipio2());

        }else{
            btalterar.setEnabled(false);
            btalterar.setClickable(false);
            btalterar.setVisibility(View.INVISIBLE);
            btdeletar.setEnabled(false);
            btdeletar.setClickable(false);
            btdeletar.setVisibility(View.INVISIBLE);

        }

        btsalvar.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                salvar();
            }
        });
        btalterar.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                alterar();
            }
        });
        btdeletar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                deletar();
            }
        });
    }

    public void deletar(){
        realm.beginTransaction();
        candidato.deleteFromRealm();
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this,"Candidato deletado!",Toast.LENGTH_LONG).show();
        this.finish();

    }
    public void salvar() {
        int proximoID = 1;
        if(realm.where(Candidato.class).max("id") !=null)
            proximoID = realm.where(Candidato.class).max("id").intValue()+1;

        realm.beginTransaction();
        Candidato candidato = new Candidato();
        candidato.setId(proximoID);
        setEGrava(candidato);

        realm.copyToRealm(candidato);
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this,"Novo Candidato Cadastrado",Toast.LENGTH_LONG).show();
        this.finish();

    }

    private void setEGrava(Candidato candidato){

        candidato.setNome(etNome.getText().toString());
        candidato.setnomePartido(etNomePartido.getText().toString());
        candidato.setNumeroUrna(Integer.parseInt(etNumeroUrna.getText().toString()));
        candidato.setCargo(etCargo.getText().toString());
        candidato.setNumeroVotos(Integer.parseInt(etNumeroVotos.getText().toString()));
        candidato.setEstado(etEstado.getText().toString());
        candidato.setMunicipio2(etMunicipio2.getText().toString());

    }
    public void alterar() {

        realm.beginTransaction();

        setEGrava(candidato);

        realm.copyToRealm(candidato);
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this,"Candidato Alterado",Toast.LENGTH_LONG).show();
        this.finish();

    }


}