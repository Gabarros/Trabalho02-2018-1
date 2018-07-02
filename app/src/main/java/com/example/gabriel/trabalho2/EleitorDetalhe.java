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

import io.realm.Realm;
import model.Eleitor;

public class EleitorDetalhe extends AppCompatActivity {

    EditText etNome, etNomeMae, etData, etNumeroTitulo, etZona, etSecao, etMunicipio;

    Button btsalvar,btalterar, btdeletar;

    int id;
    Eleitor eleitor;
    private Realm realm;

    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleitor_detalhe);

        etNome = (EditText)findViewById(R.id.etNome);
        etNomeMae = (EditText)findViewById(R.id.etNomeMae);
        etData = (EditText)findViewById(R.id.etData);
        etNumeroTitulo = (EditText)findViewById(R.id.etNumero);
        etZona = (EditText)findViewById(R.id.etZona);
        etSecao = (EditText)findViewById(R.id.etSecao);
        etMunicipio = (EditText)findViewById(R.id.etMunicipio);

        btsalvar = (Button) findViewById(R.id.bt_salvar_eleitor);
        btalterar = (Button) findViewById(R.id.bt_alterar_eleitor);
        btdeletar = (Button) findViewById(R.id.bt_deletar_eleitor);

        Intent intent = getIntent();
        id = (int) intent.getSerializableExtra("id");
        realm = Realm.getDefaultInstance();

        if (id !=0) {
            btsalvar.setEnabled(false);
            btsalvar.setClickable(false);
            btsalvar.setVisibility(View.INVISIBLE);

            eleitor = realm.where(Eleitor.class).equalTo("id",id).findFirst();


            etNome.setText(eleitor.getNome());
            etNomeMae.setText(eleitor.getNomemae());
            etData.setText(formato.format((Date) eleitor.getDatanascimento()));
            etNumeroTitulo.setText(eleitor.getNumerotitulo());
            etZona.setText(eleitor.getZona());
            etSecao.setText(eleitor.getSecao());
            etMunicipio.setText(eleitor.getMunicipio());

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
        eleitor.deleteFromRealm();
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this,"Eleitor deletado!",Toast.LENGTH_LONG).show();
        this.finish();

    }
    public void salvar() {
        int proximoID = 1;
        if(realm.where(Eleitor.class).max("id") !=null)
            proximoID = realm.where(Eleitor.class).max("id").intValue()+1;

        realm.beginTransaction();
        Eleitor eleitor = new Eleitor();
        eleitor.setId(proximoID);
        setEGrava(eleitor);

        realm.copyToRealm(eleitor);
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this,"Novo Eleitor Cadastrado",Toast.LENGTH_LONG).show();
        this.finish();

    }

    private void setEGrava(Eleitor eleitor){

        eleitor.setNome(etNome.getText().toString());
        eleitor.setNomemae(etNomeMae.getText().toString());
        eleitor.setNumerotitulo(Integer.parseInt(etNumeroTitulo.getText().toString()));
        eleitor.setZona(Integer.parseInt(etZona.getText().toString()));
        eleitor.setSecao(Integer.parseInt(etSecao.getText().toString()));
        eleitor.setMunicipio(etMunicipio.getText().toString());

        try {
            eleitor.setDatanascimento((Date) formato.parse(etData.getText().toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public void alterar() {

        realm.beginTransaction();

        setEGrava(eleitor);

        realm.copyToRealm(eleitor);
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this,"Eleitor Alterado",Toast.LENGTH_LONG).show();
        this.finish();

    }


}