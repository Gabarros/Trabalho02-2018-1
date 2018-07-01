package model;

import java.io.Serializable;
import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Eleitor extends RealmObject implements Serializable {

    @PrimaryKey
    private int id;
    private String nome;
    private String nomemae;
    private Date datanascimento;
    private int numerotitulo;
    private int zona;
    private int secao;
    private String municipio;

    public Eleitor(){

    }

    public Eleitor(int id, String nome, String nomemae, Date datanascimento, int numerotitulo,
                   int zona, int secao, String municipio){

        this.setId(id);
        this.nome = nome;
        this.nomemae = nomemae;
        this.datanascimento = datanascimento;
        this.numerotitulo = numerotitulo;
        this.zona = zona;
        this.secao = secao;
        this.municipio = municipio;

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomemae() {
        return nomemae;
    }

    public void setNomemae(String nomemae) {
        this.nomemae = nomemae;
    }

    public Date getDatanascimento() {
        return datanascimento;
    }

    public void setDatanascimento(Date datanascimento) {
        this.datanascimento = datanascimento;
    }

    public int getNumerotitulo() {
        return numerotitulo;
    }

    public void setNumerotitulo(int numerotitulo) {
        this.numerotitulo = numerotitulo;
    }

    public int getZona() {
        return zona;
    }

    public void setZona(int zona) {
        this.zona = zona;
    }

    public int getSecao() {
        return secao;
    }

    public void setSecao(int secao) {
        this.secao = secao;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
