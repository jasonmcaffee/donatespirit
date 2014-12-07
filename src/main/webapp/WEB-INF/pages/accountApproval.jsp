<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Account Approval</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/style.css">
</head>
<body>
  <%@ include file="/WEB-INF/pages/shared/topbanner.jsp" %>
  <h1>Unapproved Users</h1>
  <c:forEach var="user" items="${unapprovedUsers}">
    <form action="/approveUser" method="post">
      <span><c:out value="${user.userName}"/></span><input type="hidden" name="userId" value="${user.id}"/> <input type="submit" value="Approve User"/>
    </form>
  </c:forEach>

  <script src="/resources/js/modulus.js"></script>
  <script src="/resources/js/app.js"></script>
</body>
</html>
