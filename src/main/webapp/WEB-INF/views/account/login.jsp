<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>用户登录</title>
</head>

<body>
    <div class="row">
        <div class="col-xs-offset-3 col-xs-6">
            <form id="loginForm" action="${ctx}/login" method="post" class="form-horizontal">
                <%
                    String error = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
                    if (error != null) {
                %>
                <div class="alert alert-danger alert-dismissable ">
                    <button class="close" data-dismiss="alert">&times;</button>
                    登录失败，请重试。
                </div>
                <%
                    }
                %>
                <div class="form-group">
                    <label for="username" class="col-xs-2">用户名:</label>
                    <div class="col-xs-6 ">
                        <input type="text" id="username" name="username" value="${username}"
                               class="form-control required" placeholder="请输入用户名"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="password" class="col-xs-2">密码:</label>
                    <div class="col-xs-6">
                        <input type="password" id="password" name="password" class="form-control required" placeholder="请输入密码"/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-xs-offset-2 col-xs-8">
                        <label class="checkbox" for="rememberMe"><input type="checkbox" id="rememberMe"
                                                                        name="rememberMe"/>
                            记住我</label>
                        <input id="submit_btn" class="btn btn-primary" type="submit" value="登录"/>
                        <a class="btn btn-default" href="${ctx}/register">注册</a>
                        <span class="help-block">(管理员: <b>admin/admin</b>, 普通用户: <b>user/user</b>)</span>
                    </div>
                </div>
            </form>
        </div>
    </div>

<script>
    $(document).ready(function () {
        $("#loginForm").validate();
    });
</script>
</body>
</html>
