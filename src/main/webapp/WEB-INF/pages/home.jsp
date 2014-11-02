<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css">
</head>
<body>

    <header>

        <div class="header-content">

            <div class="top-nav">
                <img src="resources/img/donate-shield.png"/>
                <h1>Donate Spirit ${user.userName}</h1>
                <div>
                    <ul>
                        <li><a href="#rules-section">Rules</a></li><!--
                        --><li><a href="#strategy-section">Strategy</a></li><!--
                        --><li><a href="#members">Members</a></li><!--
                        --><li><a href="#chat">Chat</a></li>
                    </ul>
                    <hr/>
                </div>
            </div>

            <p>Highly active clan dedicated to war and donating generously.</p>
        </div>
    </header>

    <section id="rules-section">
        <h1>Rules</h1>
        <img src="resources/img/barb-scratching-chin.png"/>
        <ul>
            <li>Use both attacks in war.</li><!--
            --><li>Attack the hardest base you are certain you can 3 star.</li><!--
            --><li>Only use strong troops during war.</li><!--
            --><li>If you need a break from war you can go to Donate Spirit 2.</li><!--
            --><li>Donate 1 troop for every 2 that you receive.</li><!--
            --><li>If you get kicked you must earn your way back by donating in Donate Spirit 2.</li><!--
            --><li></li><!--
            --><li></li>
        </ul>
    </section>

    <section id="strategy-section">
        <h1>Strategy</h1>
        <h2>TH7 Offense</h2>
        <div>

        </div>

        <h2>TH8 Offense</h2>

        <h2>TH9 Offense</h2>

        <h2>Defense</h2>
    </section>

    <section id="members-section">
        <h1>Members</h1>
        <c:forEach var="user" items="${users}">
            <div>
                <span>${user.userName}</span>
            </div>
        </c:forEach>
    </section>

    <section id="create-account-section">
        <h1>Create Account</h1>
        <form id="createAccountForm">
            <input type="text" id="userName" name="userName"/>
            <input type="text" id="password" name="password"/>
            <input type="text" id="email" name="userInfo[email]"/>

            <input type="submit">
        </form>
    </section>

    <%--<script src="resources/js/jquery-2.1.1.js"></script>--%>
    <script src="resources/js/modulus.js"></script>
    <script src="resources/js/app.js"></script>
</body>
</html>