package com.example.davicio;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.davicio.crudproductos.ListProductsActivity;
import com.example.davicio.crudusuarios.ListUserActivity;

public class adminActivity extends sinBarraSuperior {

    ImageButton edituser,listproduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        edituser=findViewById(R.id.btncrudusuarios);
        listproduct=findViewById(R.id.btncrudproductos);

        /* LISTAS Y USUARIOS */
        edituser.setOnClickListener(new View.OnClickListener() {
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
    }

}