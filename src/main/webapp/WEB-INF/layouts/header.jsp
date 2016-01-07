<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<div id="header">
    <div id="title">
        <shiro:user>
            <div class="dropdown pull-right">
                <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1"
                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                    <span class="glyphicon glyphicon-user" aria-hidden="true"></span> <shiro:principal
                        property="name"/>
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                    <shiro:hasRole name="admin">
                        <li><a href="${ctx}/admin/user">Admin Users</a></li>
                        <li class="divider"></li>
                    </shiro:hasRole>
                    <li><a href="${ctx}/api">APIs</a></li>
                    <li><a href="${ctx}/profile">Edit Profile</a></li>
                    <li><a href="${ctx}/logout">Logout</a></li>
                </ul>
            </div>
        </shiro:user>

        <ol class="breadcrumb">
            <li><a href="${ctx}/">待办事项</a></li>
            <li class="active"><sitemesh:write property='title'/></li>

        </ol>

    </div>
</div>