package com.music.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.music.store.dto.ArtistStatsDto;
import com.music.store.dto.AvailabilityDto;
import com.music.store.dto.InventoryValueDto;
import com.music.store.entity.InventoryEntity;
import com.music.store.repository.InventoryRepository;

import jakarta.transaction.Transactional;

@Service
public class InventoryService {

	@Autowired
	private InventoryRepository inventoryRepository;

	public boolean createInventory(InventoryEntity inventory) {
		int result = inventoryRepository.save(inventory);

		return result > 0;
	}

	public List<InventoryEntity> getAllInventories() {
		return inventoryRepository.findAll();
	}

	public InventoryEntity getInventoryById(Integer id) {
		return inventoryRepository.findById(id);
	}

	public boolean updateInventory(Integer id, InventoryEntity inventory) {
		int result = inventoryRepository.update(id, inventory);

		return result > 0;
	}

	public boolean deleteInventoy(Integer id) {
		int result = inventoryRepository.delete(id);

		return result > 0;
	}

	public List<AvailabilityDto> getAvailability(String genre, String format) {
		return inventoryRepository.findAvailableByGenreAndFormat(genre, format).stream()
				.map(row -> new AvailabilityDto((String) row[0], (String) row[1], ((Number) row[2]).intValue(),
						((Number) row[3]).doubleValue()))
				.toList();
	}

	public List<InventoryValueDto> getInventoryValue() {
		return inventoryRepository.getInventoryValueByFormat().stream()
				.map(row -> new InventoryValueDto((String) row[0], ((Number) row[1]).intValue(),
						((Double) row[2]).doubleValue()))
				.toList();
	}

	public List<ArtistStatsDto> getTopArtist() {
		return inventoryRepository.getTopArtist().stream().map(
				row -> new ArtistStatsDto((String) row[0], ((Number) row[1]).intValue(), ((Number) row[2]).intValue()))
				.toList();
	}

	@Transactional
	public boolean sellAlbum(Integer albumid) {
		InventoryEntity inventory = inventoryRepository.findByAlbumId(albumid);

		if (inventory == null) {
			return false;
		}

		if (inventory.getAmount() <= 0) {
			return false;
		}

		inventory.setAmount(inventory.getAmount() - 1);

		inventoryRepository.update(inventory);

		return true;
	}

	@Transactional
	public boolean restoreAlbum(Integer albumId) {
		InventoryEntity inventory = inventoryRepository.findByAlbumId(albumId);

		if (inventory == null) {
			return false;
		}

		inventory.setAmount(inventory.getAmount() + 1);

		inventoryRepository.update(inventory);

		return true;
	}

}
