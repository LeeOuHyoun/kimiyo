<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
	<title>Home</title>
	<script src="<c:url value="/resources/js/common/jquery-2.2.1.js" />" type="text/javascript"></script>
	<script type="text/javascript">
		$(function(){
			 //alert('wellcom');
		});
	</script>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<p><a href="<c:url value='/sample/dbaccess' />">get db</a></p>
</body>
</html>
