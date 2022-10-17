package com.example.a04ejinmobilaria.Modelos;

import java.io.Serializable;

public class Piso implements Serializable {
    private String direccion;
    private int numero;
    private String ciudad;
    private String provincia;
    private String cp;
    private float volaracion;

    public Piso(String direccion, int numero, String ciudad, String provincia, String cp, float volaracion) {
        this.direccion = direccion;
        this.numero = numero;
        this.ciudad = ciudad;
        this.provincia = provincia;
        this.cp = cp;
        this.volaracion = volaracion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public float getVolaracion() {
        return volaracion;
    }

    public void setVolaracion(float volaracion) {
        this.volaracion = volaracion;
    }
}
