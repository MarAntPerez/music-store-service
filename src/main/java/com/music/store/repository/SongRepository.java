package com.music.store.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.music.store.dto.SongResponseDto;
import com.music.store.entity.SongEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
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

				((Number) row[0]).intValue(),

				(String) row[1],

				(String) row[2],

				row[3] != null ? ((Number) row[3]).intValue() : 0

		)).toList();
	}

	public int getNextTrackNumber(int albumId) {

		String sql = """
				    SELECT COALESCE(MAX(track_number), 0) + 1
				    FROM songs
				    WHERE album_id = ?
				""";

		Number result = (Number) entityManager.createNativeQuery(sql).setParameter(1, albumId).getSingleResult();

		return result.intValue();
	}

	public int save(SongEntity song) {

		String sql = """
				    INSERT INTO songs
				    (song_name, duration, track_number, album_id)
				    VALUES (?, ?, ?, ?)
				""";

		return entityManager.createNativeQuery(sql).setParameter(1, song.getSongName())
				.setParameter(2, song.getDuration()).setParameter(3, song.getTrackNumber())
				.setParameter(4, song.getAlbumId()).executeUpdate();
	}

	public boolean delete(Integer songId) {

		int rows = entityManager.createNativeQuery("""
				    DELETE FROM songs
				    WHERE id = :songId
				""").setParameter("songId", songId).executeUpdate();

		return rows > 0;
	}

	public SongEntity findById(Integer id) {
		String sql = """
				SELECT
				    id,
				    album_id,
				    song_name,
				    duration
				FROM songs
				WHERE id = :songId
				""";

		Object[] row = (Object[]) entityManager.createNativeQuery(sql).setParameter("songId", id).getSingleResult();

		SongEntity song = new SongEntity();

		song.setId(((Number) row[0]).intValue());
		song.setAlbumId(((Number) row[1]).intValue());
		song.setSongName((String) row[2]);
		song.setDuration((String) row[3]);

		return song;
	}

	public int update(Integer id, SongEntity song) {

		String sql = """
				UPDATE songs
				SET
				    song_name = :songName,
				    duration = :duration
				WHERE id = :songId
				""";

		return entityManager.createNativeQuery(sql).setParameter("songName", song.getSongName())
				.setParameter("duration", song.getDuration()).setParameter("songId", id).executeUpdate();
	}

}
