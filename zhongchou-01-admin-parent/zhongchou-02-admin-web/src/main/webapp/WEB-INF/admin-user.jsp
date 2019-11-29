<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ch-CN">
<head>
<%@ include file="/WEB-INF/include-head.jsp"%>
<link type="stylesheet" href="css/pagenation.css">
<script type="text/javascript" src="jquery/jquery.pagination.js"></script>
<script type="text/javascript">
$(function(){
		var total=${pageInfo.total};
		 $('#Pagination').pagination(total,{
             num_edge_entries: 3, //边缘页数
             num_display_entries: 5, //主体页数
             items_per_page:${requestScope.pageInfo.pageSize}, //每页显示条目
             current_page: ${requestScope.pageInfo.pageNum-1},//当前页
             prev_text: "上一页",
             next_text: "下一页",
             callback:function(pageIndex, jQuery){
            	// 给pageIndex+1得到pageNum
         		var pageNum = pageIndex+1;
         		// 跳转页面
         		// ${param.keyword}表示从请求参数获取keyword的值
         		window.location.href = "admin/user.html?pageNum="+pageNum+"&keyword=${param.keyword}";
         		// 取消超链接默认行为
         		return false;
             } 
		});
		 //设置全选和全不选
		$("#itemsBox").click(function(){
			// 获取当前box自己的状态
			var boxStatus = this.checked;
			// 设置具体多选框是否勾选
			// $(".itemBox").attr("checked",boxStatus);必须初始化，该标签用于自定义的的属性
			$(".itemBox").prop("checked",boxStatus);
		});
		$(".itemBox").click(function(){
			// 获取当前页面上被勾选的itemBox的数量
			 var checkedBoxCount=$(".itemBox:checked").length;
			// 获取当前页面上所有itemBox的数量
			 var totalBoxCount = $(".itemBox").length;
			 
			// 比较二者是否相等
			var compareResult = (checkedBoxCount == totalBoxCount);
			// 用比较的结果设置itsemsBox
			$("#itemsBox").prop("checked",compareResult);
		});
		//批量删除
		$("#removeBtn").click(function(){
			var ids=new Array();
			$(".itemBox:checked").each(function(){
				ids.push(this.id);
			})
			if(ids.length==0){
				layer.msg("请至少选择一个条目进行删除！");
				return;
			}
			// 将adminIdArr转换为JSON字符串
		
			//执行删除函数
			removeUser(ids);
		});
		//单条删除
		$(".removeBtn01").click(function(){
			var id=[this.id];
			alert(id)
			removeUser(id);
		})
	})
	function removeUser(ids){
	var requestBody = JSON.stringify(ids);
	$.ajax({
		"type":"post",
		"url":"admin/removeUser.json",
		"data":requestBody,
		"dataType":"json",
		"contentType":"application/json",//默认值:application/x-www-form-urlencoded  
		"success":function(data){
			var result = data.result;
			if(result == "FAILED") {
				layer.msg(data.message);
			}
			
			if(result == "SUCCESS") {
				window.location.href = "admin/user.html?pageNum=${param.pageNum}&keyword=${param.keyword}";
			}
		},
		error:function(data){
			layer.msg("服务器端处理请求失败，响应状态码是："+data.status+"，说明信息是："+data.statusText);
		}
	});
}
</script>
</head>
<body>

	<%@ include file="/WEB-INF/include-navbar.jsp"%>

	<div class="container-fluid">
		<div class="row">
			<%@ include file="/WEB-INF/include-menu.jsp"%>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							<i class="glyphicon glyphicon-th"></i> 数据列表
						</h3>
					</div>
					<div class="panel-body">
						<form action="admin/user.html" class="form-inline" role="form"
							style="float: left;" method="post">
							<div class="form-group has-feedback">
								<div class="input-group">
									<div class="input-group-addon">查询条件</div>
									<input name="keyword" class="form-control has-success"
										type="text" placeholder="请输入查询条件">
								</div>
							</div>
							<button type="submit" class="btn btn-warning">
								<i class="glyphicon glyphicon-search"></i> 查询
							</button>
						</form>
						<button type="button" class="btn btn-danger"
							style="float: right; margin-left: 10px;" id="removeBtn">
							<i class=" glyphicon glyphicon-remove"></i> 删除
						</button>
						<a href="admin/to/add/page.html" class="btn btn-primary"
							style="float: right;"><i class="glyphicon glyphicon-plus"></i>
							新增</a> <br>
						<hr style="clear: both;">
						<div class="table-responsive">
							<table class="table  table-bordered">
								<thead>
									<tr>
										<th width="30">#</th>
										<th width="30"><input type="checkbox" id="itemsBox"></th>
										<th>账号</th>
										<th>名称</th>
										<th>邮箱地址</th>
										<th width="100">操作</th>
									</tr>
								</thead>
								<tbody>
									<c:if test="${empty pageInfo.list}">
										<tr>
											<td colspan="6" align="center">抱歉！没有查询到您要的数据！</td>
										</tr>
									</c:if>
									<c:if test="${!empty pageInfo.list}">
										<c:forEach var="list" items="${pageInfo.list}"
											varStatus="varStatu">
											<tr>
												<td>${varStatu.count}</td>
												<td><input type="checkbox" class="itemBox"
													id="${list.id }"></td>
												<td>${list.loginAcct }</td>
												<td>${list.userName }</td>
												<td>${list.email }</td>
												<td>
													<button type="button" class="btn btn-success btn-xs">
														<i class=" glyphicon glyphicon-check"></i>
													</button> 
													<a href="admin/to/editUser.html?adminId=${list.id }&pageNum=${requestScope.pageInfo.pageNum}&keyword=${param.keyword}"
													class="btn btn-primary btn-xs"> <i
														class=" glyphicon glyphicon-pencil"></i>
												</a>
													<button id="${list.id }" type="button" class="btn btn-danger btn-xs removeBtn01">
														<i class=" glyphicon glyphicon-remove"></i>
													</button>
												</td>
											</tr>
										</c:forEach>
									</c:if>
								</tbody>
								<tfoot>
									<tr>
										<td colspan="6" align="center">
											<div id="Pagination" class="Pagination">
												<!-- 这里显示分页 -->
											</div>
										</td>
									</tr>

								</tfoot>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$("tbody .btn-success").click(function() {
		window.location.href = "assignRole.html";
	});
	$("tbody .btn-primary").click(function() {
		window.location.href = "edit.html";
	});
</script>
</html>
