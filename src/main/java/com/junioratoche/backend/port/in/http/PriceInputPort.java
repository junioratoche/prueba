package com.junioratoche.backend.port.in.http;

import java.util.Date;
import java.util.List;
import com.junioratoche.backend.domain.Price;

public interface PriceInputPort {

	List<Price> getAll();

	Price getPriceByBrandAndProductInApplicationDate(Date applicationDate, int productId, int brandId);

}
