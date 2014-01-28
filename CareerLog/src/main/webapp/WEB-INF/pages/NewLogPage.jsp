<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="meta_taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="contentSection">
		<form:form method="POST" modelAttribute="Message" action="newlog">
		<tr> 
			<td><form:textarea path="text" rows="100" cols="100"/></td>
			<td><form:errors path="text"/></td>			
		</tr>
		<tr>
			<td><input type="submit" value="<spring:message code="common.button.submit" text="Submit" />" name="submit" class="submitbutton" /></td>
		</tr>
		</form:form>
	</div>
</body>
</html>