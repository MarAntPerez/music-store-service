package com.music.store.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.music.store.entity.FormatEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class FormatRepository {

	@PersistenceContext
	private EntityManager entityManager = null;

	public int save(FormatEntity format) {
		String sql = "INSERT INTO formats (format_type) VALUES = (?)";

		return entityManager.createNativeQuery(sql).setParameter(1, format.getFormatType()).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<FormatEntity> findAll() {
		String sql = "SELECT * FROM formats";

		return entityManager.createNativeQuery(sql, FormatEntity.class).getResultList();
	}

	@SuppressWarnings("unchecked")
	public FormatEntity findById(Integer id) {
		String sql = "SELECT * FROM formats WHERE id = ?";

		List<FormatEntity> result = entityManager.createNativeQuery(sql, FormatEntity.class).setParameter(1, id)
				.getResultList();

		return result.isEmpty() ? null : result.get(0);
	}

	public int update(Integer id, FormatEntity format) {
		String sql = "UPDATE formats SET format_type = ? WHERE id = ?";

		return entityManager.createNativeQuery(sql).setParameter(1, format.getFormatType()).setParameter(2, id)
				.executeUpdate();
	}

	public int delete(Integer id) {
		String sql = "DELETE FROM formats WHERE id = ?";

		int rowsAffected = entityManager.createNativeQuery(sql).setParameter(1, id).executeUpdate();

		return rowsAffected;
	}

}
