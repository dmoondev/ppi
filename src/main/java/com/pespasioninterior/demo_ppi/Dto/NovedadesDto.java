package com.pespasioninterior.demo_ppi.Dto;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;

public class NovedadesDto {
    @NotBlank
    private String titulo;
    @NotBlank
    @Lob
    private String cuerpo;
    @NotBlank
    private String img;

    public NovedadesDto() {
    }

    public NovedadesDto(String titulo, String cuerpo, String img) {
        this.titulo = titulo;
        this.cuerpo = cuerpo;
        this.img = img;
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
