package com.extli.bloodiagluc;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    LecturaDataBaseAdapter lAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Adaptador abierto*/
        lAdapter = new LecturaDataBaseAdapter(this);
        lAdapter = lAdapter.open();
        /* Declaracion de elementos de la interfaz para operar sobre ellos*/

        final EditText preP = (EditText)findViewById(R.id.prePrandial);
        final EditText postP = (EditText)findViewById(R.id.postPrandial);
        Button registro = (Button)findViewById(R.id.btnRegistro);

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strPrep = preP.getText().toString();
                String strPost = postP.getText().toString();
                if( strPrep.matches("") || strPost.matches("") ){
                    Toast.makeText(MainActivity.this, "Por favor completa los campos", Toast.LENGTH_SHORT).show();
                }
                else{
                    try{
                        lAdapter.insertEntry(strPrep, strPost, 1);
                    }catch (Exception e){
                        Log.e("Error: ", e.toString());
                    }
                    Toast.makeText(MainActivity.this, "Registro insertado con exito", Toast.LENGTH_SHORT).show();
                    lAdapter.close();
                    //finish();
                }
                preP.setText("");
                postP.setText("");

                float fpreP = Float.parseFloat(strPrep);
                float fpostP = Float.parseFloat(strPost);
                if(fpostP > 170.0 || fpreP > 170.0){
                    String phoneNo = "2221091551";
                    String msg = "Alerta de usuario con nivel critico de glucosa";
                    try {
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(phoneNo, null, msg, null, null);
                        Toast.makeText(getApplicationContext(), "Mensaje enviado",
                                Toast.LENGTH_LONG).show();
                        sendEmail();
                    } catch (Exception ex) {
                        Toast.makeText(getApplicationContext(),
                                ex.getMessage().toString(),
                                Toast.LENGTH_LONG).show();
                        ex.printStackTrace();
                    }
                }

            }
        });

    }

    public void sendEmail() {
        Log.i("Enviar mail", "");
        String[] TO = {"jgcastrejona@gmail.com"};
        String[] CC = {"jcastrejona@gmail.com"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Alerta de glucosa");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "El nivel de glucosa del paciente ha sido rebasado");

        try {
            startActivity(Intent.createChooser(emailIntent, "Enviando..."));
            finish();
            Log.i("Email enviado", "");
        }
        catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "No hay cliente instalado.", Toast.LENGTH_SHORT).show();
        }
    }
}
