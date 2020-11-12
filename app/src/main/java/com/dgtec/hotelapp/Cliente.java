package com.dgtec.hotelapp;

public class Cliente {

    private int id;
    private String nome;
    private String RG;
    private String CPF;
    private String CEP;
    private String endereco;
    private String telefone;
    private String dataNasc;
    private String sexo;
    private  String estadoCivil;

    public String getEndereco() {
        return endereco;
    }

    public String getRG() {
        return RG;
    }

    public void setRG(String RG) {
        this.RG = RG;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Cliente() {

    }

    public Cliente(int id, String nome, String RG, String CPF, String CEP, String endereco,String telefone, String data, String sexo, String estadoCivil) {
        this.id = id;
        this.nome = nome;
        this.RG = RG;
        this.CPF = CPF;
        this.CEP = CEP;
        this.endereco = endereco;
        this.telefone = telefone;
        this.dataNasc = data;
        this.sexo = sexo;
        this.estadoCivil = estadoCivil;
    }

    public Cliente(String nome, String RG, String CPF, String CEP, String endereco,String telefone, String data, String sexo, String estadoCivil) {
        this.nome = nome;
        this.RG = RG;
        this.CPF = CPF;
        this.CEP = CEP;
        this.endereco = endereco;
        this.telefone = telefone;
        this.dataNasc = data;
        this.sexo = sexo;
        this.estadoCivil = estadoCivil;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }



    @Override
    public String toString(){
        return  nome + " - CPF " + CPF;
    }


}
