
package com.example.davicio;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;
        import android.database.sqlite.SQLiteDatabase;
        import android.os.Bundle;
        import java.util.ArrayList;
        import java.util.concurrent.ExecutorService;
        import java.util.concurrent.Executors;

public class ListProductsActivity extends sinBarraSuperior {
    private DbSQLHelper dbSQLHelper;
    private SQLiteDatabase db;
    private ExecutorService executorService;
    private ArrayList<Productos> listaArrayProductos;
    public  RecyclerView listproducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_productos);
        listproducts= findViewById(R.id.listadeProductos);
        listproducts.setLayoutManager(new LinearLayoutManager(ListProductsActivity.this));

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

        ListaUsuariosAdapter adapter = new ListaUsuariosAdapter(dbSQLHelper.mostrarUsuarios());
        listproducts.setAdapter(adapter);


    }
}