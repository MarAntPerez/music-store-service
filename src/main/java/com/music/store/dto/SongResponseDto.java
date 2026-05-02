package com.music.store.dto;

public class SongResponseDto {

	private int id;

	private String songName;

	private String duration;

	private int trackNumber;

	public SongResponseDto() {

	}

	public SongResponseDto(int id, String songName, String duration, int trackNumber) {
		this.id = id;
		this.songName = songName;
		this.duration = duration;
		this.trackNumber = trackNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSongName() {
		return songName;
	}

	public void setSongName(String songName) {
		this.songName = songName;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public int getTrackNumber() {
		return trackNumber;
	}

	public void setTrackNumber(int trackNumber) {
		this.trackNumber = trackNumber;
	}

}
