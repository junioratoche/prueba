package com.junioratoche.backend.port.out.db;

import java.util.Date;
import java.util.List;
import com.junioratoche.backend.domain.Price;

public interface EntityRepositoryOutputPort {

	public List<Price> getAll();

	public Price getPriceByBrandAndProductInApplicationDate(Date applicationDate, int productId, int brandId);
}
