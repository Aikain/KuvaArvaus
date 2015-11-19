<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>KuvaArvaus - Rekisteröinti</title>
        <script type="text/javascript" charset="UTF-8" src="<c:url value='/resources/public/signUp.js' />"></script>
        <link rel="stylesheet" type="text/css" href="<c:url value='/resources/public/signUp.css' />" />
    </head>
    <body>
        <center>
        <div id="signInCenterizingDiv">
            <h2>Rekisteröi tili</h2>
            <form method="POST" action="users">
                <div id="outerDiv">
                    <div>
                        <input id="nameInput" type="text" name="username" placeholder="Nimi" />
                    </div>
                    <div>
                        <input type="password" name="password" placeholder="Salasana" />
                    </div>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input id="signInButton" type="submit" value="Rekisteröi" />
                </div>
            </form>
        </div>
        <a href='login'>Kirjaudu sisään!</a><br /><br />
        <a href="https://github.com/Aikain/KuvaArvaus">Github</a>
    </body>
</html>
