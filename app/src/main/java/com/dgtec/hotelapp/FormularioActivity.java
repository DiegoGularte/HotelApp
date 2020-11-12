package com.dgtec.hotelapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FormularioActivity extends AppCompatActivity {

    private EditText etNome, etCPF, etEmail, etEndereco, etdata;
    private Button btnSalvar;
    private String acao;
    private Cliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        acao = getIntent().getExtras().getString("acao");

        etNome = findViewById(R.id.etNome);
        etCPF = findViewById(R.id.etCPF);
        etEndereco = findViewById(R.id.etEndereco);
        etEmail = findViewById(R.id.etEmail);
        etdata = findViewById(R.id.etData);
        btnSalvar = findViewById(R.id.btnSalvar);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();
            }
        });

        if( acao.equals("editar")){
            int id = getIntent().getExtras().getInt("idCliente");
            cliente = ClienteDAO.getProdutoById(FormularioActivity.this, id);
            etNome.setText(cliente.getNome());
            etCPF.setText(String.valueOf(cliente.getCPF()));
            etEndereco.setText(cliente.getEndereco());
            etEmail.setText(cliente.getEmail());
            etdata.setText(cliente.getData());
        }

    }

    private void salvar(){
        if ( cliente == null ){
            cliente = new Cliente();
        }

        String nome = etNome.getText().toString();
        if ( nome.isEmpty() ){
            AlertDialog.Builder alerta = new AlertDialog.Builder( this);
            alerta.setTitle("Atenção!");
            alerta.setMessage("O nome do Cliente deve ser preenchido!");
            alerta.setIcon( android.R.drawable.ic_dialog_alert );
            alerta.setPositiveButton("OK", null);
            alerta.show();
        }else{
            cliente.setNome( nome );
            String sPreco = etCPF.getText().toString();
            //hotel
            cliente.setEndereco(etEndereco.getText().toString());
            cliente.setEmail(etEndereco.getText().toString());
            cliente.setData(etdata.getText().toString());
            if( sPreco.isEmpty() ){
                cliente.setCPF( 000 );

            }else {
                sPreco = sPreco.replace( "," , ".");
                double preco = Double.valueOf( sPreco );
                cliente.setCPF( preco );
            }

            if( acao.equals( "inserir" ) ){
                ClienteDAO.inserir( this , cliente);
                cliente = null;
                etCPF.setText("");
                etNome.setText("");
                etEndereco.setText("");
                etEmail.setText("");
                etdata.setText("");
            }else{
                ClienteDAO.editar(this, cliente);
                finish();
            }

        }
    }

}