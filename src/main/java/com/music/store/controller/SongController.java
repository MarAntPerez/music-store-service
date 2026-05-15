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

import com.music.store.dto.SongResponseDto;
import com.music.store.entity.SongEntity;
import com.music.store.service.SongService;

@RestController
@RequestMapping("/songs")
public class SongController {

	@Autowired
	private SongService songService;

	@GetMapping("/album/{albumId}")
	public List<SongResponseDto> getSongsByAlbum(@PathVariable int albumId) {
		return songService.getSongsByAlbumId(albumId);
	}

	@PostMapping
	public ResponseEntity<?> saveSong(@RequestBody SongEntity song) {

		songService.save(song);

		return ResponseEntity.ok("Song saved");
	}

	@DeleteMapping("/{songId}")
	public ResponseEntity<String> deleteSong(@PathVariable Integer songId) {
		boolean deleted = songService.deleteSong(songId);

		if (deleted) {
			return ResponseEntity.ok("Cancion eliminada correctamente");
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<SongEntity> findById(@PathVariable Integer id) {
		SongEntity founded = songService.findById(id);

		if (founded != null) {
			return ResponseEntity.ok(founded);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateSong(@PathVariable Integer id, @RequestBody SongEntity song) {

		boolean updated = songService.updateSong(id, song);

		if (updated) {

			return ResponseEntity.ok("Canción actualizada correctamente");

		}

		return ResponseEntity.badRequest().body("No se pudo actualizar la canción");

	}

}
