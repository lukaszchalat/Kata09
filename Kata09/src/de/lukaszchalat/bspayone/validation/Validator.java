package de.lukaszchalat.bspayone.validation;

import de.lukaszchalat.bspayone.service.PriceService;

public class Validator 
{
	public static boolean hasIndividualPrice( String id )
	{
		if( PriceService.getInstance().getPrice( id ) != null )
		{
			return true;
		}
		else
		{
			System.out.println( String.format( ValidationMessages.ERROR_NO_INDIVIDUAL_PRICE, id ) );
			
			return false;
		}
	}
}
