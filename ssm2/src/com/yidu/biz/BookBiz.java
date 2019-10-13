package com.yidu.biz;

import java.util.Map;

import org.springframework.stereotype.Repository;
@Repository
public interface BookBiz {
	 public int getBookPriceByBookId(int bookId);
	 public void updeateBookStock(int bookId);
	 public void updateBalance(Map<String, Object> map);
	 public int getBookStock(int bookId);
	 public int getBalance(String username);

}
