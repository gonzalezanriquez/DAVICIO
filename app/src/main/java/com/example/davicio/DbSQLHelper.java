    package com.example.davicio;

    import android.content.ContentValues;
    import android.content.Context;
    import android.database.Cursor;
    import android.database.sqlite.SQLiteDatabase;
    import android.database.sqlite.SQLiteOpenHelper;
    import android.view.View;
    import android.widget.Toast;

    import androidx.annotation.Nullable;

    import java.util.ArrayList;
    import java.util.LinkedHashMap;
    import java.util.Map;

    public class DbSQLHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "davicio";
        private static final int DATABASE_VERSION = 1;

        //USUARIOS
        private static final String TABLE_USUARIO = "usuario";
        private static final String COLUMN_USUARIO_ID = "id";
        private static final String COLUMN_USUARIO_NOMBRE = "nombre";
        private static final String COLUMN_USUARIO_APELLIDO = "apellido";
        private static final String COLUMN_USUARIO_MAIL = "mail";
        private static final String COLUMN_USUARIO_CONTRASENIA = "contrasenia";



        // PRODUCTOS
        private static final String TABLE_PRODUCTO = "producto";
        private static final String COLUMN_PRODUCTO_ID = "id";
        private static final String COLUMN_PRODUCTO_NOMBRE = "nombre";
        private static final String COLUMN_PRODUCTO_DESCRIPCION = "descripcion";
        private static final String COLUMN_PRODUCTO_PRECIO = "precio";
        private static final String COLUMN_PRODUCTO_USUARIO_ID = "usuario_id";




        public DbSQLHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // Crea la tabla "Usuario"
            String createUsuarioTableQuery = "CREATE TABLE " + TABLE_USUARIO + "("
                    + COLUMN_USUARIO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_USUARIO_NOMBRE + " TEXT,"
                    + COLUMN_USUARIO_APELLIDO + " TEXT,"
                    + COLUMN_USUARIO_MAIL + " TEXT,"
                    + COLUMN_USUARIO_CONTRASENIA + " TEXT"
                    + ")";

            db.execSQL(createUsuarioTableQuery);


            String createProductosTableQuery = "CREATE TABLE " + TABLE_PRODUCTO + "("
                    + COLUMN_PRODUCTO_ID + "  INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_PRODUCTO_NOMBRE + " TEXT,"
                    + COLUMN_PRODUCTO_DESCRIPCION + " TEXT,"
                    + COLUMN_PRODUCTO_PRECIO + " REAL,"
                    + COLUMN_PRODUCTO_USUARIO_ID + " INTEGER,"
                    + "FOREIGN KEY (" + COLUMN_PRODUCTO_USUARIO_ID + ") REFERENCES " + TABLE_USUARIO + "(" + COLUMN_USUARIO_ID + ")"
                    + ")";
            db.execSQL(createProductosTableQuery);

            String queryPrecargados1 = precargados("Admin","admin","mailadmin","1234");
            String queryPrecargados2 = precargados("Usuario1","Usuario1","mail1","1234");
            String queryPrecargados3 = precargados("Usuario2","Usuario2","mail2","1234");
            db.execSQL(queryPrecargados1);
            db.execSQL(queryPrecargados2);
            db.execSQL(queryPrecargados3);
            String queryProdPrecargados1=productosprecargados("NASTY FOREACH","Tus iteraciones mas complejas y duras a tu disposición","1500");
            String queryProdPrecargados2=productosprecargados("NASTY JAVA","Te gusta el masoquismo? Aca te damos tu opcion para que te diviertas. Experiencia pura y completa de enriedos","1500");
            String queryProdPrecargados3=productosprecargados("NASTY ANDROID","Pensabas que Java era complejo? Aca te damos java CON ANDROID. Revienta tus sentidos","1500");
            db.execSQL(queryProdPrecargados1);
            db.execSQL(queryProdPrecargados2);
            db.execSQL(queryProdPrecargados3);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }

        /*METODO AGEGAR USUARIOS*/


        public Boolean insertUsuario(String nombre, String apellido, String mail, String contrasenia) {

            if(usuarioRegistrado(mail)){
                return false;
            }else{
                SQLiteDatabase db = getWritableDatabase();
                ContentValues registro = new ContentValues();
                registro.put(COLUMN_USUARIO_NOMBRE, nombre);
                registro.put(COLUMN_USUARIO_APELLIDO, apellido);
                registro.put(COLUMN_USUARIO_MAIL, mail);
                registro.put(COLUMN_USUARIO_CONTRASENIA, contrasenia);
                db.insert(TABLE_USUARIO, null, registro);

                db.close();
                return true;
            }
        }

        public Boolean insertProductos(String nombre, String descripcion, String precio) {

            if(productoregistrado(nombre)){
                return false;
            }else{
                SQLiteDatabase db = getWritableDatabase();
                ContentValues registro = new ContentValues();
                registro.put(COLUMN_PRODUCTO_NOMBRE, nombre);
                registro.put(COLUMN_PRODUCTO_DESCRIPCION, descripcion);
                registro.put(COLUMN_PRODUCTO_PRECIO, precio);

                db.insert(TABLE_PRODUCTO, null, registro);

                db.close();
                return true;
            }
        }



        public String precargados(String nombre, String apellido, String mail, String contrasenia) {
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT OR IGNORE INTO usuario");
            sb.append("(");
            sb.append("nombre");
            sb.append(" , ");
            sb.append("apellido");
            sb.append(" , ");
            sb.append("mail");
            sb.append(" , ");
            sb.append("contrasenia");
            sb.append(")");
            sb.append(" VALUES ");
            sb.append("( ");
            sb.append(" '");
            sb.append(nombre);
            sb.append("' ");
            sb.append(" , ");
            sb.append(" '");
            sb.append(apellido);
            sb.append("' ");
            sb.append(" , ");
            sb.append(" '");
            sb.append(mail);
            sb.append("' ");
            sb.append(" , ");
            sb.append(" '");
            sb.append(contrasenia);
            sb.append("' ");
            sb.append(")");
            return sb.toString();
    }

        public String productosprecargados(String nombre, String descripcion, String precio) {
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT OR IGNORE INTO producto");
            sb.append("(");
            sb.append("nombre");
            sb.append(" , ");
            sb.append("descripcion");
            sb.append(" , ");
            sb.append("precio");
            sb.append(")");
            sb.append(" VALUES ");
            sb.append("( ");
            sb.append(" '");
            sb.append(nombre);
            sb.append("' ");
            sb.append(" , ");
            sb.append(" '");
            sb.append(descripcion);
            sb.append("' ");
            sb.append(" , ");
            sb.append(" '");
            sb.append(precio);
            sb.append("' ");
            sb.append(")");
            return sb.toString();
        }

//METODO PARA LISTADO DE USUARIOS
        public ArrayList<Usuarios> mostrarUsuarios(){
            SQLiteDatabase db = getReadableDatabase();
            ArrayList<Usuarios>listaUsuarios = new ArrayList<>();
            Usuarios usu=null;
            Cursor cursor = db.rawQuery("SELECT * FROM usuario" , null);

            if(cursor.moveToFirst()){
                do {
                    usu= new Usuarios();
                    usu.setId(cursor.getInt(0));
                    usu.setNombre(cursor.getString(1));
                    usu.setApellido(cursor.getString(2));
                    usu.setMail(cursor.getString(3));
                    usu.setContraseña(cursor.getString(4));
                    listaUsuarios.add(usu);
                }while (cursor.moveToNext());
            }
                cursor.close();
                return listaUsuarios;
        }


        //METODO PARA LISTADO DE PRODUCTOS
        public ArrayList<Productos> mostrarProductos(){
            SQLiteDatabase db = getReadableDatabase();
            ArrayList<Productos>listaProductos = new ArrayList<>();
            Productos prod=null;
            Cursor cursor = db.rawQuery("SELECT * FROM producto" , null);

            if(cursor.moveToFirst()){
                do {
                    prod= new Productos();
                    prod.setId(cursor.getInt(0));
                    prod.setNombre(cursor.getString(1));
                    prod.setDescripcion(cursor.getString(2));
                    prod.setNombre(cursor.getString(3));

                    listaProductos.add(prod);
                }while (cursor.moveToNext());
            }
            cursor.close();
            return listaProductos;
        }

        public Boolean usuarioRegistrado(String mail){
            SQLiteDatabase db = getReadableDatabase();

            Cursor cursor = db.rawQuery("SELECT * FROM usuario WHERE mail = '" + mail + "' " , null);

            if(cursor.getCount()>0){
                return true;
            }else{
                return false;
            }

        }



        public Boolean productoregistrado(String nombre){
            SQLiteDatabase db = getReadableDatabase();

            Cursor cursor = db.rawQuery("SELECT * FROM producto WHERE nombre = '" + nombre + "' " , null);

            if(cursor.getCount()>0){
                return true;
            }else{
                return false;
            }

        }


        public Map<String,String> consulta(int id){
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM usuario WHERE id = '" + id + "' " , null);
            if(cursor.moveToFirst()){
                Map<String, String> consulta = new LinkedHashMap<String, String>();

                consulta.put("nombre", cursor.getString(1));
                consulta.put("apellido",  cursor.getString(2));
                consulta.put("mail",  cursor.getString(3));
                consulta.put("contrasenia",  cursor.getString(4));
                return consulta;
            }else{
                return null;
            }

        }


        public Map<String,String> consultaproductos(int id){
            SQLiteDatabase db = getReadableDatabase();

            Cursor cursor = db.rawQuery("SELECT * FROM producto WHERE id = '" + id + "' " , null);            if(cursor.moveToFirst()){
                Map<String, String> consulta = new LinkedHashMap<String, String>();

                consulta.put("nombre", cursor.getString(1));
                consulta.put("descripcion",  cursor.getString(2));
                consulta.put("precio",  cursor.getString(3));

                return consulta;
            }else{
                return null;
            }

        }

        public String queryEditarUsuario(int id, String nombre, String apellido, String mail, String contrasenia){
            StringBuilder sb = new StringBuilder();
            sb.append("UPDATE usuario SET");
            sb.append(" nombre = ");
            sb.append(" '");
            sb.append(nombre);
            sb.append("' ");
            sb.append(" , ");
            sb.append("apellido = ");
            sb.append(" '");
            sb.append(apellido);
            sb.append("' ");
            sb.append(" , ");
            sb.append("mail = ");
            sb.append(" '");
            sb.append(mail);
            sb.append("' ");
            sb.append(" , ");
            sb.append("contrasenia = ");
            sb.append(" '");
            sb.append(contrasenia);
            sb.append("' ");
            sb.append("WHERE id = ");
            sb.append(id);
            sb.append(";");
            return sb.toString();
        }


        public String queryEditarProducto(int id, String nombre, String descripcion, String precio){
            StringBuilder sb = new StringBuilder();
            sb.append("UPDATE producto SET");
            sb.append(" nombre = ");
            sb.append(" '");
            sb.append(nombre);
            sb.append("' ");
            sb.append(" , ");
            sb.append("descripcion = ");
            sb.append(" '");
            sb.append(descripcion);
            sb.append("' ");
            sb.append(" , ");
            sb.append("precio = ");
            sb.append(" '");
            sb.append(precio);
            sb.append("' ");
            sb.append("WHERE id = ");
            sb.append(id);
            sb.append(";");
            return sb.toString();
        }


        public String queryEliminarUsuario(int id){
            StringBuilder sb = new StringBuilder();
            sb.append("DELETE FROM usuario ");
             sb.append("WHERE id = ");
            sb.append(id);
            sb.append(";");
            return sb.toString();
        }


        public String queryEliminarProducto(int id){
            StringBuilder sb = new StringBuilder();
            sb.append("DELETE FROM producto ");
            sb.append("WHERE id = ");
            sb.append(id);
            sb.append(";");
            return sb.toString();
        }

    }
