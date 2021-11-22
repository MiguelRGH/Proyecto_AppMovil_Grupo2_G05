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



        /*
        Integer posicionPersona = listaDatos.get(position).getId();


        String altura = listaDatos.get(position).getAltura();

        Float basal = listaDatos.get(position).getmBasal();


        int tamañolista = listaDatos.size();

        holder.getEdad().setText(edad);
//      String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        holder.getFecha().setText(""+fecha);
        holder.getPeso().setText(peso);
        holder.getAltura().setText(altura);
        holder.getImc().setText("" + imc);
        holder.getMb().setText("" + basal);
        holder.getEstadoSalud().setText("" + ((estadoSalud == 0) ? "Peso insuficiente" : (estadoSalud == 1) ? "Normal" : (estadoSalud == 2) ? "Sobrepeso" : "Obesidad"));
        holder.getNumeroRegistros().setText("" + contador);
        */
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

    /*
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final EditText edad;
        private final EditText peso;
        private final EditText altura;
        private final TextView imc;
        private final EditText mb;
        private final TextView estadoSalud;
        private final EditText fecha;
        private final EditText numeroRegistros;
        private final TextView consecutivo;
        private final TextView fecha1;
        private final LinearLayout bandera;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            edad = (EditText) itemView.findViewById(R.id.etEdad2);
            fecha = (EditText) itemView.findViewById(R.id.etFecha2);
            peso = (EditText) itemView.findViewById(R.id.etPeso2);
            altura = (EditText) itemView.findViewById(R.id.etAltura2);
            imc = (TextView) itemView.findViewById(R.id.tvIMC1);
            mb = (EditText) itemView.findViewById(R.id.etMB2);
            estadoSalud = (TextView) itemView.findViewById(R.id.tvEstado1);
            numeroRegistros = (EditText) itemView.findViewById(R.id.etNRegistro2);
            consecutivo = (TextView) itemView.findViewById(R.id.tvConsecutivo1);
            fecha1 = (TextView) itemView.findViewById(R.id.tvFecha1);
            bandera = (LinearLayout) itemView.findViewById(R.id.lybandera);


            }

        public EditText getEdad() {
            return edad;
        }

        public EditText getPeso() {
            return peso;
        }

        public EditText getAltura() {
            return altura;
        }

        public TextView getImc() {
            return imc;
        }

        public EditText getMb() {
            return mb;
        }

        public TextView getEstadoSalud() {
            return estadoSalud;
        }

        public EditText getNumeroRegistros() {return numeroRegistros;
        }

        public TextView getFecha() {
            return fecha;
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
    }
    */
}
