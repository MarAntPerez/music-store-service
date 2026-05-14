package com.music.store.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.music.store.entity.GenerEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class GenreRepository {

	@PersistenceContext
	private EntityManager entityManager = null;

	public int save(GenerEntity gener) {
		Integer existingId = findByGenre(gener.getGenresName());

		if (existingId != null) {
			return existingId;
		}

		String sql = """
				INSERT INTO genres (genres_name)
				VALUES (?)
				""";

		entityManager.createNativeQuery(sql).setParameter(1, gener.getGenresName()).executeUpdate();

		return findByGenre(gener.getGenresName());
	}

	@SuppressWarnings("unchecked")
	public List<GenerEntity> findAll() {
		String sql = "SELECT * FROM genres";

		return entityManager.createNativeQuery(sql, GenerEntity.class).getResultList();
	}

	@SuppressWarnings("unchecked")
	public GenerEntity findById(Integer id) {
		String sql = "SELECT * FROM genres WHERE id = ?";

		List<GenerEntity> result = entityManager.createNativeQuery(sql, GenerEntity.class).setParameter(1, id)
				.getResultList();

		return result.isEmpty() ? null : result.get(0);
	}

	public int update(Integer id, GenerEntity gener) {
		String sql = "UPDATE genres SET genres_name = ? WHERE id = ?";

		return entityManager.createNativeQuery(sql).setParameter(1, gener.getGenresName()).setParameter(2, id)
				.executeUpdate();
	}

	public int delete(Integer id) {
		String sql = "DELETE FROM genres WHERE id = ?";

		return entityManager.createNativeQuery(sql).setParameter(1, id).executeUpdate();
	}

	public Integer findByGenre(String genres_name) {
		String sql = """
				SELECT id
				FROM genres
				WHERE genres_name = ?
				""";

		List<?> result = entityManager.createNativeQuery(sql).setParameter(1, genres_name).getResultList();

		if (result.isEmpty()) {
			return null;
		}

		return ((Number) result.get(0)).intValue();
	}

}
