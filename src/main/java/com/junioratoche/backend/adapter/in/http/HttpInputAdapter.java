package com.junioratoche.backend.adapter.in.http;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.junioratoche.backend.adapter.out.db.dto.PriceResponse;
import com.junioratoche.backend.adapter.out.db.mapper.PriceResponseMapper;
import com.junioratoche.backend.domain.Price;
import com.junioratoche.backend.port.in.http.PriceInputPort;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "price")
public class HttpInputAdapter {

	@Autowired
	PriceInputPort priceInputPort;
	
	@Autowired
	PriceResponseMapper priceResponseMapper;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PriceResponse> getAll() {
		List<Price> priceList = priceInputPort.getAll();
		List<PriceResponse> priceListToPriceResposeList = priceResponseMapper.priceListToPriceResponseList(priceList);
		return priceListToPriceResposeList;
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public PriceResponse getPriceByBrandAndProductInApplicationDate(
			@RequestParam(name = "applicationDate", required = false) Date applicationDate,
			@RequestParam(name = "productId", required = false) Integer productId,
			@RequestParam(name = "brandId", required = false) Integer brandId) {
		Price findById = priceInputPort.getPriceByBrandAndProductInApplicationDate(applicationDate, productId,
				brandId);
		PriceResponse priceToPriceResponse = priceResponseMapper.priceToPriceResponse(findById);
		return priceToPriceResponse;
	}
}