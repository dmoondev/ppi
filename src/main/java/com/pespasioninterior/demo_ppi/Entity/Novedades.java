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
    private String title;
    
    @NotNull
    @Lob
    private String body;
    
    @NotNull
    private String img;
    
    public Novedades(){}
    
    public Novedades(String title, String body, String img){
        this.title = title;
        this.body = body;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String cuerpo) {
        this.body = cuerpo;
    }
    
    public String getImg() {
    	return this.img;
    }
    
    public void setImg(String img) {
    	this.img = img;
    }
}
