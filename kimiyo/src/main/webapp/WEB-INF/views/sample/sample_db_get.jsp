<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
	<title>get DB</title>
	<script src="<c:url value="/resources/js/jquery-2.2.1.js" />" type="text/javascript"></script>
	<script type="text/javascript">
		$(function(){
			// alert('wellcom');
		});
	</script>
</head>
<body>
<h1>
	Hello get DB!
</h1>
<div>
	<div>sample_data</div>
	<c:forEach items="${resultList}" var="result">
		<div>
			ID : <c:out value="${result.id}"></c:out> <br/>
			NAME: <c:out value="${result.name}"></c:out>
		</div>
	</c:forEach>
</div>
<div>
	<div>sample2_data</div>
	<c:forEach items="${resultList}" var="result">
		<div>
			ID : <c:out value="${result.id}"></c:out> <br/>
			NAME: <c:out value="${result.name}"></c:out>
		</div>
	</c:forEach>
</div>
</body>
</html>
