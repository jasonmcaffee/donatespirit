<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Error</title>
    <%@ include file="/WEB-INF/pages/shared/head.jsp" %>
</head>
<body>
<%@ include file="/WEB-INF/pages/shared/topbanner.jsp" %>
<div>
  <h1>Error</h1>
  <p>${errorMessage}</p>
</div>


<script src="resources/js/modulus.js"></script>
<script src="resources/js/app.js"></script>
</body>
</html>