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

import com.music.store.entity.GenerEntity;
import com.music.store.service.GenerService;

@RestController
@RequestMapping("/genres")
public class GenerController {

	@Autowired
	private GenerService generService;

	@PostMapping
	public ResponseEntity<String> CreateGenre(@RequestBody GenerEntity genre) {
		boolean created = generService.createGenre(genre);

		if (created) {
			return ResponseEntity.ok("Genero creado correctamente");
		} else {
			return ResponseEntity.badRequest().body("No fue posible crear el genero");
		}
	}

	@GetMapping
	public List<GenerEntity> getAllGenres() {
		return generService.getAllGenres();
	}

	@GetMapping("/{id}")
	public ResponseEntity<GenerEntity> getGenreById(@PathVariable Integer id) {
		GenerEntity genre = generService.getGenreById(id);

		if (genre != null) {
			return ResponseEntity.ok(genre);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateGenre(@PathVariable Integer id, @RequestBody GenerEntity gener) {
		boolean updated = generService.updateGenre(id, gener);

		if (updated) {
			return ResponseEntity.ok("Genero actualizado con exito");
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteGenre(@PathVariable Integer id) {
		boolean deleted = generService.deleteGenre(id);

		if (deleted) {
			return ResponseEntity.ok("Genero eliminado con exito");
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
