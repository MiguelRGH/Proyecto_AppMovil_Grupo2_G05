package co.edu.unab.ejemplo2.repository;

import co.edu.unab.ejemplo2.Callback;
import co.edu.unab.ejemplo2.model.Localizacion;

public interface LocalizacionRepository {

    public void createWithoutID(Localizacion localizacion, Callback callback);

    public void findAll(Callback callback);

    public void findById(String id, Callback callback);
}
