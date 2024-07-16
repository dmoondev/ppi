package com.pespasioninterior.demo_ppi.Dto;

import jakarta.validation.constraints.NotBlank;

public class UserDto {
	@NotBlank
	private String username;
	@NotBlank
    private String password;
	@NotBlank
    private String firstname;
	@NotBlank
    private String lastname;
	@NotBlank
    private String country;
	@NotBlank
    private String img;

    // Constructor
    public UserDto() {}

    public UserDto(String username, String password, String firstname, String lastname, String country,
    						String img) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.country = country;
    }

    // Getters y Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    public String getImg() {
        return this.img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
