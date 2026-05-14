package com.music.store.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.music.store.dto.AlbumRequestDto;
import com.music.store.dto.AlbumResponseDto;
import com.music.store.dto.YearResponse;
import com.music.store.entity.AlbumEntity;
import com.music.store.service.AlbumService;

@RestController
@RequestMapping("/albums")
public class AlbumController {

	@Autowired
	private AlbumService albumService;

	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> createAlbum(@ModelAttribute AlbumRequestDto album, @RequestParam(required = false) MultipartFile image) throws IOException {
		boolean created = albumService.createAlbum(album, image);

		if (created) {
			return ResponseEntity.ok("Album creado correctamente");
		} else {
			return ResponseEntity.badRequest().body("No fue posible crear el album");
		}
	}

	@GetMapping
	public List<AlbumResponseDto> getAllAlbums() {
		return albumService.getAllAlbums();
	}

	@GetMapping("/{id}")
	public ResponseEntity<AlbumResponseDto > getAlbumById(@PathVariable Integer id) {
		AlbumResponseDto  album = albumService.getAlbumById(id);

		if (album != null) {
			return ResponseEntity.ok(album);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateAlbum(@PathVariable Integer id, @RequestBody AlbumEntity album) {

		boolean updated = albumService.updateAlbum(id, album);

		if (updated) {
			return ResponseEntity.ok("Album actualizado correctamente");
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAlbum(@PathVariable Integer id) {
		boolean deleted = albumService.deleteAlbum(id);

		if (deleted) {
			return ResponseEntity.ok("Album eliminado correctamente");
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/artist/{artistId}")
	public List<AlbumResponseDto> getAlbumsByArtist(@PathVariable Integer artistId) {
		return albumService.getAlbumsByArtist(artistId);
	}

	@GetMapping("/genre/{genreId}")
	public List<AlbumResponseDto> getAlbumsByGenre(@PathVariable Integer genreId) {
		return albumService.getAlbumsByGenre(genreId);
	}

	@GetMapping("/format/{formatId}")
	public List<AlbumResponseDto> getAlbumsByFormat(@PathVariable Integer formatId) {
		return albumService.getAlbumsByFormat(formatId);
	}

	@GetMapping("/year/{year}")
	public List<AlbumResponseDto> getAlbumeByYear(@PathVariable Integer year) {
		return albumService.getAlbumsByYear(year);
	}

	@GetMapping("/search")
	public List<AlbumResponseDto> globalSearch(@RequestParam(required = false) String query) {
		return albumService.globalSearch(query);
	}
	
	@GetMapping("/years")
	public List<YearResponse> getYears(){
		return albumService.getYears();
	}

}
