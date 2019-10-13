<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="layui/css/layui.css">
<script src="layui/layui.js"></script>
<title>Insert title here</title>
</head>
<body>
		<div class="layui-inline">
      		<label class="layui-form-label">姓名：</label>
      		<div class="layui-input-inline">
        	<input type="text" name="moName" id="moName"   autocomplete="off" class="layui-input">
           </div>
           <button type="button" class="layui-btn layui-btn-normal layui-btn-radius  layui-icon layui-icon-search"  id="selectName">查询</button>
           
        </div>
	<!-- 数据表格 -->
	<div style="width: 100%;">
		<table class="layui-hide" id="userTable" lay-filter="userTable"></table>
	</div>
	<!-- 表格toolbar -->
	<div style="display: none;" id="userToolBar">
		<div >
   			<button type="button" class="layui-btn layui-btn-normal layui-btn-radius  layui-icon layui-icon-add-circle-fine"  lay-event="add">增加一条数据</button>
    		<button type="button" class="layui-btn layui-btn-danger layui-btn-radius  layui-icon layui-icon-delete"  lay-event="delete">批量删除</button>
		</div>
	</div>
	<!-- 表格行内toolbar -->
	<div style="display: none;" id="barDemo">
		<a class="layui-btn layui-btn-xs  layui-btn-radius layui-icon layui-icon-edit"
			lay-event="edit">编辑</a> <a
			class="layui-btn layui-btn-danger   layui-btn-radius layui-btn-xs layui-icon layui-icon-delete"
			lay-event="del">删除</a>
	</div>
	<!-- 弹出层div(新增和修改) -->
	<div id="mydiv"  style="display: none">
		<form  class="layui-form" lay-filter="dataform" id="dataform" method="post">
			<input type="hidden" name="stuId">
			<div class="layui-form-item" >
				<label class="layui-form-label">学生姓名：</label>
				<div class="layui-input-inline">
					<input type="text" name="stuName" required lay-verify="required"
						autocomplete="off" placeholder="学生姓名" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">学生年龄:</label>
				<div class="layui-input-inline">
					<input type="number"  min="1" max="120" name="stuAge" required lay-verify="required"
						autocomplete="off" placeholder="学生年龄" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">学生地址：</label>
				<div class="layui-input-inline">
					<input type="text" name="stuAddress" required lay-verify="required"
						autocomplete="off" placeholder="学生地址" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button type="submit" class="layui-btn" lay-submit=""
						lay-filter="dosubmit">立即提交</button>
					<button type="reset" class="layui-btn layui-btn-primary">重置</button>
				</div>
			</div>
		</form>
	</div>
	<script type="text/javascript">
		layui.use([ "element", "jquery", "layer", "form", "table" ],
				function() {
					var $ = layui.jquery;
					var element = layui.element;
					var layer = layui.layer;
					var form = layui.form;
					var table = layui.table;
                    var url;
                    var tanIndex;
					//数据表格渲染
					var tableIns = table.render({
						elem : '#userTable' //代表渲染的目标对象
						,
						url : 'moSelectName.do?moName='+$("#moName").val() //数据接口	
						,
						title : '学生数据表' //数据导出的标题
						,
						toolbar : "#userToolBar" //表格工具条
						//,height: 'full-100'////高度最大化减去差值
						,
						cellMinWidth : 100 //设置列的最小默认宽度
						/* ,limit:20    //设置每页显示的条数 默认是10
						,limits:[20,40,60]   */
						,
						text : {
							none : '暂无相关数据' //默认：无数据。注：该属性为 layui 2.2.5 开始新增
						}
						//defaultToolbar : [ 'filter', 'exports' ]
						// ,totalRow:true//开启合并行
						,
						page : true //是否启用分页
						,
						cols : [ [//列数据
						{
							type : 'checkbox',
							fixed : 'left'
						} //启动复选框
						, {
							type : 'numbers'
						}, {
							field : 'stuId',
							title : '学生编号',
							sort : true
						}, {
							field : 'stuName',
							title : '学生姓名',
							edit : true
						}, {
							field : 'stuAge',
							title : '学生年龄',
							templet : function(res) {
								return '<em>' + res.stuAge + '</em>';
							}
						}, {
							field : 'stuAddress',
							title : '学生地址'
						}, {
							fixed : 'right',
							title : '操作',
							toolbar : '#barDemo',
							width : 150,
							align : 'center'
						} ] ]
					});
					
					
					 //监听头部工具栏事件
				      table.on('toolbar(userTable)', function(obj){
						  var checkStatus = table.checkStatus(obj.config.id);
						  switch(obj.event){
						    case 'add':
						    	openAdd();
						    break;
						    case 'delete':
						    	var checkStatus = table.checkStatus('userTable'); //idTest 即为基础参数 id 对应的值
						    	console.log(checkStatus.data) //获取选中行的数据
						    	console.log(checkStatus.data.length) //获取选中行数量，可作为是否有选中行的条件
						    	console.log(checkStatus.isAll ) //表格是否全选
						    	var id="";
						    	if(checkStatus.data.length>0){
						    		for(var i=0;i<checkStatus.data.length;i++){
						    			if(i==0){
						    				id=checkStatus.data[i].stuId+id;
						    			}else{
						    				id=checkStatus.data[i].stuId+","+id;
						    			}
						    			
						    		}
						    		 $.post("deleteBatch.do","stuId="+id,function(back){
										//刷新数据表格
										tableIns.reload();
									 }); 
						    	}else{
						    		layer.msg("没有选择数据",{  icon: 2,
						    			  time: 1000});
						    	}
						    break;
					  };
				    });	
					 //打开新增页面
					 function openAdd(){
						 tanIndex= layer.open({
			   				 type:1,  //设置类型 默认为0 1 页面层 2 iframe层
			   				 title:'新增一条学生信息',  //标题
			   				 content:$("#mydiv"),//内容  type=0 的内容
			   				 skin:'layui-layer-molv',//skin - 样式类名
			   				 area:['340px'],  //area - 宽高
			   				 //offset:'lt',//offset - 坐标 默认：垂直水平居中
			   				 btnAlign:'c',//按钮排列
			   				 closeBtn:2,  //设置关闭按钮的样式 默认是1  0是没有 2
			   			     shade:[0.8, '#000'],//shade - 遮罩
			   			     shadeClose:true,//点击遮罩 是否关闭弹层
			   			     anim: 4,//设置动画
			   				 maxmin:true,//该参数值对type:1和type:2有效。默认不显示最大小化按钮。需要显示配置maxmin: true即可
			   				 success:function(index){
			   					 url="addstu.do";
								  //清空表单数据
								  $("#dataform")[0].reset();
								  
							  }		  
			   		});
					 }
					 //打开修改页面
					  function  openUpdate(data){
						  tanIndex=layer.open({
				   				 type:1,  //设置类型 默认为0 1 页面层 2 iframe层
				   				 title:'修改学生信息',  //标题
				   				 content:$("#mydiv"),//内容  type=0 的内容
				   				 skin:'layui-layer-molv',//skin - 样式类名
				   				 area:['340px'],  //area - 宽高
				   				 //offset:'lt',//offset - 坐标 默认：垂直水平居中
				   				 btnAlign:'c',//按钮排列
				   				 closeBtn:2,  //设置关闭按钮的样式 默认是1  0是没有 2
				   			     shade:[0.8, '#000'],//shade - 遮罩
				   			     shadeClose:true,//点击遮罩 是否关闭弹层
				   			     anim: 4,//设置动画
				   				 maxmin:true,//该参数值对type:1和type:2有效。默认不显示最大小化按钮。需要显示配置maxmin: true即可
				   				 success:function(index){
									  form.val("dataform",data);
									  url="updateStudents.do";
								  }
						  });	
						 
					   }	
					 //提交数据
					 form.on("submit(dosubmit)",function(obj){
						 //序列化表单数据
						 var params=$("#dataform").serialize();
					/* 	 $.ajax({
							type:"post",
							url:url,
							data:params,
							success:function(data){
								//刷新数据表格
								tableIns.reload();
								//关闭弹出层
								layer.close(tanIndex);
								
							}
						 }); */
						  $.post(url,params,function(data){
							//刷新数据表格
							tableIns.reload();
							//关闭弹出层
							layer.close(tanIndex);
							
							
						 }); 
					 })
					 $("#selectName").click(function(){
						 table.render({
								elem : '#userTable' //代表渲染的目标对象
								,
								url : 'moSelectName.do?moName='+$("#moName").val() //数据接口	
								,
								title : '学生数据表' //数据导出的标题
								,
								toolbar : "#userToolBar" //表格工具条
								//,height: 'full-100'////高度最大化减去差值
								,
								cellMinWidth : 100 //设置列的最小默认宽度
								/* ,limit:20    //设置每页显示的条数 默认是10
								,limits:[20,40,60]   */
								,
								text : {
									none : '暂无相关数据' //默认：无数据。注：该属性为 layui 2.2.5 开始新增
								}
								//defaultToolbar : [ 'filter', 'exports' ]
								// ,totalRow:true//开启合并行
								,
								page : true //是否启用分页
								,
								cols : [ [//列数据
								{
									type : 'checkbox',
									fixed : 'left'
								} //启动复选框
								, {
									type : 'numbers'
								}, {
									field : 'stuId',
									title : '学生编号',
									sort : true
								}, {
									field : 'stuName',
									title : '学生姓名',
									edit : true
								}, {
									field : 'stuAge',
									title : '学生年龄',
									templet : function(res) {
										return '<em>' + res.stuAge + '</em>';
									}
								}, {
									field : 'stuAddress',
									title : '学生地址'
								}, {
									fixed : 'right',
									title : '操作',
									toolbar : '#barDemo',
									width : 150,
									align : 'center'
								} ] ]
							});
						 
					 })
				  	//监听行工具条事件
				      table.on('tool(userTable)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
				    	  var data = obj.data; //获得当前行数据
				    	  var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
				    	  var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）
				    	 if(layEvent === 'del'){ //删除
				    	    layer.confirm('真的删除行么', function(index){
				    	   	 $.post("deleteStudents.do","stuId="+data.stuId,function(back){
									//刷新数据表格
									tableIns.reload();
								 });
				    	      layer.close(index);
				    	    });
				    	  } else if(layEvent === 'edit'){ //编辑
				    	      openUpdate(data);
				    	  } 
				    	});

				})
	</script>
</body>
</html>