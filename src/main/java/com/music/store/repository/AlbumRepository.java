package com.music.store.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.music.store.dto.AlbumResponseDto;
import com.music.store.dto.YearResponse;
import com.music.store.entity.AlbumEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class AlbumRepository {

	@PersistenceContext
	private EntityManager entityManager = null;

	// INSERTA UN ALBUM NUEVO
	public Integer save(AlbumEntity album) {

		String sql = """
				INSERT INTO albums
				(
					artist_id,
					genre_id,
					format_id,
					album_name,
					year_release,
					image_url
				)
				VALUES (?, ?, ?, ?, ?, ?)
				""";

		entityManager.createNativeQuery(sql).setParameter(1, album.getArtistId()).setParameter(2, album.getGenreId())
				.setParameter(3, album.getFormatId()).setParameter(4, album.getAlbumName())
				.setParameter(5, album.getYearRelease()).setParameter(6, album.getImageUrl()).executeUpdate();

		String lastIdSql = """
				SELECT MAX(id)
				FROM albums
				""";

		Number result = (Number) entityManager.createNativeQuery(lastIdSql).getSingleResult();

		return result.intValue();
	}

	// DEVUELVE TODOS LOS ALBUMS
	@SuppressWarnings("unchecked")
	public List<AlbumResponseDto> findAll() {

		String sql = """
				SELECT
				    a.id,
				    a.album_name,
				    ar.artist_name,
				    g.genres_name,
				    f.format_type,
				    a.year_release,
				    a.image_url,
				    i.cost
				FROM albums a
				JOIN artists ar ON a.artist_id = ar.id
				JOIN genres g ON a.genre_id = g.id
				JOIN formats f ON a.format_id = f.id
				JOIN inventory i ON a.id = i.album_id
				""";

		List<Object[]> results = entityManager.createNativeQuery(sql).getResultList();

		return results.stream().map(row -> new AlbumResponseDto(

				((Number) row[0]).intValue(), (String) row[1], (String) row[2], (String) row[3], (String) row[4],
				((Number) row[5]).intValue(), (String) row[6], ((Number) row[7]).floatValue()

		)).toList();

	}

	// Devuelve un album especificado por el id
	public AlbumResponseDto findById(Integer albumId) {
		String sql = """
				SELECT
				    a.id,
				    a.album_name,
				    ar.artist_name,
				    g.genres_name,
				    f.format_type,
				    a.year_release,
				    a.image_url,
				    i.cost
				FROM albums a
				JOIN artists ar ON a.artist_id = ar.id
				JOIN genres g ON a.genre_id = g.id
				JOIN formats f ON a.format_id = f.id
				JOIN inventory i ON a.id = i.album_id
				WHERE a.id = :albumId
				""";

		Object[] row = (Object[]) entityManager.createNativeQuery(sql).setParameter("albumId", albumId)
				.getSingleResult();

		return new AlbumResponseDto(

				((Number) row[0]).intValue(), (String) row[1], (String) row[2], (String) row[3], (String) row[4],
				((Number) row[5]).intValue(), (String) row[6], ((Number) row[7]).floatValue()

		);
	}

	// Actualiza la informacion de un album
	public int update(Integer id, AlbumEntity album) {

		String sql = """
				UPDATE albums
				SET album_name = ?,
					year_release = ?
				WHERE id = ?
				""";

		return entityManager.createNativeQuery(sql).setParameter(1, album.getAlbumName())
				.setParameter(2, album.getYearRelease()).setParameter(3, id).executeUpdate();
	}

	// Elimina un album
	public int delete(Integer id) {

		String sql = "DELETE FROM albums WHERE id = ?";

		int rowsAffected = entityManager.createNativeQuery(sql).setParameter(1, id).executeUpdate();

		return rowsAffected;
	}

	// Buscar albums de un artista en especifico
	@SuppressWarnings("unchecked")
	public List<AlbumResponseDto> findByArtist(Integer artistId) {
		String sql = """
				SELECT
				    a.id,
				    a.album_name,
				    ar.artist_name,
				    g.genres_name,
				    f.format_type,
				    a.year_release,
				    a.image_url,
				    i.cost
				FROM albums a
				JOIN artists ar ON a.artist_id = ar.id
				JOIN genres g ON a.genre_id = g.id
				JOIN formats f ON a.format_id = f.id
				JOIN inventory i ON a.id = i.album_id
				WHERE ar.id = :artistId
				ORDER BY a.year_release
				""";

		List<Object[]> results = entityManager.createNativeQuery(sql).setParameter("artistId", artistId)
				.getResultList();

		return results.stream().map(row -> new AlbumResponseDto(

				((Number) row[0]).intValue(), (String) row[1], (String) row[2], (String) row[3], (String) row[4],
				((Number) row[5]).intValue(), (String) row[6], ((Number) row[7]).floatValue()

		)).toList();
	}

	// Buscar albums por genero
	@SuppressWarnings("unchecked")
	public List<AlbumResponseDto> findByGenre(Integer genreId) {

		String sql = """
				SELECT
				    a.id,
				    a.album_name,
				    ar.artist_name,
				    g.genres_name,
				    f.format_type,
				    a.year_release,
				    a.image_url,
				    i.cost
				FROM albums a
				JOIN artists ar ON a.artist_id = ar.id
				JOIN genres g ON a.genre_id = g.id
				JOIN formats f ON a.format_id = f.id
				JOIN inventory i ON a.id = i.album_id
				WHERE g.id = :genreId
				ORDER BY a.year_release
				""";

		List<Object[]> results = entityManager.createNativeQuery(sql).setParameter("genreId", genreId).getResultList();

		return results.stream().map(row -> new AlbumResponseDto(

				((Number) row[0]).intValue(), (String) row[1], (String) row[2], (String) row[3], (String) row[4],
				((Number) row[5]).intValue(), (String) row[6], ((Number) row[7]).floatValue()

		)).toList();
	}

	// Buscar por formato
	@SuppressWarnings("unchecked")
	public List<AlbumResponseDto> findByFormat(Integer formatId) {

		String sql = """
				SELECT
				    a.id,
				    a.album_name,
				    ar.artist_name,
				    g.genres_name,
				    f.format_type,
				    a.year_release,
				    a.image_url,
				    i.cost
				FROM albums a
				JOIN artists ar ON a.artist_id = ar.id
				JOIN genres g ON a.genre_id = g.id
				JOIN formats f ON a.format_id = f.id
				JOIN inventory i ON a.id = i.album_id
				WHERE f.id = :formatId
				ORDER BY a.year_release
				""";

		List<Object[]> results = entityManager.createNativeQuery(sql).setParameter("formatId", formatId)
				.getResultList();

		return results.stream().map(row -> new AlbumResponseDto(

				((Number) row[0]).intValue(), (String) row[1], (String) row[2], (String) row[3], (String) row[4],
				((Number) row[5]).intValue(), (String) row[6], ((Number) row[7]).floatValue()

		)).toList();
	}

	@SuppressWarnings("unchecked")
	public List<AlbumResponseDto> findByYear(Integer year) {

		String sql = """
				SELECT
				    a.id,
				    a.album_name,
				    ar.artist_name,
				    g.genres_name,
				    f.format_type,
				    a.year_release,
				    a.image_url,
				    i.cost
				FROM albums a
				JOIN artists ar ON a.artist_id = ar.id
				JOIN genres g ON a.genre_id = g.id
				JOIN formats f ON a.format_id = f.id
				JOIN inventory i ON a.id = i.album_id
				WHERE a.year_release = :year
				ORDER BY a.year_release
				""";

		List<Object[]> results = entityManager.createNativeQuery(sql).setParameter("year", year).getResultList();

		return results.stream().map(row -> new AlbumResponseDto(

				((Number) row[0]).intValue(), (String) row[1], (String) row[2], (String) row[3], (String) row[4],
				((Number) row[5]).intValue(), (String) row[6], ((Number) row[7]).floatValue()

		)).toList();
	}

	@SuppressWarnings("unchecked")
	public List<AlbumResponseDto> globalSearch(String searchText) {
		String sql = """
				SELECT
				        a.id,
				        a.album_name,
				        ar.artist_name,
				        g.genres_name,
				        f.format_type,
				        a.year_release,
				        a.image_url,
				        i.cost
				    FROM albums a
				    JOIN artists ar ON a.artist_id = ar.id
				    JOIN genres g ON a.genre_id = g.id
				    JOIN formats f ON a.format_id = f.id
				    JOIN inventory i ON a.id = i.album_id
				    WHERE
				        LOWER(a.album_name) LIKE LOWER(?)
				    OR LOWER(ar.artist_name) LIKE LOWER(?)
				    OR LOWER(g.genres_name) LIKE LOWER(?)
				    OR LOWER(f.format_type) LIKE LOWER(?)
				    OR CAST(a.year_release AS CHAR) LIKE ?
				    LIMIT 20
				""";

		String pattern = "%" + searchText + "%";

		List<Object[]> results = entityManager.createNativeQuery(sql).setParameter(1, pattern).setParameter(2, pattern)
				.setParameter(3, pattern).setParameter(4, pattern).setParameter(5, pattern).getResultList();

		return results.stream()
				.map(row -> new AlbumResponseDto(((Number) row[0]).intValue(), (String) row[1], (String) row[2],
						(String) row[3], (String) row[4], ((Number) row[5]).intValue(), (String) row[6],
						((Number) row[7]).floatValue()))
				.toList();
	}

	@SuppressWarnings("unchecked")
	public List<YearResponse> getYears() {

		String sql = """
				SELECT DISTINCT year_release
				FROM albums
				ORDER BY year_release
				""";

		List<Number> results = entityManager.createNativeQuery(sql).getResultList();

		return results.stream().map(year -> new YearResponse(year.intValue())).toList();
	}

	public boolean deleteAnio(Integer year) {
		if (year == 0) {
			return false;
		}

		int rows = entityManager.createNativeQuery("""
				    UPDATE albums
				    SET year_release = 0
				    WHERE year_release = :year
				""").setParameter("year", year).executeUpdate();

		return rows > 0;

	}

}
