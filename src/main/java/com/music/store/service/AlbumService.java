package com.music.store.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.music.store.dto.AlbumRequestDto;
import com.music.store.dto.AlbumResponseDto;
import com.music.store.dto.YearResponse;
import com.music.store.entity.AlbumEntity;
import com.music.store.entity.InventoryEntity;
import com.music.store.repository.AlbumRepository;
import com.music.store.repository.InventoryRepository;

@Service
public class AlbumService {

	@Autowired
	private AlbumRepository albumRepository;

	@Autowired
	private InventoryRepository inventoryRepository;

	public boolean createAlbum(AlbumRequestDto album, MultipartFile image) throws IOException {

		try {

			String imageName;

			String cleanName = album.getAlbumName().toLowerCase().trim().replaceAll("\\s+", "_");

			imageName = cleanName + ".jpg";

			if (image != null && !image.isEmpty()) {

				String path = "src/main/resources/static/images/";

				Path imagePath = Paths.get(path + imageName);

				Files.copy(image.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);
			}

			AlbumEntity entity = dtoToEntity(album);

			entity.setImageUrl(imageName);

			Integer albumId = albumRepository.save(entity);

			InventoryEntity inventory = new InventoryEntity();

			inventory.setAlbumId(albumId);
			inventory.setAmount(album.getStock());
			inventory.setCost(album.getCost());

			inventoryRepository.save(inventory);

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}

	private AlbumEntity dtoToEntity(AlbumRequestDto albumDto) {
		AlbumEntity album = new AlbumEntity();

		album.setAlbumName(albumDto.getAlbumName());

		album.setYearRelease(albumDto.getYearRelease());

		album.setArtistId(albumDto.getArtistId());

		album.setGenreId(albumDto.getGenreId());

		album.setFormatId(albumDto.getFormatId());

		return album;
	}

	public List<AlbumResponseDto> getAllAlbums() {
		return albumRepository.findAll();
	}

	public AlbumResponseDto getAlbumById(Integer id) {
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
	
	public boolean deleteAnio(Integer year) {
		boolean result = albumRepository.deleteAnio(year);
		
		return result;
	}

}
