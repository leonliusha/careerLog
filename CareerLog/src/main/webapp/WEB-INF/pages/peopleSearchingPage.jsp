<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="meta_taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/common.css'/>" />
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js" ></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('.small-add-button').click(function(){addUserToFriends($(this).attr("id"));});
	});
	
	
</script>
<title>Insert title here</title>
</head>
<body>
	<div class="sectionModel1">
 	<c:if test="${fn:length(searchedUserList)>0 }">
		<c:forEach items="${searchedUserList}" var="searchedUser">
		<div class="itemModel1">			
			<div class="itemInfo">${searchedUser.userName}</div>								
			<div class="addItem">
				<input type="button" id="userId_${searchedUser.userId}" class="small-add-button" value="<spring:message code="common.button.add"/>"/>
			</div>
			<div id="message_${searchedUser.userId}"> </div>
		</div>						
		</c:forEach>
	</c:if>
	</div>
	
<script type="text/javascript">
function addUserToFriends(btnId){
	//var btnId = $(this).attr("id");
	var id = null;
	if(id = btnId.match(/[0-9]/)){
		alert('Friend id is:'+id);
		$.ajax(
			{
				type:"POST",
				url: "findPeople/addUserToFriends",
				data: "friendId="+id,
				success: function(response){
					$('#'+btnId).val('<spring:message code="common.button.added"/>');
					$('#message_'+id).html(response);
				},
				error:function(e){
				 alert('ERROR: '+e);	
				}
			}		
		);
	}
}

</script>
</body>
</html>