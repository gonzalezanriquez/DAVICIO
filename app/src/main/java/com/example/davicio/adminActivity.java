package com.example.davicio;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.davicio.crudproductos.ListProductsActivity;
import com.example.davicio.crudusuarios.ListUserActivity;

public class adminActivity extends sinBarraSuperior {

    ImageButton listuser,listproduct, listsucursales;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        listuser=findViewById(R.id.btnlistUsu);
        listproduct=findViewById(R.id.btnlistaproductos);
        listsucursales=findViewById(R.id.btnlistasucursales);
        title=findViewById(R.id.titulonombre);



        /* LISTAS Y USUARIOS */
        listuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(adminActivity.this, ListUserActivity.class);
                startActivity(intent);
            }
        });

        listproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent prod= new Intent(adminActivity.this, ListProductsActivity.class);
                startActivity(prod);
            }
        });
        listsucursales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent prod= new Intent(adminActivity.this, ListProductsActivity.class);
                startActivity(prod);
            }
        });
    }

}