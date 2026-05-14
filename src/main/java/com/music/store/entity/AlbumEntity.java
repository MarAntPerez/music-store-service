package com.music.store.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "albums")
public class AlbumEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "artist_id", nullable = false)
	private Integer artistId;

	@Column(name = "genre_id", nullable = false)
	private Integer genreId;

	@Column(name = "format_id", nullable = false)
	private Integer formatId;

	@Column(name = "album_name", nullable = false)
	private String albumName;

	@Column(name = "year_release", nullable = false)
	private Integer yearRelease;

	@Column(name = "image_url")
	private String imageUrl;

	public AlbumEntity() {

	}

	public AlbumEntity(Integer artistId, Integer genreId, Integer formatId, String albumName, Integer yearRelease,
			String imageUrl) {
		this.artistId = artistId;
		this.genreId = genreId;
		this.formatId = formatId;
		this.albumName = albumName;
		this.yearRelease = yearRelease;
		this.imageUrl = imageUrl;
	}

	public AlbumEntity(String albumName, Integer yearRelease) {
		super();
		this.albumName = albumName;
		this.yearRelease = yearRelease;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
