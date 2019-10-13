package com.yidu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yidu.biz.StudentsBiz;
import com.yidu.entity.PageArg;
import com.yidu.entity.PageResult;
import com.yidu.entity.Students;
@Controller
public class StudentsController {
	@Autowired
	private StudentsBiz studentsBiz;
	@RequestMapping("/showStu.do")
	public  @ResponseBody PageResult<Students> showStu(PageArg pArg){
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		PageResult<Students> pageResult=context.getBean("pageResult",PageResult.class);
		List<Students> list=studentsBiz.getData(pArg);
		/*	Map<String, Object> tableData = new HashMap<String, Object>();
        //这是layui要求返回的json数据格式
       tableData.put("code", "0");
       tableData.put("msg", "");
        //将全部数据的条数作为count传给前台（一共多少条）
        tableData.put("count","1000");
        //将分页后的数据返回（每页要显示的数据）
        tableData.put("data", list);  */
		int count=studentsBiz.getCount();
		pageResult.setCode(0);
		pageResult.setMsg("");
		pageResult.setCount(count);
		pageResult.setData(list);
		return pageResult;
	}
	@RequestMapping("/moSelectName.do")
	public  @ResponseBody PageResult<Students> moSelectName(PageArg pArg,String moName){
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		PageResult<Students> pageResult=context.getBean("pageResult",PageResult.class);
		Map<String, Object> map = new HashMap<String, Object>();
	    map.put("page", (pArg.getPage()-1)*pArg.getLimit());		
	    map.put("limit", pArg.getLimit());
	    map.put("moName", moName);
	    List<Students> list=studentsBiz.moSelectName(map);
		Integer count=studentsBiz.getMoCount(moName);
		System.err.println("geshu:"+count);
		pageResult.setCode(0);
		pageResult.setMsg("");
		pageResult.setCount(count);
		pageResult.setData(list);
		return pageResult;
	}
      @RequestMapping("/addstu.do")
	 public  @ResponseBody int addStudents(String stuName,int stuAge,String stuAddress){
    	Students students=new Students(stuName, stuAge, stuAddress);
		 int row = studentsBiz.addStudents(students);
		 return row;
	 }
      @RequestMapping("/deleteStudents.do")
      public  @ResponseBody int deleteStudents(String stuId){
    	  int id=Integer.parseInt(stuId);
    	  int row = studentsBiz.deleteStudents(id);
    	  return row;
      }
      @RequestMapping("/updateStudents.do")
  	public  @ResponseBody int updateStudents(Students students){
    	  int row = studentsBiz.updateStudents(students);
    	  return row;
  	}
      @RequestMapping("/deleteBatch.do")
      public @ResponseBody int deleteBatch(String stuId){
    	    String[] split = stuId.split(",");
    	    List<Integer> list=new ArrayList<Integer>();
    	    for(String string:split){
    	    	  int id=Integer.parseInt(string);
    	    	 list.add(id);
    	    }
    	    studentsBiz.deleteBatch(list);
    	  return 1;
      }
      @RequestMapping(value="loginStu.do")
      public  Students loginStu(){
    	  Map<String, Object> map=new HashMap<String, Object>();
    	  map.put("stuName", "luobo");
    	  map.put("stuId", 80);
    	  Students loginStu = studentsBiz.loginStu(map);
    	  if(loginStu!=null){
    		  System.err.println("登录成功！");
    	  }else{
    		  System.err.println("登录失败！");
    	  }
    	  return loginStu;
      }

}
