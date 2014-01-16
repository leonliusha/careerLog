<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true"%>

<%@ include file="meta_taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 <h2>user id is: ${sessionScope.user.userId}</h2>
 <h2>user name is:${userName}</h2>
 <div class="contentSection">
 	<div class="sectionName"><a href="${pageContext.request.contextPath}/friends/${userName}"><spring:message code="common.friends.friends"/>${friendsCount}</a></div>		
</div>
 <form:form action="findPeople" method="POST" >
 	<spring:message code="common.search.people"/><input type="text" name="searchingPeopleName"/><input type="submit" value="<spring:message code="common.button.ok"/>"/>
 </form:form>
</body>
</html>