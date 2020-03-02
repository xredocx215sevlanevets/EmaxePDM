package com.marlonheraclito.emaxepdm.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.marlonheraclito.emaxepdm.AtualizarActivity;
import com.marlonheraclito.emaxepdm.R;
import com.marlonheraclito.emaxepdm.modelo.Aluno;

import java.util.List;

public class AdapterWorld extends RecyclerView.Adapter<AdapterWorld.WordViewHolder> {

    private Context context;
    private List<Aluno> lista;

    public AdapterWorld(Context context, List<Aluno> lista) {
        this.context = context;
        this.lista = lista;
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layouthelper, parent, false);
        return new WordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, final int position) {

        holder.txtNome.setText(lista.get(position).getNome());
        holder.txtNumero.setText(lista.get(position).getNumero());

        holder.txtNome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AtualizarActivity.class);
                intent.putExtra("posicao", position);
                v.getContext().startActivity(intent);
                ((Activity)context).finish();
            }
        });


    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    class WordViewHolder extends RecyclerView.ViewHolder {

        public final TextView txtNome;
        public final TextView txtNumero;
        public final ImageView imageView;


        public WordViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNome = itemView.findViewById(R.id.txtNome2);
            txtNumero = itemView.findViewById(R.id.txtNumero2);
            imageView = itemView.findViewById(R.id.imageView_Id);

        }

    }

}
