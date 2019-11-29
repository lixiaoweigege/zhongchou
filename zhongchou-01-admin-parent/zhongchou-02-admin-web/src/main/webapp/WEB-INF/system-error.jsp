<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	
	window.onload = function(){
		
		var btn = document.getElementById("btn");
		
		btn.onclick = function(){
			
			// 相当于浏览器的后退按钮
			window.history.back();
			
		};
		
	};
	
</script>
</head>
<body>

	<h1>看什么看，出错啦！</h1>
	
	<p>${requestScope.exception.message }</p>
	
	<button id="btn">点这里回到上一步</button>

</body>
</html>