package utils;

import java.util.HashMap;
import static com.app.banking.AcType.*;
import static java.time.LocalDate.parse;
import com.app.banking.BankAccount;

import custom_exceptions.AccountHandlingException;

public class CollectionUtils {
  //add a static method to return populated map of bank accounts
	public static HashMap<Integer, BankAccount> populateMap(){
		//create  empty map
		HashMap<Integer, BankAccount> map=new HashMap<Integer, BankAccount>();
		//acctNo(int),type(enum),balance,creationDate(localDate),customerName
//		System.out.println("added: putIfAbsent "+map.put(10, new BankAccount(10, SAVING,5000, parse("2010-04-23"), "Reema")));
//		System.out.println("added: putIfAbsent "+map.put(101, new BankAccount(101, CURRENT,3500, parse("2011-06-21"), "Sameer")));
//		System.out.println("added: putIfAbsent "+map.put(76, new BankAccount(76, FD,150000, parse("2009-04-01"), "Mohan")));
//		System.out.println("added: putIfAbsent "+map.put(56, new BankAccount(56, SAVING,7800, parse("2020-04-07"), "Meera")));
//		System.out.println("added: putIfAbsent "+map.put(10, new BankAccount(10, SAVING,9500, parse("2015-11-23"), "Riya")));
//		System.out.println(map);//{4 entries}
	//	put==> if key is same then replace old value with new value and return old reference(Reema details replaced by riya)
		
		
		System.out.println("added: putIfAbsent "+map.putIfAbsent(10, new BankAccount(10, SAVING,5000, parse("2010-04-23"), "Reema")));
		System.out.println("added: putIfAbsent "+map.putIfAbsent(101, new BankAccount(101, CURRENT,3500, parse("2011-06-21"), "Sameer")));
		System.out.println("added: putIfAbsent "+map.putIfAbsent(76, new BankAccount(76, FD,150000, parse("2009-04-01"), "Mohan")));
		System.out.println("added: putIfAbsent "+map.putIfAbsent(56, new BankAccount(56, SAVING,7800, parse("2020-04-07"), "Meera")));
		System.out.println("added: putIfAbsent "+map.putIfAbsent(10, new BankAccount(10, SAVING,9500, parse("2015-11-23"), "Riya")));
		System.out.println(map);//{4 entries}
		return map;
		
//		putifAbsent==> if key is same then return old value reference and avoid new value 
	}
	
	//add a static method to return a/c details or throw exception
	public static BankAccount findByAccountNo(int accNo,HashMap<Integer,BankAccount> map) throws AccountHandlingException
	{
		BankAccount a = map.get(accNo);
		if(a==null)
			throw new AccountHandlingException("Invalid account no!!!!!1");
		return a;
	}
}
