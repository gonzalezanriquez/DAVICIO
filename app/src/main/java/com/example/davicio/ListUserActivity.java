package com.example.davicio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ListUserActivity extends sinBarraSuperior {
RecyclerView listausuarios;
private DbSQLHelper dbSQLHelper;
private SQLiteDatabase db;
private ExecutorService executorService;
private ArrayList<Usuarios> listaArrayUsuarios;
public  RecyclerView listuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_usuarios);
        listuser= findViewById(R.id.listaUsuarios);
        listuser.setLayoutManager(new LinearLayoutManager(ListUserActivity.this));

        dbSQLHelper = new DbSQLHelper(ListUserActivity.this);
        listaArrayUsuarios= new ArrayList<>();

        //HILO SECUNDARIO PARA LA CARGA DE LA BASE DE DATOS
        executorService = Executors.newFixedThreadPool(1);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                db = dbSQLHelper.getReadableDatabase();
            }
        });

        ListaUsuariosAdapter adapter = new ListaUsuariosAdapter(dbSQLHelper.mostrarUsuarios());
        listuser.setAdapter(adapter);


    }
}