package com.music.store.dto;

public class AlbumResponseDto {

	private Integer id;
	private String albumName;
	private String artistName;
	private String genreName;
	private String formatType;
	private Integer yearRelease;
	private String imageUrl;

	public AlbumResponseDto(Integer id, String albumName, String artistName, String genreName, String formatType,
			Integer yearRelease, String imageUrl) {
		this.id = id;
		this.albumName = albumName;
		this.artistName = artistName;
		this.genreName = genreName;
		this.formatType = formatType;
		this.yearRelease = yearRelease;
		this.imageUrl = imageUrl;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public void setArtistName(String atistName) {
		this.artistName = atistName;
	}

	public String getGenreName() {
		return genreName;
	}

	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}

	public String getFormatType() {
		return formatType;
	}

	public void setFormatType(String formatType) {
		this.formatType = formatType;
	}

	public Integer getYearRelease() {
		return yearRelease;
	}

	public void setYearRelease(Integer yearRelease) {
		this.yearRelease = yearRelease;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
