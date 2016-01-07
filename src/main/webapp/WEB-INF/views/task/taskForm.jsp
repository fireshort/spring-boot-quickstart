<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>任务管理</title>
</head>

<body>
	<form id="inputForm" action="${ctx}/task/${action}" method="post" class="form-horizontal">
		<input type="hidden" name="id" value="${task.id}"/>

			<div class="form-group">
				<label for="task_title" class="col-xs-2">任务名称:</label>
				<div class="col-xs-6">
					<input type="text" id="task_title" name="title"  value="${task.title}" class="form-control required" minlength="3"/>
				</div>
			</div>	
			<div class="form-group">
				<label for="description" class="col-xs-2">任务描述:</label>
				<div class="col-xs-6">
					<textarea id="description" name="description" class="form-control">${task.description}</textarea>
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
			$("#task_title").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate();
		});
	</script>
</body>
</html>
