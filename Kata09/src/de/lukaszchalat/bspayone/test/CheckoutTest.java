package de.lukaszchalat.bspayone.test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import de.lukaszchalat.bspayone.checkout.Checkout;
import de.lukaszchalat.bspayone.pricingRule.AmountSpecialPriceRule;
import de.lukaszchalat.bspayone.pricingRule.PricingRuleCapable;
import de.lukaszchalat.bspayone.service.PriceService;

class CheckoutTest 
{	
	@BeforeEach
	private void init()
	{
		setProductsPrices();
	}
	
	@Test
	public void testTotals() 
	{
		assertEquals( BigDecimal.valueOf( 0 ), price( "" ) );
		assertEquals( BigDecimal.valueOf( 50 ), price( "A" ) );
		assertEquals( BigDecimal.valueOf( 80 ), price( "AB" ) );
		assertEquals( BigDecimal.valueOf( 115 ), price( "CDBA" ) );
		
		assertEquals( BigDecimal.valueOf( 100 ), price( "AA" ) );
		assertEquals( BigDecimal.valueOf( 130 ), price( "AAA" ) );
		assertEquals( BigDecimal.valueOf( 180 ), price( "AAAA" ) );
		assertEquals( BigDecimal.valueOf( 230 ), price( "AAAAA" ) );
		assertEquals( BigDecimal.valueOf( 260 ), price( "AAAAAA" ) );
		
		assertEquals( BigDecimal.valueOf( 160 ), price( "AAAB" ) );
		assertEquals( BigDecimal.valueOf( 175 ), price( "AAABB" ) );
		assertEquals( BigDecimal.valueOf( 190 ), price( "AAABBD" ) );
		assertEquals( BigDecimal.valueOf( 190 ), price( "DABABA" ) );
		
	}
	
	@Test
	public void testIncremental()
	{	
		Checkout checkout = Checkout.valueOf( getPricingRules() );
		
		assertEquals( BigDecimal.valueOf( 0 ), checkout.getTotalPrice() );
		
		checkout.scan( "A" ); assertEquals( BigDecimal.valueOf( 50 ), checkout.getTotalPrice() );
		checkout.scan( "B" ); assertEquals( BigDecimal.valueOf( 80 ), checkout.getTotalPrice() );
		checkout.scan( "A" ); assertEquals( BigDecimal.valueOf( 130 ), checkout.getTotalPrice() );
		checkout.scan( "A" ); assertEquals( BigDecimal.valueOf( 160 ), checkout.getTotalPrice() );
		checkout.scan( "B" ); assertEquals( BigDecimal.valueOf( 175 ), checkout.getTotalPrice() );
		
	}
	
	private BigDecimal price( String products )
	{
		Checkout checkout = Checkout.valueOf( getPricingRules() );
		
		checkout.scan( products );
		
		return checkout.getTotalPrice();
	}
	
	private void setProductsPrices()
	{

		PriceService priceService = PriceService.getInstance();
				
		priceService.setPrice( "A", 50 );
		priceService.setPrice( "B", 30 );
		priceService.setPrice( "C", 20 );
		priceService.setPrice( "D", 15 );
	}
	

	private Map<String, PricingRuleCapable> getPricingRules()
	{	
		Map<String, PricingRuleCapable> productIdToRuleMapping = new HashMap<>();
		
		productIdToRuleMapping.put( "A", new AmountSpecialPriceRule( 3, 130 ) );
		productIdToRuleMapping.put( "B", new AmountSpecialPriceRule( 2, 45 ) );
		
		return productIdToRuleMapping;
	}

}
