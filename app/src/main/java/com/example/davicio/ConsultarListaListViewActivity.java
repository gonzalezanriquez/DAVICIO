package com.example.davicio;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class ConsultarListaListViewActivity extends AppCompatActivity {

    ListView listViewPersonas;
    ArrayList<String> listaInformacion;
    ArrayList<Personas> listaPersonas;

    private DbSQLHelper dbSQLHelper;
    private SQLiteDatabase db;
    private ExecutorService executorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_lista_list_view);

        executorService = Executors.newFixedThreadPool(1);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                db = dbSQLHelper.getReadableDatabase();

            }
        });


        listViewPersonas= (ListView) findViewById(R.id.listViewPersonas);

        consultarListaPersonas();

        ArrayAdapter adaptador=new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaInformacion);
        listViewPersonas.setAdapter(adaptador);

        listViewPersonas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                String informacion="id: "+ listaPersonas.get(pos).getId()+"\n";
                informacion+="Nombre: "+ listaPersonas.get(pos).getNombre()+"\n";
                informacion+="Apellido: "+ listaPersonas.get(pos).getApellido()+"\n";
                informacion+="Mail: "+ listaPersonas.get(pos).getMail()+"\n";

                Toast.makeText(getApplicationContext(),informacion,Toast.LENGTH_LONG).show();

                Personas user= listaPersonas.get(pos);

                Intent intent=new Intent(ConsultarListaListViewActivity.this,DetalleUsuarioActivity.class);

                Bundle bundle=new Bundle();
                bundle.putSerializable("usuario",user);

                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

    }

    private void consultarListaPersonas() {
        //SQLiteDatabase db=db.getReadableDatabase();

        Personas personas =null;
        listaPersonas =new ArrayList<Personas>();
        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM usuarios",null);

        while (cursor.moveToNext()){
            personas =new Personas();
            personas.setId(cursor.getInt(0));
            personas.setNombre(cursor.getString(1));
            personas.setApellido(cursor.getString(1));
            personas.setMail(cursor.getString(2));

            listaPersonas.add(personas);
        }
        obtenerLista();
    }

    private void obtenerLista() {
        listaInformacion=new ArrayList<String>();

        for (int i = 0; i< listaPersonas.size(); i++){
            listaInformacion.add(listaPersonas.get(i).getId()+" - "
                    + listaPersonas.get(i).getNombre());
        }

    }

}
