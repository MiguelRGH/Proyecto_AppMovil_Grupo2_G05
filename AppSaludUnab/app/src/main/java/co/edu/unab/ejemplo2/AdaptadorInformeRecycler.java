package co.edu.unab.ejemplo2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import co.edu.unab.ejemplo2.model.Informe;

public class AdaptadorInformeRecycler extends RecyclerView.Adapter<InformeViewHolder> {
    Context contexto;
    ArrayList<Informe> listaDatos;
    LayoutInflater inflater;
    private int layoutSeleccionado;



    public AdaptadorInformeRecycler(Context contexto,   int layoutSeleccionado,ArrayList<Informe> listaDatos) {
        this.contexto = contexto;
        this.listaDatos = listaDatos;
        this.layoutSeleccionado = layoutSeleccionado;
        inflater = LayoutInflater.from(contexto);

    }

    @NonNull
    @Override
    public InformeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(layoutSeleccionado, null);
        return new InformeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InformeViewHolder holder, int position) {

        String fecha = listaDatos.get(position).getFecha();
        String peso = listaDatos.get(position).getPeso();
        String edad = listaDatos.get(position).getEdad();
        String altura = listaDatos.get(position).getAltura();
        Float imc= listaDatos.get(position).getImc();
        Float basal = listaDatos.get(position).getmBasal();
        Boolean genero = listaDatos.get(position).getGenero();
        Integer estadoSalud= listaDatos.get(position).getEstadoSalud();
        holder.getPeso().setText("Peso: "+ peso + " Kg");
        holder.getEdad().setText("Edad: "+edad+" años");
        holder.getAltura().setText("Altura: "+altura+" mts");
        holder.getMb().setText("M. Basal: "+ basal);
        holder.getGenero().setText(""+((genero == true) ? "Femenino" : "Masculino"));

        holder.getFlechaBoton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(holder.getLayoutSecundario().getVisibility() == View.GONE){
                    holder.getLayoutSecundario().setVisibility(View.VISIBLE);
                }else holder.getLayoutSecundario().setVisibility(View.GONE);


            }
        });


        holder.getEstadoSalud().setText("" + ((estadoSalud == 0) ? "Peso insuficiente" : (estadoSalud == 1) ? "Saludable" : (estadoSalud == 2) ? "Sobrepeso" : "Obesidad"));

        if (estadoSalud == 0)  holder.getBandera().setBackground(this.contexto.getDrawable(R.drawable.stylo_borde_izq_amarillo));
        if (estadoSalud == 1)  holder.getBandera().setBackground(this.contexto.getDrawable(R.drawable.stylo_borde_izq_verde));
        if (estadoSalud == 2)  holder.getBandera().setBackground(this.contexto.getDrawable(R.drawable.stylo_borde_izq_naranja));
        if (estadoSalud == 3)  holder.getBandera().setBackground(this.contexto.getDrawable(R.drawable.stylo_borde_izq_rojo));

        holder.getImc().setText(String.format("Tu IMC: %.2f" , imc ));
        holder.getConsecutivo().setText("Registro No." + (position+1));
        holder.getFecha1().setText(""+fecha);

    }

    @Override
    public int getItemCount() {
        return listaDatos.size();
    }


}
