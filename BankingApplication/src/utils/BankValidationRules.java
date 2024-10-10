package utils;

import custom_exceptions.AccountOverdrawnException;

public class BankValidationRules {
	public static final double MIN_BALANCE;
	static {
		MIN_BALANCE=1000;
	}
//add a static method to validate balance
	public static double validateBalance(double balance) throws AccountOverdrawnException //managed checked exception usuing throws
	{
		if(balance<MIN_BALANCE)
			throw new AccountOverdrawnException("Insufficient funds");//checked exception
		return balance;
	}
}
