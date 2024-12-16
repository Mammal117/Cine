package com.raven.model;

import javax.swing.Icon;

public class Model_Card {

    private Icon icon;
    private String title;
    private String description;
    private int idPelicula; // NUEVO: ID de la película

    public Model_Card(Icon icon, String title, String description, int idPelicula) {
        this.icon = icon;
        this.title = title;
        this.description = description;
        this.idPelicula = idPelicula; // Almacena la ID de la película
    }

    public Model_Card(Icon icon, int idPelicula) {
        this.icon = icon;
        this.idPelicula = idPelicula;
    }

    public Model_Card() {
    }

    // Getters y Setters para idPelicula
    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
