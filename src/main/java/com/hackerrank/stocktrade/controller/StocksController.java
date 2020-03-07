package com.hackerrank.stocktrade.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hackerrank.stocktrade.service.ITradeService;

@RestController
@RequestMapping(value = "/stocks")
public class StocksController {
    
	@Autowired
	private ITradeService tradeServ;
	
	@GetMapping("/{symbol}/price")
	public ResponseEntity<?> findMaxMinBySymbol(@PathVariable(value="symbol") String symbol, 
												@RequestParam String start,
												@RequestParam String end){
		List<Float> lstPrices = tradeServ.findMaxMinBySymbol(symbol, start, end);
		
		System.out.println("symbol: " + symbol);
		System.out.println("start: " + start);
		System.out.println("end: " + end);
		
		if(lstPrices != null) {
			System.out.println("lstPrices not null");
			System.out.println("lstPrices: " + lstPrices.toString());
			if(lstPrices.size() > 1) {
				System.out.println("lstPrices not null");
				Map<String, String> resp = new HashMap<String, String>();
				resp.put("highest", lstPrices.get(0).toString());
				resp.put("lowest", lstPrices.get(1).toString());
				return new ResponseEntity<Map<String, String>>(resp, HttpStatus.OK);
			}
		}
		
		return new ResponseEntity<String>(Collections.singletonMap("message", "There are no trades in the given date range").toString(),HttpStatus.NOT_FOUND);
	}
	
}
