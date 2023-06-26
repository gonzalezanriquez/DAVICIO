package com.example.davicio;


import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class UsuarioViewHolder extends RecyclerView.ViewHolder {
    public TextView textViewNombre;
    public TextView textViewApellido;
    public TextView textViewMail;
    public TextView textViewContrasenia;

    public UsuarioViewHolder(View itemView) {
        super(itemView);
        textViewNombre = itemView.findViewById(R.id.textViewNombre);
        textViewApellido = itemView.findViewById(R.id.textViewApellido);
        textViewMail = itemView.findViewById(R.id.textViewMail);
        textViewContrasenia = itemView.findViewById(R.id.textViewContrasenia);
    }
}
