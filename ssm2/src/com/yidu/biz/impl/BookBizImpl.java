package com.yidu.biz.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yidu.biz.BookBiz;
import com.yidu.mapper.BookMapper;

@Service
public class BookBizImpl implements BookBiz{
	@Autowired
	private BookMapper bookMapper;

	@Override
	public int getBookPriceByBookId(int bookId) {
		
		return bookMapper.getBookPriceByBookId(bookId);
	}

	@Override
	public void updeateBookStock(int bookId) {
		bookMapper.updeateBookStock(bookId);
	}

	@Override
	public void updateBalance(Map<String, Object> map) {
		bookMapper.updateBalance(map);
	}

	@Override
	public int getBookStock(int bookId) {
		return bookMapper.getBookStock(bookId);
	}

	@Override
	public int getBalance(String username) {
		return bookMapper.getBalance(username);
	}
}
