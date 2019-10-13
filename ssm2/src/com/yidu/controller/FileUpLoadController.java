package com.yidu.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUpLoadController {
    /**
     * 文件上传
     * @param myFile 文件对象
     * @return
     * @throws IOException 
     * @throws IllegalStateException 
     */
	@RequestMapping("/fileUpLoad.do")
	public String fileUpLoad(@RequestParam MultipartFile[] myFile) throws IllegalStateException, IOException{
    	//得到上传文件的：原始文件名
		//System.err.println(myFile.getOriginalFilename());
		//服务器路劲
		String filePath="D:\\fileUpLoad\\";
		if(myFile.length>0){
			for( MultipartFile mFile:myFile){
				Date date=new Date();
				SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMddhhmmssSS");
				String format = simpleDateFormat.format(date);
				//改名字
				String name=mFile.getOriginalFilename();
				//String newFile1=UUID.randomUUID()+name.substring(name.lastIndexOf("."));
				//File newFile=new File(filePath+newFile1);
				//传输数据
				File newFile=new File(filePath+format+name.substring(name.lastIndexOf(".")));
				mFile.transferTo(newFile);
			}
		}
		return "redirect:index.jsp";
		
    }
	 @RequestMapping("fileDownLoad.do")
	public String fileDownLoad(String fileName,HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("ISO-8859-1");
		//响应回类型
		response.setContentType("multipart/form-data");
		//设置响应头 attachment:附件
		String fileName1=new String(fileName.getBytes("UTF-8"),"ISO-8859-1");
		response.setHeader("Content-Disposition","attachment;fileName="+fileName1);
		//下载文件的路径
		String filePath="D:\\fileUpLoad\\";
		//根据路径：得到文件对象
		File file=new File(filePath+fileName);
		//字节流的输入流  (输入和输出流 是站在程序的角度来看得   硬盘往程序里写入数据是输入流)
		FileInputStream input=new FileInputStream(file);
		byte bytes[]=new byte[(int) file.length()];
		//读取硬盘上的文件：一次性读取整个文件
		input.read(bytes);
		//把读取的文件：响应给客户端
		ServletOutputStream out = response.getOutputStream();
		//把读取的字节数组：响应给客服端
		out.write(bytes);
		out.close();
		input.close();
		return null;
	}
}
