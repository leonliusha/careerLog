<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="meta_taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/common.css' />" />
</head>
<body>
	<div class="sectionModel1">
		<c:if test="${fn:length(page.results)>0}" >
			<c:forEach items="${page.results}" var="log">
				<div class="itemModel1">
					<div class="itemTitle">
					 ${log.title}
					</div>
					<div class="itemContent">
						<a href="${pageContext.request.contextPath}/message/logid/${log.messageId}">
					 		${log.text}
						</a>					
					</div>
				</div>
			</c:forEach>
		</c:if>
		
	</div>
</body>
</html>