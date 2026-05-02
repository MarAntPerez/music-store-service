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

	@SuppressWarnings("unchecked")
	public List<Object[]> findAvailableByGenreAndFormat(String genre, String format) {
		String sql = """
				    SELECT
				        a.album_name,
				        ar.artist_name,
				        i.amount,
				        i.cost
				    FROM albums a
				    JOIN artists ar ON a.artist_id = ar.id
				    JOIN genres g ON a.genre_id = g.id
				    JOIN formats f ON a.format_id = f.id
				    JOIN inventory i ON a.id = i.album_id
				    WHERE g.genres_name = ?
				      AND f.format_type = ?
				      AND i.amount > 0
				""";

		return entityManager.createNativeQuery(sql).setParameter(1, genre).setParameter(2, format).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getInventoryValueByFormat() {
		String sql = """
				    SELECT
				        f.format_type,
				        SUM(i.amount),
				        ROUND(SUM(i.amount * i.cost), 2)
				    FROM inventory i
				    JOIN albums a ON i.album_id = a.id
				    JOIN formats f ON a.format_id = f.id
				    GROUP BY f.format_type
				    ORDER BY 3 DESC
				""";

		return entityManager.createNativeQuery(sql).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getTopArtist() {
		String sql = """
				    SELECT
				        ar.artist_name,
				        COUNT(a.id),
				        ROUND(SUM(i.amount), 2)
				    FROM artists ar
				    JOIN albums a ON ar.id = a.artist_id
				    JOIN inventory i ON a.id = i.album_id
				    GROUP BY ar.artist_name
				    HAVING COUNT(a.id) > 1
				    ORDER BY 2 DESC
				""";

		return entityManager.createNativeQuery(sql).getResultList();
	}

}
