<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
    </head>
    <body>
        <h1>Login Page</h1>
        <center>
            <h2>Signup Details</h2>
            <form name="login">
            <br/>Username:<input id="email" type="text" name="username">
            <br/>Password:<input id="password" type="password" name="password">
            <br/><input onclick="submit()" type="submit" value="Submit">
            </form>
        </center>
        <script src="loginScript.js" type="text/javascript"></script>
    </body>
</html>