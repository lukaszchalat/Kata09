package de.lukaszchalat.bspayone.checkout;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import de.lukaszchalat.bspayone.pricingRule.PricingRuleCapable;
import de.lukaszchalat.bspayone.product.Product;
import de.lukaszchalat.bspayone.product.Scannable;
import de.lukaszchalat.bspayone.service.ComputationService;
import de.lukaszchalat.bspayone.validation.Validator;

public class Checkout 
{
	private Map<String, PricingRuleCapable> productIdToRuleMapping = new HashMap<>();
	private Map<Scannable, Integer> productToAmountMapping         = new HashMap<>();
	private BigDecimal totalPrice                                  = BigDecimal.ZERO;
	
	private Checkout() {}
	
	/*
	 * here I have used static factory method to create new Checkout instance
	 * because it was also in Kata example but I could use also normal public constructor to did it
	 */
	public static Checkout valueOf( Map<String, PricingRuleCapable> productToRuleMapping )
	{
		Checkout checkout = new Checkout();
		
		checkout.setProductToRuleMapping( productToRuleMapping );
		
		return checkout;
	}

	public void setProductToRuleMapping(Map<String, PricingRuleCapable> productIdToRuleMapping) 
	{
		this.productIdToRuleMapping = productIdToRuleMapping;
	}
	
	// this method scan new product and actualize total price for all already scanned products
	public void scan( Scannable product )
	{
		// check if product has individual price
		if( ! Validator.hasIndividualPrice( product.getId() ) ) return;
		
		/*
		 *  if this product was already scanned, we increment its amount in map collection,
		 *  if not we put it in map with value 1
		 */
		productToAmountMapping.computeIfPresent( product, ( k, v ) -> v + 1 );
		productToAmountMapping.computeIfAbsent( product, v -> 1 );
		
		/*
		 * here I create an entry stream from map and compute price from each product taking into
		 * consideration its pricing rule, computation take place in ComputationService because it isn't
		 * role of Checkout object to compute it, its job is just to scan a product and to return total price
		 */
		totalPrice = productToAmountMapping.entrySet().stream()
				                                      .map( entry -> ComputationService.computePriceForProduct( entry.getKey(), entry.getValue(), productIdToRuleMapping.get( entry.getKey().getId() ) ) )
				                                      .reduce( BigDecimal.ZERO, BigDecimal::add );	
	}
	
	public void scan( String products )
	{
		List<Scannable> productsList = createProductList( products );
		
		productsList.forEach( product -> scan( product ) );
	}

	public BigDecimal getTotalPrice() 
	{
		return totalPrice;
	}
	
	private List<Scannable> createProductList( String productString )
	{
		return productString.chars() 
				            .mapToObj( product -> new Product( String.valueOf( (char) product ) ) )
				            .collect( Collectors.toList() );
	}
	
	
	

}
