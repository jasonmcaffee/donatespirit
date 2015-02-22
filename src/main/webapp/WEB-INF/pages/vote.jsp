<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Vote</title>
  <%@ include file="/WEB-INF/pages/shared/head.jsp" %>
</head>
<body>
  <%@ include file="/WEB-INF/pages/shared/topbanner.jsp" %>
  <h1>Vote</h1>

  <c:forEach items="${voteTopics}" var="vt">
    <ul><li><c:out value="${vt.topic}"/></li><li>${vt.yesVotes}</li><li>${vt.noVotes}</li><li>${vt.totalVotes}</li><li>${vt.date}</li><li>${vt.expiresDate}</li></ul>
  </c:forEach>

  <script src="resources/js/modulus.js"></script>
  <script src="resources/js/app.js"></script>
</body>
</html>
