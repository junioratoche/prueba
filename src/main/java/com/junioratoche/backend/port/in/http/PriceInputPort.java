package com.junioratoche.backend.port.in.http;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import com.junioratoche.backend.domain.Price;

public interface PriceInputPort {

	List<Price> getAll();

	Price getPriceByBrandAndProductInApplicationDate(LocalDateTime applicationDate, int productId, int brandId);

}
