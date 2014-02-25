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
	<div class="element_left">
		<spring:message code="common.messages.total"/>${page.totalRecord}<spring:message code="common.messages.items"/>
	</div>
	<div class="element_left">
		${page.totalPage}<spring:message code="common.messages.page"/>
	</div>
		<c:if test="${fn:length(page.results)>0}" >
		<div class="sectionModel1">
			<c:forEach items="${page.results}" var="log">
				<div class="itemModel1">
					<div class="itemTitle">
						<a href="${pageContext.request.contextPath}/message/logid/${log.messageId}">
						 	${log.title}
						</a>
					</div>
					<div class="itemContent">
						<a href="${pageContext.request.contextPath}/message/logid/${log.messageId}">
					 		${log.text}
						</a>					
					</div>
				</div>
			</c:forEach>
		</div>
		</c:if>
	
	<div class="element_left">
		<c:if test="${page.totalPage > 6}">
			<c:forEach var="item" varStatus="status" begin="1" end="6">
				<div class="pageNumberElement"><a href="${pageContext.request.contextPath}/message/logs/${status.index}">${status.index}</a></div>
			</c:forEach>
		</c:if>
		
		<c:if test="${page.totalPage <6}">
			<c:forEach var="item" varStatus="status" begin="1" end="${page.totalPage}">
				<div class="pageNumberElement"><a href="${pageContext.request.contextPath}/message/logs/${status.index}">${status.index}</a></div>
			</c:forEach>
			
		</c:if>
	
	</div>
</body>
</html>