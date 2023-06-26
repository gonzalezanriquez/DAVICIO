package com.example.davicio;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

public class mapaActivity extends sinBarraSuperior implements OnMapReadyCallback, GoogleMap.OnMapClickListener,GoogleMap.OnMapLongClickListener {

    EditText ingresodireccion;
    Button buscar;
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
                //SupportMapFragment mapFragment= (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

                //mapFragment.getMapAsync(this);


            }
        });

    }
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

    }
    @Override
    public void onMapClick(@NonNull LatLng latLng) {

    }



    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {

    }


}