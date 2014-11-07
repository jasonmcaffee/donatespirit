<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css">
</head>
<body>

<%@ include file="/WEB-INF/pages/shared/topbanner.jsp" %>

<section id="messages-section">
    <h3>Message Board</h3>
    <div>
        <ul>
            <li>Date</li><li>User</li><li>Message</li>
        </ul>

        <c:forEach var="message" items="${messages}">
            <ul><li>${message.date}</li><li>${message.user.userName}</li><li>${message.message}</li></ul>
        </c:forEach>
    </div>

</section>
<section id="members-section">
    <h1>Members</h1>

    <h2>This is the members section of the site, which only signed in users can see. </h2>
    <h2>There will be a message board here soon.</h2>
    <h2>For now, check out the <a href="/strategies">strategies</a> page that Onyx wrote.</h2>

    <br/>
    <h3>Registered Users</h3>
    <c:forEach var="user" items="${users}">
        <div>
            <span>${user.userName}</span>
        </div>
    </c:forEach>
</section>


    <script src="resources/js/modulus.js"></script>
    <script src="resources/js/app.js"></script>
</body>
</html>