package com.example.davicio;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class adminActivity extends sinBarraSuperior {

    Button adduser,edituser,deleteuser,listusers,addproduct,editproduct,deleteproduct,listproduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crudusuarios);
        /*adduser=findViewById(R.id.btnaddusuarios);
        edituser=findViewById(R.id.btnedituser);
        deleteuser=findViewById(R.id.btndeleteuser);
        listusers=findViewById(R.id.btnuserlist);
        addproduct=findViewById(R.id.btnaddproducto);
        editproduct=findViewById(R.id.btneditporduct);
        deleteproduct=findViewById(R.id.btndeleteproduct);
        listproduct=findViewById(R.id.btnproductlist);
*/
        /* USUARIOS
        adduser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(adminActivity.this, addusuarioActivity.class);
                startActivity(intent);
            }
        });
        edituser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(adminActivity.this, edtuserActivity.class);
                startActivity(intent);
            }
        });
        deleteuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(adminActivity.this, edtuserActivity.class);
                startActivity(intent);
            }
        });
        listusers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(adminActivity.this, mapaActivity.class);
                startActivity(intent);
            }
        });

        addproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(adminActivity.this, addproductoActivity.class);
                startActivity(intent);
            }
        });
        editproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(adminActivity.this, editproductActivity.class);
                startActivity(intent);
            }
        });
        deleteproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(adminActivity.this, editproductActivity.class);
                startActivity(intent);
            }
        });
        listproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(adminActivity.this, modificarproductoActivity.class);
                startActivity(intent);
            }
        });
*/

    }
}