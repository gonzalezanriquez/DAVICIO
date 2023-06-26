package com.example.davicio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class listadoUsuariosActivity extends AppCompatActivity {
RecyclerView listausuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_usuarios);
        listausuarios.findViewById(R.id.listausuarios);
        listausuarios.setLayoutManager(new LinearLayoutManager(this));


    }
}