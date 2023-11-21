package com.junioratoche.backend.adapter.db.dto;

import java.time.LocalDateTime;

public class PriceRequest {

	private LocalDateTime applicationDate;
	private Long productId;
	private Long brandId;

	public LocalDateTime getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(LocalDateTime applicationDate) {
		this.applicationDate = applicationDate;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

}
