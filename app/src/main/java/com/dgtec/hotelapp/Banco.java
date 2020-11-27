package com.dgtec.hotelapp;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Banco extends SQLiteOpenHelper{

    private static  final  String NOME_BANCO = "ClientesHotel";
    private static final int VERSAO_BANCO = 1;

    public Banco(Context context){

        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS  clientes (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, nome TEXT NOT NULL,RG TEXT, CPF TEXT, CEP TEXT, endereco TEXT, telefone TEXT, dataNasc TEXT, sexo TEXT, estadoCivil TEXT, ativo TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


}
