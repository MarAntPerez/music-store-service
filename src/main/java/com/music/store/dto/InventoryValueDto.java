package com.music.store.dto;

public class InventoryValueDto {

	private String formatType;
	private Integer totalUnits;
	private Double totalValue;

	public InventoryValueDto(String formatType, Integer totalUnits, Double totalValue) {
		this.formatType = formatType;
		this.totalUnits = totalUnits;
		this.totalValue = totalValue;
	}

	public String getFormatType() {
		return formatType;
	}

	public void setFormatType(String formatType) {
		this.formatType = formatType;
	}

	public Integer getTotalUnits() {
		return totalUnits;
	}

	public void setTotalUnits(Integer totalUnits) {
		this.totalUnits = totalUnits;
	}

	public Double getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(Double totalValue) {
		this.totalValue = totalValue;
	}

}
