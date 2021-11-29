package co.edu.unab.ejemplo2.repository;

import co.edu.unab.ejemplo2.Callback;
import co.edu.unab.ejemplo2.model.Informe;

public interface InformeRepository {

    //public void create(Informe informe, Callback callback);
    public void createWithoutID(Informe informe, Callback callback);

    // public void update(Informe persona, Callback callback);
    //public void delete(Informe persona, Callback callback);
    public void findAll(Callback callback);

    public void findById(String id, Callback callback);
    //public void findByImc(String imc, Callback callback);
}
