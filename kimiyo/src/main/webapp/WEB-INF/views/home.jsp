<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
			$("#submitBtn").on("click", function(){
				//$("#sampleForm").submit();
			});
		});
	</script>
</head>
<body>
<h1>
	Hello world!_日本語
</h1>

<P>  The time on the server is ${serverTime}. </P>
<p><a href="<c:url value='/sample/dbaccess' />">get db</a></p>

<div>↓↓↓JPAメソッドで取得↓↓↓</div>
<form:form method="post" action="${pageContext.request.contextPath}/sample/setSampleDb">
	<p/>
	<div>DB SET SAMPLE</div>
	ID : <input type="text" id="id" name="id" /><br />
	NAME : <input type="text" id="name" name="name" /><br />
	<button id="submit">submit</button>
</form:form>
<div>↓↓↓ネイティブクエリで取得↓↓↓</div>
<form:form method="post" action="${pageContext.request.contextPath}/sample/getDbSampleOnNativeQuery">
	<p/>
	<div>DB SET SAMPLE</div>
	ID : <input type="text" id="id" name="id" /><br />
	<button id="submit">submit</button>
</form:form>

</body>
</html>
