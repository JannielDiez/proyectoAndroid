package com.example.proyectoandroid.model;

import java.io.Serializable;

public class Usuario implements Serializable {


    private String correoUsuario;
    private String usuario;
    private String contraseña;
    private int imagen;

    public Usuario() {
    }

    public Usuario(String correoUsuario, String usuario,int imagen) {
        this.correoUsuario = correoUsuario;
        this.usuario = usuario;
        this.imagen = imagen;
    }
    public Usuario(String correoUsuario, String usuario,String password) {
        this.correoUsuario = correoUsuario;
        this.usuario = usuario;
        this.contraseña = password;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
}
