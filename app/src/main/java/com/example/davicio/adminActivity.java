package com.example.davicio;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class adminActivity extends sinBarraSuperior {

    ImageButton edituser,editproduct,editsucursales, listusers,listproduct,listsucursales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);




        edituser=findViewById(R.id.btncrudusuarios);
        //editproduct=findViewById(R.id.btncrudproductos);
       // editsucursales=findViewById(R.id.btncrudsuucursales);
        listproduct=findViewById(R.id.btncrudsuucursales);
        //listusers=findViewById(R.id.btncrudusuarios);



        /* USUARIOS*/
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
                Intent intent= new Intent(adminActivity.this, ListProductsActivity.class);
                startActivity(intent);
            }
        });
/* listproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(adminActivity.this, ListProductsActivity.class);
                startActivity(intent);
            }
        });
        editproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(adminActivity.this, edtuserActivity.class);
                startActivity(intent);
            }
        });
        editsucursales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(adminActivity.this, edtuserActivity.class);
                startActivity(intent);
            }
        });*/


      /*  listusers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(adminActivity.this, addproductoActivity.class);
                startActivity(intent);
            }
        });*/



    }
}