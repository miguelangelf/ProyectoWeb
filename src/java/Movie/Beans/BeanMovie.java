/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Movie.Beans;

import java.io.InputStream;
import java.util.ArrayList;

/**
 *
 * @author miguel
 */
public class BeanMovie {
    public int id;
    public String nombre;
    public int anio;
    public String clasificacion;
    public int duracion;
    public ArrayList <String> genero;
    public String director;
    public ArrayList <String> actores;
    public InputStream image;
    
    public BeanMovie()
    {
        genero=new ArrayList();
        actores=new ArrayList();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public ArrayList<String> getGenero() {
        return genero;
    }

    public void setGenero(ArrayList<String> genero) {
        this.genero = genero;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public ArrayList<String> getActores() {
        return actores;
    }

    public void setActores(ArrayList<String> actores) {
        this.actores = actores;
    }

    public InputStream getImage() {
        return image;
    }

    public void setImage(InputStream image) {
        this.image = image;
    }


    
}
