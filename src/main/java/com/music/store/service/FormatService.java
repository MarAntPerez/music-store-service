package com.music.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.music.store.entity.FormatEntity;
import com.music.store.repository.FormatRepository;

@Service
public class FormatService {

	@Autowired
	private FormatRepository formatRepository;

	public boolean createFormat(FormatEntity format) {
		int result = formatRepository.save(format);

		return result > 0;
	}

	public List<FormatEntity> getAllFormats() {
		return formatRepository.findAll();
	}

	public FormatEntity getFormatById(Integer id) {
		return formatRepository.findById(id);
	}

	public boolean updateFormat(Integer id, FormatEntity format) {
		int result = formatRepository.update(id, format);
		return result > 0;
	}

	public boolean deleteFormat(Integer id) {
		int result = formatRepository.delete(id);
		return result > 0;
	}

}
