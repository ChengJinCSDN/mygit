<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
		String msg = (String)request.getParameter("msg");
		String times = (String)request.getParameter("times");
		String resultNum = (String)request.getParameter("resultNum");
		if(msg!=null){
			out.println(msg);
		}
		if(times!=null){	
			out.println("<br/>" + times);
		}
		if(resultNum!=null){	
			out.println("<br/>" + resultNum);
		}
	%>
	<br />
	<form action="guess" method="get">
		<input type="text" name="guessNum" /> <input type="submit" />
	</form>
</body>
</html>