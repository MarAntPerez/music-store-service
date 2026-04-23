package com.music.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.music.store.entity.InventoryEntity;
import com.music.store.repository.InventoryRepository;

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

}
