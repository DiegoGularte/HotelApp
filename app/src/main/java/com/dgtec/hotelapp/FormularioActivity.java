package com.dgtec.hotelapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
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
    private Spinner spinner;


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
         spinner = (Spinner) findViewById(R.id.estadoCivil_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.estadoCivil_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);



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
            etRG.setText(cliente.getRG());
            etCPF.setText(cliente.getCPF());
            etCEP.setText(cliente.getCEP());
            etEndereco.setText(cliente.getEndereco());
            etTelefone.setText(cliente.getTelefone());
            etDataNasc.setText(cliente.getDataNasc());

            // setar o valor da tabela no campo sexo
            String selecSexo = cliente.getSexo();

            Log.i("sexo",selecSexo);
            switch (selecSexo) {
                case "Masculino":
                    RadioButton radioButtonM = findViewById(R.id.radio_masculino);
                    radioButtonM.setChecked(true);
                    break;

                case "Feminino":
                    RadioButton radioButtonF = findViewById(R.id.radio_feminino);
                    radioButtonF.setChecked(true);
                    break;
                default:
                    RadioButton radioButtonO = findViewById(R.id.radio_outros);
                    radioButtonO.setChecked(true);
                    break;
            }

            String estadoCivil = cliente.getEstadoCivil();
           // Log.i("estado",estadoCivil);
            String[] arrayEstados = getResources().getStringArray(R.array.estadoCivil_array);
            for(int i = 0; i < arrayEstados.length; i++){
                if(arrayEstados[i].equals(estadoCivil)){
                    spinner.setSelection(i);
                    break;
                }
            }


        }
    }

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
            cliente.setEstadoCivil(spinner.getSelectedItem().toString());;
            //Sexo
            int sexoSelec = rgSexo.getCheckedRadioButtonId();

            if (sexoSelec != -1){
                //algum radiobutton foi selecionado
                RadioButton rbSexoSelec = findViewById(sexoSelec);

                cliente.setSexo(rbSexoSelec.getText().toString());
                //Log.i("sexoR",cliente.getSexo());
            }

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