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
import com.google.firebase.firestore.model.ObjectValue;

import co.edu.unab.ejemplo2.Callback;
import co.edu.unab.ejemplo2.model.Person;


public class PersonaRepositoryImpl implements PersonaRepository {


    FirebaseFirestore db = FirebaseFirestore.getInstance();
    final String COLLECTION = "personas";

    @Override
    public void create(Person persona, Callback callback) {
        db.collection(COLLECTION)
                .document("" + persona.getId())
                .set(persona.getMapa()).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                callback.onSuccess(persona);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                callback.onFailure(e);
            }
        });

    }

    @Override
    public void createWithoutID(Person persona, Callback callback) {
        db.collection(COLLECTION)
                .add(persona.getMapa())
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
    public void update(Person persona, Callback callback) {
        db.collection(COLLECTION)
                .document("" + persona.getId()).update(persona.getMapa())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        callback.onSuccess(persona);
                    }
                });

    }

    @Override
    public void delete(Person persona, Callback callback) {
        db.collection(COLLECTION)
                .document("" + persona.getId()).delete()
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
        db.collection(COLLECTION).document(id).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            callback.onSuccess(task.getResult().exists());
                            //callback.onSuccess(task.getResult().getData());
                            //callback.onSuccess(task.getResult());
                        } else {
                            callback.onFailure(null);
                        }
                    }
                });

    }

    @Override
    public void findByName(String nombre, Callback callback) {
        db.collection(COLLECTION).whereEqualTo("nombre", nombre).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        callback.onSuccess(task.getResult());
                    }
                });

    }


}
