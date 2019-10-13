package com.yidu.entity;

public class UserAccount {
	private String userName;
	private boolean balance;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public boolean isBalance() {
		return balance;
	}
	public void setBalance(boolean balance) {
		this.balance = balance;
	}
	public UserAccount(String userName, boolean balance) {
		super();
		this.userName = userName;
		this.balance = balance;
	}
	public UserAccount() {
		super();
	}
	
}
