package com.dgtec.hotelapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class FormularioActivity extends AppCompatActivity {

    private EditText etNome, etRG, etCPF, etCEP, etEndereco, etTelefone, etDataNasc;
    private Button btnSalvar;
    private String acao;
    private Cliente cliente;
    private RadioGroup rgSexo;
   // private Spinner spinner_estadoCivil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        acao = getIntent().getExtras().getString("acao");

        etNome = findViewById(R.id.etNome);
        etRG = findViewById(R.id.etRG);
        etCPF = findViewById(R.id.etCPF);
        etCEP = findViewById(R.id.etCEP);
        etEndereco = findViewById(R.id.etEndereco);
        etTelefone = findViewById(R.id.etEmail);
        etDataNasc = findViewById(R.id.etData);

        rgSexo = findViewById(R.id.rgSexo);


        //Criando o Spinner estado civil
        /*spinner_estadoCivil = (Spinner) findViewById(R.id.estadoCivil_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.estado_civil, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_estadoCivil.setAdapter(adapter);*/



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
            etTelefone.setText(cliente.getEstadoCivil());
            etDataNasc.setText(cliente.getDataNasc());
        }



    }
    //Radio grupo SEXO
   /* public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_masculino:
                if (checked)
                    cliente.setSexo("masculino");
                    break;
            case R.id.radio_feminino:
                if (checked)
                    cliente.setSexo("Feminino");
                    break;
            default:
                cliente.setSexo("outros");
        }
    }*/
    //Botao salvar um novo Cliente
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
            cliente.setRG(etRG.getText().toString());
            cliente.setCPF(etCPF.getText().toString());; // validacao
            cliente.setCEP(etCEP.getText().toString());
            cliente.setEndereco(etEndereco.getText().toString());
            cliente.setTelefone(etTelefone.getText().toString());
            cliente.setDataNasc(etDataNasc.getText().toString());

            //Sexo
            int sexoSelec = rgSexo.getCheckedRadioButtonId();

            if (sexoSelec != -1){
                //algum radiobutton foi selecionado
                RadioButton rbSexoSelec = findViewById(sexoSelec);

                cliente.setSexo(rbSexoSelec.getText().toString());
            }


            //cliente.setSexo("Outros");
            cliente.setEstadoCivil("Solteiro");

            if( acao.equals( "inserir" ) ){
                ClienteDAO.inserir( this , cliente);
                cliente = null;
                etNome.setText("");
                etRG.setText("");
                etCPF.setText("");
                etCEP.setText("");
                etEndereco.setText("");
                etTelefone.setText("");
                etDataNasc.setText("");
            }else{
                ClienteDAO.editar(this, cliente);
                finish();
            }

        }
    }

}