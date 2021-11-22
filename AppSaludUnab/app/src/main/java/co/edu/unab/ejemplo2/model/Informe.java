package co.edu.unab.ejemplo2.model;

import java.io.Serializable;

public class Informe implements Serializable {
    int id;
    String edad;
    String peso;
    String altura;
    Float imc;
    Float mBasal;
    int estadoSalud;
    String fecha;
    boolean genero;

    public Informe(){

    }

    public Informe(int id, String edad, String peso, String altura, Float imc, float mBasal, int estadoSalud, String fecha, boolean genero) {
        this.id = id;
        this.edad = edad;
        this.peso = peso;
        this.altura = altura;
        this.imc = imc;
        this.mBasal = mBasal;
        this.estadoSalud = estadoSalud;
        this.fecha = fecha;
        this.genero = genero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public Float getImc() {
        return imc;
    }

    public void setImc(Float imc) {
        this.imc = imc;
    }

    public Float getmBasal() {
        return mBasal;
    }

    public void setmBasal(Float mBasal) {
        this.mBasal = mBasal;
    }

    public int getEstadoSalud() {
        return estadoSalud;
    }

    public void setEstadoSalud(int estadoSalud) {
        this.estadoSalud = estadoSalud;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public boolean getGenero() {
        return genero;
    }

    public void setGenero(boolean genero) {
        this.genero = genero;
    }
}
