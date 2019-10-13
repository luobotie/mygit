package com.yidu.mapper;

import java.util.Map;

public interface BookMapper {
	 public int getBookPriceByBookId(int bookId);
	 public void updeateBookStock(int bookId);
	 public void updateBalance(Map<String, Object> map);
	 public int getBookStock(int bookId);
	 public int getBalance(String username);
} 
