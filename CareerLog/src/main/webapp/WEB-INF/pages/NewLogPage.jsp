<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="meta_taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/ckeditor/ckeditor.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div class="contentSection">
		<form:form method="POST" modelAttribute="Message" action="newlog">
		<tr>
			<td><spring:message code="common.messages.title"/></td>
			<td><form:input path="title" class="text textarea" autocomplete="off"/>
		</tr>
		<tr> 
			<td><form:textarea path="text" rows="30" cols="80" name="logEditor"></form:textarea>
				<!--  <script type="text/javascript">CKEDITOR.replace('logEditor');</script>-->
				<ckeditor:replace replace="text" basePath="../ckeditor/"/>
			</td>
			<td><form:errors path="text"/></td>			
		</tr>
		<tr>
			<td><input type="submit" value="<spring:message code="common.button.submit" text="Submit" />" name="submit" class="submitbutton" /></td>
		</tr>
		</form:form>
	</div>
</body>
</html>