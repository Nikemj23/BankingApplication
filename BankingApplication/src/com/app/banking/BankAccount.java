package com.app.banking;

import java.time.LocalDate;

import custom_exceptions.AccountOverdrawnException;

import static utils.BankValidationRules.validateBalance;
//acctNo(int),type(enum),balance,creationDate(localDate),customerName
public class BankAccount {
private int acctNo;
private AcType type;
private double balance;
private LocalDate creationDate; 
private String customerName;
public BankAccount(int acctNo, AcType type, double balance, LocalDate creationDate, String customerName) {
	super();
	this.acctNo = acctNo;
	this.type = type;
	this.balance = balance;
	this.creationDate = creationDate;
	this.customerName = customerName;
}
@Override
public String toString() {
	return "BankAccount [acctNo=" + acctNo + ", type=" + type + ", balance=" + balance + ", creationDate="
			+ creationDate + ", customerName=" + customerName + "]";
}

//B.L deposit funds
public void deposit(double amount)
{
	balance+=amount;
}
//bl withdraw funds
public void withdraw(double amount) throws AccountOverdrawnException
{
	validateBalance(balance-amount);
	balance-=amount;
}
 public void transferFunds(BankAccount dest, double transferAmount) throws AccountOverdrawnException
 {
	 this.withdraw(transferAmount);
	 dest.deposit(transferAmount);
 }
 //getetre for account type
public AcType getType() {
	return type;
}

public LocalDate getCreationDate() {
	return creationDate;
}
public double getBalance() {
	return balance;
}
 
}
