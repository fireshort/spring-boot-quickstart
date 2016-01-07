<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>资料修改</title>
</head>

<body>
	<form id="inputForm" action="${ctx}/profile" method="post" class="form-horizontal">
		<input type="hidden" name="id" value="${user.id}"/>
			<div class="form-group">
				<label for="name" class="col-xs-2">用户名:</label>
				<div class="col-xs-6">
					<input type="text" id="name" name="name" value="${user.name}" class="form-control required"/>
				</div>
			</div>
			<div class="form-group">
				<label for="plainPassword" class="col-xs-2">密码:</label>
				<div class="col-xs-6">
					<input type="password" id="plainPassword" name="plainPassword" class="form-control" placeholder="...Leave it blank if no change"/>
				</div>
			</div>
			<div class="form-group">
				<label for="confirmPassword" class="col-xs-2">确认密码:</label>
				<div class="col-xs-6">
					<input type="password" id="confirmPassword" name="confirmPassword" class="form-control" equalTo="#plainPassword" />
				</div>
			</div>
			<div class="form-actions">
				<input id="submit_btn" class="btn btn-primary" type="submit" value="提交"/>&nbsp;	
				<input id="cancel_btn" class="btn btn-default" type="button" value="返回" onclick="history.back()"/>
			</div>
	</form>
	
	<script>
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#name").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate();
		});
	</script>
</body>
</html>
