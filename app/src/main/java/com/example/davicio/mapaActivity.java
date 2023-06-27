package com.example.davicio;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class mapaActivity extends sinBarraSuperior implements OnMapReadyCallback, GoogleMap.OnMapClickListener,GoogleMap.OnMapLongClickListener {

    EditText ingresodireccion;
    Button buscar;
    GoogleMap map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        ingresodireccion=findViewById(R.id.ingresodireccion);
        buscar=findViewById(R.id.btnmapsearch);

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String direccion= ingresodireccion.getText().toString();

                Uri map= Uri.parse("geo:0,0?q="+ Uri.encode(direccion));

                Intent mapa= new Intent(Intent.ACTION_VIEW,map);

                startActivity(mapa);
                SupportMapFragment mapFragment= (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

                mapFragment.getMapAsync(mapaActivity.this);


            }
        });

    }
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        //Instancia de googlemap
        map= googleMap;

        //cargar controladores de mapas
        this.map.setOnMapClickListener(this);
        this.map.setOnMapLongClickListener(this);

        LatLng argentina= new LatLng(-34.6045441,-58.3822102);

        map.addMarker(new MarkerOptions().position(argentina).title("Ciudad de Buenos Aires"));
        map.moveCamera(CameraUpdateFactory.newLatLng(argentina));





    }
    @Override
    public void onMapClick(@NonNull LatLng latLng) {

    }



    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {

    }


}