package co.edu.unab.ejemplo2.repository;

import co.edu.unab.ejemplo2.Callback;
import co.edu.unab.ejemplo2.model.Person;

public interface PersonaRepository {

    public void create(Person persona, Callback callback);

    public void createWithoutID(Person persona, Callback callback);

    public void update(Person persona, Callback callback);

    public void delete(Person persona, Callback callback);

    public void findAll(Callback callback);

    public void findById(String id, Callback callback);

    public void findByName(String nombre, Callback callback);


}
