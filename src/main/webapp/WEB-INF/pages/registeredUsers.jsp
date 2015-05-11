<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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

        <c:if test="${currentUser.isAllowedToAssignUserRoles() == true}">

          <form action="" method="POST">

            <c:choose>
              <c:when test="${user.elder}">
                <input type="checkbox" name="isElder" id="isElder" checked/><label for="isElder">Elder</label>
              </c:when>
              <c:otherwise>
                <input type="checkbox" name="isElder" id="isElder"/><label for="isElder">Elder</label>
              </c:otherwise>
            </c:choose>

            <c:choose>
              <c:when test="${user.coleader}">
                <input type="checkbox" name="isColeader" id="isColeader" checked/><label for="isColeader">Coleader</label>
              </c:when>
              <c:otherwise>
                <input type="checkbox" name="isColeader" id="isColeader"/><label for="isColeader">Coleader</label>
              </c:otherwise>
            </c:choose>

            <input type="hidden" name="userId" value="${user.id}">
          </form>

        </c:if>

      </div>

    </c:forEach>
  </section>

</body>
</html>

<%--<c:forEach var="roleType" items="${user.userRoleTypes}">--%>
  <%--<span><c:out value="${roleType}"></c:out></span>--%>
<%--</c:forEach>--%>


<%--<c:forEach var="role" items="${roles}">--%>
  <%--<c:choose>--%>
    <%--<c:when test="${fn:contains(currentUser.userRoleTypes, role)}">--%>
      <%--<span class="user-role"> XXXX<c:out value="${role}"></c:out> </span>--%>
    <%--</c:when>--%>
    <%--<c:otherwise>--%>
      <%--<span class="user-role"> no </span>--%>
    <%--</c:otherwise>--%>
  <%--</c:choose>--%>
<%--</c:forEach>--%>
