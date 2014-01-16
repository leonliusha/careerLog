<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="meta_taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/common.css'/>" />
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js" ></script>
<title>Friends Page</title>
</head>
<body>
	<div class="sectionModel1">
		<c:if test="${fn:length(friendsList)>0}">
			<c:forEach items="${friendsList}" var="friend">
				<div class="itemModel1">
					<div class="itemInfo">${friend.userName}</div>
				</div>
			</c:forEach>
		</c:if>
	</div>
</body>
</html>