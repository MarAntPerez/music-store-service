package com.music.store.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.music.store.entity.InventoryEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class InventoryRepository {

	@PersistenceContext
	private EntityManager entityManager = null;

	public int save(InventoryEntity inventory) {
		String sql = "INSERT INTO inventory (album_id, amount, cost) VALUES (?, ?, ?)";

		return entityManager.createNativeQuery(sql).setParameter(1, inventory.getAlbumId())
				.setParameter(2, inventory.getAmount()).setParameter(3, inventory.getCost()).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<InventoryEntity> findAll() {
		String sql = "SELECT * FROM inventory";

		return entityManager.createNativeQuery(sql, InventoryEntity.class).getResultList();
	}

	@SuppressWarnings("unchecked")
	public InventoryEntity findById(Integer id) {
		String sql = "SELECT * FROM inventory WHERE id = ?";

		List<InventoryEntity> result = entityManager.createNativeQuery(sql, InventoryEntity.class).setParameter(1, id)
				.getResultList();

		return result.isEmpty() ? null : result.get(0);
	}

	public int update(Integer id, InventoryEntity inventory) {
		String sql = "UPDATE inventory SET album_id = ?, amount = ?, cost = ? WHERE id = ?";

		return entityManager.createNativeQuery(sql).setParameter(1, inventory.getAlbumId())
				.setParameter(2, inventory.getAmount()).setParameter(3, inventory.getCost()).setParameter(4, id)
				.executeUpdate();
	}

	public int delete(Integer id) {
		String sql = "DELETE FROM inventory WHERE id = ?";

		return entityManager.createNativeQuery(sql).setParameter(1, id).executeUpdate();
	}

}
