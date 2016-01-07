<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>用户注册</title>
	
	<script>
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#loginName").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate({
				rules: {
					loginName: {
						remote: "${ctx}/register/checkLoginName"
					}
				},
				messages: {
					loginName: {
						remote: "用户登录名已存在"
					}
				}
			});
		});
	</script>
</head>

<body>
<div class="row">
	<div class="col-xs-offset-3 col-xs-6">
	<form id="inputForm" action="${ctx}/register" method="post" class="form-horizontal">

			<div class="form-group">
				<label for="loginName" class="col-xs-2">登录名:</label>
				<div class="col-xs-6">
					<input type="text" id="loginName" name="loginName" class="form-control required" minlength="3"/>
				</div>
			</div>
			<div class="form-group">
				<label for="name" class="col-xs-2">用户名:</label>
				<div class="col-xs-6">
					<input type="text" id="name" name="name" class="form-control required"/>
				</div>
			</div>
			<div class="form-group">
				<label for="plainPassword" class="col-xs-2">密码:</label>
				<div class="col-xs-6">
					<input type="password" id="plainPassword" name="plainPassword" class="form-control required"/>
				</div>
			</div>
			<div class="form-group">
				<label for="confirmPassword" class="col-xs-2">确认密码:</label>
				<div class="col-xs-6">
					<input type="password" id="confirmPassword" name="confirmPassword" class="form-control required" equalTo="#plainPassword"/>
				</div>
			</div>
			<div class="form-actions">
				<input id="submit_btn" class="btn btn-primary" type="submit" value="提交"/>&nbsp;	
				<input id="cancel_btn" class="btn btn-default" type="button" value="返回" onclick="history.back()"/>
			</div>
	</form>
		</div>
	</div>
</body>
</html>
