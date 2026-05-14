package com.music.store.dto;

public class AlbumRequestDto {

	private String albumName;

	private Integer yearRelease;

	private Integer artistId;

	private Integer genreId;

	private Integer formatId;

	private Float cost;

	private Integer stock;

	public AlbumRequestDto() {
	}

	public AlbumRequestDto(String albumName, Integer yearRelease, Integer artistId, Integer genreId, Integer formatId,
			Float cost, Integer stock) {
		this.albumName = albumName;
		this.yearRelease = yearRelease;
		this.artistId = artistId;
		this.genreId = genreId;
		this.formatId = formatId;
		this.cost = cost;
		this.stock = stock;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public Integer getYearRelease() {
		return yearRelease;
	}

	public void setYearRelease(Integer yearRelease) {
		this.yearRelease = yearRelease;
	}

	public Integer getArtistId() {
		return artistId;
	}

	public void setArtistId(Integer artistId) {
		this.artistId = artistId;
	}

	public Integer getGenreId() {
		return genreId;
	}

	public void setGenreId(Integer genreId) {
		this.genreId = genreId;
	}

	public Integer getFormatId() {
		return formatId;
	}

	public void setFormatId(Integer formatId) {
		this.formatId = formatId;
	}

	public Float getCost() {
		return cost;
	}

	public void setCost(Float cost) {
		this.cost = cost;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

}
