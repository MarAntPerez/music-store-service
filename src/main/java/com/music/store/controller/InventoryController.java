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

import com.music.store.dto.ArtistStatsDto;
import com.music.store.dto.AvailabilityDto;
import com.music.store.dto.InventoryValueDto;
import com.music.store.entity.InventoryEntity;
import com.music.store.service.InventoryService;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

	@Autowired
	private InventoryService inventoryService;

	@PostMapping
	public ResponseEntity<String> CreateInventory(@RequestBody InventoryEntity inventory) {
		boolean created = inventoryService.createInventory(inventory);

		if (created) {
			return ResponseEntity.ok("Inventario creado correctamente");
		} else {
			return ResponseEntity.badRequest().body("No fue posible crear el inventario");
		}
	}

	@GetMapping
	public List<InventoryEntity> getAllInventories() {
		return inventoryService.getAllInventories();
	}

	@GetMapping("/{id}")
	public ResponseEntity<InventoryEntity> getInventoryById(@PathVariable Integer id) {
		InventoryEntity inventory = inventoryService.getInventoryById(id);

		if (inventory != null) {
			return ResponseEntity.ok(inventory);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateInventory(@PathVariable Integer id, @RequestBody InventoryEntity inventory) {
		boolean updated = inventoryService.updateInventory(id, inventory);

		if (updated) {
			return ResponseEntity.ok("Inventario actualizado con exito");
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteInventory(@PathVariable Integer id) {
		boolean deleted = inventoryService.deleteInventoy(id);

		if (deleted) {
			return ResponseEntity.ok("Inventario eliminado con exito");
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/availability")
	public List<AvailabilityDto> getAvailability(@RequestParam String genre, @RequestParam String format) {
		return inventoryService.getAvailability(genre, format);
	}

	@GetMapping("/value")
	public List<InventoryValueDto> getInventoryValueDto() {
		return inventoryService.getInventoryValue();
	}

	@GetMapping("/artist/top")
	public List<ArtistStatsDto> getTopArtist() {
		return inventoryService.getTopArtist();
	}

	@PostMapping("/sell/{albumId}")
	public ResponseEntity<?> sellAlbum(@PathVariable Integer albumId) {
		boolean sold = inventoryService.sellAlbum(albumId);
		if (!sold) {
			return ResponseEntity.badRequest().body("Album no disponibles");
		}

		return ResponseEntity.ok("Venta completada");
	}

	@PostMapping("/restore/{albumId}")
	public ResponseEntity<?> restoreAlbum(@PathVariable Integer albumId) {
		boolean restored = inventoryService.restoreAlbum(albumId);

		if (!restored) {
			ResponseEntity.badRequest().body("Album no disponible");
		}

		return ResponseEntity.ok("Inventario restaurado");
	}

}
