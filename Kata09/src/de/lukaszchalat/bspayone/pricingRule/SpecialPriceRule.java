package de.lukaszchalat.bspayone.pricingRule;

import java.math.BigDecimal;

public class SpecialPriceRule implements PricingRuleCapable
{
	private BigDecimal price;

	public SpecialPriceRule( int price ) 
	{
		this.price = BigDecimal.valueOf( price );
	}

	@Override
	public int getRequiredAmount()
	{
		return 1;
	}

	@Override
	public BigDecimal getPrice() 
	{
		return price;
	}

	@Override
	public String toString() 
	{
		return "SpecialPriceRule [price=" + price + "]";
	}

	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((price == null) ? 0 : price.hashCode());
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
		SpecialPriceRule other = (SpecialPriceRule) obj;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		return true;
	}	
}
