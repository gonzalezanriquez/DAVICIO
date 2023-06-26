package com.example.davicio;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Map;

// In the activityUsuarios class

public class UsuariosAdapter extends RecyclerView.Adapter<UsuarioViewHolder> {
    private List<Map<String, String>> usuariosList;

    public UsuariosAdapter(List<Map<String, String>> usuariosList) {
        this.usuariosList = usuariosList;
    }

    @NonNull
    @Override
    public UsuarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_usuario, parent, false);
        return new UsuarioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioViewHolder holder, int position) {
        Map<String, String> usuario = usuariosList.get(position);
        holder.textViewNombre.setText(usuario.get("nombre"));
        holder.textViewApellido.setText(usuario.get("apellido"));
        holder.textViewMail.setText(usuario.get("mail"));
        holder.textViewContrasenia.setText(usuario.get("contrasenia"));
    }

    @Override
    public int getItemCount() {
        return usuariosList.size();
    }
}
