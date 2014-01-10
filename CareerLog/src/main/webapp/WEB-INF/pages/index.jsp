<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="meta_taglib.jsp" %>
<html>

<head>
<title>Welcome to CareerLog </title>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/common.css' />" />
</head>

<body>
		<div class="topBar">
			
		</div>
		<div class="header">
			<div class="contentSection">
				<div id="logoSection" class="simple-left">
					<a class="simple-left" href="http://localhost:8080/CareerLog/index">
						<i class="careerLog-logo"></i>
					</a>
				</div>
				<div id="loginSection" class="simple-right">
					<form:form method="POST" modelAttribute="loginCommand" action="login">
						<div id="loginTable">
							<table>
							<tr>
								<td><spring:message code="common.userName"/></td>
								<td><form:input path="userName" class="text textarea invalidate-username"  autocomplete="off" /></td>
								<td><form:errors path="userName" class="errors" /></td>
							</tr>
							<tr>
								<td><spring:message code="common.password"/></td>
								<td><form:password path="password" class="text textarea"/></td>
								<td><form:errors path="password" class="errors"/></td>
							</tr>
							</table>
						</div>
						<div id="loginButton">
							<input type="submit" value="<spring:message code="common.button.login"/>" name="submit" class="submitbutton"/>
						</div>					
				</form:form>
				</div>
			</div>
		</div>
		<div id="mainContent" class="contentSection">
			<div id="introduction" class="simple-left">
				<h2>Career Log is where you can records your career events, communicating with your colleagues, </h2>
			</div>
			<div id="indexRegistration" class="simple-right">
				<form:form method="POST" modelAttribute="User" action="signIn">
					<table>
						<tr>
							<td><spring:message code="common.signin.userName"/></td>
							<td><form:input path="userName" class="" /></td>
							<td><form:errors path="userName" class="errors"/></td>
						</tr>
						<tr>
							<td><spring:message code="common.signin.email"/></td>
							<td><form:input path="email" class=""/></td>
							<td><form:errors path="email" class="errors"/></td>
						</tr>
						<tr>
							<td><spring:message code="common.signin.re-email"/></td>
							<td><input type="text" /></td>
							<td></td>
						</tr>
						<tr>
							<td><spring:message code="common.signin.password"/></td>
							<td><form:password path="password" class=""/></td>
							<td><form:errors path="password" class="errors"/></td>
						</tr>
						<tr>
							<td><spring:message code="common.signin.lastName"/></td>
							<td><form:input path="lastName" class=""/></td>
 							<td><form:errors path="lastName" class="errors"/></td>
						</tr>
						<tr>
							<td><spring:message code="common.signin.firstName"/></td>
							<td><form:input path="firstName" class=""/></td>
 							<td><form:errors path="firstName" class="errors"/></td>
						</tr>
					</table>
					<div id="signinButton">
						<input type="submit" value="<spring:message code="common.sign-in"/>" name="submit" class="submitbutton" />
					</div>
				</form:form>
			</div>
		</div>
		<div class="footer">
		
		</div>
</body>
</html>