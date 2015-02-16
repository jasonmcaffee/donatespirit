<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Members</title>
    <%@ include file="/WEB-INF/pages/shared/head.jsp" %>
</head>
<body>

<%@ include file="/WEB-INF/pages/shared/topbanner.jsp" %>

<section id="messages-section">
    <h3>Message Board</h3>

    <h4>Post Message</h4>
    <form id="postMessageForm">
        <textarea name="message" rows="4"></textarea>
        <input type="submit"/>
    </form>

    <div>
        <ul>
            <li>User</li><li>Message</li><li>Date</li>
        </ul>

        <c:forEach var="message" items="${messages}">
            <ul><li><c:out value="${message.user.userName}"/></li><li><c:out value="${message.message}"/></li><li>${message.date}</li></ul>
        </c:forEach>
    </div>
</section>
<section id="members-section">
    <h3>Registered Users</h3>
    <c:forEach var="user" items="${users}">
        <div>
            <span><c:out value="${user.userName}"/></span>
        </div>
    </c:forEach>
</section>


    <script src="resources/js/modulus.js"></script>
    <script src="resources/js/app.js"></script>
</body>
</html>