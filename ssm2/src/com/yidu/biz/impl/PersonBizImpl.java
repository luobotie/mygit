package com.yidu.biz.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.yidu.biz.BookBiz;
import com.yidu.biz.PersonBiz;

@Service
public class PersonBizImpl implements PersonBiz{
    @Autowired   
	private BookBiz bookBiz;
	@Override
	public void personBuyBook(String userName, int bookId) {
          	int price=	bookBiz.getBookPriceByBookId(bookId);
            int bookStock = bookBiz.getBookStock(bookId);
          	if(bookStock==0){
                throw new RuntimeException("书本库存不足！");
          	}
          	bookBiz.updeateBookStock(bookId);
          	
          	Map<String, Object> map=new HashMap<String, Object>();
          	map.put("username", userName);
          	map.put("price", price);
          	int balance = bookBiz.getBalance(userName);
          	if(balance<price){
          		throw new RuntimeException("钱不够！");
          	}
          	bookBiz.updateBalance(map);
	}
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		PersonBiz personBiz = context.getBean(PersonBiz.class);//jdk 的动态代理是基于接口的
		personBiz.personBuyBook("zs", 1001);
	}
   
}
