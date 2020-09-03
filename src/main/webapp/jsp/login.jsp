<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Title</title>
</head>
    <body>
        <form action="process_controller" method="post">
            <p><strong>Login:</strong>
                <input maxlength="20" size="30" name="login"></p>
            <p><strong>Password:</strong>
                <input type="password" maxlength="20" size="30" name="password"></p>
            <input type="hidden" name="command" value="log_in_user">
            <input type="submit" value="Log In">
        </form>
    </body>
</html>
