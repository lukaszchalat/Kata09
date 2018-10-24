package de.lukaszchalat.bspayone.service;

import java.math.BigDecimal;

import de.lukaszchalat.bspayone.pricingRule.PricingRuleCapable;
import de.lukaszchalat.bspayone.product.Scannable;

public class ComputationService 
{
	// we need PriceService instance to get price for the product
	private static PriceService priceService = PriceService.getInstance();
	
	// this method compute price for product taking into consideration its pricing rule if there is any
	public static BigDecimal computePriceForProduct( Scannable product, int amount, PricingRuleCapable pricingRule )
	{
		// get individual price for the product
		BigDecimal individualPrice = priceService.getPrice( product.getId() );
		
		// if there is any pricing rule for this product use just individual price
		if( pricingRule == null )
		{
			return individualPrice.multiply( BigDecimal.valueOf( amount ) );
		}
		else
		{
			// compute if pricing rule is in force
			int pricingRuleAmount = amount / pricingRule.getRequiredAmount();
			
			// if not compute with only with individual price
			if( pricingRuleAmount == 0 )
			{
				return individualPrice.multiply( BigDecimal.valueOf( amount ) );
			}
			else 
			{
				// if pricing rule is in force check how many products we have compute with individual price
				int individualPriceAmount = amount - ( pricingRuleAmount * pricingRule.getRequiredAmount() );
				
				BigDecimal pricingRulePrice    = pricingRule.getPrice().multiply( BigDecimal.valueOf( pricingRuleAmount ) );
				BigDecimal nonPricingRulePrice = individualPrice.multiply( BigDecimal.valueOf( individualPriceAmount ) );
				
				return pricingRulePrice.add( nonPricingRulePrice );
			}
		}
	}
}
