package com.dgtec.hotelapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public static void inserir(Context context, Cliente cliente){

        ContentValues valores = new ContentValues();
        valores.put("nome", cliente.getNome());
        valores.put("RG", cliente.getRG());
        valores.put("CPF", cliente.getCPF());
        valores.put("CEP", cliente.getCEP());
        valores.put("endereco", cliente.getEndereco());
        valores.put("dataNasc",cliente.getDataNasc());
        valores.put("sexo", cliente.getSexo());
        valores.put("estadoCivil",cliente.getEstadoCivil());

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();

        db.insert("clientes", null, valores);


    }

    public static void editar(Context context, Cliente cliente){

        ContentValues valores = new ContentValues();
        valores.put("nome", cliente.getNome());
        valores.put("RG", cliente.getRG());
        valores.put("CPF", cliente.getCPF());
        valores.put("CEP", cliente.getCEP());
        valores.put("endereco", cliente.getEndereco());
        valores.put("dataNasc",cliente.getDataNasc());
        valores.put("sexo", cliente.getSexo());
        valores.put("estadoCivil",cliente.getEstadoCivil());

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();

        db.update("clientes",valores,"id = " + cliente.getId(), null);

    }

    public static void excluir(Context context, int idCliente){

        Banco banco = new Banco(context);

        SQLiteDatabase db = banco.getWritableDatabase();

        db.delete("clientes","id = " + idCliente, null);

    }

    public static List<Cliente> getClientes(Context context){
        List<Cliente> lista = new ArrayList<>();

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM clientes", null);

        if(cursor.getCount() > 0){

            cursor.moveToFirst();
            do{
                Cliente p = new Cliente();
                p.setId(cursor.getInt(0));
                p.setNome(cursor.getString(1));
                p.setRG(cursor.getString(2));
                p.setCPF(cursor.getString(3));
                p.setCEP(cursor.getString(4));
                p.setEndereco(cursor.getString(5));
                p.setTelefone(cursor.getString(6));
                p.setDataNasc(cursor.getString(7));
                p.setSexo(cursor.getString(8));
                p.setEstadoCivil(cursor.getString(9));
                lista.add( p );

            }while(cursor.moveToNext());
        }

        return lista;
    }

    public static Cliente getProdutoById(Context context, int idCliente){

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM clientes WHERE id = " + idCliente, null);

        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            Cliente p = new Cliente();
            p.setId(cursor.getInt(0));
            p.setNome(cursor.getString(1));
            p.setRG(cursor.getString(2));
            p.setCPF(cursor.getString(3));
            p.setCEP(cursor.getString(4));
            p.setEndereco(cursor.getString(5));
            p.setTelefone(cursor.getString(6));
            p.setDataNasc(cursor.getString(7));
            p.setSexo(cursor.getString(8));
            p.setEstadoCivil(cursor.getString(9));
            return  p;
        }else
            return null;
    }
}
