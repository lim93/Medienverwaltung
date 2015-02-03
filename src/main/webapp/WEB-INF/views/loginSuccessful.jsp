<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Medienverwaltung</title>
</head>
<body>
	<%
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");

		session.setAttribute("userId", userId);
		session.setAttribute("userName", userName);
		response.sendRedirect("/medienverwaltung/profil");
	%>


</body>
</html>