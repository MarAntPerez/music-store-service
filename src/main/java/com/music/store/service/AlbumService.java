package com.music.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.music.store.dto.AlbumResponseDto;
import com.music.store.dto.YearResponse;
import com.music.store.entity.AlbumEntity;
import com.music.store.repository.AlbumRepository;

@Service
public class AlbumService {

	@Autowired
	private AlbumRepository albumRepository;

	public boolean createAlbum(AlbumEntity album) {

		int result = albumRepository.save(album);

		return result > 0;
	}

	public List<AlbumResponseDto> getAllAlbums() {
		return albumRepository.findAll();
	}

	public AlbumResponseDto  getAlbumById(Integer id) {
		return albumRepository.findById(id);
	}

	public boolean updateAlbum(Integer id, AlbumEntity album) {
		int result = albumRepository.update(id, album);

		return result > 0;
	}

	public boolean deleteAlbum(Integer id) {
		int result = albumRepository.delete(id);

		return result > 0;
	}

	public List<AlbumResponseDto> getAlbumsByArtist(Integer artistId) {
		return albumRepository.findByArtist(artistId);
	}

	public List<AlbumResponseDto> getAlbumsByGenre(Integer genreId) {
		return albumRepository.findByGenre(genreId);
	}

	public List<AlbumResponseDto> getAlbumsByFormat(Integer formatId) {
		return albumRepository.findByFormat(formatId);
	}

	public List<AlbumResponseDto> getAlbumsByYear(Integer year) {
		return albumRepository.findByYear(year);
	}

	public List<AlbumResponseDto> globalSearch(String searchText) {
		if (searchText == null || searchText.isEmpty()) {
			return List.of();
		}

		return albumRepository.globalSearch(searchText);
	}

	public List<YearResponse> getYears() {
		return albumRepository.getYears();
	}

}
