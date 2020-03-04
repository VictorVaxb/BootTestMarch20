package com.hackerrank.stocktrade.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hackerrank.stocktrade.service.ITradeService;
import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.model.User;

@RestController
@RequestMapping(value = "/trades")
public class TradesController {
    
	@Autowired
	private ITradeService tradeServ;
	
	@GetMapping("/list")
	public ResponseEntity<?> getAllTrades(){
		System.out.println("getAllTrades()");
		List<Trade> listTrades = tradeServ.findAllTrades();
		if(listTrades != null) {
			if(listTrades.size() > 0) {
				return new ResponseEntity<List<Trade>>(listTrades, HttpStatus.OK);
			}else {
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			}
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/findtradebyid")
	public ResponseEntity<?> findTradeById(@RequestBody Long id){
		Optional<Trade> trade = tradeServ.findTradeById(id);
		return new ResponseEntity<Optional<Trade>>(trade, HttpStatus.OK);
	}
	
	@PostMapping("/newtrade")
	public ResponseEntity<?> saveTrade(@RequestBody Trade trade){
		tradeServ.saveTrade(trade);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deletetrades")
	public ResponseEntity<?> deleteAllTrades(){
		tradeServ.deleteAllTrades();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("/findtradesbyuser")
	public ResponseEntity<?> findTradesByUser(@RequestBody User user){
		List<Trade> trades = tradeServ.findAllByUser(user);
		if(trades != null) {
			if(trades.size() > 0) {
				return new ResponseEntity<List<Trade>>(trades, HttpStatus.OK);
			}else {
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			}
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/stocks/{symbol}")
	public ResponseEntity<?> findTradesByStockDates(@PathVariable(value="symbol") String symbol, 
													@RequestParam String start,
													@RequestParam String end){
		List<Trade> trades = tradeServ.findTradesByStockDates(symbol, start, end);
		
		if(trades != null) {
			if(trades.size() > 0) {
				return new ResponseEntity<List<Trade>>(trades, HttpStatus.OK);
			}else {
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			}
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
}
