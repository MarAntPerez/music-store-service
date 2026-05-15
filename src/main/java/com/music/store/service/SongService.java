package com.music.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.music.store.dto.SongResponseDto;
import com.music.store.entity.SongEntity;
import com.music.store.repository.SongRepository;

@Service
public class SongService {

	@Autowired
	private SongRepository songRepository;

	public List<SongResponseDto> getSongsByAlbumId(int albumId) {
		return songRepository.getSongsByAlbumId(albumId);
	}

	public void save(SongEntity song) {

		int nextTrack = songRepository.getNextTrackNumber(song.getAlbumId());

		song.setTrackNumber(nextTrack);

		songRepository.save(song);
	}

	public boolean deleteSong(Integer songId) {
		return songRepository.delete(songId);
	}

}
