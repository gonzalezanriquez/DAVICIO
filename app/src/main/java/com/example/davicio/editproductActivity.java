package com.example.davicio;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Map;
import java.util.concurrent.ExecutorService;

public class editproductActivity extends sinBarraSuperior {
    Button btneliminarproducto, btnEditarProducto, btnconsultarProducto;
    EditText nombre, descripcion, precio, id;
    Map<String, String> info;
    private DbSQLHelper dbSQLHelper;
    private SQLiteDatabase db;
    ExecutorService executorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editproduct);

        btnconsultarProducto = findViewById(R.id.btnconsultarProducto);
        btnEditarProducto = findViewById(R.id.btnEditarProducto);
        btneliminarproducto = findViewById(R.id.btneliminarproducto);
        nombre = findViewById(R.id.editTextNombreproducto);
        descripcion = findViewById(R.id.editTextDescripcionproducto);
        precio = findViewById(R.id.editTextPrecioproducto);
        id = findViewById(R.id.editIdProducto);

        dbSQLHelper = new DbSQLHelper(this);
        db = dbSQLHelper.getWritableDatabase();



        btnconsultarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int productId = Integer.parseInt(id.getText().toString());
                info = dbSQLHelper.consultaproductos(productId);
                if (info != null) {
                    nombre.setText(info.get("nombre"));
                    descripcion.setText(info.get("descripcion"));
                    precio.setText(info.get("precio"));
                } else {
                    Toast.makeText(editproductActivity.this, "No se encontró el producto con el ID especificado", Toast.LENGTH_SHORT).show();
                    nombre.setText("");
                    descripcion.setText("");
                    precio.setText("");
                }
            }
        });

        btnEditarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query=dbSQLHelper.queryEditarProducto(Integer.parseInt(id.getText().toString()),nombre.getText().toString(), descripcion.getText().toString(),precio.getText().toString());
                db.execSQL(query);

                id.setText("");
                nombre.setText("");
                descripcion.setText("");
                precio.setText("");

                Toast.makeText(editproductActivity.this, "PRODUCTO EDITADO DE MANERA CORRECTA", Toast.LENGTH_SHORT).show();


            }
        });

        btneliminarproducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String query=dbSQLHelper.queryEliminarProducto(Integer.parseInt(id.getText().toString()));
                db.execSQL(query);
                id.setText("");
                nombre.setText("");
                descripcion.setText("");
                precio.setText("");

                Toast.makeText(editproductActivity.this, "PRODUCTO ELIMINADO DE MANERA CORRECTA", Toast.LENGTH_SHORT).show();

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
