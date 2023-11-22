package com.junioratoche.backend.adapter.in.http;


import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import com.junioratoche.backend.adapter.out.db.dto.PriceResponse;
import com.junioratoche.backend.adapter.out.db.mapper.PriceResponseMapper;
import com.junioratoche.backend.domain.Price;
import com.junioratoche.backend.port.in.http.PriceInputPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ComponentScan(basePackages = "com.junioratoche.backend")
class HttpInputAdapterTest {

	@Autowired
	PriceInputPort priceInputPort;

	@Autowired
	PriceResponseMapper priceResponseMapper;

	@DisplayName("Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
	@Test
	void getPriceByBrandAndProductInApplicationDateTest1() throws Exception {

		LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
		Integer productId = 35455;
		Integer brandId = 1;

		PriceResponse priceResponse = new PriceResponse();
		Price price = new Price();

		price = priceInputPort.getPriceByBrandAndProductInApplicationDate(applicationDate, productId, brandId);
		priceResponse = priceResponseMapper.priceToPriceResponse(price);

		assertEquals(priceResponse.getBrandId(), 1);
		assertEquals(priceResponse.getProductId(), 35455);
		assertEquals(priceResponse.getPriceList(), 1);
		assertEquals(priceResponse.getPrice(), 35.5);
		assertEquals(priceResponse.getStartDate(), LocalDateTime.of(2020, 6, 14, 0, 0, 0));
		assertEquals(priceResponse.getEndDate(), LocalDateTime.of(2020, 12, 31, 23, 59, 59));

	}

}