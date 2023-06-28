package com.example.davicio.crudusuarios;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.davicio.InicioActivity;
import com.example.davicio.R;
import com.example.davicio.adminActivity;
import com.example.davicio.adptadores.ListaSucursalesAdapter;
import com.example.davicio.contexto.DbSQLHelper;
import com.example.davicio.entidades.Sucursales;
import com.example.davicio.sinBarraSuperior;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ListSucursalesActivity extends sinBarraSuperior {

    private DbSQLHelper dbSQLHelper;
    private SQLiteDatabase db;
    private ExecutorService executorService;
    private ArrayList<Sucursales> listaArraySucursales;
    public  RecyclerView listaSucursales;
    ImageButton btnvolver;
    String nombreUsu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_sucursales);
        btnvolver=findViewById(R.id.btnvolver);
        listaSucursales= findViewById(R.id.listaSucursales);
        listaSucursales.setLayoutManager(new LinearLayoutManager(ListSucursalesActivity.this));

        dbSQLHelper = new DbSQLHelper(ListSucursalesActivity.this);
        listaArraySucursales= new ArrayList<>();

        Bundle caja= getIntent().getExtras();
        nombreUsu= caja.getString("nombre");

        //HILO SECUNDARIO PARA LA CARGA DE LA BASE DE DATOS
        executorService = Executors.newFixedThreadPool(1);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                db = dbSQLHelper.getReadableDatabase();
                ListaSucursalesAdapter adapter = new ListaSucursalesAdapter(dbSQLHelper.mostrarSucursales());
                listaSucursales.setAdapter(adapter);
            }
        });
        btnvolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ListSucursalesActivity.this, InicioActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("nombre", nombreUsu);

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


    }




}
