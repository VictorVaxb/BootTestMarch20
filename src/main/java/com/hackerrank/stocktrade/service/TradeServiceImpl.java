package com.hackerrank.stocktrade.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackerrank.stocktrade.dao.ITradeDao;
import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.model.User;


@Service
public class TradeServiceImpl implements ITradeService {

	@Autowired
	private ITradeDao tradeDao;
	
	@Override
	public List<Trade> findAllTrades() {
		List<Trade> listaTrades = (List<Trade>) tradeDao.findAll();
		return listaTrades;
	}

	@Override
	public void deleteAllTrades() {
		tradeDao.deleteAll();
	}

	@Override
	public void saveTrade(Trade trade) {
		tradeDao.save(trade);
	}

	@Override
	public Optional<Trade> findTradeById(Long tradeid) {
		Optional<Trade> trade = tradeDao.findById(tradeid);
		return trade;
	}

	@Override
	public List<Trade> findAllByUser(User user) {
		List<Trade> trades = tradeDao.findByUser(user);
		return trades;
	}

	@Override
	public List<Trade> findTradesByStockDates(String symbol, String startdate, String enddate) {
		List<Trade> trades = tradeDao.findByStockDate(symbol, startdate, enddate);
		return trades;
	}

	@Override
	public List<Float> findMaxMinBySymbol(String symbol, String startdate, String enddate) {
		List<Float> listprices = new ArrayList<>();
		Float max = tradeDao.findHighPriceTrade(symbol, startdate, enddate);
		Float min = tradeDao.findHighPriceTrade(symbol, startdate, enddate);
		listprices.add(max);
		listprices.add(min);
		
		return listprices;
	}

}
