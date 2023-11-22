package com.junioratoche.backend.adapter.in.http;

import org.springframework.beans.factory.annotation.Autowired;
import java.text.ParseException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.junioratoche.backend.adapter.out.db.dto.PriceResponse;
import com.junioratoche.backend.adapter.out.db.mapper.PriceResponseMapper;
import com.junioratoche.backend.domain.Price;
import com.junioratoche.backend.port.in.http.PriceInputPort;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = "price")
public class HttpInputAdapter {

	@Autowired
	PriceInputPort priceInputPort;

	@Autowired
	PriceResponseMapper priceResponseMapper;

	@Operation(description = "Return all prices")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Exito"),
	@ApiResponse(responseCode = "500", description = "Internal error") })
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PriceResponse> getAll() {
		List<Price> priceList = priceInputPort.getAll();
		List<PriceResponse> priceListToPriceResposeList = priceResponseMapper.priceListToPriceResponseList(priceList);
		return priceListToPriceResposeList;
	}
	
	
	@Operation(description = "Returns the price filtered by product, brand and date of application")
	@GetMapping(value = "/getByBrandAndProduct", produces = MediaType.APPLICATION_JSON_VALUE)
	public PriceResponse getPriceByBrandAndProductInApplicationDate(
			@RequestParam(name = "applicationDate", required = false) String applicationDateString,
			@RequestParam(name = "productId", required = false) Integer productId,
			@RequestParam(name = "brandId", required = false) Integer brandId) {

		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
			Date applicationDate = dateFormat.parse(applicationDateString);

			Price findById = priceInputPort.getPriceByBrandAndProductInApplicationDate(applicationDate, productId,
					brandId);
			PriceResponse priceToPriceResponse = priceResponseMapper.priceToPriceResponse(findById);
			return priceToPriceResponse;
		} catch (ParseException e) {

			e.printStackTrace();
			return null;
		}
	}
}