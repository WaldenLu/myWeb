<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%-- <%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%> --%>

<%
	String path = request.getContextPath();
	int port = request.getServerPort();
	String basePath  = null;
	if(port==80){
		basePath = request.getScheme()+"://"+request.getServerName()+path;
	}else{
		basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
	}
	request.setAttribute("basePath", basePath);
%>