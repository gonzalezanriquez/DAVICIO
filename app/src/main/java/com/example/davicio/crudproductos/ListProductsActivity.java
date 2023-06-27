package com.example.davicio.crudproductos;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.davicio.R;
import com.example.davicio.adptadores.ListaProductosAdapter;
import com.example.davicio.adptadores.ListaUsuariosAdapter;
import com.example.davicio.contexto.DbSQLHelper;
import com.example.davicio.entidades.Productos;
import com.example.davicio.entidades.Usuarios;
import com.example.davicio.sinBarraSuperior;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ListProductsActivity extends sinBarraSuperior {
RecyclerView listaproductos;
private DbSQLHelper dbSQLHelper;
private SQLiteDatabase db;
private ExecutorService executorService;
private ArrayList<Productos> listaArrayProductos;
public  RecyclerView listproductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_productos);
        listproductos= findViewById(R.id.listadeProductos);
        listproductos.setLayoutManager(new LinearLayoutManager(ListProductsActivity.this));

        dbSQLHelper = new DbSQLHelper(ListProductsActivity.this);
        listaArrayProductos= new ArrayList<>();

        //HILO SECUNDARIO PARA LA CARGA DE LA BASE DE DATOS
        executorService = Executors.newFixedThreadPool(1);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                db = dbSQLHelper.getReadableDatabase();
            }
        });

        ListaProductosAdapter adapter = new ListaProductosAdapter(dbSQLHelper.mostrarProductos());
        listproductos.setAdapter(adapter);


    }
}