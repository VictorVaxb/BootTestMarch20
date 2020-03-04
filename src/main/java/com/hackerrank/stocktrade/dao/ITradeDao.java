package com.hackerrank.stocktrade.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.model.User;

public interface ITradeDao extends CrudRepository<Trade, Long> {
	
	public List<Trade> findByUser(User user);
	
	@Query("select t from trade t where t.symbol=?1 and t.timestamp between ?2 and ?3")
	public List<Trade> findByStockDate(String symbol, String startdate, String enddate);
	
	@Query("select max(price) from trade where symbol=?1 and timestamp between ?2 and ?3")
	public Float findHighPriceTrade(String symbol, String startdate, String enddate);
	
	@Query("select min(price) from trade where symbol=?1 and timestamp between ?2 and ?3")
	public Float findLowPriceTrade(String symbol, String startdate, String enddate);
}
