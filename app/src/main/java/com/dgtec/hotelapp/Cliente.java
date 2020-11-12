package com.dgtec.hotelapp;

public class Cliente {

    private int id;
    private String nome;
    private double CPF;
    private String endereco;
    private String data;
    private  String email;

    public String getEndereco() {
        return endereco;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Cliente() {

    }

    public Cliente(int id, String nome, double CPF, String endereco, String data, String email) {
        this.id = id;
        this.nome = nome;
        this.CPF = CPF;
        this.endereco = endereco;
        this.data = data;
        this.email = email;
    }

    public Cliente(String nome, double CPF, String endereco, String data, String email) {
        this.nome = nome;
        this.CPF = CPF;
        this.endereco = endereco;
        this.data = data;
        this.email = email;
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

    public double getCPF() {
        return CPF;
    }

    public void setCPF(double CPF) {
        this.CPF = CPF;

    }

    @Override
    public String toString(){
        return  nome + " - CPF " + CPF;
    }


}
