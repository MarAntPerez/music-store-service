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
import org.springframework.web.bind.annotation.RestController;

import com.music.store.entity.ArtistEntity;
import com.music.store.service.ArtistService;

@RestController
@RequestMapping("/artists")
public class ArtistController {

	@Autowired
	private ArtistService artistService;

	@GetMapping
	public List<ArtistEntity> getAllArtists() {
		return artistService.getAllArtists();
	}

	@GetMapping("/{id}")
	public ResponseEntity<ArtistEntity> getArtistById(@PathVariable Integer id) {
		ArtistEntity artist = artistService.getArtistById(id);

		if (artist != null) {
			return ResponseEntity.ok(artist);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateArtist(@PathVariable Integer id, @RequestBody ArtistEntity artist) {
		boolean updated = artistService.updateArtist(id, artist);

		if (updated) {
			return ResponseEntity.ok("Artista actualizado correctamente");
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteArtist(@PathVariable Integer id) {
		boolean deleted = artistService.deleteArtist(id);

		if (deleted) {
			return ResponseEntity.ok("Artista eliminado correctamente");
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<ArtistEntity> saveArtist(@RequestBody ArtistEntity artist) {
		Integer id = artistService.save(artist);

		artist.setId(id);

		return ResponseEntity.ok(artist);
	}

}
