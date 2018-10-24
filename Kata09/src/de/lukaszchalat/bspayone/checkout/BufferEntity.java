package de.lukaszchalat.bspayone.checkout;

import java.math.BigDecimal;

public class BufferEntity 
{
	private int productAmount;
	private BigDecimal price;
	
	public BufferEntity(int productAmount, BigDecimal price) 
	{
		super();
		this.productAmount = productAmount;
		this.price = price;
	}

	public int getProductAmount() 
	{
		return productAmount;
	}

	public void setProductAmount(int productAmount) 
	{
		this.productAmount = productAmount;
	}

	public BigDecimal getPrice() 
	{
		return price;
	}

	public void setPrice(BigDecimal price) 
	{
		this.price = price;
	}
	
	public void incrementProductAmount()
	{
		this.productAmount++;
	}

	@Override
	public String toString() 
	{
		return "BufferEntity [productAmount=" + productAmount + ", price=" + price + "]";
	}

	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + productAmount;
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
		BufferEntity other = (BufferEntity) obj;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (productAmount != other.productAmount)
			return false;
		return true;
	}
	
}
