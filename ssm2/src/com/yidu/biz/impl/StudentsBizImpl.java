package com.yidu.biz.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.istack.internal.logging.Logger;
import com.yidu.biz.StudentsBiz;
import com.yidu.entity.PageArg;
import com.yidu.entity.Students;
import com.yidu.mapper.StudentsMapper;
@Service
public class StudentsBizImpl implements StudentsBiz {
	@Autowired
    private StudentsMapper studentsMapper;
	@Override
	public List<Students> selectAllStu() {
		return studentsMapper.selectAllStu(); 
	}
	@Override
	public int getCount() {
		return studentsMapper.getCount();
	}
	@Override
	public List<Students> getData(PageArg pArg) {
		//原始模式写日志的方式
		Logger logger=Logger.getLogger(StudentsBizImpl.class);
		logger.info("正在查询所有学生的信息");
				
		return studentsMapper.getData(pArg);
	}
	@Override
	public int  addStudents(Students students) {
		return studentsMapper.addStudents(students);
	}
	@Override
	public int  deleteStudents(int stuId) {
		return studentsMapper.deleteStudents(stuId);
	}
	@Override
	public int updateStudents(Students students) {
		return studentsMapper.updateStudents(students);
	}
	@Override
	public int deleteBatch(List<Integer> list) {
		return studentsMapper.deleteBatch(list);
	}
	@Override
	public Students loginStu(Map<String, Object> map) {
		return studentsMapper.loginStu(map);
	}
	@Override
	public List<Students> moSelectName(Map<String, Object> map) {
		return studentsMapper.moSelectName(map);
	}
	@Override
	public int getMoCount(String moName) {
		return studentsMapper.getMoCount(moName);
	}

}
