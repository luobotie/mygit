package com.yidu.entity;


public class BookStock {
	private Integer bookId;			//书的编号
	private Integer bookStock;    	//书的库存
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public Integer getBookStock() {
		return bookStock;
	}
	public void setBookStock(Integer bookStock) {
		this.bookStock = bookStock;
	}
	public BookStock(Integer bookId, Integer bookStock) {
		super();
		this.bookId = bookId;
		this.bookStock = bookStock;
	}
	public BookStock() {
		super();
	}
	
}
