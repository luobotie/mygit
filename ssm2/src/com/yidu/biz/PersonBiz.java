package com.yidu.biz;

import org.springframework.stereotype.Repository;

@Repository
public interface PersonBiz {
   public void personBuyBook(String userName,int bookId);
}
