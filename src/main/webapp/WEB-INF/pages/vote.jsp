<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Vote</title>
  <%@ include file="/WEB-INF/pages/shared/head.jsp" %>
  <link rel="stylesheet" type="text/css" href="resources/css/vote.css">
</head>
<body>
  <%@ include file="/WEB-INF/pages/shared/topbanner.jsp" %>
  <h1>Vote</h1>

  <div class="vote-topics">

    <c:forEach items="${voteTopics}" var="vt">
      <ul class="vote-topic">
        <li class="topic"><c:out value="${vt.topic}"/></li>
        <li class="vote-count">Votes Yes: ${vt.yesVotes}</li>
        <li class="vote-count">Votes No: ${vt.noVotes}</li>
        <li class="vote-count">Total Votes: ${vt.totalVotes}</li>
        <%--<li>${vt.date}</li>--%>
        <%--<li>${vt.expiresDate}</li>--%>
        <li class="vote">
          <div class="vote-yes">Yes</div>
          <div class="vote-no">No</div>
        </li>
      </ul>
    </c:forEach>

  </div>

  <script src="resources/js/modulus.js"></script>
  <script src="resources/js/app.js"></script>
</body>
</html>
