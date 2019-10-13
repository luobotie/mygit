package com.yidu.mapper;

import java.util.List;
import java.util.Map;

import com.yidu.entity.PageArg;
import com.yidu.entity.Students;

public interface StudentsMapper {
	 public List<Students> selectAllStu();
	 public int getCount();
	 public List<Students> getData(PageArg pArg);
	 public int  addStudents(Students students);
	 public int  deleteStudents(int stuId);
	 public int  updateStudents(Students students);
	 public int deleteBatch(List<Integer> list);
	 public Students loginStu(Map<String, Object> map);
	 public List<Students> moSelectName(Map<String, Object> map);
	 public int getMoCount(String moName);
	 
} 
