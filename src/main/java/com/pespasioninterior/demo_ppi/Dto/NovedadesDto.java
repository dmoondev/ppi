package com.pespasioninterior.demo_ppi.Dto;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;

public class NovedadesDto {
    @NotBlank
    private String title;
    @NotBlank
    @Lob
    private String body;
    @NotBlank
    private String img;

    public NovedadesDto() {
    }

    public NovedadesDto(String title, String body, String img) {
        this.title = title;
        this.body = body;
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return title;
    }

    public void setBody(String body) {
        this.body = body;
    }
    
    public String getImg() {
    	return this.img;
    }
    
    public void setImg(String img) {
    	this.img = img;
    }
}
