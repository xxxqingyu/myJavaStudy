<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="BASE" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客户管理</title>
</head>
<body>
	<h1>客户列表</h1>
	<table>
		<tr>
			<th>Name</th>
			<th>Contact</th>
			<th>Telephone</th>
			<th>Email</th>
			<th>Operate</th>
		</tr>
		<c:forEach var="customer" items="${customerList}">
			<tr>
				<td>${customer.name}</td>
				<td>${customer.contact}</td>
				<td>${customer.telephone}</td>
				<td>${customer.email}</td>
				<td>
					<a href="${BASE}/customer_edit?id=${customer.id}">编辑</a>
					<a href="${BASE}/customer_delete?id=${customer.id}">删除</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>