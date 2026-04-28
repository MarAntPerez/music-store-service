package com.music.store.dto;

public class YearResponse {

	private Integer yearRelease;

	public YearResponse(Integer yearRelease) {
		this.yearRelease = yearRelease;
	}

	public Integer getYearRelease() {
		return yearRelease;
	}

	public void setYearRelease(Integer yearRelease) {
		this.yearRelease = yearRelease;
	}

}
