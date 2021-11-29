package co.edu.unab.ejemplo2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import co.edu.unab.ejemplo2.model.DatosPerson;
import co.edu.unab.ejemplo2.model.Informe;
import co.edu.unab.ejemplo2.model.Person;

public class AdaptadorPersonaRecycler extends RecyclerView.Adapter<PersonaViewHolder> {
    Context contexto;
    ArrayList<DatosPerson> listaDatos;
    LayoutInflater inflater;
    private int layoutSeleccionado;

    public AdaptadorPersonaRecycler(Context contexto, int layoutSeleccionado, ArrayList<DatosPerson> listaDatos) {
        this.contexto = contexto;
        this.listaDatos = listaDatos;
        this.layoutSeleccionado = layoutSeleccionado;
        inflater = LayoutInflater.from(contexto);
    }

    @NonNull
    @Override
    public PersonaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(layoutSeleccionado, null);
        return new PersonaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonaViewHolder holder, int position) {
        String name = listaDatos.get(position).getName();
        String lastName = listaDatos.get(position).getLastName();
        String email = listaDatos.get(position).getEmail();
        //int cantidadRegistros = listaDatos.get(position).getCantidadRegistros();


        holder.getNameViewHolder().setText(name);
        holder.getLastNameViewHolder().setText(" " + lastName);
        holder.getEmailViewHolder().setText(email);
        //holder.getButtonRegistros().setText(""+cantidadRegistros);
        switch (position) {
            case 0:
                holder.getUserImageViewHolder().setImageResource(R.drawable.user_rosado);
                break;
            case 1:
                holder.getUserImageViewHolder().setImageResource(R.drawable.user_verce);
                break;
            case 2:
                holder.getUserImageViewHolder().setImageResource(R.drawable.user_azul_claro);
                break;
            case 3:
                holder.getUserImageViewHolder().setImageResource(R.drawable.user_naranja);
                break;
            case 4:
                holder.getUserImageViewHolder().setImageResource(R.drawable.user_gris);
                break;
            case 5:
                holder.getUserImageViewHolder().setImageResource(R.drawable.user_grisclaro);
                break;
            case 6:
                holder.getUserImageViewHolder().setImageResource(R.drawable.user_morado);
                break;
            case 7:
                holder.getUserImageViewHolder().setImageResource(R.drawable.user_azulverde);
                break;
            case 8:
                holder.getUserImageViewHolder().setImageResource(R.drawable.user_naranjaclaro);
                break;
            case 9:
                holder.getUserImageViewHolder().setImageResource(R.drawable.user_purple);
                break;
            case 10:
                holder.getUserImageViewHolder().setImageResource(R.drawable.user_verdeclaro);
                break;
            case 11:
                holder.getUserImageViewHolder().setImageResource(R.drawable.user_azulverde);
                break;
            case 12:
                holder.getUserImageViewHolder().setImageResource(R.drawable.user_rosado);
                break;
            case 13:
                holder.getUserImageViewHolder().setImageResource(R.drawable.user_verce);
                break;
            case 14:
                holder.getUserImageViewHolder().setImageResource(R.drawable.user_azul_claro);
                break;
            case 15:
                holder.getUserImageViewHolder().setImageResource(R.drawable.user_azulverde);
                break;

        }
        holder.getButtonRegistros().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(contexto, InformeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("email", email);
                intent.putExtras(bundle);
                contexto.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return listaDatos.size();
    }

}
