<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Vote</title>
  <%@ include file="/WEB-INF/pages/shared/head.jsp" %>
  <link rel="stylesheet" type="text/css" href="resources/css/vote.css">
</head>
<body>
  <%@ include file="/WEB-INF/pages/shared/topbanner.jsp" %>
  <h2>Create a Topic for Others to Vote On</h2>

  <form id="createVoteTopicForm">
    <textarea name="topic" placeholder="Enter a description for a topic you would like others to vote on."></textarea>
    <input type="submit" value="Create a Vote Topic"/>
  </form>

  <h2>Vote on Topics</h2>
  <div class="vote-topics">

    <c:forEach items="${voteTopics}" var="vt">
      <ul class="vote-topic">
        <li class="topic-creator"><c:out value="${vt.creatorUserName}"/></li>
        <li class="topic"><c:out value="${vt.topic}"/></li>
        <li class="vote-count">Votes Yes: ${vt.yesVotes}</li>
        <li class="vote-count">Votes No: ${vt.noVotes}</li>
        <li class="vote-count">Total Votes: ${vt.totalVotes}</li>
        <%--<li>${vt.date}</li>--%>
        <%--<li>${vt.expiresDate}</li>--%>
        <li class="vote">
          <form class="cast-vote-form">
            <button type="submit" class="vote-yes">Yes</button>
            <input type="hidden" name="vote" value="true"/>
            <input type="hidden" name="voteTopicId" value="${vt.id}"/>
          </form>
          <form class="cast-vote-form">
            <button type="submit" class="vote-no">No</button>
            <input type="hidden" name="vote" value="false"/>
            <input type="hidden" name="voteTopicId" value="${vt.id}"/>
          </form>
          <%--<div class="vote-yes">Yes</div>--%>
          <%--<div class="vote-no">No</div>--%>
        </li>
      </ul>
    </c:forEach>

  </div>

  <script>
    var viewModel = ${voteTopicsJson};
    console.log(JSON.stringify(viewModel, null, 2));
  </script>
  <script src="resources/js/modulus.js"></script>
  <script src="resources/js/app.js"></script>
</body>
</html>
