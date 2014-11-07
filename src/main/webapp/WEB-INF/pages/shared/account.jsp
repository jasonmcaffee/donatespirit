<section id="account-section">
    <h1> Account</h1>
    <div>
        <h2>Create Account</h2>
        <form id="createAccountForm">
            <label for="userName">User Name</label>
            <input type="text" id="userName" name="userName" required="true" placeholder="your clan name e.g. -$(jsin)$-"/>  <br/>
            <label for="password">Password</label>
            <input id="password" name="password" type="password" required="true"/>  <br/>
            <label for="email">Email Address</label>
            <input type="email" id="email" name="userInfo[email]" required="true"/>
            <br/>
            <input type="submit">
        </form>
    </div>

    <div>
        <h2>Sign In</h2>
        <form id="signinForm">
            <label for="userName">User Name</label>
            <input type="text" id="userName" name="userName" required="true"/> <br/>
            <label for="password">Password</label>
            <input type="password" id="password" name="password" required="true"/>
            <br/>
            <input type="submit">
        </form>
    </div>

</section>