package com.example.davicio;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class addproductoActivity extends sinBarraSuperior {

    Button agregar;
    EditText nombre, descripcion,precio;
    TextView camposincompletos;
    private DbSQLHelper dbSQLHelper;
    private SQLiteDatabase db;

    private ExecutorService executorService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproducto);

        agregar=findViewById(R.id.agregarProductoButton);
        nombre=findViewById(R.id.editTextNombre);
        descripcion=findViewById(R.id.editTextDescripcion);
        precio=findViewById(R.id.editTextPrecio);

        dbSQLHelper = new DbSQLHelper(this);

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                db = dbSQLHelper.getWritableDatabase();
            }
        });



        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!nombre.equals("") || !descripcion.equals("")|| !precio.equals("")){
                    if(dbSQLHelper.insertProductos(nombre.getText().toString(),descripcion.getText().toString(),precio.getText().toString()))
                    {
                        nombre.setText("");
                        descripcion.setText("");
                        precio.setText("");
                        Toast.makeText(addproductoActivity.this, "Producto Registrado con éxito", Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(addproductoActivity.this, "El producto ya se encuentra registrado", Toast.LENGTH_SHORT).show();
                    }


                }
                else{
                    Toast.makeText(addproductoActivity.this, "Completar todos los campos", Toast.LENGTH_SHORT).show();
                }
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