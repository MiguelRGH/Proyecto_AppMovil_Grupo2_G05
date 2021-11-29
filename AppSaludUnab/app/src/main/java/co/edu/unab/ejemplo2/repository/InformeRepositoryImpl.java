package co.edu.unab.ejemplo2.repository;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import co.edu.unab.ejemplo2.Callback;
import co.edu.unab.ejemplo2.model.Informe;
import co.edu.unab.ejemplo2.model.Person;

public class InformeRepositoryImpl implements InformeRepository {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    final String COLLECTION = "registros";
    /*
    @Override
    public void create(Informe informe, Callback callback) {
        db.collection(COLLECTION)
                .document(""+informe.getId())
                .set(informe.getMapa()).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {callback.onSuccess(informe);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                callback.onFailure(e);
            }
        });

    }

 */

    @Override
    public void createWithoutID(Informe informe, Callback callback) {
        db.collection(COLLECTION)
                .add(informe.getMapa())
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
/*
    @Override
    public void update(Informe informe, Callback callback) {
        db.collection(COLLECTION)
                .document(""+persona.getId()).update(persona.getMapa())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        callback.onSuccess(persona);
                    }
                });

    }

 */
/*
    @Override
    public void delete(Person persona, Callback callback) {
        db.collection(COLLECTION)
                .document(""+persona.getId()).delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        callback.onSuccess(null);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                callback.onFailure(e);
            }
        });

    }

 */


    @Override
    public void findAll(Callback callback) {
        db.collection(COLLECTION).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            //callback.onSuccess(task.getResult().getDocuments().get(0).getString("nombre"));
                            //callback.onSuccess(task.getResult().getDocuments().get(2).getString("nombre"));
                            //callback.onSuccess(task.getResult().getDocuments().get(0)); funciono
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