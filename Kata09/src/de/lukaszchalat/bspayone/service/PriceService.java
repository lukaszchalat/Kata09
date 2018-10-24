package de.lukaszchalat.bspayone.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class PriceService 
{
	private static Map<String, BigDecimal> productIdToPriceMapping = new HashMap<>();
	private static PriceService instance                           = null;
	
	// constructor is private because PriceService is singleton, we want to have only one instance of it
	private PriceService()
	{
		
	}
	
	public static PriceService getInstance()
	{
		// lazy initialization
		if( instance == null ) instance = new PriceService();
		
		return instance;
	}
	
	public void setPrice( String product, Integer price )
	{
		// we handle with prices also money so we need to use BigDecimal object
		productIdToPriceMapping.put( product, new BigDecimal( price ) );
	}
	
	public BigDecimal getPrice( String id )
	{
		return productIdToPriceMapping.get( id );
	}
}
