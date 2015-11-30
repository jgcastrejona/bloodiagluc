package com.extli.bloodiagluc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistroUsr extends AppCompatActivity {

    MedicoDataBaseHelper medicoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usr);
        medicoAdapter = new MedicoDataBaseHelper(this);
        medicoAdapter = medicoAdapter.open();

        final EditText edNombre = (EditText)findViewById(R.id.nameField);
        final EditText edDirecc = (EditText)findViewById(R.id.addrField);
        final EditText edTelefo = (EditText)findViewById(R.id.phoneField);
        final EditText edMail = (EditText)findViewById(R.id.mailField);
        final EditText adPass = (EditText)findViewById(R.id.passField);
        final EditText edPass = (EditText)findViewById(R.id.passField);
        Button btnRegistro = (Button)findViewById(R.id.btnRegMedico);

        /*Seteamos el listener*/

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strNombre = edNombre.getText().toString();
                String strDirecc = edDirecc.getText().toString();
                String strTelefo = edTelefo.getText().toString();
                String strMail = edMail.getText().toString();
                String strPass = edPass.getText().toString();


                if(strNombre.matches("") || strDirecc.matches("") || strTelefo.matches("") || strMail.matches("") || strPass.matches("") ){
                    Toast.makeText(RegistroUsr.this, "Por favor completa los campos", Toast.LENGTH_SHORT).show();
                }
                else{
                    try{
                        medicoAdapter.insertEntry(strNombre, strDirecc, strTelefo, strMail, strPass);
                    }catch (Exception e){
                        Log.e("Error: ", e.toString());
                    }
                    Toast.makeText(RegistroUsr.this, "Cuenta creada con exito", Toast.LENGTH_SHORT).show();
                    Intent returnLogin = new Intent(RegistroUsr.this, LoginActivity.class);
                    startActivity(returnLogin);
                    //finish();
                }


            }
        });


    }

    protected  void onDestroy(){
        super.onDestroy();
        medicoAdapter.close();
    }
}
