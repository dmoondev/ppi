package com.pespasioninterior.demo_ppi.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotNull;


@Entity
public class Novedades {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotNull
    private String titulo;
    
    @NotNull
    @Lob
    private String cuerpo;
    
    @NotNull
    private String img;
    
    public Novedades(){}
    
    public Novedades(String titulo, String cuerpo, String img){
        this.titulo = titulo;
        this.cuerpo = cuerpo;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }
    
    public String getImg() {
    	return this.img;
    }
    
    public void setImg(String img) {
    	this.img = img;
    }
}
