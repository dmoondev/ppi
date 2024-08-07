package com.pespasioninterior.demo_ppi.Security.Request;

public class RegisterRequest {
    
	private String email;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String country;
    private String img;

    // Constructor
    public RegisterRequest() {}

    public RegisterRequest(String email, String username, String password, String firstname, String lastname, String country,
    						String img) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.country = country;
    }

    // Getters y Setters
    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    
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
