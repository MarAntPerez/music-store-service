package com.music.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.music.store.entity.GenerEntity;
import com.music.store.repository.GenreRepository;

@Service
public class GenreService {

	@Autowired
	private GenreRepository generRepository;

	public boolean createGenre(GenerEntity genre) {
		int result = generRepository.save(genre);

		return result > 0;
	}

	public List<GenerEntity> getAllGenres() {
		return generRepository.findAll();
	}

	public GenerEntity getGenreById(Integer id) {
		return generRepository.findById(id);
	}

	public boolean updateGenre(Integer id, GenerEntity gener) {
		int result = generRepository.update(id, gener);

		return result > 0;
	}

	public boolean deleteGenre(Integer id) {
		int result = generRepository.delete(id);

		return result > 0;
	}

}
