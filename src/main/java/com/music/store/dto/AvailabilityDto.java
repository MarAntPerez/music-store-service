package com.music.store.dto;

public class AvailabilityDto {

	private String albumName;
	private String artistName;
	private Integer amount;
	private Double cost;

	public AvailabilityDto(String albumName, String artistName, Integer amount, Double cost) {
		this.albumName = albumName;
		this.artistName = artistName;
		this.amount = amount;
		this.cost = cost;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

}
