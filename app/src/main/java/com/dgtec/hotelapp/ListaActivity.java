package com.dgtec.hotelapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class ListaActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        Button btnIncluir = findViewById(R.id.btnIncluir);
        btnIncluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListaActivity.this, ListClientesActivity.class);
                startActivity(intent);
            }
        });
    }

    public  void exportCSV(View view){

        //geração do dados dos Clientes para exportacao
        List<Cliente> lista = ClienteDAO.getClientes(this);
        StringBuilder data = new StringBuilder();
       /* data.append("nome"  + ","
                + "cpf" + ","
                + "telefone" + ","
                + "cep" + ","
                + "ativo" + "\n");*/
        for(Cliente obj : lista) {
            data.append(  obj.getCPF() + ","
                        + obj.getNome() + ","
                        + obj.getTelefone() + ","
                        + obj.getCEP() + ","
                        + obj.getAtivo() + "\n" );
        }
        //saving the file into device
        try{
            FileOutputStream out = openFileOutput("data.csv", Context.MODE_PRIVATE);
            out.write((data.toString()).getBytes());
            out.close();

            //export
            Context context = getApplicationContext();
            File fileLocation = new File(getFilesDir(),"data.csv");
            Uri path = FileProvider.getUriForFile(context,"com.dgtec.hotelapp.fileprovider",fileLocation);
            Intent fileIntent = new Intent(Intent.ACTION_SEND);
            fileIntent.setType("text/csv");
            fileIntent.putExtra(Intent.EXTRA_SUBJECT,"Data");
            fileIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            fileIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            fileIntent.putExtra(Intent.EXTRA_STREAM,path);
            startActivity(Intent.createChooser(fileIntent,"Export Data"));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

}