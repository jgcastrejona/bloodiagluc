package com.extli.bloodiagluc;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        /* Declaracion de los elementos para operar sobre la interfaz*/
        EditText textoUsuario = (EditText)findViewById(R.id.txtUsr);
        EditText textoPasswrd = (EditText)findViewById(R.id.txtPasswd);
        Button btnLogin = (Button)findViewById(R.id.button);
        Button btnRegistro = (Button)findViewById(R.id.buttonreg);
        btnRegistro.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        final CharSequence[] items = {"Medico", "Paciente"};
        AlertDialog.Builder diagRegister = new AlertDialog.Builder(this);

        diagRegister.setTitle("Selecciona una opcion");
        diagRegister.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                    /*Hacer cosas*/
                switch (which) {
                    case 0:
                        Toast.makeText(LoginActivity.this, "Opcion 1", Toast.LENGTH_SHORT).show();
                        Intent changeToMedico = new Intent(getApplicationContext(), RegistroUsr.class);
                        startActivity(changeToMedico);
                        break;
                    case 1:
                        Toast.makeText(LoginActivity.this, "Opcion 2", Toast.LENGTH_SHORT).show();
                        Intent changeToPaciente = new Intent(getApplicationContext(), RegistroUsr.class);
                        startActivity(changeToPaciente);
                        break;

                }
            }
        });
        AlertDialog alerta = diagRegister.create();
        alerta.show();
    }
}
