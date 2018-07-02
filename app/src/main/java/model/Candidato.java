package model;

import java.io.Serializable;
import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Candidato extends RealmObject implements Serializable {



    @PrimaryKey
    private int id;
    private String nome;
    private String nomePartido;
    private int numeroUrna;
    private String cargo;
    private int numeroVotos;
    private String estado;
    private String municipio2;


    public Candidato(){

    }
    public Candidato(int id, String nome, String nomePartido, int numeroUrna, int numeroVotos){
        this.setId(id);
        this.nome = nome;
        this.nomePartido = nomePartido;
        this.numeroUrna = numeroUrna;
        this.numeroVotos= numeroVotos;

    }

    public Candidato(int id, String nome, String nomePartido, int numeroUrna,
                   String cargo, int numeroVotos, String estado, String municipio2){

        this.setId(id);
        this.nome = nome;
        this.nomePartido = nomePartido;
        this.numeroUrna = numeroUrna;
       this.cargo = cargo;
       this.numeroVotos = numeroVotos;
       this.estado = estado;
       this.municipio2= municipio2;

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getnomePartido(){
        return nomePartido;
    }

    public void setnomePartido(String nomePartido){
        this.nomePartido = nomePartido;
    }

    public int getNumeroUrna() {
        return numeroUrna;
    }

    public void setNumeroUrna(int numeroUrna) {
        this.numeroUrna = numeroUrna;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public int getNumeroVotos() {
        return numeroVotos;
    }

    public void setNumeroVotos(int numeroVotos) {
        this.numeroVotos = numeroVotos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMunicipio2(){
        return municipio2;
    }

    public void setMunicipio2(String municipio2){
        this.municipio2= municipio2;
    }
}
