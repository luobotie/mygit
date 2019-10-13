package com.yidu.entity;

import java.util.List;

import org.springframework.stereotype.Component;
@Component
public class PageResult<T>{
	private   int code;//规定数据状态的字段名称
	private    String msg;//规定状态信息的字段名称
	private    int count;//规定数据总数的字段名称
	private   List<T> data; //规定数据列表的字段名称
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	public PageResult() {
		super();
	}
	public PageResult(int code, String msg, int count, List<T> data) {
		super();
		this.code = code;
		this.msg = msg;
		this.count = count;
		this.data = data;
	}
	
	

}
