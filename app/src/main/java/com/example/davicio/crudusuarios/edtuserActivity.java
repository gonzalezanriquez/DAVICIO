package com.example.davicio.crudusuarios;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.davicio.R;
import com.example.davicio.contexto.DbSQLHelper;
import com.example.davicio.sinBarraSuperior;

import java.util.Map;
import java.util.concurrent.ExecutorService;

public class edtuserActivity extends sinBarraSuperior {
    Button consultar, editar,eliminar;
    EditText nombre, apellido, mail, contrasenia,id;
    TextView camposincompletos;
    Map<String,String> info;
    ExecutorService executorService;
    private DbSQLHelper dbSQLHelper;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edtuser);

        id = findViewById(R.id.editTextId);
        nombre = findViewById(R.id.editTextNombre);
        apellido = findViewById(R.id.editTextApellido);
        mail = findViewById(R.id.editTextMail);
        contrasenia = findViewById(R.id.editTextContrasenia);
        camposincompletos = findViewById(R.id.camposincompletos);
        consultar= findViewById(R.id.btnconsultar);
        eliminar= findViewById(R.id.btneliminar);
        editar=findViewById(R.id.btneditar);
        dbSQLHelper = new DbSQLHelper(this);
        db = dbSQLHelper.getWritableDatabase();


        consultar.setOnClickListener(view -> {
            info = dbSQLHelper.consulta(Integer.parseInt(id.getText().toString()));
            if (info != null) {
                nombre.setText(info.get("nombre"));
                apellido.setText(info.get("apellido"));
                mail.setText(info.get("mail"));
                contrasenia.setText(info.get("contrasenia"));
            }
        }   );
        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String queryEditarUsuario=dbSQLHelper.queryEditarUsuario(Integer.parseInt(id.getText().toString()),nombre.getText().toString(),apellido.getText().toString(),mail.getText().toString(),contrasenia.getText().toString());

                db.execSQL(queryEditarUsuario);

                id.setText("");
                nombre.setText("");
                apellido.setText("");
                mail.setText("");
                contrasenia.setText("");
                Toast.makeText(edtuserActivity.this, "USUARIO EDITADO DE MANERA CORRECTA", Toast.LENGTH_SHORT).show();


            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query=dbSQLHelper.queryEliminarUsuario(Integer.parseInt(id.getText().toString()));
                db.execSQL(query);
                id.setText("");
                nombre.setText("");
                apellido.setText("");
                mail.setText("");
                contrasenia.setText("");
                Toast.makeText(edtuserActivity.this, "USUARIO ELIMINADO DE MANERA CORRECTA", Toast.LENGTH_SHORT).show();


            }
        });
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