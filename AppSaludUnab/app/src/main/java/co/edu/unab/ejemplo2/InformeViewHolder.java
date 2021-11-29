package co.edu.unab.ejemplo2;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class InformeViewHolder extends RecyclerView.ViewHolder {

    private final TextView edad;
    private final TextView peso;
    private final TextView altura;
    private final TextView imc;
    private final TextView mb;
    private final TextView estadoSalud;

    private final TextView consecutivo;
    private final TextView fecha1;
    private final LinearLayout bandera;
    private final ImageButton flechaBoton;
    private final LinearLayout layoutSecundario;
    private final TextView genero;


    public InformeViewHolder(@NonNull View itemView) {
        super(itemView);
        edad = (TextView) itemView.findViewById(R.id.tvEdad1);
        //fecha = (EditText) itemView.findViewById(R.id.etFecha2);
        peso = (TextView) itemView.findViewById(R.id.tvPeso1);
        altura = (TextView) itemView.findViewById(R.id.tvAltura1);
        imc = (TextView) itemView.findViewById(R.id.tvIMC1);
        mb = (TextView) itemView.findViewById(R.id.tvMB1);
        estadoSalud = (TextView) itemView.findViewById(R.id.tvEstado1);
        consecutivo = (TextView) itemView.findViewById(R.id.tvConsecutivo1);
        fecha1 = (TextView) itemView.findViewById(R.id.tvFecha1);
        bandera = (LinearLayout) itemView.findViewById(R.id.lybandera);
        flechaBoton = (ImageButton) itemView.findViewById(R.id.ibtndown_arrow);
        layoutSecundario = (LinearLayout) itemView.findViewById(R.id.lySecundario);
        genero = (TextView) itemView.findViewById(R.id.tvGenero1);


    }

    public TextView getEdad() {
        return edad;
    }

    public TextView getPeso() {
        return peso;
    }

    public TextView getAltura() {
        return altura;
    }

    public TextView getImc() {
        return imc;
    }

    public TextView getMb() {
        return mb;
    }

    public TextView getEstadoSalud() {
        return estadoSalud;
    }

    public TextView getConsecutivo() {
        return consecutivo;
    }

    public TextView getFecha1() {
        return fecha1;
    }

    public LinearLayout getBandera() {
        return bandera;
    }

    public ImageButton getFlechaBoton() {
        return flechaBoton;
    }

    public LinearLayout getLayoutSecundario() {
        return layoutSecundario;
    }

    public TextView getGenero() {
        return genero;
    }
}