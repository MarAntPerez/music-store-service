package com.music.store.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "formats")
public class FormatEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "format_type", nullable = false)
	private String format_type;

	protected FormatEntity() {
	}

	public FormatEntity(String format_type) {
		this.format_type = format_type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFormat_type() {
		return format_type;
	}

	public void setFormat_type(String format_type) {
		this.format_type = format_type;
	}

}
