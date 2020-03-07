package com.hackerrank.stocktrade.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackerrank.stocktrade.dao.ITradeDao;
import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.model.User;


@Service
public class TradeServiceImpl implements ITradeService {

	@Autowired
	private ITradeDao tradeDao;
	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	//private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
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
	public Trade findTradeById(Long tradeid) {
		Trade trade = tradeDao.findByIdSQL(tradeid);
		return trade;
	}

	@Override
	public List<Trade> findAllByUser(User user) {
		List<Trade> trades = tradeDao.findByUser(user);
		return trades;
	}

	@Override
	public List<Trade> findTradesByStockDates(String symbol, String startdate, String enddate) {
		
		Date startDate = null;
		Date endDate = null;
		
		try {
			startDate = dateFormat.parse(startdate);
			endDate = dateFormat.parse(enddate);
			List<Trade> trades = tradeDao.findByStockDate(symbol, startDate, endDate);
			return trades;
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return null;
	}

	@Override
	public List<Float> findMaxMinBySymbol(String symbol, String startdate, String enddate) {
		List<Float> listprices = new ArrayList<>();
		Date startDate = null;
		Date endDate = null;
		
		try {
			startDate = dateFormat.parse(startdate);
			endDate = dateFormat.parse(enddate);
			
			Float max = tradeDao.findHighPriceTrade(symbol, startDate, endDate);
			System.out.println("Max: " + max);
			Float min = tradeDao.findLowPriceTrade(symbol, startDate, endDate);
			System.out.println("Min: " + min);
			
			if(max != null && min != null) {
				listprices.add(max);
				listprices.add(min);
			
				return listprices;
			}else {
				return null;
			}
		}catch(Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			return null;
		}
	}

}
