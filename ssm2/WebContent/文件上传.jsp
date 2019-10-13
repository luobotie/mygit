<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
     <form action="fileUpLoad.do"  method="post" enctype="multipart/form-data">
          文件：<input type="file" name="myFile"></input><br/>
          文件1：<input type="file" name="myFile"></input><br/>
          文件2：<input type="file" name="myFile"></input>
          <input type="submit">
          <a href="fileDownLoad.do?fileName=第一章 初识SpringMVC框架.docx">下载文件</a>
     </form>
</body>
</html>