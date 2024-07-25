package com.pespasioninterior.demo_ppi.Dto;

import jakarta.validation.constraints.NotBlank;

public class DownloadDto {

	@NotBlank
    private String name;
	
	@NotBlank
    private String link;
	
	public DownloadDto() {}

	public DownloadDto(@NotBlank String name, @NotBlank String link) {
		this.name = name;
		this.link = link;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	
}
