package com.music.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.music.store.dto.SongResponseDto;
import com.music.store.service.SongService;

@RestController
@RequestMapping("/songs")
public class SongController {

	@Autowired
	private SongService songSevice;

	@GetMapping("/album/{albumId}")
	public List<SongResponseDto> getSongsByAlbum(@PathVariable int albumId) {
		return songSevice.getSongsByAlbumId(albumId);
	}

}
