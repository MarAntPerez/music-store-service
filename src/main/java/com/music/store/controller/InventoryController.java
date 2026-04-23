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

}
