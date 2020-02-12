package com.example.proyectoandroid.model;

public class Cancion {


    private int imagenAlbum;
    private String nombreCancion;
    private String nombreArtista;

    public Cancion(int imagenAlbum, String nombreCancion, String nombreArtista) {
        this.imagenAlbum = imagenAlbum;
        this.nombreCancion = nombreCancion;
        this.nombreArtista = nombreArtista;
    }
    public int getImagenAlbum() {
        return imagenAlbum;
    }

    public void setImagenAlbum(int imagenAlbum) {
        this.imagenAlbum = imagenAlbum;
    }

    public String getNombreCancion() {
        return nombreCancion;
    }

    public void setNombreCancion(String nombreCancion) {
        this.nombreCancion = nombreCancion;
    }

    public String getNombreArtista() {
        return nombreArtista;
    }

    public void setNombreArtista(String nombreArtista) {
        this.nombreArtista = nombreArtista;
    }

}
