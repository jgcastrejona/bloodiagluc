package com.extli.bloodiagluc;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class RegistroPaciente extends AppCompatActivity{

    LoginDataBaseAdapter loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_paciente);

        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();
        final EditText nombrePac = (EditText)findViewById(R.id.namePaciente);
        final EditText direccPac = (EditText)findViewById(R.id.addrPaciente);
        final EditText edadPac = (EditText)findViewById(R.id.agePaciente);
        final EditText padeciPac = (EditText)findViewById(R.id.disPaciente);
        final EditText userPac = (EditText)findViewById(R.id.usrPaciente);
        final EditText passPac = (EditText)findViewById(R.id.passPaciente);


        Button registroPac = (Button)findViewById(R.id.btnRegMedico);

        registroPac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nomText = nombrePac.getText().toString();
                String dirText = direccPac.getText().toString();
                String edaText = edadPac.getText().toString();
                String padText = padeciPac.getText().toString();
                String usrText = userPac.getText().toString();
                String pasText = passPac.getText().toString();
                /* A String*/
                /*String nPac = nomText.toString();
                String dPac = dirText.toString();
                String ePac = edaText.toString();
                String pPac = padText.toString();
                String uPac = usrText.toString();
                String cPac = pasText.toString();*/

                if(nomText.matches("") || dirText.matches("") || edaText.matches("") || padText.matches("") || usrText.matches("") || pasText.matches("") ){
                    Toast.makeText(RegistroPaciente.this, "Por favor completa los campos", Toast.LENGTH_SHORT).show();
                }
                else{
                    try{
                        loginDataBaseAdapter.insertEntry(nomText, dirText, edaText, padText, usrText, pasText);
                    }catch (Exception e){
                        Log.e("Error: ", e.toString());
                    }
                    Toast.makeText(RegistroPaciente.this, "Cuenta creada con exito", Toast.LENGTH_SHORT).show();
                    Intent returnLogin = new Intent(RegistroPaciente.this, LoginActivity.class);
                    startActivity(returnLogin);
                    //finish();
                }
            }
        });

    }

   protected  void onDestroy(){
        super.onDestroy();
        loginDataBaseAdapter.close();
    }
}
