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
		<form:form method="POST" modelAttribute="loginCommand" action="login">
						<div id="loginTable">
							<table>
							<tr>
								<td><spring:message code="common.userName" text="User Name"/></td>
								<td><form:input path="userName" class="text textarea invalidate-username"  autocomplete="off" /></td>
								<td><form:errors path="userName" class="errors" /></td>
							</tr>
							<tr>
								<td><spring:message code="common.password" text="Password"/></td>
								<td><form:password path="password" class="text textarea"/></td>
								<td><form:errors path="password" class="errors"/></td>
							</tr>
							</table>
						</div>
						<div id="loginButton">
							<input type="submit" value="<spring:message code="common.button.login" text="Login"/>" name="submit" class="submitbutton"/>
						</div>					
		</form:form>
	</div>
</body>
</html>