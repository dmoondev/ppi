package com.pespasioninterior.demo_ppi.Dto;

public class ContactDto {
	
	private String name;
	private String numberPhone;
    private String emailFrom;
    private String emailTo;
    private String emailSubject;
    private String emailText;

    public ContactDto(String name, String numberPhone, String emailFrom, String emailTo, String emailSubject, String emailText){
        this.name = name;
        this.numberPhone = numberPhone;
        this.emailFrom = emailFrom;
        this.emailTo = emailTo;
        this.emailSubject = emailSubject;
        this.emailText = emailText;
    }

	public String getNumberPhone() {
		return numberPhone;
	}

	public void setNumberPhone(String numberPhone) {
		this.numberPhone = numberPhone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailFrom() {
		return emailFrom;
	}

	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}

	public String getEmailTo() {
		return emailTo;
	}

	public void setEmailTo(String emailTo) {
		this.emailTo = emailTo;
	}

	public String getEmailSubject() {
		return emailSubject;
	}

	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

	public String getEmailText() {
		return emailText;
	}

	public void setEmailText(String emailText) {
		this.emailText = emailText;
	}
    
    public String toString() {
    	return "Nombre: " + this.name + " Correo desde: " + this.emailFrom +
    			" Correo para: " + this.emailTo + " Numero de telefono: " + this.numberPhone +
    			" Asunto: " + this.emailSubject + " Mensaje: " + this.emailText;
    }
}
