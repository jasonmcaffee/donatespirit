<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Vote</title>
  <%@ include file="/WEB-INF/pages/shared/head.jsp" %>
</head>
<body>
  <%@ include file="/WEB-INF/pages/shared/topbanner.jsp" %>
  <h1>Vote</h1>

  <c:forEach var="voteTopic" items="${voteTopics}">
    <ul><li><c:out value="${voteTopic.topic}"/></li><li>${voteTopic.yesVotes}</li><li>${voteTopic.noVotes}</li><li>${voteTopic.totalVotes}</li><li>${voteTopic.date}</li><li>${voteTopic.expiresDate}</li></ul>
  </c:forEach>

  <script src="resources/js/modulus.js"></script>
  <script src="resources/js/app.js"></script>
</body>
</html>
