package com.music.store.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.music.store.dto.SongResponseDto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class SongRepository {

	@PersistenceContext
	private EntityManager entityManager = null;

	@SuppressWarnings("unchecked")
	public List<SongResponseDto> getSongsByAlbumId(int albumId) {
		String sql = """
				SELECT
				    s.id,
				    s.song_name,
				    s.duration,
				    s.track_number
				FROM songs s
				WHERE s.album_id = :albumId
				ORDER BY s.id
				""";

		List<Object[]> results = entityManager.createNativeQuery(sql).setParameter("albumId", albumId).getResultList();

		return results.stream().map(row -> new SongResponseDto(

				((Number) row[0]).intValue(), (String) row[1], (String) row[2], ((Number) row[3]).intValue()

		)).toList();
	}

}
