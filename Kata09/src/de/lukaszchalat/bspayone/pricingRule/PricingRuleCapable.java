package de.lukaszchalat.bspayone.pricingRule;

import java.math.BigDecimal;

// every special offer have to know required amount of product and its special price
public interface PricingRuleCapable 
{
	public int getRequiredAmount();
	
	public BigDecimal getPrice();
}
