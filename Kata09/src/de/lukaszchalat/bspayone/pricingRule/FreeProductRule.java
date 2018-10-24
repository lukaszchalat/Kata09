package de.lukaszchalat.bspayone.pricingRule;

import java.math.BigDecimal;

public class FreeProductRule implements PricingRuleCapable 
{
	private int requiredAmount;
	private int freeAmount;
	private BigDecimal price;
	
	public FreeProductRule( int requiredAmount, int freeAmount, int price ) 
	{
		this.requiredAmount = requiredAmount;
		this.freeAmount     = freeAmount;
		this.price          = BigDecimal.valueOf( price );
	}
	
	public FreeProductRule( int requiredAmount, int price ) 
	{
		this.requiredAmount = requiredAmount;
		this.freeAmount     = 1;
		this.price          = BigDecimal.valueOf( price );
	}

	@Override
	public int getRequiredAmount() 
	{
		return this.requiredAmount + this.freeAmount;
	}

	@Override
	public BigDecimal getPrice() 
	{
		return this.price;
	}

	@Override
	public String toString() 
	{
		return "FreeProductRule [requiredAmount=" + requiredAmount + ", freeAmount=" + freeAmount + ", price="
				+ price + "]";
	}

	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + freeAmount;
		result = prime * result + requiredAmount;
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
		FreeProductRule other = (FreeProductRule) obj;
		if (freeAmount != other.freeAmount)
			return false;
		if (requiredAmount != other.requiredAmount)
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		return true;
	}
	
}
