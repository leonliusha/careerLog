<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@	include file="meta_taglib.jsp"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/common.css'/>"/>
<title>Insert title here</title>
</head>
<body>		
	<c:if test="${fn:length(searchingResult)>0}">
		<div class="sectionModel1">
			<c:forEach items="${searchingResult}" var="message">
				<div class="itemModel1">
					<div class="itemTitle">
						<a href="">${message.getTitle}</a>
					</div>
					<div class="itemContent">
						<a href="">${message.getText}</a>
					</div>
				</div>				
			</c:forEach>
		</div>
	</c:if>
</body>
</html>