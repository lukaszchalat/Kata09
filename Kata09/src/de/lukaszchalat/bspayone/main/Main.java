package de.lukaszchalat.bspayone.main;

import java.util.HashMap;
import java.util.Map;

import de.lukaszchalat.bspayone.checkout.Checkout;
import de.lukaszchalat.bspayone.pricingRule.*;
import de.lukaszchalat.bspayone.service.PriceService;

// solution to : http://codekata.com/kata/kata09-back-to-the-checkout/

public class Main 
{
	public static void main(String[] args) 
	{
		setProductsPrices();	
		
		// initialize checkout object and set pricing rule for it
		Checkout checkout = Checkout.valueOf( getPricingRules() );
		
		// scan some products
		checkout.scan( "AAABBCD" );
		
		// show total price
		System.out.println( checkout.getTotalPrice() );
	}
	
	/*
	 *  this method set prices in PriceService class, in this implementation
	 *  price is not bind to the product but is managed by special service that
	 *  returns correct price by product id, we can easily change normal price 
	 *  for each product type (id)
	 */
	private static void setProductsPrices()
	{
		// initialize price and pricing rule service
		PriceService priceService = PriceService.getInstance();
				
		// set prices for the products, product id in this implementation is case sensitive so "A" is not equals "a"
		priceService.setPrice( "A", 50 );
		priceService.setPrice( "B", 30 );
		priceService.setPrice( "C", 20 );
		priceService.setPrice( "D", 15 );
	}
	
	/*
	 * this method returns mapping of product id (String) and pricing rule
	 * capable object, so we can easily find out what pricing rule has concrete product,
	 * I acknowledged that one product (id) has only one pricing rule   
	 */
	private static Map<String, PricingRuleCapable> getPricingRules()
	{
		Map<String, PricingRuleCapable> productIdToRuleMapping = new HashMap<>();
		
		productIdToRuleMapping.put( "A", new AmountSpecialPriceRule( 3, 130 ) );
		productIdToRuleMapping.put( "B", new AmountSpecialPriceRule( 2, 45 ) );
		
		return productIdToRuleMapping;
	}

}
