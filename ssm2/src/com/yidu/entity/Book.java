package com.yidu.entity;


public class Book {
	private Integer bookId;
	private String bookName;
	private boolean bookPrice;
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public boolean isBookPrice() {
		return bookPrice;
	}
	public void setBookPrice(boolean bookPrice) {
		this.bookPrice = bookPrice;
	}
	public Book(Integer bookId, String bookName, boolean bookPrice) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.bookPrice = bookPrice;
	}
	public Book() {
		super();
	}
	
}
