package de.lukaszchalat.bspayone.pricingRule;

import java.math.BigDecimal;

public class AmountSpecialPriceRule implements PricingRuleCapable
{
	private int requiredAmount;
	private BigDecimal price;
	
	public AmountSpecialPriceRule( int requiredAmount, int price )
	{
		this.requiredAmount = requiredAmount;
		this.price   = BigDecimal.valueOf( price );
	}

	public int getRequiredAmount() 
	{
		return requiredAmount;
	}

	public BigDecimal getPrice() 
	{
		return price;
	}
	
	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + price.intValue();
		result = prime * result + requiredAmount;
		return result;
	}

	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AmountSpecialPriceRule other = (AmountSpecialPriceRule) obj;
		if (price != other.price)
			return false;
		if (requiredAmount != other.requiredAmount)
			return false;
		return true;
	}

	@Override
	public String toString() 
	{
		return "PricingRule [productAmount=" + requiredAmount + ", price=" + price + "]";
	}
	
	
	
	
}
