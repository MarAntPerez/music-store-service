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

import com.music.store.entity.FormatEntity;
import com.music.store.service.FormatService;

@RestController
@RequestMapping("/formats")
public class FormatController {

	@Autowired
	private FormatService formatService;

	@PostMapping
	public ResponseEntity<String> createFormat(@RequestBody FormatEntity format) {
		boolean created = formatService.createFormat(format);

		if (created) {
			return ResponseEntity.ok("Formato creado correctamente");
		} else {
			return ResponseEntity.badRequest().body("No fue posible crear el formato");
		}
	}

	@GetMapping
	public List<FormatEntity> getAllFormats() {
		return formatService.getAllFormats();
	}

	@GetMapping("/{id}")
	public ResponseEntity<FormatEntity> getFormatById(@PathVariable Integer id) {
		FormatEntity format = formatService.getFormatById(id);

		if (format != null) {
			return ResponseEntity.ok(format);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateFormat(@PathVariable Integer id, @RequestBody FormatEntity format) {
		boolean updated = formatService.updateFormat(id, format);

		if (updated) {
			return ResponseEntity.ok("Formato actualizado correctamente");
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteFormat(@PathVariable Integer id) {
		boolean deleted = formatService.deleteFormat(id);

		if (deleted) {
			return ResponseEntity.ok("Formato eliminado correctamente");
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
