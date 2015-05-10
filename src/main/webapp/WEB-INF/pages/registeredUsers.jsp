<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Registered Users</title>
    <%@ include file="/WEB-INF/pages/shared/head.jsp" %>
</head>
<body>
  <%@ include file="/WEB-INF/pages/shared/topbanner.jsp" %>
  <section id="members-section">
    <h3>Registered Users</h3>
    <c:forEach var="user" items="${users}">
      <div>
        <span><c:out value="${user.userName}"/></span>

        <c:forEach var="roleType" items="${user.userRoleTypes}">
          <span><c:out value="${roleType}"></c:out></span>
        </c:forEach>
      </div>

    </c:forEach>
  </section>

</body>
</html>
