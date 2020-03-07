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
import com.hackerrank.stocktrade.service.IUserService;
import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.model.User;

@RestController
@RequestMapping(value = "/")
public class TradesController {
    
	@Autowired
	private IUserService userServ;
	
	@Autowired
	private ITradeService tradeServ;
	
	@PostMapping("/trades") //OK
	public ResponseEntity<?> saveTrade(@RequestBody Trade trade){
		System.out.println("saveTrade()");
		System.out.println("Id: " + trade.getUser().getId());
		System.out.println("User: " + trade.getUser().getName());
		
		Trade tradeDb = tradeServ.findTradeById(trade.getId());
		
		if(tradeDb == null) {
			User usrBd = userServ.findUserById(trade.getUser().getId());
			if(usrBd == null) {
				User newUser = new User(trade.getUser().getId(), trade.getUser().getName());
				userServ.saveUser(newUser);
			}
			
			tradeServ.saveTrade(trade);
			System.out.println("Se guardo trade");
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/trades") //OK
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
	
	@GetMapping("/trades/{id}") //OK
	public ResponseEntity<?> findTradeById(@PathVariable(value="id") Long id){
		Trade trade = tradeServ.findTradeById(id);
		if(trade == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);	
		}
		return new ResponseEntity<Trade>(trade, HttpStatus.OK);
	}
	
	@DeleteMapping("/erase") //OK
	public ResponseEntity<?> deleteAllTrades(){
		System.out.println("deletetrades");
		tradeServ.deleteAllTrades();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("/trades/users/{id}")
	public ResponseEntity<?> findTradesByUser(@PathVariable(value="id") Long id){
		User userDb = userServ.findUserById(id);
		
		List<Trade> trades = tradeServ.findAllByUser(userDb);
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
	
	@GetMapping("/trades/stocks/{symbol}") //OK
	public ResponseEntity<?> findTradesByStockDates(@PathVariable(value="symbol") String symbol, 
													@RequestParam String start,
													@RequestParam String end){
		System.out.println("Symbol: " + symbol);
		System.out.println("Start: " + start);
		System.out.println("End: " + end);
		
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
