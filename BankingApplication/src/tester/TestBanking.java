package tester;
import static utils.CollectionUtils.populateMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeMap;

import com.app.banking.AcType;
import com.app.banking.BankAccount;

import custom_exceptions.AccountHandlingException;
import custom_exceptions.DupAccountException;
import static utils.BankValidationRules.validateBalance;
import static java.time.LocalDate.parse;
import static utils.CollectionUtils.findByAccountNo;
public class TestBanking {

	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in)) {
			HashMap<Integer, BankAccount> accts = populateMap();
			boolean exit=false;
			while(!exit) {
				System.out.println("Options 1.Create A/C 2. Diplay all accounts 3.Get A/C summary 4.Funds transfer 5.Close Account "
						+ "6. Display account by type 7.Display accounts by balance 8.Remove loan accts "
						+ "9. print accounts sorted as per asc accts no 100.Exit");
				System.out.println("Enter your option");
				try {
					switch (sc.nextInt()) {
					case 1://Create a/c
						 System.out.println("Enter account no");
						 int acctNo=sc.nextInt();
						 if(accts.containsKey(acctNo))//int-->integer(autoboxing)--> object(upcasting)
							 //dup acct no
							 throw new DupAccountException("A/C NO already exists!!!!");
						 //new acct no
						 System.out.println("Enter acct type, balance, creationDate(yr-mon-day,customer name)");
						 accts.put(acctNo, new BankAccount(acctNo, AcType.valueOf(sc.next().toUpperCase()), validateBalance(sc.nextDouble()), parse(sc.next()),sc.next()));
						System.out.println("A/C created");
						 break;
					case 2:
						System.out.println("Al a/c details");
						for(BankAccount a:accts.values())  //since accts.values() is a collection, 
							//and collection is iterable therefore we can apply for each loop
							System.out.println(a);
						break;
					case 3:
						System.out.println("Enter A/c no ");
						System.out.println("Bank A/C summary "+findByAccountNo(sc.nextInt(),accts));
						break;
						
					case 4://funds transfer
						System.out.println("Enter source A/c no, destination A/c no, amount to be transferred");
						BankAccount src = findByAccountNo(sc.nextInt(),accts);
						//src acct exists
						BankAccount destn = findByAccountNo(sc.nextInt(),accts);
						//destn acct exists
						src.transferFunds(destn,sc.nextDouble() );
						//==>transfer success
						System.out.println("funds transfer succesfull");
						break;
						
					case 5:// close a/c
						System.out.println("Enter a/c no");
						BankAccount a = accts.remove(sc.nextInt());
						if(a==null)
							throw new AccountHandlingException("A/c closing failed:invalid a/c no");
						System.out.println("closed a/c "+a);
						break;
						
					case 6://Display all account details of specific account type
						//searching criteria is value based, so had to convert map--> collection view.
						System.out.println("Enter a/c type");
						AcType acctType = AcType.valueOf(sc.next().toUpperCase());
						System.out.println("Account details for account type"+acctType);
						for(BankAccount a1: accts.values())   //since accts.values() is a collection, 
							//and collection is iterable therefore we can apply for each loop
							if(a1.getType()==acctType)
								System.out.println(a1);
						break;
						
					case 7://search accts by balance>specific balance,display thei details.
						System.out.println("Enter balance ");
					double bal=sc.nextDouble();
					//searching criterion is value based(balance),so have to convert map--> collection view
					for(BankAccount a1:accts.values())
						if(a1.getBalance()>bal)
							System.out.println(a1);
					break;
					
					case 8://Remove all loan accounts.
						Collection<BankAccount> acctCollection = accts.values();
//						for(BankAccount a1: acctCollection)
//							if(a1.getType()==AcType.LOAN)
//								acctCollection.remove(a1);
						
						
						//attach explicit iterator
						Iterator<BankAccount> itr = acctCollection.iterator();
						while(itr.hasNext()) {
							if(itr.next().getType()==AcType.LOAN)
								itr.remove();
						}
						
						break;
						
					case 9://print accts sorted as per ascending account no.
						//since sorting criterion is key based(acct no):can be done using treee map
						//ctor : TreeMap(Map<?extends K,? extends v> map)
						//TreeMap<Integer, BankAccount> sortedAccts=new TreeMap<>(accts);
						//jvm invokes for sorting :Integer's compareTo
						TreeMap<Integer, BankAccount> sortedAccts=new TreeMap<>();
						// to populate the treemap from hashmap:putAll
						sortedAccts.putAll(accts);
						System.out.println("sorted accts as per ascending acct no");
						for(BankAccount a1:sortedAccts.values())
							System.out.println(a1);
						break;
						
					case 10://print a/cs sorted as per desc acct no 
						//key based sorting but custom ordering
						//TreeMap(Comparator)<? super k>comp)
						TreeMap<Integer, BankAccount> sortedAccts2=new TreeMap<>(new Comparator<Integer>() {

							@Override
							public int compare(Integer o1, Integer o2) {
								return o2.compareTo(o1);
								
							}
						});
						System.out.println(sortedAccts2);//{}
						sortedAccts2.putAll(accts);
						System.out.println("sorted accts as per desc accts no");
						for(BankAccount a1: sortedAccts2.values())
							System.out.println(a1);
						break;
						
					case 11:// sort account details as per acct creation date
						//sorting criteria: creationDate:value based criterion==>Must convert it in collection view
						Collection<BankAccount> coll = accts.values();
						//coll : sorted or not sorted? un sorted
						//coll-->ArrayList --Collections.sort(list,comp)
						ArrayList<BankAccount> list=new ArrayList<BankAccount>(coll);
						//AL: sorted or unsorted: un sorted
						//Collections.sort(List<T> list, Comparator comp)
						Collections.sort(list, new Comparator<BankAccount>() {

							@Override
							public int compare(BankAccount o1, BankAccount o2) {
								//date based sorting
								return o1.getCreationDate().compareTo(o2.getCreationDate());
								
							}
						});
						System.out.println("Accts as per creation date");
						for(BankAccount a1: list)
							System.out.println(a1);
						break;
					case 100:
						exit=true;
						break;
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				//read off pending inputs from sacnner
				sc.nextLine();
			}
		}
		

	}

}
