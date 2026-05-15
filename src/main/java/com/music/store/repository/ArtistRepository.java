package com.music.store.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.music.store.entity.ArtistEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class ArtistRepository {

	@PersistenceContext
	private EntityManager entityManager = null;

	// Guarda un artista nuevo
	public int save(ArtistEntity artist) {
		Integer existingId = findByName(artist.getArtistName());

		if (existingId != null) {
			return existingId;
		}

		String sql = """
				INSERT INTO artists (artist_name)
				VALUES (?)
				""";

		entityManager.createNativeQuery(sql).setParameter(1, artist.getArtistName()).executeUpdate();

		return findByName(artist.getArtistName());
	}

	// Devuelve todos los artistas
	@SuppressWarnings("unchecked")
	public List<ArtistEntity> findAll() {
		String sql = "SELECT * FROM artists";

		return entityManager.createNativeQuery(sql, ArtistEntity.class).getResultList();
	}

	// Devuelve artista especificado por id
	@SuppressWarnings("unchecked")
	public ArtistEntity findById(Integer id) {
		String sql = "SELECT * FROM artists WHERE id = ?";

		List<ArtistEntity> result = entityManager.createNativeQuery(sql, ArtistEntity.class).setParameter(1, id)
				.getResultList();

		return result.isEmpty() ? null : result.get(0);
	}

	// Actualiza la informacion de un artista
	public int update(Integer id, ArtistEntity artist) {
		String sql = "UPDATE artists SET artist_name = ? WHERE id = ?";

		return entityManager.createNativeQuery(sql).setParameter(1, artist.getArtistName()).setParameter(2, id)
				.executeUpdate();
	}

	// Elimina un artista
	public boolean delete(Integer id) {

		if (id == 0) {
			return false;
		}

		entityManager.createNativeQuery("""
				    UPDATE albums
				    SET artist_id = 0
				    WHERE artist_id = :id
				""").setParameter("id", id).executeUpdate();

		int rows = entityManager.createNativeQuery("""
				    DELETE FROM artists
				    WHERE id = :id
				""").setParameter("id", id).executeUpdate();

		return rows > 0;
	}

	public Integer findByName(String artistName) {
		String sql = """
				SELECT id
				FROM artists
				WHERE artist_name = ?
				""";

		List<?> result = entityManager.createNativeQuery(sql).setParameter(1, artistName).getResultList();

		if (result.isEmpty()) {
			return null;
		}

		return ((Number) result.get(0)).intValue();
	}

}
