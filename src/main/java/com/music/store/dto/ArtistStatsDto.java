package com.music.store.dto;

public class ArtistStatsDto {

	private String artistName;
    private Integer totalAlbums;
    private Integer totalUnits;

    public ArtistStatsDto(String artistName, Integer totalAlbums, Integer totalUnits) {
        this.artistName = artistName;
        this.totalAlbums = totalAlbums;
        this.totalUnits = totalUnits;
	}

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	public Integer getTotalAlbums() {
		return totalAlbums;
	}

	public void setTotalAlbums(Integer totalAlbums) {
		this.totalAlbums = totalAlbums;
	}

	public Integer getTotalUnits() {
		return totalUnits;
	}

	public void setTotalUnits(Integer totalUnits) {
		this.totalUnits = totalUnits;
	}
}
