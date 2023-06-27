package com.example.davicio;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;



    public class ListaProductosAdapter extends RecyclerView.Adapter<com.example.davicio.ListaProductosAdapter.ProductostoViewHolder> {

        ArrayList<Productos> listasdeproductos;

        public ListaProductosAdapter(ArrayList<Productos> listasdeproductos){
            this.listasdeproductos=listasdeproductos;
        }

        @NonNull
        @Override
        public com.example.davicio.ListaProductosAdapter.ProductostoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_productos,null,false);
            return  new com.example.davicio.ListaProductosAdapter.ProductostoViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ProductostoViewHolder holder, int position) {
            holder.viewNombre.setText(listasdeproductos.get(position).getNombre());
            holder.viewDescripcion.setText(listasdeproductos.get(position).getDescripcion());
            holder.viewPrecio.setText(listasdeproductos.get(position).getPrecio());

        }


        @Override
        public int getItemCount() {
            return listasdeproductos.size();
        }

        public class ProductostoViewHolder extends RecyclerView.ViewHolder {
            TextView viewNombre, viewDescripcion,viewPrecio;
            public ProductostoViewHolder(@NonNull View itemView) {
                super(itemView);
                viewNombre= itemView.findViewById(R.id.viewNombre);
                viewDescripcion= itemView.findViewById(R.id.viewDescripcion);
                viewPrecio= itemView.findViewById(R.id.viewPrecio);



            }
        }
    }




