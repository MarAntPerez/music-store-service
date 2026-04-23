package com.music.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.music.store.dto.AlbumResponseDto;
import com.music.store.entity.AlbumEntity;
import com.music.store.service.AlbumService;

@RestController
@RequestMapping("/albums")
public class AlbumController {

	@Autowired
	private AlbumService albumService;

	@PostMapping
	public ResponseEntity<String> createAlbum(@RequestBody AlbumEntity album) {
		boolean created = albumService.createAlbum(album);

		if (created) {
			return ResponseEntity.ok("Album creado correctamente");
		} else {
			return ResponseEntity.badRequest().body("No fue posible crear el album");
		}
	}

	@GetMapping
	public List<AlbumEntity> getAllAlbums() {
		return albumService.getAllAlbums();
	}

	@GetMapping("/{id}")
	public ResponseEntity<AlbumEntity> getAlbumById(@PathVariable Integer id) {
		AlbumEntity album = albumService.getAlbumById(id);

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
	public List<AlbumEntity> getAlbumsByArtist(@PathVariable Integer artistId) {
		return albumService.getAlbumsByArtist(artistId);
	}

	@GetMapping("/genre/{genreId}")
	public List<AlbumEntity> getAlbumsByGenre(@PathVariable Integer genreId) {
		return albumService.getAlbumsByGenre(genreId);
	}

	@GetMapping("/format/{formatId}")
	public List<AlbumEntity> getAlbumsByFormat(@PathVariable Integer formatId) {
		return albumService.getAlbumsByFormat(formatId);
	}

	@GetMapping("/year/{year}")
	public List<AlbumEntity> getAlbumeByYear(@PathVariable Integer year) {
		return albumService.getAlbumsByYear(year);
	}

	@GetMapping("/search")
	public List<AlbumResponseDto> globalSearch(@RequestParam(required = false) String query) {
		return albumService.globalSearch(query);
	}

}
