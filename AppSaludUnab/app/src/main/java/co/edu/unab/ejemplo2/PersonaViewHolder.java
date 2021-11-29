package co.edu.unab.ejemplo2;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import de.hdodenhof.circleimageview.CircleImageView;

public class PersonaViewHolder extends RecyclerView.ViewHolder {

    private final TextView nameViewHolder;
    private final TextView lastNameViewHolder;
    private final TextView emailViewHolder;
    private final CircleImageView userImageViewHolder;
    private final ImageButton buttonRegistros;

    public PersonaViewHolder(@NonNull View itemView) {
        super(itemView);
        nameViewHolder = (TextView) itemView.findViewById(R.id.tvNombre);
        lastNameViewHolder = (TextView) itemView.findViewById(R.id.tvApellido);
        emailViewHolder = (TextView) itemView.findViewById(R.id.tvEmail);
        userImageViewHolder = (CircleImageView) itemView.findViewById(R.id.iVImagenInicio);
        buttonRegistros = (ImageButton) itemView.findViewById(R.id.btnRegisterFinal);

    }

    public TextView getNameViewHolder() {
        return nameViewHolder;
    }

    public TextView getLastNameViewHolder() {
        return lastNameViewHolder;
    }

    public TextView getEmailViewHolder() {
        return emailViewHolder;
    }

    public CircleImageView getUserImageViewHolder() {
        return userImageViewHolder;
    }

    public ImageButton getButtonRegistros() {
        return buttonRegistros;
    }
}
