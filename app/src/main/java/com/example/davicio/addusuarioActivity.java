package com.example.davicio;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import java.util.concurrent.ExecutorService;

public class addusuarioActivity extends sinBarraSuperior {
    private DbSQLHelper dbSQLHelper;
    private SQLiteDatabase db;

    Button add, cancelar;
    EditText nombre, apellido, mail, contrasenia;
    TextView camposincompletos;
    ExecutorService executorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificarusuario);

        add=findViewById(R.id.buttonadduser);
        nombre = findViewById(R.id.editTextNombre);
        apellido = findViewById(R.id.editTextApellido);
        mail = findViewById(R.id.editTextMail);
        contrasenia = findViewById(R.id.editTextContrasenia);
        camposincompletos = findViewById(R.id.camposincompletos);
        dbSQLHelper = new DbSQLHelper(this);
        db = dbSQLHelper.getWritableDatabase();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!nombre.equals("") || !apellido.equals("")|| !mail.equals("")|| !contrasenia.equals("")){
                    if( dbSQLHelper.insertUsuario(nombre.getText().toString(),apellido.getText().toString(),mail.getText().toString(),contrasenia.getText().toString())){


                        nombre.setText("");
                        apellido.setText("");
                        mail.setText("");
                        contrasenia.setText("");
                        camposincompletos.setText("Usuario Registrado con éxito");
                    }else{
                        camposincompletos.setText(R.string.yaregistrado);
                    }


                }
                else{
                    camposincompletos.setText(R.string.camposincompletos);
                }
            }
        });







    }






    public void editar (int id){

        Cursor cursor = db.rawQuery("SELECT * FROM usuario WHERE id = '" + id + "' " , null);

        if(cursor.moveToFirst()){

        }else{

        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (db != null) {
            db.close();
        }
        executorService.shutdown();
    }



}