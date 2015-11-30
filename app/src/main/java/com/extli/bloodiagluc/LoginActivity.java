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

    LoginDataBaseAdapter dbAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        /*Abre el adaptador*/
        dbAdapter = new LoginDataBaseAdapter(this);
        dbAdapter.open();
        /* Declaracion de los elementos para operar sobre la interfaz*/
        final EditText textoUsuario = (EditText)findViewById(R.id.txtUsr);
        final EditText textoPasswrd = (EditText)findViewById(R.id.txtPasswd);
        Button btnLogin = (Button)findViewById(R.id.button);
        Button btnRegistro = (Button)findViewById(R.id.buttonreg);
        /*Listeners*/
        btnRegistro.setOnClickListener(this);

        btnLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(LoginActivity.this, "Click!", Toast.LENGTH_SHORT).show();
                String userName = textoUsuario.getText().toString();
                String password = textoPasswrd.getText().toString();
                String storedPassword = dbAdapter.getSinlgeEntry(userName);
                if(password.equals(storedPassword)){
                    Toast.makeText(LoginActivity.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                    Intent passMain = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(passMain);
                    finish();
                }
                else{
                    Toast.makeText(LoginActivity.this, "Verifica los datos", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override  /*Esto muestra la seleccion de registro*/
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
                        //Toast.makeText(LoginActivity.this, "Opcion 1", Toast.LENGTH_SHORT).show();
                        Intent changeToMedico = new Intent(getApplicationContext(), RegistroUsr.class);
                        startActivity(changeToMedico);
                        break;
                    case 1:
                        //Toast.makeText(LoginActivity.this, "Opcion 2", Toast.LENGTH_SHORT).show();
                        Intent changeToPaciente = new Intent(getApplicationContext(), RegistroPaciente.class);
                        startActivity(changeToPaciente);
                        break;

                }
            }
        });
        AlertDialog alerta = diagRegister.create();
        alerta.show();
    }

    protected  void onDestroy(){
        super.onDestroy();
        dbAdapter.close();
    }

}
