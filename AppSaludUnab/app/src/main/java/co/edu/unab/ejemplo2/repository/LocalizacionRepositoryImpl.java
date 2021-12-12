package co.edu.unab.ejemplo2.repository;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import co.edu.unab.ejemplo2.Callback;
import co.edu.unab.ejemplo2.model.Localizacion;

public class LocalizacionRepositoryImpl implements LocalizacionRepository {


    FirebaseFirestore db = FirebaseFirestore.getInstance();
    final String COLLECTION = "Localizacion";


    @Override
    public void createWithoutID(Localizacion localizacion, Callback callback) {
        db.collection(COLLECTION)
                .add(localizacion.getMapa())
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        callback.onSuccess(documentReference);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                callback.onFailure(e);
            }
        });

    }

    @Override
    public void findAll(Callback callback) {
        db.collection(COLLECTION).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            callback.onSuccess(task.getResult());
                        } else {
                            callback.onFailure(null);
                        }
                    }
                });

    }

    @Override
    public void findById(String id, Callback callback) {
        db.collection(COLLECTION).whereEqualTo("id", id).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        callback.onSuccess(task.getResult());
                    }
                });

    }
}
