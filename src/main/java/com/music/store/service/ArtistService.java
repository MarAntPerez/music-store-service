package com.music.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.music.store.entity.ArtistEntity;
import com.music.store.repository.ArtistRepository;

@Service
public class ArtistService {

	@Autowired
	private ArtistRepository artistRepository;

	public boolean createArtist(ArtistEntity artist) {
		int result = artistRepository.save(artist);

		return result > 0;
	}

	public List<ArtistEntity> getAllArtists() {
		return artistRepository.findAll();
	}

	public ArtistEntity getArtistById(Integer id) {
		return artistRepository.findById(id);
	}

	public boolean updateArtist(Integer id, ArtistEntity artist) {
		int result = artistRepository.update(id, artist);
		return result > 0;
	}
	
	public boolean deleteArtist(Integer id) {
		int result = artistRepository.delete(id);
		return result > 0;
	}

}
