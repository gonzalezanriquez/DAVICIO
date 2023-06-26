package com.example.davicio;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class InicioActivity extends sinBarraSuperior {
ImageButton sucursales;
    TextView nombre;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        nombre= findViewById(R.id.titulohola);
        sucursales=findViewById(R.id.btnsucursales);

sucursales.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent =new Intent(InicioActivity.this,UsuarioViewHolder.class);
    }
});
        Bundle caja= getIntent().getExtras();
        String name= caja.getString("nombre");

        nombre.setText("Hola, "+ name+"!");


    }
}