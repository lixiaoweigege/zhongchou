<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ch-CN">
  <head>
    <%@ include file="/WEB-INF/include-head.jsp"%>
  </head>

  <body>

    <%@ include file="/WEB-INF/include-navbar.jsp"%>
    <div class="container-fluid">
      <div class="row">
       <%@ include file="/WEB-INF/include-menu.jsp"%>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<ol class="breadcrumb">
				  <li><a href="#">首页</a></li>
				  <li><a href="#">数据列表</a></li>
				  <li class="active">新增</li>
				</ol>
			<div class="panel panel-default">
              <div class="panel-heading">表单数据<div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
			  <div class="panel-body">
				<form action="admin/editUser.html" method="post" role="form">
				  <p>${requestScope.exception.message }</p>
							<input type="hidden" name="id" value="${param.adminId }" />
							<input type="hidden" name="pageNum" value="${param.pageNum }"/>
							<input type="hidden" name="keyword" value="${param.keyword }"/>
							<input type="hidden" name="oldLoginAcct" value="${requestScope.admin.loginAcct }"/>
							<div class="form-group">
								<label for="exampleInputPassword1">登录账号</label> <input
									type="text" name="loginAcct" class="form-control" id="exampleInputPassword1"
									value="${requestScope.admin.loginAcct }"
									value="test">
							</div>
							<div class="form-group">
								<label for="exampleInputPassword1">新的登录密码</label> <input
									type="text" name="userPswd" class="form-control"  id="exampleInputPassword1" placeholder="请求输入密码">
							</div>
							<div class="form-group">
								<label for="exampleInputPassword1">确认密码</label> <input
									type="text" name="confirmPswd" class="form-control" id="exampleInputPassword1" placeholder="请求再输入一次">
							</div>
							<div class="form-group">
								<label for="exampleInputPassword1">用户昵称</label> <input
									type="text" name="userName" value="${requestScope.admin.userName }" class="form-control" id="exampleInputPassword1"
									value="测试用户">
							</div>
							<div class="form-group">
								<label for="exampleInputEmail1">邮箱地址</label> <input
									type="email" name="email" value="${requestScope.admin.email }" class="form-control" id="exampleInputEmail1"
									value="xxxx@xxxx.com">
								<p class="help-block label label-warning">请输入合法的邮箱地址, 格式为：
									xxxx@xxxx.com</p>
							</div>
							<button type="submit" class="btn btn-success">
								<i class="glyphicon glyphicon-edit"></i> 修改
							</button>
							<button type="reset" class="btn btn-danger">
								<i class="glyphicon glyphicon-refresh"></i> 重置
							</button>
				</form>
			  </div>
			</div>
        </div>
      </div>
    </div>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
		<div class="modal-content">
		  <div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
			<h4 class="modal-title" id="myModalLabel">帮助</h4>
		  </div>
		  <div class="modal-body">
			<div class="bs-callout bs-callout-info">
				<h4>测试标题1</h4>
				<p>测试内容1，测试内容1，测试内容1，测试内容1，测试内容1，测试内容1</p>
			  </div>
			<div class="bs-callout bs-callout-info">
				<h4>测试标题2</h4>
				<p>测试内容2，测试内容2，测试内容2，测试内容2，测试内容2，测试内容2</p>
			  </div>
		  </div>
		  <!--
		  <div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="button" class="btn btn-primary">Save changes</button>
		  </div>
		  -->
		</div>
	  </div>
	</div>
  </body>
</html>
