package com.hackerrank.stocktrade.service;

import java.util.List;
import java.util.Optional;

import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.model.User;

public interface ITradeService {
	
	public List<Trade> findAllTrades();
	
	public void deleteAllTrades();
	
	public void saveTrade(Trade trade);
	
	public Trade findTradeById(Long tradeid);
	
	public List<Trade> findAllByUser(User user);
	
	public List<Trade> findTradesByStockDates(String symbol, String startdate, String enddate);
	
	public List<Float> findMaxMinBySymbol(String symbol, String startdate, String enddate);
}
