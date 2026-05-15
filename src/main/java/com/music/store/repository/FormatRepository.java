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
		Integer existingId = findByFormatType(format.getFormatType());

		if (existingId != null) {
			return existingId;
		}

		String sql = """
				INSERT INTO formats (format_type)
				VALUES (?)
				""";

		entityManager.createNativeQuery(sql).setParameter(1, format.getFormatType()).executeUpdate();

		return findByFormatType(format.getFormatType());
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

	public boolean delete(Integer id) {
		if (id == 0) {
			return false;
		}

		entityManager.createNativeQuery("""
				    UPDATE albums
				    SET format_id = 0
				    WHERE format_id = :id
				""").setParameter("id", id).executeUpdate();

		int rows = entityManager.createNativeQuery("""
				    DELETE FROM formats
				    WHERE id = :id
				""").setParameter("id", id).executeUpdate();

		return rows > 0;
	}

	public Integer findByFormatType(String formatType) {
		String sql = """
				SELECT id
				FROM formats
				WHERE format_type = ?
				""";

		List<?> result = entityManager.createNativeQuery(sql).setParameter(1, formatType).getResultList();

		if (result.isEmpty()) {
			return null;
		}

		return ((Number) result.get(0)).intValue();
	}

}
